package LifeGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ViewPanel extends JPanel
implements MouseListener, MouseMotionListener, Runnable, Define{
	/**
	 * 
	 * Cell の描画を行い、各種イベントを拾うクラス
	 * 
	 * b			LifeGameのCellの情報を格納する
	 * viewX, viewY	平行移動量
	 * speed		LifeGame の一世代あたりのDelay
	 * buffer		毎F変更を必要としない描画
	 */
	private static final long serialVersionUID = 1L;
	private Board b;
	private int viewX,viewY;
	private int speed;
	private Image buffer;

	/*
	 * mouseRealPosition	マウスのセル座標
	 * mousePosition		マウスのセル座標 同じ場所を連続で変更させない役割を持つ 
	 * castPosition			Caputure時の終点セル座標
	 * mouseLocation		mouseの座標
	 * drown				bufferの書き直しが必要か
	 * pattern				登録されたパターン
	 */
	private Point mouseRealPosition, mousePosition, captPosition;
	private Point mouseLocation;
	private boolean drown;
	private static int[][] pattern;

	/*
	 * シングルトンを実装する
	 */
	private static final ViewPanel instance=new ViewPanel();
	public static ViewPanel getInstance(){
		return instance;
	}

	private ViewPanel() {
		viewX=viewY=0;
		speed=STD_SPEED;
		drown=true;
		mousePosition=new Point();
		mouseRealPosition=new Point();
		mouseLocation=new Point();
		captPosition=null;
		pattern=null;

		addMouseListener(this);
		addMouseMotionListener(this);
		new Thread(this).start();
	}

	/*
	 * Board のセッター
	 */
	public static void setBoard(Board b){
		getInstance().b=b;
	}

	/*
	 * マウスのセル座標のCell に点を打つ
	 */
	private void update(){
		if (pattern != null) return;
		if (!Mode.getSelected().equals(Mode.Draw)) return;
		int X=viewX/LifeGame.chipSize, Y=viewY/LifeGame.chipSize;
		b.turn(mousePosition.x+X, mousePosition.y+Y);
		rerepaint();
	}

	/*
	 * マウスのセル座標からPattern を適応する
	 */
	private void addPattern(){
		if (!Mode.getSelected().equals(Mode.Draw)) return;
		int X=viewX/LifeGame.chipSize, Y=viewY/LifeGame.chipSize;
		b.addPattern(mousePosition.x+X, mousePosition.y+Y, pattern);
		rerepaint();
	}

	/*
	 * 平行移動する
	 */
	private void move(MouseEvent e){
		viewX+=mouseLocation.x-e.getX();
		viewY+=mouseLocation.y-e.getY();
		mouseLocation.setLocation(e.getPoint());
		mouseRealPosition.setLocation(e.getPoint());
		rerepaint();
	}

	/*
	 * mouseReleased 時に呼ぶ
	 * mouseRealPosition captPosition 間のPattern を保存する
	 */
	private void capture() {
		int xs=(int)(Math.ceil((captPosition.x-mouseRealPosition.x+1.)/Board.BIT));
		int ys=(int)(Math.ceil((captPosition.y-mouseRealPosition.y+1.)/Board.BIT));
		int X=mouseRealPosition.x+viewX/LifeGame.chipSize, Y=mouseRealPosition.y+viewY/LifeGame.chipSize;
		/*
		 * 負の方向に広げたなら行わない
		 */
		if(xs<0||ys<0)return;

		/*
		 * isUsable		範囲内に生のセルが存在するか
		 * set			Pattern を代入する !=0 の時有効
		 */
		int isUsable=0;
		int[][] set=new int[xs][ys];
		ByteArrayOutputStream oss=new ByteArrayOutputStream();
		PrintStream pss=new PrintStream(oss);
		for(int x=0;x<xs;x++)
			for(int y=0;y<ys;y++){
				int n=0;
				for(int z=0;z<Board.BIT;z++)
					for(int w=0;w<Board.BIT;w++){
						n <<= 1;
						n |= b.isOn(X+x*Board.BIT+w, Y+y*Board.BIT+z) ? 1 : 0;
					}
				isUsable|=n;
				set[x][y]=n;
				if(n!=0)
					pss.printf("pattern[%d][%d]=0x%s;\n",x,y,Integer.toHexString(n));
			}
		pss.close();
		if(isUsable!=0){
			ByteArrayOutputStream os=new ByteArrayOutputStream();
			PrintStream ps=new PrintStream(os);
			ps.printf("Give the Pattern Name.\nPosition : %d, %d\nthe Pattern's Size : %d * %d\n", X, Y, xs, ys);
			ps.close();
			ImageIcon icon=Icons.createIcon(set);

			/*
			 * 有効な名前を入力した時のみ保存
			 */
			String name=null;
			do{
				if(name!=null)
					JOptionPane.showMessageDialog(this, "The Name `"+name+"` Already Exits.\nPlease Set the Other Name", "Duplicate Names", JOptionPane.ERROR_MESSAGE, icon);
				Object o=JOptionPane.showInputDialog(this, os.toString(), "Give the Pattern Name", JOptionPane.QUESTION_MESSAGE, icon, null, "");
				if(o==null)return;
				name=o.toString();
				if(name == null||name.length()==0)
					return;

			}while(!EditPanel.getInstance().addPattern(name, set, true));

			/*
			 * 標準出力の結果をPattern に貼り付ければ、Pattern を追加できる。
			 */
			System.out.printf("%s(\"%s, %d, %d\"){\n\n@Override\nvoid setPattern(int[][] pattern) {\n%s},", name, name, xs, ys, oss.toString());
		}
	}

	/*
	 * buffer も含めて書き直す
	 */
	public void rerepaint(){
		drown=true;
		repaint();
	}

	/*
	 * Cell を描画する
	 * 
	 * X, Y		変移セル座標
	 * Z		枠線を描くか
	 */
	public void paint(Graphics g){
		super.paint(g);
		if (buffer == null) buffer = createImage(W, H);
		Graphics bg=buffer.getGraphics();
		int X=viewX/LifeGame.chipSize, Y=viewY/LifeGame.chipSize;
		int Z=LifeGame.chipSize<5?0:1;

		/*
		 * Bufferへの書き込み
		 */
		if(drown){
			drown^=true;
			bg.setColor(Color.DARK_GRAY);
			bg.fillRect(0, 0, getWidth(), getHeight());
			bg.setColor(Color.BLACK);
			bg.fillRect(-X*LifeGame.chipSize, -Y*LifeGame.chipSize, b.getW()*LifeGame.chipSize, b.getH()*LifeGame.chipSize);
			bg.setColor(Color.GREEN);
			for(int y=0;y+Y<b.getH() && y * LifeGame.chipSize < H; y++){
				if(y+Y<0||b.getH()<y+Y) continue;
				for(int x=0;x+X<b.getW() && x * LifeGame.chipSize < W; x++){
					if(x+X<0||b.getW()<x+X) continue;
					if (b.isOn(x+X, y+Y))
						bg.fillRect(x * LifeGame.chipSize, y * LifeGame.chipSize, LifeGame.chipSize - Z, LifeGame.chipSize - Z);
				}
			}
		}

		/*
		 * 背景とセルの描画
		 */
		g.setColor(Color.BLACK);
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(buffer, 0, 0, null);

		/*
		 * マウス座標や登録されているPattern を表示する
		 */
		switch(Mode.getSelected()){
		case Draw:
			if(pattern == null){
				g.setColor(Color.YELLOW);
				g.drawRect(mouseRealPosition.x * LifeGame.chipSize, mouseRealPosition.y * LifeGame.chipSize, LifeGame.chipSize - 1, LifeGame.chipSize - 1);
			}else{
				g.setColor(Color.BLUE);
				for(int x = 0; x < Board.BIT * pattern.length; x++)
					for(int y = 0; y < Board.BIT * pattern[x / Board.BIT].length; y++)
						if(Board.isOn(pattern, x, y))
							g.drawRect((mouseRealPosition.x + x) * LifeGame.chipSize, (mouseRealPosition.y + y) * LifeGame.chipSize, LifeGame.chipSize - 1, LifeGame.chipSize - 1);
			}
			break;
		case Capture:
			int xs=1, ys=1;
			if(captPosition != null){
				xs=(int)(Math.ceil((captPosition.x-mouseRealPosition.x+1.)/Board.BIT)*Board.BIT);
				ys=(int)(Math.ceil((captPosition.y-mouseRealPosition.y+1.)/Board.BIT)*Board.BIT);
				if(xs < 0) xs = 0;
				if(ys < 0) ys = 0;
			}
			g.setColor(Color.RED);
			g.drawRect(mouseRealPosition.x * LifeGame.chipSize, mouseRealPosition.y * LifeGame.chipSize, xs*LifeGame.chipSize - 1, ys*LifeGame.chipSize - 1);
			break;
		default:
			break;
		}
	}

	/*
	 * 変移を考慮したうえで与えられた点をconstrain する
	 */
	private void constrainPoint(Point p){
		int X=viewX/LifeGame.chipSize, Y=viewY/LifeGame.chipSize;
		p.x=constrain(p.x, 0-X, b.getW()-1-X);
		p.y=constrain(p.y, 0-Y, b.getH()-1-Y);
	}

	/*
	 * 整数の範囲を限定する
	 */
	public static int constrain(int trg, int min, int max){
		int m=trg<min?min:trg;
		return m>max?max:m;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseLocation.setLocation(e.getPoint());
		if((e.getModifiers()&MouseEvent.BUTTON1_MASK)!=0){
			mousePosition.setLocation(e.getPoint());
			mousePosition.x/=LifeGame.chipSize;
			mousePosition.y/=LifeGame.chipSize;
			constrainPoint(mousePosition);
			switch(Mode.getSelected()){
			case Draw:
				update();
				if(pattern != null)
					addPattern();
				break;
			case Capture:
				captPosition=new Point(e.getPoint());
				captPosition.x/=LifeGame.chipSize;
				captPosition.y/=LifeGame.chipSize;
				constrainPoint(captPosition);
				break;
			default:
				break;

			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if((e.getModifiers()&MouseEvent.BUTTON1_MASK)!=0 && Mode.getSelected().equals(Mode.Capture)){
			capture();

			mouseRealPosition.setLocation(e.getPoint());
			mouseRealPosition.x/=LifeGame.chipSize;
			mouseRealPosition.y/=LifeGame.chipSize;
			constrainPoint(mouseRealPosition);
			captPosition=null;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point cmp=new Point(e.getPoint());
		cmp.x/=LifeGame.chipSize;
		cmp.y/=LifeGame.chipSize;
		constrainPoint(cmp);
		if((e.getModifiers()&MouseEvent.BUTTON1_MASK)!=0)
			switch(Mode.getSelected()){
			case Draw:
				if(!cmp.equals(mousePosition)){
					mousePosition.setLocation(cmp);
					mouseRealPosition.setLocation(cmp);
					update();
				}
				break;
			case Move:
				mouseRealPosition.setLocation(cmp);
				move(e);
				break;
			case Capture:
				if(!cmp.equals(captPosition)){
					captPosition.setLocation(cmp);
					update();
				}
				break;
			default:
				break;
			}
		else if((e.getModifiers()&MouseEvent.BUTTON3_MASK)!=0)
			move(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseRealPosition.setLocation(e.getPoint());
		mouseRealPosition.x/=LifeGame.chipSize;
		mouseRealPosition.y/=LifeGame.chipSize;
		constrainPoint(mouseRealPosition);
	}

	@Override
	public void run() {
		int cnt=0;
		while(true){
			if(EditPanel.moving&&++cnt>=speed){
				b.step();
				cnt=0;
				drown=true;
			}
			repaint();

			try {
				Thread.sleep(10l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Pattern のセッター
	 */
	public static void setPattern(int[][] p) {
		pattern=p;
	}

	/*
	 * speed のセッター
	 */
	public static void setSpeed(int speed){
		getInstance().speed=speed;
	}

	/*
	 * L 方向に拡張するときに注視点を変更しないようにする
	 */
	public static void expandX(){
		getInstance().viewX+=LifeGame.chipSize*Board.BIT;
	}

	/*
	 * U 方向に拡張するときに注視点を変更しないようにする
	 */
	public static void expandY(){
		getInstance().viewY+=LifeGame.chipSize*Board.BIT;
	}

	public static void changeMag(int pchipSize, int chipSize) {
		//TODO 倍率が変わった時にも指すセルが変更されないように平行移動する
	}

}
