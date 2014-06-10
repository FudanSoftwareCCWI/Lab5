/**
 * Software Engineer lab4
 */
package wm.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import wm.config.UI_Constants;
import wm.config.IconConstants;
import wm.controller.IReciteMainController;
import wm.view.component.*;

/**
 * Class ReciteMainView represents a recite main view, which shows a list of all
 * dictionaries and allows user to select one to recite. It is managed by a
 * recite main controller. It is the beginning view of the recite.
 * 
 * @author Sidney Fan
 * 
 */
public class ReciteMainView extends WMView implements IReciteMainView {

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -5827806794895012421L;
	private IReciteMainController controller;
	private JPanel dictionaryInfoPanel;
	private JPanel listPanel;
	private JScrollPane scrollPane;

	/* dictionary info */
	private WMLabel dictionaryNameLabel;
	private WMLabel totalNumLabel;
	private WMLabel dictSizeLabel;
	private WMLabel sizeRateLabel;
	private WMPie pie;
	private JPanel piePanel;
	private JButton quitBtn;
	private JButton homeBtn;
	private JButton nextBtn;
	/* dictionary list */
	private List<String> dictList;
	private int currentDictIndex;

	public ReciteMainView(IReciteMainController controller) {
		super();
		this.controller = controller;
		initComponents();
		initListener();
	}

	@Override
	public void setListPanelContent(List<String> nameList) {
		dictList = nameList;
		listPanel.removeAll();
		listPanel.setLayout(new GridLayout(nameList.size(), 1));
		WMBlock tempBlock;
		String tempname;
		Iterator<String> it = dictList.iterator();
		int i = 0;
		while (it.hasNext()) {
			tempname = it.next();
			tempBlock = new WMBlock(UI_Constants.UNITLONGWIDTH,
					UI_Constants.UNITHEIGHT, 1, 1);
			tempBlock.addLabel(tempname, WMLabel.LABEL_MIDDLE);
			listPanel.add(tempBlock);
			if (i % 2 == 0) {
				tempBlock.setColor(UI_Constants.NORMALGREEN,
						UI_Constants.DARKGREEN);
			} else {
				tempBlock.setColor(UI_Constants.LIGHTGREEN,
						UI_Constants.DARKGREEN);
			}
			i++;
		}
		// set new blocks listener
		setListBlockListener();
	}

	@Override
	public void setNameLabelText(String name) {
		dictionaryNameLabel.setText(name);
		dictionaryNameLabel.revalidate();
	}

	@Override
	public void setPieIcon(int size, int totalSize) {
		dictSizeLabel.setText(String.valueOf(size));
		totalNumLabel.setText(String.valueOf(totalSize));

		double rate = (double) (((double) size / (double) totalSize) * 100);
		sizeRateLabel.setText(String.format("%.2f%%", rate));
		pie.createPie(new int[] { size, totalSize - size });
		piePanel.repaint();
	}

	@Override
	public void setSizeLabelText(int size) {
		dictSizeLabel.setText(String.valueOf(size));
	}

	@Override
	public void setTotalSizeLabelText(int totalSize) {
		totalNumLabel.setText(String.valueOf(totalSize));
	}

	@Override
	public int getCurrentDictIndex() {
		return currentDictIndex;
	}

	@Override
	public void setCurrentDictIndex(int currentDictIndex) {
		setCurrentBlock((WMBlock) (listPanel.getComponents()[currentDictIndex]));
		this.currentDictIndex = currentDictIndex;
	}

	@Override
	protected void initComponents() {
		initUnitInfoPanel();
		initListPanel();
		updateView();
	}

	private void updateView() {
		// add left and right to view
		this.removeAll();
		this.setLayout(null);
		this.add(dictionaryInfoPanel);
		this.add(scrollPane);
		// set position and size
		dictionaryInfoPanel.setBounds(0, 0, UI_Constants.UNITSHORTWIDTH,
				UI_Constants.GLOBAL_HEIGHT);
		dictionaryInfoPanel.setOpaque(false);
		scrollPane.setBounds(UI_Constants.UNITSHORTWIDTH, 0,
				UI_Constants.UNITLONGWIDTH, UI_Constants.GLOBAL_HEIGHT);
		scrollPane.setOpaque(false);
		// scrollPane.getVerticalScrollBar().setUI(null);
		listPanel.setPreferredSize(new Dimension(scrollPane.getWidth() - 50,
				UI_Constants.NUM_ROW * UI_Constants.UNITHEIGHT));
		listPanel.revalidate();
	}

	private void initUnitInfoPanel() {
		// init statistic panels
		dictionaryInfoPanel = new JPanel();
		dictionaryInfoPanel.setLayout(null);

		// 1 row
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(1, 1));
		titlePanel.add(new WMLabel("Word Master", WMLabel.LABEL_NORMAL));
		titlePanel.setOpaque(false);

		// 2 row
		dictionaryNameLabel = new WMLabel("词库 ~", WMLabel.LABEL_MIDDLE);
		JPanel totalPanel = new JPanel();
		totalPanel.setLayout(new GridLayout(1, 1));
		totalPanel.add(dictionaryNameLabel);
		totalPanel.setOpaque(false);

