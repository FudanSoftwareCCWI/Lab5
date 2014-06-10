package wm.view.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import wm.config.UI_Constants;

public class WMListBox extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7550452879023653824L;
	private JTextField field;
	private JScrollPane scrollPane;
	private JPanel comboBox;

	private int width;
	private int height;
	private int currentIndex;
	private boolean isComboShow;
	/* Dictionary list */
	private List<String> dictist;

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public void setDictist(List<String> dictist) {
		this.dictist = dictist;
		field.setText(dictist.get(currentIndex));
	}

	public WMListBox(int width, int height) {
		this.width = width;
		this.height = height;
		this.isComboShow = false;
		this.currentIndex = -1;
		initWMComboBox();
		initComboListener();
	}

	private void initWMComboBox() {
		field = new JTextField();
		comboBox = new JPanel();
		scrollPane = new JScrollPane(comboBox);

		field.setBorder(new MatteBorder(1, 0, 1, 0, Color.WHITE));
		scrollPane.setBorder(null);
//		scrollPane.getVerticalScrollBar().setUI(null);
		field.setFont(new Font(WMLabel.LABEL_FONT, Font.PLAIN,
				WMLabel.LABEL_TINY));
		field.setForeground(Color.WHITE);
		field.setBackground(UI_Constants.LIGHTGREEN);
		field.setEditable(false);

		this.setLayout(null);
		this.add(field);
		this.add(scrollPane);
		field.setBounds(0, 0, width, height);
		scrollPane.setBounds(0, height + 5, width, 3 * height);
		hideComboBox();
	}

	public String getPrefix() {
		return field.getText();
	}

	private void initComboListener() {
		field.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (isComboShow) {
					hideComboBox();
				} else {
					setComboBox();
				}
			}
		});
	}

	public void setComboBox() {
		comboBox.removeAll();
		comboBox.setLayout(new GridLayout(dictist.size(), 1));
		if (dictist.size() == 0)
			return;
		WMBlock tempBlock;
		String tempname;
		Iterator<String> it = dictist.iterator();
		while (it.hasNext()) {
			tempname = it.next();
			tempBlock = new WMBlock(width, 20, 1, 1);
			tempBlock.addLeftLabel(tempname, WMLabel.LABEL_TINY);
			tempBlock.setColor(UI_Constants.LIGHTGREEN, UI_Constants.NOTEALPHA);
			comboBox.add(tempBlock);
		}
		comboBox.setPreferredSize(new Dimension(scrollPane.getWidth() - 50,
				dictist.size() * (WMLabel.LABEL_SMALL + 3)));
		setListBlockListener();
		showComboBox();
	}

	public void showComboBox() {
		isComboShow = true;
		scrollPane.setVisible(true);
	}

	public void hideComboBox() {
		isComboShow = false;
		scrollPane.setVisible(false);
	}

	private void setListBlockListener() {
		int i = 0;
		for (Component block : comboBox.getComponents()) {
			block.addMouseListener(new WMMouseClickListener(i));
			i++;
		}
	}

	private void setCurrentBlock(WMBlock clickedBlock) {
		Component[] siblings = comboBox.getComponents();
		for (Component block : siblings) {
			if (block.equals(clickedBlock)) {
				continue;
			}
			((WMBlock) block).release();
			((WMBlock) block).paintLocal();
		}
		clickedBlock.paintPress();
		clickedBlock.fix();
		hideComboBox();
	}

	protected JTextField getField() {
		return field;
	}

	protected void setText(String text) {
		field.setText(text);
	}

	class WMMouseClickListener extends MouseAdapter {

		private int index;

		WMMouseClickListener(int index) {
			super();
			this.index = index;
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			currentIndex = index;
			setCurrentBlock((WMBlock) comboBox.getComponents().clone()[index]);
			field.setText(dictist.get(currentIndex));
			// Fire chosen
			WMListBox.this.firePropertyChange(UI_Constants.PROPERTY_DICTCHANGE, new Integer(-1), new Integer(index));
		}
	}

}