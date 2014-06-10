package wm.view;

import java.util.ArrayList;

import javax.swing.JPanel;

import wm.config.UI_Constants;
import wm.config.IconConstants;
import wm.config.ScriptConstants;
import wm.controller.IReciteProcessController;
import wm.view.component.WMLabel;
import wm.view.component.WMTable;

/**
 * Class ReciteRecordView represents at the end of the recite. It will show a
 * table which contains the recite information.
 * 
 * @author Sidney Fan
 * 
 */
public class ReciteRecordView extends ReciteProcessView implements IReciteRecordView{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7420407063090542202L;
	/* Label */
	protected WMLabel dictNameLabel;
	protected WMLabel dictNameValue;
	protected int dictRecitedValue;
	protected int dictCorrectValue;
	protected int dictWrongValue;
	protected int dictRateValue;
	/* Panel */
	protected JPanel bottomNav;
	protected JPanel tablePanel;
	protected JPanel iconPanel;
	/* Icons */
	private WMTable tableIcon;

	public ReciteRecordView(IReciteProcessController controller) {
		super(controller);
		addComponents();
	}
	
	@Override
	public void setCorrectPrecentageText(double percentage) {
		dictRateValue = (int) (percentage * 100);
	}

	@Override
	public void setCorrectCountText(int currentCount) {
		dictCorrectValue = currentCount;
	}

	@Override
	public void setIncorrectCountText(int incorrectCount) {
		dictWrongValue = incorrectCount;
	}

	@Override
	public void setNameText(String name) {
		dictNameValue.setText(name);
	}

	@Override
	public void setRecitedSizeText(int recitedSize) {
		dictRecitedValue = recitedSize;
	}

	@Override
	public void showTablePanel() {
		// default
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Integer> values = new ArrayList<Integer>();
		names.add(ScriptConstants.CHIN_SELECTED);
		names.add(ScriptConstants.CHIN_CORRECT);
		names.add(ScriptConstants.CHIN_WRONG);
		names.add(ScriptConstants.CHIN_RATE+"(%)");
		values.add(dictRecitedValue);
		values.add(dictCorrectValue);
		values.add(dictWrongValue);
		values.add(dictRateValue);

		iconPanel.removeAll();
		tableIcon.createTable(values, names);
		iconPanel.add(tablePanel);
		iconPanel.repaint();
	}

	protected void addComponents() {
		// initialize different components
		initDictNamePanel();
		initIconPanel();
	}

	private void initDictNamePanel() {
		dictNameLabel = new WMLabel(ScriptConstants.CHIN_DICTNAME,
				WMLabel.LABEL_TINY);
		dictNameValue = new WMLabel("Dictionary", WMLabel.LABEL_TINY);
		dictNameLabel.setBounds(0, 0, UI_Constants.GLOBAL_WIDTH, PADDING);
		dictNameValue.setBounds(0, PADDING, UI_Constants.GLOBAL_WIDTH, PADDING);
		centerPanel.add(dictNameLabel);
		centerPanel.add(dictNameValue);
	}

	private void initIconPanel() {
		iconPanel = new JPanel();
		iconPanel.setBounds(UI_Constants.GLOBAL_WIDTH / 2 - IconConstants.ICON_MIDDLE,
				UI_Constants.UNITHEIGHT, 2 * IconConstants.ICON_MIDDLE,
				IconConstants.ICON_MIDDLE + PADDING);
		iconPanel.setLayout(null);
		iconPanel.setOpaque(false);

		centerPanel.add(iconPanel);
		initTablePanel();
	}

	private void initTablePanel() {
		tablePanel = new JPanel();
		// default
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Integer> values = new ArrayList<Integer>();
		names.add(ScriptConstants.CHIN_SELECTED);
		names.add(ScriptConstants.CHIN_CORRECT);
		names.add(ScriptConstants.CHIN_WRONG);
		names.add(ScriptConstants.CHIN_RATE+"(%)");
		values.add(dictRecitedValue);
		values.add(dictCorrectValue);
		values.add(dictWrongValue);
		values.add(dictRateValue);

		tableIcon = new WMTable(values, names);
		tableIcon.setBounds(0, PADDING, 2 * IconConstants.ICON_MIDDLE,
				IconConstants.ICON_MIDDLE / 2);

		tablePanel.setLayout(null);
		tablePanel.setOpaque(false);
		tablePanel.add(tableIcon);

		tablePanel.setBounds(0, 0, 2 * IconConstants.ICON_MIDDLE,
				IconConstants.ICON_MIDDLE + PADDING);
		iconPanel.add(tablePanel);
	}
}
