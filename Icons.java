package LifeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public enum Icons implements Define{
	/*
	 * Window Icon やButton Icon の列挙体
	 * 
	 * Icon		Window Icon
	 * 以下Button Icon
	 * Start	再生する
	 * Pause 	停止する
	 * Step		一世代進める
	 * Clear	Cell を全て殺す
	 * Draw		Board の書き換え
	 * Move		平行移動
	 * Capture	Pattern の保存
	 */
	Icon(ICON_S){

		@Override
		void drawIcon(Graphics g) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, ICON_S, ICON_S);
			g.setColor(Color.GREEN);

			int[][] glider=Pattern.Glider.getPattern();
			for(int x=0;x<glider.length*Board.BIT;x++)
				for(int y=0;y<glider[x/Board.BIT].length*Board.BIT;y++)
					if(Board.isOn(glider, x, y))
						g.fillRect(x*ICON_S/4+ICON_S/8, y*ICON_S/4+ICON_S/8, ICON_S/4-1, ICON_S/4-1);
			g.dispose();
		}

	},
	Start(ICON_S/2){

		@Override
		void drawIcon(Graphics g) {
			g.setColor(Color.getHSBColor(0, .8f, 1));

			int[] xPoints={ICON_S/2/3, ICON_S/2/3, ICON_S/3};
			int[] yPoints={ICON_S/2/3, ICON_S/3, ICON_S/2/2};
			g.fillPolygon(xPoints, yPoints, xPoints.length);
			g.dispose();
		}

	},
	Pause(ICON_S/2){

		@Override
		void drawIcon(Graphics g) {
			g.setColor(Color.getHSBColor(.5f, .5f, .5f));

			g.fillRect(ICON_S/6+ICON_S/30, ICON_S/6, ICON_S/20, ICON_S/5);
			g.fillRect(ICON_S/3-ICON_S/30, ICON_S/6, ICON_S/20, ICON_S/5);
			g.dispose();
		}

	},
	Step(ICON_S/2){

		@Override
		void drawIcon(Graphics g) {
			g.setColor(Color.getHSBColor(.7f, .5f, 1));

			int[] xPoints={ICON_S/2/3, ICON_S/2/3, ICON_S/3};
			int[] yPoints={ICON_S/2/3, ICON_S/3, ICON_S/2/2};
			g.fillPolygon(xPoints, yPoints, xPoints.length);
			g.fillRect(ICON_S/3, ICON_S/2/3, ICON_S/20, ICON_S/5);
			g.dispose();
		}

	},
	Clear(ICON_S/2){

		@Override
		void drawIcon(Graphics g) {
			Color c=Color.getHSBColor(.8f, .4f, .7f);
			Graphics2D g2=(Graphics2D)g;

			g2.translate(ICON_S/4, ICON_S/4);
			g2.rotate(-Math.PI/4);
			g2.setColor(Color.WHITE);
			g2.fillRect(-ICON_S/14, -ICON_S/6, ICON_S/7, ICON_S/5);
			g2.setColor(c);
			g2.drawRect(-ICON_S/14-1, -ICON_S/6-1, ICON_S/7+1, ICON_S/5+1);
			g2.fillRect(-ICON_S/8, -ICON_S/12, ICON_S*2/8, ICON_S*2/9);
			g2.dispose();
		}

	},
	Draw(ICON_S/2){

		@Override
		void drawIcon(Graphics g) {
			Color c=Color.getHSBColor(.25f, .9f, .9f);
			Graphics2D g2=(Graphics2D)g;

			g2.translate(ICON_S/4, ICON_S/4);
			g2.rotate(-Math.PI/4);
			int[] xPoints={-ICON_S/12, ICON_S/12, 0};
			int[] yPoints={-ICON_S/10, -ICON_S/10, -ICON_S*2/9};
			g2.setColor(Color.WHITE);
			g.fillPolygon(xPoints, yPoints, xPoints.length);
			g2.setColor(c);
			g2.drawPolygon(xPoints, yPoints, xPoints.length);
			g2.fillRect(-ICON_S/10, -ICON_S/12, ICON_S*2/10, ICON_S*2/8);
			g2.setColor(Color.BLACK);
			g2.fillOval(xPoints[2]-2, yPoints[2]-2, 4, 4);
			g2.dispose();
		}

	},
	Move(ICON_S/2){

		@Override
		void drawIcon(Graphics g) {
			Graphics2D g2=(Graphics2D)g;
			g2.setColor(Color.getHSBColor(0, 0, .15f));

			g2.translate(ICON_S/4, ICON_S/4);
			g2.fillRect(-ICON_S/40, -ICON_S/6, ICON_S/20, ICON_S/3);
			g2.fillRect(-ICON_S/6, -ICON_S/40, ICON_S/3, ICON_S/20);
			int[] xPoints={-ICON_S/10, ICON_S/10, 0};
			int[] yPoints={-ICON_S/10, -ICON_S/10, -ICON_S*2/9};
			for(int i=0;i<4;i++){
				g2.rotate(Math.PI/2);
				g.fillPolygon(xPoints, yPoints, xPoints.length);
			}
			g2.dispose();
		}

	},
	Capture(ICON_S/2){

		@Override
		void drawIcon(Graphics g) {
			g.translate(ICON_S/4, ICON_S/4);

			g.setColor(Color.getHSBColor(.17f, .4f, 1));
			g.fillRect(-ICON_S/6, -ICON_S/5, ICON_S/3, ICON_S*2/5);

			g.setColor(Color.getHSBColor(.13f, .8f, 1));
			g.fillOval(-ICON_S/20, -ICON_S/5, ICON_S/4, ICON_S/4);
			
			g.translate(-ICON_S/20+ICON_S/8, -ICON_S/5+ICON_S/8);
			g.setColor(Color.getHSBColor(0, 0, .15f));
			g.fillRect(-ICON_S/6/2, -ICON_S/30/2, ICON_S/5, ICON_S/15);
			g.fillRect(-ICON_S/30/2, -ICON_S/6/2, ICON_S/15, ICON_S/5);
			g.dispose();
		}

	};

	/*
	 * img		アイコンのイメージ
	 * ii		アイコンのイメージアイコン
	 * d		アイコンのサイズ
	 */
	private BufferedImage img;
	private ImageIcon ii;
	private Dimension d;
	
	/*
	 * アイコンの描画を実装する
	 */
	abstract void drawIcon(Graphics g);

	/*
	 * s		アイコンのサイズ
	 */
	private Icons(int s){
		img=new BufferedImage(s, s, BufferedImage.TYPE_INT_ARGB);
		drawIcon(img.getGraphics());
		ii=new ImageIcon(img);
		d=new Dimension(img.getWidth(), img.getHeight());
	}

	/*
	 * サイズのゲッター
	 */
	public Dimension getSize(){
		return d;
	}
	/*
	 * アイコンのゲッター
	 */
	public Image getIcon(){
		return img;
	}
	/*
	 * イメージアイコンのゲッター
	 */
	public ImageIcon getImageIcon(){
		return ii;
	}
	
	/*
	 * pattern を与えるとそれを描画したイメージアイコンを返す
	 */
	public static ImageIcon createIcon(int[][] set) {
		int BitSize=(set.length>set[0].length?set.length:set[0].length);
		int IconW=ICON_S;
		int IconH=ICON_S;
		/*
		 * 余りにも1Pixel が小さいようならアイコンを大きくする
		 */
		if(ICON_S/BitSize/4<4){
			IconW=4*4*set.length;
			IconH=4*4*set[0].length;
			BitSize=4*4;
		}else{
			BitSize=ICON_S/BitSize;
		}
		Image img=new BufferedImage(IconW, IconH, BufferedImage.TYPE_INT_ARGB);
		Graphics g=img.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, IconW, IconH);
		g.setColor(Color.GREEN);

		for(int x=0;x<set.length*Board.BIT;x++)
			for(int y=0;y<set[x/Board.BIT].length*Board.BIT;y++)
				if(Board.isOn(set, x, y))
					g.fillRect(x*BitSize/4, y*BitSize/4, BitSize/4-1, BitSize/4-1);
		g.dispose();

		return new ImageIcon(img);
	}
}
