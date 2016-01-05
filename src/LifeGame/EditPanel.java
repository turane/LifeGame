package LifeGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EditPanel extends JPanel implements Define{

	/**
	 * Board の編集、管理を行うためのPanel
	 * 
	 * b		LifeGameのCellの情報を格納する
	 * pp		Pattern をBoard に追加ためのPanel
	 * phdel	ユーザーが登録したPattern を削除するときに使用する
	 * popup	ユーザーが登録したPattern を右クリックしたときにPopする
	 */
	private static final long serialVersionUID = -2879891668578948296L;
	private Board b;
	private PatternPanel pp;
	private AbstractButton phdel;
	private JPopupMenu popup;

	/*
	 * start 		LifeGame を再生、停止するボタン
	 * draw			Board に書き込みを行うモードに切り替えるボタン
	 * btn			Pattern の非選択を容易にするためPixel を代入する
	 * speed		LifeGame の一世代あたりのDelay を管理する
	 * byPattern	drawButton をPattern からdoClick された時には真になる
	 * userDef		user が定義したパターンを格納する
	 * moving		LifeGame が再生中の時, 真、停止中の時, 偽となる
	 */
	private static AbstractButton start, draw, btn;
	private static JSlider speed;
	private static boolean byPattern;
	private static Map<String, int[][]> userDef;
	public static boolean moving;

	/*
	 * シングルトンを実現する
	 */
	private static final EditPanel instance=new EditPanel();
	public static EditPanel getInstance(){
		return instance;
	}

	/*
	 * Board のセッター
	 */
	public static void setBoard(Board b){
		getInstance().b=b;
	}

	private EditPanel(){
		byPattern=false;
		
		/*
		 * step		一世代進める
		 * clear	全てのCell を殺す
		 * showP	ppを表示する
		 * delete	ユーザーが登録したPattern を削除する
		 */
		start=new JToggleButton(Icons.Start.getImageIcon());
		AbstractButton step=new JButton(Icons.Step.getImageIcon());
		AbstractButton clear=new JButton(Icons.Clear.getImageIcon());
		AbstractButton showP=new JCheckBox("show Pattern");
		pp=new PatternPanel(showP);
		popup=new JPopupMenu();
		JMenuItem delete=new JMenuItem("Delete");

		start.setSelectedIcon(Icons.Pause.getImageIcon());
		start.setPreferredSize(Icons.Start.getSize());
		step.setPreferredSize(Icons.Step.getSize());
		clear.setPreferredSize(Icons.Clear.getSize());
		Hashtable<Integer, JComponent> table = new Hashtable<Integer, JComponent>();
		table.put(new Integer(MIN_SPEED), new JLabel("min"));
		table.put(new Integer(STD_SPEED), new JLabel("std"));
		table.put(new Integer(MAX_SPEED), new JLabel("max"));
		speed=new JSlider(MAX_SPEED, MIN_SPEED, STD_SPEED);
		speed.setPreferredSize(new Dimension(150, 40));
		speed.setInverted(true);
		speed.setLabelTable(table);
		speed.setPaintLabels(true);
		moving=start.isSelected();
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				moving=((AbstractButton)e.getSource()).isSelected();
			}
		});
		step.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				b.step();
				ViewPanel.getInstance().rerepaint();
			}
		});
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				b.clear();
				ViewPanel.getInstance().rerepaint();
				pause();
			}
		});
		speed.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				ViewPanel.setSpeed(((JSlider)e.getSource()).getValue());
			}
		});
		showP.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				pp.setVisible(e.getStateChange()==ItemEvent.SELECTED);
			}
		});
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pp.remove(phdel);
				pp.validate();
				pp.repaint();
				userDef.remove(phdel.getText());
				ReadWriter.write(userDef);
			}
		});

		/*
		 * 関連するボタン群をgroup にして枠で囲う
		 */
		JPanel group;
		group=new JPanel();
		group.setBorder(new LineBorder(Color.LIGHT_GRAY));
		group.add(start);
		group.add(step);
		group.add(clear);
		add(group);

		group=new JPanel();
		group.setBorder(new LineBorder(Color.LIGHT_GRAY));

		/*
		 * Mode を切り替えるボタンを追加する
		 * 
		 * bg		Mode は一つのみ選択可能
		 * Draw		Board に書き込みを行う
		 * Move		平行移動を行う
		 * Capture	Pattern を登録する
		 */
		ButtonGroup bg=new ButtonGroup();
		for(Mode m:Mode.values()){
			AbstractButton b=m.getButton();
			switch(m){
			case Draw:
				draw=b;
				b.setSelected(true);
				b.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(!byPattern)
							patternReset();
					}
				});
				break;
			case Capture:
				b.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						pause();
					}
				});
				break;
			default:
				break;
			}
			bg.add(b);
			group.add(b);
		}
		add(group);
		add(Box.createHorizontalStrut(5));

		group=new JPanel();
		group.setBorder(new LineBorder(Color.LIGHT_GRAY));
		group.add(new JLabel("SPEED"));
		group.add(speed);
		add(group);

		add(showP);

		popup.add(delete);

		/*
		 * user が定義したPattern の読み込みを行い、追加する
		 */
		userDef=new LinkedHashMap<String, int[][]>();
		Map<String, int[][]> map=ReadWriter.read();
		for(String name:map.keySet())
			addPattern(name, map.get(name), false);
	}

	private class PatternPanel extends JDialog{

		/**
		 * Pattern をboard に追加するためのPanel
		 * 
		 * bg		Patternは一つのみ選択可能
		 */
		private static final long serialVersionUID = -2248213363823230613L;
		private ButtonGroup bg;

		/*
		 * 閉じるときに同時にshowP にもvisible を反映させる
		 */
		public PatternPanel(AbstractButton showP){
			setTitle("Patterns");
			setSize(W/2,H);
			setLayout(new BorderLayout());
			setLocationRelativeTo(EditPanel.getInstance());
			setAlwaysOnTop(true);
			setLayout(new FlowLayout());
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e){
					showP.setSelected(false);
				}
			});
			setIconImage(Icons.Icon.getIcon());

			bg=new ButtonGroup();
			JPanel group;
			group=new JPanel();
			group.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btn=addButton(group, Pattern.Pixel.toString(), null, true);
			btn.setSelected(true);
			add(group, BorderLayout.NORTH);

			for(Pattern p:Pattern.values())
				if(!p.equals(Pattern.Pixel)) {
					addButton(getContentPane(), p.toString(), p.getPattern(), true);				}
		}

		/*
		 * 第一引数のp にPattern を変更するボタンを追加する
		 * 
		 * !isDefault のときuser が定義したPattern であるので
		 * 色を変更し、削除可能にする
		 * draw.doClick はパターン選択時にMode を鉛筆に戻すために必要
		 */
		public AbstractButton addButton(Container p, String name, int[][] pattern, boolean isDefault){
			AbstractButton b=new JToggleButton(name);
			if(!isDefault){
				userDef.put(b.getText(), pattern);
				b.setBackground(Color.LIGHT_GRAY);
				b.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mousePressed(MouseEvent e){
						showPopup(e);
					}

					@Override
					public void mouseReleased(MouseEvent e){
						showPopup(e);
					}

				});
			}
			bg.add(b);
			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					byPattern=true;
					draw.doClick();
					byPattern=false;
					ViewPanel.setPattern(pattern);
				}
			});
			p.add(b);
			return b;
		}
	}

	/*
	 * user が定義したPattern に関数Popup
	 */
	private void showPopup(MouseEvent e){
		if (e.isPopupTrigger()) {
			popup.show(e.getComponent(), e.getX(), e.getY());
			phdel=(AbstractButton)e.getSource();
		}
	}

	/*
	 * LifeGameを一時停止
	 */
	private void pause(){
		moving=false;
		start.setSelected(moving);
	}

	/*
	 * 登録してあるPattern を取消
	 */
	public static void patternReset() {
		ViewPanel.setPattern(null);
		btn.setSelected(true);
	}

	/*
	 * パターンをpp に追加する
	 */
	public boolean addPattern(String name, int[][] set, boolean doSave) {
		if(userDef.containsKey(name))
			return false;
		pp.addButton(pp, name, set, false);
		pp.validate();
		if(doSave)
			ReadWriter.write(userDef);
		return true;
	}
}
