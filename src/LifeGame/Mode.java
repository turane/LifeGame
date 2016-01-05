package LifeGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JToggleButton;

public enum Mode {
	/*
	 * Edit 時のMode を管理する

	 * Draw		Board の書き換え
	 * Move		平行移動
	 * Capture	Pattern の保存
	 */
	Draw(Icons.Draw),
	Move(Icons.Move),
	Capture(Icons.Capture);
	
	/*
	 * selected		enum 内部で現在選択されているModeを管理する
	 * b			Mode をthis に変更するためのボタン
	 */
	private static Mode selected=Draw;
	private AbstractButton b;
	private Mode(Icons i){
		b=new JToggleButton(i.getImageIcon());
		b.setPreferredSize(i.getSize());
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				select();
			}
		});
	}
	
	/*
	 * Mode this に変更する
	 */
	public void select(){
		selected=get();
	}
	
	/*
	 * 現在選択されているMode を返す
	 */
	public static Mode getSelected(){
		return selected;
	}

	/*
	 * Mode に関連するボタンを返す
	 */
	public AbstractButton getButton() {
		return b;
	}
	
	/*
	 * this を返す
	 */
	public Mode get(){
		return this;
	}
}
