/**
 * Software Engineer lab4
 */
package wm.view;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import wm.config.UI_Constants;
import wm.config.IconConstants;
import wm.config.ScriptConstants;
import wm.controller.IRecordController;
import wm.view.component.WMBar;
import wm.view.component.WMButton;
import wm.view.component.WMLabel;
import wm.view.component.WMListBox;
import wm.view.component.WMPie;
import wm.view.component.WMTable;

/**
 * Class RecordView represents a record view and is managed by a record view
 * controller. It provides different methods to show the recite record.
 * 
 * @author Sidney Fan
 * 
 */
public class RecordView extends WMView implements IRecordView{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2917311740345650657L;
	protected IRecordController controller;
	/* Button */
	protected JButton quitBtn;
	protected JButton homeBtn;
	protected WMButton tableBlock;
	protected WMButton pieBlock;
	protected WMButton barBlock;
	/* Label */
	protected WMLabel headLine;
	protected WMLabel dictNameLabel;
	protected WMLabel dictSizeLabel;
	protected WMLabel dictRecitedLabel;
	protected WMLabel dictCorrectLabel;
	protected WMLabel dictWrongLabel;
	protected WMLabel dictRateLabel;
	protected int dictSizeValue;
	protected int dictRecitedValue;
	protected int dictCorrectValue;
	protected int dictWrongValue;
	protected int dictRateValue;
	/* Panel */
	protected JPanel navPanel;
	protected JPanel centerPanel;
	protected JPanel bottomNav;
	protected JPanel tablePanel;
	protected JPanel piePanel;
	protected JPanel barPanel;
	protected JPanel infoPanel;
	protected JPanel iconPanel;
	/* Padding */
	static int PADDING = 50;
	/* Combobox */
	protected WMListBox listBox;
	/* Status */
	private int currentBtnIndex;
	private static int INTABLE = 1;
	private static int INPIE = 2;
	private static int INBAR = 3;
	private int currentDicIndex;
	/* Icons */
	private WMTable tableIcon;
	private WMPie pieCorrectIcon;
	private WMPie pieRecitedIcon;
	private WMBar barCorrectIcon;
	private WMBar barRecitedIcon;

	/**
	 * Constructor of RecordView.<br>
	 * Invoke sequence<br>
	 * <blockquote> After Instantiate, invoke following first:<br>
	 * <ul>
	 * <li>setDictist(List<String> dictist): set dictionary list</li>
	 * <li>setSizeText</li>
	 * <li>setReciteSizeText</li>
	 * <li>setCorrectText</li>
	 * <li>setCorrectPercentage</li>
	 * <li>setWrongText</li>
	 * </ul>
	 * After above, for specified condition (default show table panel), invoke
	 * following functions:
	 * <ul>
	 * <li>showTablePanel</li>
	 * <li>showPiePanel</li>
	 * <li>showBarPanel</li>
	 * </ul>
	 * 
	 * </blockquote>
	 * 
	 * @param controller
	 */
	public RecordView(IRecordController controller) {
		super();
		this.controller = controller;
		initComponents();
		initListener();
		// default
		// showTablePanel();
	}

	@Override
	public void setDictist(List<String> dictist) {
		listBox.setDictist(dictist);
		listBox.setCurrentIndex(0);
	}

	@Override
	public void setSizeText(int size) {
		dictSizeValue = size;
		dictSizeLabel.setText(String.format("<html>%s<br>%d</html>",
				ScriptConstants.CHIN_TOTAL, size));
	}

	@Override
	public void setReciteSizeText(int recitedSize) {
		dictRecitedValue = recitedSize;
		dictRecitedLabel.setText(String.format("<html>%s<br>%d</html>",
				ScriptConstants.CHIN_RECITED, recitedSize));
	}

	@Override
	public void setCorrectText(int correct) {
		dictCorrectValue = correct;
		dictCorrectLabel.setText(String.format("<html>%s<br>%d</html>",
				ScriptConstants.CHIN_CORRECT, correct));
	}

