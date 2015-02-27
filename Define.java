package LifeGame;

public interface Define {
	public final int W=600;			//Window の標準幅
	public final int H=600;			//Window の標準高さ
	public final int ICON_S=64;		//Icon の標準サイズ
	
	/*
	 * セルの描画時のサイズ
	 */
	public final int MIN_CELL=1;
	public final int STD_CELL=10;
	public final int MAX_CELL=40;

	/*
	 * 一世代あたりのDelay時間
	 */
	public final int MIN_SPEED=30;
	public final int STD_SPEED=7;
	public final int MAX_SPEED=1;
}