		// 3 row
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(2, 3));
		WMLabel recite = new WMLabel("单词总量", WMLabel.LABEL_SMALL);
		WMLabel correct = new WMLabel("单词量", WMLabel.LABEL_SMALL);
		WMLabel rate = new WMLabel("词量比例", WMLabel.LABEL_SMALL);
		totalNumLabel = new WMLabel("~", WMLabel.LABEL_SMALL);
		dictSizeLabel = new WMLabel("~", WMLabel.LABEL_SMALL);
		sizeRateLabel = new WMLabel("~", WMLabel.LABEL_SMALL);
		totalNumLabel.setVerticalAlignment(JLabel.TOP);
		dictSizeLabel.setVerticalAlignment(JLabel.TOP);
		sizeRateLabel.setVerticalAlignment(JLabel.TOP);
		infoPanel.add(recite);
		infoPanel.add(correct);
		infoPanel.add(rate);
		infoPanel.add(totalNumLabel);
		infoPanel.add(dictSizeLabel);
		infoPanel.add(sizeRateLabel);
		infoPanel.setOpaque(false);

		// 4-5
		pie = new WMPie();
		pie.setBounds(
				(UI_Constants.UNITSHORTWIDTH - IconConstants.ICON_MIDDLE) / 2,
				0, IconConstants.ICON_MIDDLE + 1, IconConstants.ICON_MIDDLE);
		piePanel = new JPanel();
		piePanel.setLayout(null);
		piePanel.setOpaque(false);
		piePanel.add(pie);

		// 6
		quitBtn = new JButton(IconConstants.QUITICON);
		homeBtn = new JButton(IconConstants.HOMEICON);
		nextBtn = new JButton(IconConstants.NEXTICON);
		quitBtn.setBorderPainted(false);
		homeBtn.setBorderPainted(false);
		nextBtn.setBorderPainted(false);
		quitBtn.setBackground(UI_Constants.DARKGREEN);
		homeBtn.setBackground(UI_Constants.DARKGREEN);
		nextBtn.setBackground(UI_Constants.DARKGREEN);

		JPanel iconPanel = new JPanel();
		iconPanel.setOpaque(false);
		iconPanel.setLayout(new GridLayout(1, 3));
		iconPanel.add(homeBtn);
		iconPanel.add(nextBtn);
		iconPanel.add(quitBtn);

		// add all and set position
		dictionaryInfoPanel.add(titlePanel);
		dictionaryInfoPanel.add(totalPanel);
		dictionaryInfoPanel.add(infoPanel);
		dictionaryInfoPanel.add(piePanel);
		dictionaryInfoPanel.add(iconPanel);
		titlePanel.setBounds(0, 0, UI_Constants.UNITSHORTWIDTH,
				UI_Constants.UNITHEIGHT);
		totalPanel.setBounds(0, UI_Constants.UNITHEIGHT,
				UI_Constants.UNITSHORTWIDTH, UI_Constants.UNITHEIGHT);
		infoPanel.setBounds(25, 2 * UI_Constants.UNITHEIGHT,
				UI_Constants.UNITSHORTWIDTH - 50, UI_Constants.UNITHEIGHT);
		piePanel.setBounds(0, 3 * UI_Constants.UNITHEIGHT,
				UI_Constants.UNITSHORTWIDTH, 2 * UI_Constants.UNITHEIGHT);
		iconPanel.setBounds(0, UI_Constants.GLOBAL_HEIGHT
				- UI_Constants.UNITHEIGHT, UI_Constants.UNITSHORTWIDTH,
				UI_Constants.UNITHEIGHT);
	}

	private void initListPanel() {
		listPanel = new JPanel();
		scrollPane = new JScrollPane(listPanel);
		scrollPane.setBorder(null);
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

		nextBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.switchToStartSelect(currentDictIndex);
			}
		});
	}

	private void setListBlockListener() {
		for (final Component block : listPanel.getComponents()) {
			block.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					setCurrentBlock((WMBlock) block);
					controller.showDictionaryDetail(currentDictIndex);
				}
			});
		}
	}

	private void setCurrentBlock(WMBlock clickedBlock) {
		Component[] siblings = listPanel.getComponents();
		int i = 0;
		for (Component block : siblings) {
			if (clickedBlock.equals((WMBlock) block)) {
				this.currentDictIndex = i;
				continue;
			}
			((WMBlock) block).release();
			((WMBlock) block).paintLocal();
			i++;
		}
		clickedBlock.paintPress();
		clickedBlock.fix();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(UI_Constants.DARKGREEN);
		g.fillRect(0, 0, UI_Constants.UNITSHORTWIDTH,
				UI_Constants.GLOBAL_HEIGHT);
		g.setColor(UI_Constants.LIGHTGREEN);
		g.fillRect(UI_Constants.UNITSHORTWIDTH, 0, UI_Constants.UNITLONGWIDTH,
				UI_Constants.GLOBAL_HEIGHT);
	}
}
