package LifeGame;

import java.awt.BorderLayout;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;

public class LifeGame extends JFrame implements MouseWheelListener, Define{
	/**
	 * MainClass
	 * 
	 * chipSize		Cell 描画の大きさ
	 * momving		LifeGame が再生中なら真
	 */
	private static final long serialVersionUID = -9173953117499624322L;
	public static int chipSize;
	public static boolean moving;

	public LifeGame(){
		setTitle("Life Game");
		setSize(W, H);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(Icons.Icon.getIcon());

		moving=false;
		chipSize = STD_CELL;
		Board b=new Board(getWidth()/(chipSize*Board.BIT), getHeight()/(chipSize*Board.BIT));
		ViewPanel.setBoard(b);
		EditPanel.setBoard(b);
		add(ViewPanel.getInstance(),BorderLayout.CENTER);
		add(EditPanel.getInstance(), BorderLayout.SOUTH);

		addMouseWheelListener(this);
		setVisible(true);
	}

	public static void main(String[] args) {
		new LifeGame();
	}

	/*
	 * マウスホイールしたときにchipSizeを変更
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int value=chipSize - e.getWheelRotation();
		int pchipSize=chipSize;
		chipSize=ViewPanel.constrain(value, MIN_CELL, MAX_CELL);
		ViewPanel.changeMag(pchipSize, chipSize);
		ViewPanel.getInstance().rerepaint();
	}

}