	@Override
	public void setCorrectPercentage(double percentage) {
		dictRateValue = (int) (percentage * 100.00);
		dictRateLabel.setText(String.format("<html>%s<br>%.2f%%</html>",
				ScriptConstants.CHIN_RATE, percentage * 100.00));
	}

	@Override
	public void setWrongText(int wrong) {
		dictWrongValue = wrong;
		dictWrongLabel.setText(String.format("<html>%s<br>%d</html>",
				ScriptConstants.CHIN_WRONG, wrong));
	}

	@Override
	public void setPieCorrectIcon(int correct, int size) {
		pieCorrectIcon.createPie(new int[] { correct, size - correct });
	}

	@Override
	public void setPieRecitedIcon(int recited, int size) {
		pieRecitedIcon.createPie(new int[] { recited, size - recited });
		pieRecitedIcon.repaint();
	}

	@Override
	public void setBarCorrectIcon(List<Integer> list) {
		barCorrectIcon.createBar(list, currentDicIndex - 1);
		barCorrectIcon.repaint();
	}

	@Override
	public void setBarRecitedIcon(List<Integer> list) {
		barRecitedIcon.createBar(list, currentDicIndex - 1);
		barRecitedIcon.repaint();
	}

	@Override
	public void setHeadLineText(String text) {
		headLine.setText(text);
	}

	@Override
	public String getHeadLineText() {
		return headLine.getText();
	}

	@Override
	protected void initComponents() {
		// Global
		navPanel = new JPanel();
		centerPanel = new JPanel();
		this.setLayout(null);
		this.add(navPanel);
		this.add(centerPanel);
		navPanel.setBounds(0, 0, UI_Constants.GLOBAL_WIDTH, UI_Constants.UNITHEIGHT);
		centerPanel.setBounds(0, UI_Constants.UNITHEIGHT, UI_Constants.GLOBAL_WIDTH,
				UI_Constants.GLOBAL_HEIGHT - UI_Constants.UNITHEIGHT);
		navPanel.setOpaque(false);
		centerPanel.setOpaque(false);
		// navigator
		homeBtn = new JButton(IconConstants.HOMEICON);
		quitBtn = new JButton(IconConstants.QUITICON);
		homeBtn.setBorderPainted(false);
		homeBtn.setBackground(UI_Constants.NORMALGREEN);
		quitBtn.setBorderPainted(false);
		quitBtn.setBackground(UI_Constants.NORMALGREEN);
		headLine = new WMLabel("Statisics", WMLabel.LABEL_NORMAL);
		navPanel.setLayout(null);
		navPanel.add(homeBtn);
		navPanel.add(quitBtn);
		navPanel.add(headLine);
		homeBtn.setBounds(0, 0, UI_Constants.UNITHEIGHT, UI_Constants.UNITHEIGHT);
		quitBtn.setBounds(UI_Constants.GLOBAL_WIDTH - UI_Constants.UNITHEIGHT, 0,
				UI_Constants.UNITHEIGHT, UI_Constants.UNITHEIGHT);
		headLine.setBounds(UI_Constants.UNITHEIGHT, 0, UI_Constants.GLOBAL_WIDTH - 2
				* UI_Constants.UNITHEIGHT, UI_Constants.UNITHEIGHT);
		addCenterPanel();
	}

	private void addCenterPanel() {
		centerPanel.setLayout(null);
		// initialize different components
		initDictNamePanel();
		initInfoPanel();
		initIconPanel();
		initBottomNav();
	}

	private void initDictNamePanel() {
		currentDicIndex = 0;
		listBox = new WMListBox(UI_Constants.UNITHEIGHT+30, WMLabel.LABEL_SMALL + 5);
		listBox.setCurrentIndex(currentDicIndex);
		dictNameLabel = new WMLabel(ScriptConstants.CHIN_DICTNAME,
				WMLabel.LABEL_TINY);
		dictNameLabel.setBounds(0, 0, UI_Constants.GLOBAL_WIDTH, PADDING);
		listBox.setBounds((UI_Constants.GLOBAL_WIDTH - UI_Constants.UNITHEIGHT-30) / 2,
				PADDING, UI_Constants.GLOBAL_WIDTH, UI_Constants.UNITHEIGHT);
		centerPanel.add(dictNameLabel);
		centerPanel.add(listBox);
	}

