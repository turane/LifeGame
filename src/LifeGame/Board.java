package LifeGame;

/*
 * 用語としてCell, Chip, Board を用いる
 * Cell が 4 * 4 集まってChip に
 * Chip が w * h 集まってBoard になる
 * 
 * bit board の考え方を用いて,4 * 4 の16BIT を
 * ライフゲームのルールを適応して同時に計算する
 * Cell の情報を1 bit 毎に生死を管理する方法である
 * 
 */
public class Board {

	/*
	 * BIT 		Chip 中のCell の一辺当りの数
	 * w, h		Board 中のChip の一辺あたりの数
	 * boards	Chip の情報
	 */
	public static final int BIT = 4;
	public int w, h;
	public int[][] boards;

	/*
	 * w * h の広さのBoard を生成する
	 * +2 とってあるのは周りの番人を生成するため
	 */
	public Board(int w, int h){
		this.w = w;
		this.h = h;
		boards = new int[w + 2][h + 2];
		clear();
	}

	/*
	 * 全てのChip をリセットする
	 */
	public void clear(){
		for(int y=1; y < h + 1; y++)
			for(int x=1; x < w + 1; x++)
				boards[x][y]=0;
	}

	/*
	 * 一世代先に進める
	 * 一番端に生のChip が存在するならその方向に世界を広げる
	 */
	public void step(){
		/*
		 * 左右上下それぞれをexpand する必要があるかチェックする
		 * Chip 中に一つでも生のCell が存在すれば必要と判断する
		 */
		int expL=0, expU=0, expR=0, expD=0;
		for(int y=1; y < h + 1; y++){
			expL|=boards[1][y];
			expR|=boards[w][y];
		}
		for(int x=1; x < w + 1; x++){
			expU|=boards[x][1];
			expD|=boards[x][h];
		}

		int xs=expL!=0?1:0, ys=expU!=0?1:0;
		int xe=expR!=0?1:0, ye=expD!=0?1:0;
		int[][] boards2=new int[w+xs+xe+2][h+ys+ye+2];
		if(xs==1)ViewPanel.expandX();
		if(ys==1)ViewPanel.expandY();

		/*
		 * Chip の更新
		 */
		for(int y=1; y < h + 1; y++)
			for(int x=1; x < w + 1; x++)
				boards2[x+xs][y+ys] = step(boards[x][y], boards[x-1][y-1], boards[x][y-1], boards[x+1][y-1], boards[x-1][y], boards[x+1][y], boards[x-1][y+1], boards[x][y+1], boards[x+1][y+1]);

		w+=xs+xe;
		h+=ys+ye;

		boards=boards2;
	}

	/*
	 * 隣接8 マスの情報を与え、board を一世代先に進める
	 */
	private int step(int board, int board_a, int board_b, int board_c, int board_d, int board_e, int board_f, int board_g, int board_h) {
		/*
		 * s2	Cellが2 個,生のbitが真になる
		 * s3	Cellが3 個,生のbitが真になる
		 */
		int s2, s3;

		/*
		 * 隣接8 マスのセルをそれぞれキャストし、boardと重ね合わせる
		 */
		int a, b, c, d, e, f, g, h;
		a = ( ( board   & 0xeee0 ) >> 5  ) | ( ( board_a & 0x0001 ) << 15 ) |
				( ( board_b & 0x000e ) << 11 ) | ( ( board_d & 0x1110 ) >> 1  ) ;
		b = ( ( board   & 0xfff0 ) >> 4  ) | ( ( board_b & 0x000f ) << 12 ) ;
		c = ( ( board   & 0x7770 ) >> 3  ) | ( ( board_c & 0x0008 ) << 9 ) |
				( ( board_b & 0x0007 ) << 13 ) | ( ( board_e & 0x8880 ) >> 7 ) ;
		d = ( ( board   & 0xeeee ) >> 1  ) | ( ( board_d & 0x1111 ) << 3  ) ;
		e = ( ( board   & 0x7777 ) << 1  ) | ( ( board_e & 0x8888 ) >> 3  ) ;
		f = ( ( board   & 0x0eee ) << 3  ) | ( ( board_f & 0x1000 ) >> 9 ) |
				( ( board_g & 0xe000 ) >> 13 ) | ( ( board_d & 0x0111 ) << 7 ) ;
		g = ( ( board   & 0x0fff ) << 4  ) | ( ( board_g & 0xf000 ) >> 12 ) ;
		h = ( ( board   & 0x0777 ) << 5  ) | ( ( board_h & 0x8000 ) >> 15 ) |
				( ( board_g & 0x7000 ) >> 11 ) | ( ( board_e & 0x0888 ) << 1  ) ;

		/*
		 * 論理演算で数をカウントする
		 */
		int xab, xcd, xef, xgh, x;
		xab = a & b;
		a ^= b;
		xcd = c & d;
		c ^= d;
		xef = e & f;
		e ^= f;
		xgh = g & h;
		g ^= h;
		d = a & c;
		a ^= c;
		c = xab & xcd;
		b = xab ^ xcd ^ d;
		h = e & g;
		e ^= g;
		g = xef & xgh;
		f = xef ^ xgh ^ h;
		d = a & e;
		a ^= e;
		h = b & f;
		b ^= f;
		h |= b & d;
		b ^= d;
		c ^= g ^ h;
		x = ~c & b;
		s2 = x & ~a;
		s3 = x & a;
		return ( ~board & s3 ) | ( board & ( s2 | s3 ) );
	}

	/*
	 * Board の状態を標準出力する
	 */
	public void view(){
		for(int y=0;y<getH();y++){
			for(int x=0;x<getW();x++){
				System.out.print((isOn(x, y)?"*":".")+" ");
			}
			System.out.println();
		}
		System.out.println("----------------");
	}

	/*
	 * board 中のCell の生死を返す
	 */
	public static boolean isOn(int[][] board, int X,int Y){
		int x=X/BIT,y=Y/BIT,w=X-x*BIT,z=Y-y*BIT;
		return 1 == (1 & (board[x][y] >> (BIT-1-z)*BIT+(BIT-1-w)));
	}

	/*
	 * boards 中のCell の生死を返す
	 * isOn(boards, X, Y) と isOn(X, Y)は同義
	 */
	public boolean isOn(int X, int Y){
		int x=X/BIT,y=Y/BIT,w=X-x*BIT,z=Y-y*BIT;
		return 1 == (1 & (boards[x+1][y+1] >> (BIT-1-z)*BIT+(BIT-1-w)));
	}

	/*
	 * Cell の横幅を返す
	 */
	public int getW(){
		return w*BIT;
	}
	/*
	 * Cell の縦幅を返す
	 */
	public int getH(){
		return h*BIT;
	}

	/*
	 * X, Y の位置のCell の生死を入れ替える
	 */
	public void turn(int X, int Y){
		if(X<0||getW()<=X||Y<0||getH()<=Y)return;
		int x=X/BIT,y=Y/BIT,w=X-x*BIT,z=Y-y*BIT;
		boards[x+1][y+1]^=(1 << (BIT-1-z)*BIT+(BIT-1-w));
	}

	/*
	 * X, Y を左上始点として
	 * pattern をboards に反映させる
	 */
	public void addPattern(int X, int Y, int[][] pattern) {
		for(int ix=0;ix<BIT*pattern.length;ix++)
			for(int iy=0;iy<BIT*pattern[ix/BIT].length;iy++)
				if(isOn(pattern, ix, iy)) turn(X+ix, Y+iy);
	}

}
