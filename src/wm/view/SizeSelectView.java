package wm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import wm.config.UI_Constants;
import wm.config.IconConstants;
import wm.config.ScriptConstants;
import wm.controller.IReciteProcessController;
import wm.view.component.WMLabel;

/**
 * Class SizeSelectView represents a scroll and the user can move it to select
 * the number of the word he want to recite
 * 
 * @author Sidney Fan
 * 
 */
public class SizeSelectView extends ReciteProcessView implements ISizeSelectView{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3014427720952625543L;

	private JButton nextBtn;
	private WMLabel nextTip;
	private JSlider slider;

	private WMLabel left;
	private WMLabel right;
	private WMLabel pivot;
	private int leftValue;
	private int rightValue;
	private int pivotValue;

	public SizeSelectView(IReciteProcessController controller) {
		super(controller);
		addComponents();
		addListener();
	}

	protected void addComponents() {
		setTip(ScriptConstants.CHIN_SELECT_SZIE);

		nextBtn = new JButton(IconConstants.NEXTICON);
		nextTip = new WMLabel(ScriptConstants.CHIN_COMFIRM_TO_START,
				WMLabel.LABEL_TINY);
		nextBtn.setBorderPainted(false);
		nextBtn.setBackground(UI_Constants.LIGHTGREEN);

		leftValue = 1;
		rightValue = controller.getAvailableSize();

		pivotValue = rightValue / 2;
		slider = new JSlider(leftValue, rightValue);
		slider = new JSlider(leftValue, rightValue, pivotValue);
		slider.setBackground(UI_Constants.LIGHTGREEN);
		slider.setForeground(UI_Constants.DARKGREEN);
		left = new WMLabel(String.valueOf(leftValue), WMLabel.LABEL_SMALL);
		right = new WMLabel(String.valueOf(rightValue), WMLabel.LABEL_SMALL);
		pivot = new WMLabel(String.valueOf(pivotValue), WMLabel.LABEL_SMALL);

		centerPanel.add(slider);
		centerPanel.add(nextBtn);
		centerPanel.add(nextTip);
		centerPanel.add(left);
		centerPanel.add(right);
		centerPanel.add(pivot);

		slider.setBounds(UI_Constants.GLOBAL_WIDTH / 4, 2*UI_Constants.UNITHEIGHT+PADDING-10,
				UI_Constants.GLOBAL_WIDTH / 2, 20);
		left.setBounds(UI_Constants.GLOBAL_WIDTH / 4 - 20,
				2 * UI_Constants.UNITHEIGHT + 37, WMLabel.LABEL_SMALL,
				WMLabel.LABEL_SMALL);
		right.setBounds((int) (UI_Constants.GLOBAL_WIDTH * 0.75),
				2 * UI_Constants.UNITHEIGHT + 37, WMLabel.LABEL_NORMAL,
				WMLabel.LABEL_SMALL);
		setPivot(pivotValue);
		nextBtn.setBounds((UI_Constants.GLOBAL_WIDTH - IconConstants.ICON_TINY) / 2, 3
				* UI_Constants.UNITHEIGHT + PADDING, IconConstants.ICON_TINY,
				IconConstants.ICON_TINY);
		nextTip.setBounds(0, 3 * UI_Constants.UNITHEIGHT + PADDING + 5,
				UI_Constants.GLOBAL_WIDTH, UI_Constants.UNITHEIGHT);

	}

	protected void addListener() {
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent evt) {
				setPivot(slider.getValue());
			}
		});

		nextBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.setReciteSize(slider.getValue());
//				controller.switchToReciteWord();
			}
		});
	}

	private void setPivot(int value) {
		int x = (int) (UI_Constants.GLOBAL_WIDTH / 4 + (double) value
				/ (double) rightValue * UI_Constants.GLOBAL_WIDTH / 2)
				- WMLabel.LABEL_SMALL;
		pivot.setText(String.valueOf(value));
		pivot.setBounds(x, 2 * UI_Constants.UNITHEIGHT, WMLabel.LABEL_NORMAL,
				WMLabel.LABEL_SMALL);
	}

	public int getSelectedSize() {
		return pivotValue;
	}

}