	private void initInfoPanel() {
		infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(5, 1));
		infoPanel.setOpaque(false);

		dictSizeLabel = new WMLabel(ScriptConstants.CHIN_TOTAL, WMLabel.LABEL_TINY);
		dictRecitedLabel = new WMLabel(ScriptConstants.CHIN_RECITED,
				WMLabel.LABEL_TINY);
		dictCorrectLabel = new WMLabel(ScriptConstants.CHIN_CORRECT,
				WMLabel.LABEL_TINY);
		dictWrongLabel = new WMLabel(ScriptConstants.CHIN_WRONG, WMLabel.LABEL_TINY);
		dictRateLabel = new WMLabel(ScriptConstants.CHIN_RATE, WMLabel.LABEL_TINY);

		dictSizeLabel.setHorizontalAlignment(JLabel.LEFT);
		dictRecitedLabel.setHorizontalAlignment(JLabel.LEFT);
		dictCorrectLabel.setHorizontalAlignment(JLabel.LEFT);
		dictWrongLabel.setHorizontalAlignment(JLabel.LEFT);
		dictRateLabel.setHorizontalAlignment(JLabel.LEFT);

		infoPanel.add(dictSizeLabel);
		infoPanel.add(dictRecitedLabel);
		infoPanel.add(dictCorrectLabel);
		infoPanel.add(dictWrongLabel);
		infoPanel.add(dictRateLabel);
		infoPanel.setBounds(UI_Constants.UNITHEIGHT, UI_Constants.UNITHEIGHT
				- PADDING / 2, 2 * UI_Constants.UNITHEIGHT,
				UI_Constants.GLOBAL_HEIGHT - 2 * UI_Constants.UNITHEIGHT - 2
						* PADDING);

		centerPanel.add(infoPanel);
	}

	private void initIconPanel() {
		iconPanel = new JPanel();
		iconPanel.setBounds(2 * UI_Constants.UNITHEIGHT + PADDING,
				UI_Constants.UNITHEIGHT, 2 * IconConstants.ICON_MIDDLE + PADDING,
				IconConstants.ICON_MIDDLE + PADDING);
		iconPanel.setLayout(null);
		iconPanel.setOpaque(false);

		centerPanel.add(iconPanel);

		initPiePanel();
		initBarPanel();
		initTablePanel();
	}

	private void initTablePanel() {
		tablePanel = new JPanel();
		// default
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Integer> values = new ArrayList<Integer>();
		names.add(ScriptConstants.CHIN_TOTAL);
		names.add(ScriptConstants.CHIN_RECITED);
		names.add(ScriptConstants.CHIN_CORRECT);
		names.add(ScriptConstants.CHIN_WRONG);
		names.add(ScriptConstants.CHIN_RATE);
		values.add(dictSizeValue);
		values.add(dictRecitedValue);
		values.add(dictCorrectValue);
		values.add(dictWrongValue);
		values.add(dictRateValue);

		tableIcon = new WMTable(values, names);
		tableIcon.setBounds(0, PADDING, 2 * IconConstants.ICON_MIDDLE + PADDING,
				IconConstants.ICON_MIDDLE / 2);

		tablePanel.setLayout(null);
		tablePanel.setOpaque(false);
		tablePanel.add(tableIcon);

		tablePanel.setBounds(0, 0, 2 * IconConstants.ICON_MIDDLE + PADDING,
				IconConstants.ICON_MIDDLE + PADDING);
	}

	private void initPiePanel() {
		piePanel = new JPanel();
		pieCorrectIcon = new WMPie();
		pieRecitedIcon = new WMPie();
		WMLabel corrLabel = new WMLabel(ScriptConstants.CHIN_CORRECT_WRONG_RATIO,
				WMLabel.LABEL_TINY);
		WMLabel reciLabel = new WMLabel(ScriptConstants.CHIN_RECITE_PIE,
				WMLabel.LABEL_TINY);

		piePanel.setLayout(null);
		piePanel.setOpaque(false);

		pieCorrectIcon.setBounds(0, 0, IconConstants.ICON_MIDDLE,
				IconConstants.ICON_MIDDLE);
		pieRecitedIcon.setBounds(IconConstants.ICON_MIDDLE + PADDING, 0,
				IconConstants.ICON_MIDDLE, IconConstants.ICON_MIDDLE);
		corrLabel.setBounds(0, IconConstants.ICON_MIDDLE + 10,
				IconConstants.ICON_MIDDLE, WMLabel.LABEL_SMALL);
		reciLabel.setBounds(IconConstants.ICON_MIDDLE + PADDING,
				IconConstants.ICON_MIDDLE + 10, IconConstants.ICON_MIDDLE,
				WMLabel.LABEL_SMALL);

		piePanel.add(pieCorrectIcon);
		piePanel.add(pieRecitedIcon);
		piePanel.add(corrLabel);
		piePanel.add(reciLabel);

		piePanel.setBounds(0, 0, 2 * IconConstants.ICON_MIDDLE + PADDING,
				IconConstants.ICON_MIDDLE + PADDING);
	}

	private void initBarPanel() {
		barPanel = new JPanel();
		barCorrectIcon = new WMBar();
		barRecitedIcon = new WMBar();
		WMLabel barCorrectLabel = new WMLabel(
				ScriptConstants.CHIN_CORRECT_WRONG_RATIO_BAR, WMLabel.LABEL_TINY);
		WMLabel barRecitedLabel = new WMLabel(ScriptConstants.CHIN_RECITE_BAR,
				WMLabel.LABEL_TINY);

		barPanel.setLayout(null);
		barPanel.setOpaque(false);

		barCorrectIcon.setBounds(0, 0, 2 * IconConstants.ICON_MIDDLE,
				UI_Constants.UNITHEIGHT);
		barRecitedIcon.setBounds(0, UI_Constants.UNITHEIGHT+25,
				2 * IconConstants.ICON_MIDDLE, UI_Constants.UNITHEIGHT);
		barCorrectLabel.setBounds(0, UI_Constants.UNITHEIGHT-PADDING+10,
				2 * IconConstants.ICON_MIDDLE, UI_Constants.UNITHEIGHT);
		barRecitedLabel.setBounds(0, 2 * UI_Constants.UNITHEIGHT-15,
				2 * IconConstants.ICON_MIDDLE, UI_Constants.UNITHEIGHT);

		barPanel.add(barCorrectIcon);
		barPanel.add(barRecitedIcon);
		barPanel.add(barCorrectLabel);
		barPanel.add(barRecitedLabel);

		barPanel.setBounds(0, 0, 2 * IconConstants.ICON_MIDDLE + PADDING,
				IconConstants.ICON_MIDDLE + PADDING);
	}

	private void initBottomNav() {
		currentBtnIndex = 0;
		bottomNav = new JPanel();
		tableBlock = new WMButton(IconConstants.TABLEICON96);
		pieBlock = new WMButton(IconConstants.PIEICON96);
		barBlock = new WMButton(IconConstants.BARICON96);
		tableBlock.setBorder(null);
		pieBlock.setBorder(null);
		barBlock.setBorder(null);
		tableBlock.setColor(UI_Constants.NORMALGREEN, UI_Constants.DARKGREEN);
		pieBlock.setColor(UI_Constants.NORMALGREEN, UI_Constants.DARKGREEN);
		barBlock.setColor(UI_Constants.NORMALGREEN, UI_Constants.DARKGREEN);
		tableBlock.setOpaque(true);
		pieBlock.setOpaque(true);
		barBlock.setOpaque(true);

		centerPanel.add(tableBlock);
		centerPanel.add(pieBlock);
		centerPanel.add(barBlock);

		tableBlock.setBounds(0, UI_Constants.GLOBAL_HEIGHT - 2
				* UI_Constants.UNITHEIGHT, UI_Constants.GLOBAL_WIDTH / 3,
				UI_Constants.UNITHEIGHT);
		pieBlock.setBounds(UI_Constants.GLOBAL_WIDTH / 3, UI_Constants.GLOBAL_HEIGHT
				- 2 * UI_Constants.UNITHEIGHT, UI_Constants.GLOBAL_WIDTH / 3 + 1,
				UI_Constants.UNITHEIGHT);
		barBlock.setBounds(UI_Constants.GLOBAL_WIDTH * 2 / 3,
				UI_Constants.GLOBAL_HEIGHT - 2 * UI_Constants.UNITHEIGHT,
				UI_Constants.GLOBAL_WIDTH / 3, UI_Constants.UNITHEIGHT);
	}

	@Override
	protected void initListener() {
		quitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.closeWindow();
			}

		});

		homeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.switchToHome();
			}

		});

		tableBlock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentBtnIndex = INTABLE;
				setCurrentBlock(tableBlock);
			}
		});

		pieBlock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentBtnIndex = INPIE;
				setCurrentBlock(pieBlock);
			}
		});

		barBlock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentBtnIndex = INBAR;
				setCurrentBlock(barBlock);
			}
		});

		listBox.addPropertyChangeListener(UI_Constants.PROPERTY_DICTCHANGE,
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						int newValue = (Integer) evt.getNewValue();
						currentDicIndex = newValue;
						if (currentBtnIndex == INTABLE) {
							controller.showRecordByTable(newValue);
						} else if (currentBtnIndex == INPIE) {
							controller.showRecordByPie(newValue);
						} else if (currentBtnIndex == INBAR) {
							controller.showRecordByBar(currentDicIndex);
						}
					}
				});

	}

	private void setCurrentBlock(WMButton clickedbtn) {
		tableBlock.release();
		tableBlock.paintLocal();
		pieBlock.release();
		pieBlock.paintLocal();
		barBlock.release();
		barBlock.paintLocal();
		clickedbtn.paintPress();
		clickedbtn.fix();
		if (currentBtnIndex == INTABLE) {
			controller.showRecordByTable(currentDicIndex);
		} else if (currentBtnIndex == INPIE) {
			controller.showRecordByPie(currentDicIndex);
		} else if (currentBtnIndex == INBAR) {
			controller.showRecordByBar(currentDicIndex);
		}

	}

	public void showTablePanel() {
		// default
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Integer> values = new ArrayList<Integer>();
		names.add(ScriptConstants.CHIN_TOTAL);
		names.add(ScriptConstants.CHIN_RECITED);
		names.add(ScriptConstants.CHIN_CORRECT);
		names.add(ScriptConstants.CHIN_WRONG);
		names.add(ScriptConstants.CHIN_RATE);
		values.add(dictSizeValue);
		values.add(dictRecitedValue);
		values.add(dictCorrectValue);
		values.add(dictWrongValue);
		values.add(dictRateValue);

		iconPanel.removeAll();
		tableIcon.createTable(values, names);
		iconPanel.add(tablePanel);
		iconPanel.repaint();

		// TODO
		currentBtnIndex = INTABLE;
		listBox.setCurrentIndex(currentDicIndex);
	}

	public void showPiePanel() {
		iconPanel.removeAll();
		iconPanel.add(piePanel);
		iconPanel.repaint();

		// TODO
		currentBtnIndex = INPIE;
		listBox.setCurrentIndex(currentDicIndex);
	}

	public void showBarPanel() {
		iconPanel.removeAll();
		iconPanel.add(barPanel);
		iconPanel.repaint();

		// TODO
		currentBtnIndex = INBAR;
		listBox.setCurrentIndex(currentDicIndex);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(UI_Constants.LIGHTGREEN);
		g.fillRect(0, 0, UI_Constants.GLOBAL_WIDTH, UI_Constants.GLOBAL_HEIGHT);
		g.setColor(UI_Constants.NORMALGREEN);
		g.fillRect(0, 0, UI_Constants.GLOBAL_WIDTH, UI_Constants.UNITHEIGHT);
		g.fillRect(0, UI_Constants.GLOBAL_HEIGHT - UI_Constants.UNITHEIGHT,
				UI_Constants.GLOBAL_WIDTH, UI_Constants.UNITHEIGHT);
	}

}
