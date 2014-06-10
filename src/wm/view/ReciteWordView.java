package wm.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import wm.config.UI_Constants;
import wm.config.ScriptConstants;
import wm.controller.IReciteProcessController;
import wm.view.component.WMLabel;

/**
 * Class ReciteWordView present the chinese meaning of the word. The user input
 * the spelling into the input area and get the feedback, then goto next word.
 * 
 * @author Sidney Fan
 * 
 */
public class ReciteWordView extends ReciteProcessView implements IReciteWordView{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5402598615372695937L;
	private WMLabel paraphrase;
	private WMLabel resultHint;
	private JTextField inputField;

	public ReciteWordView(IReciteProcessController controller) {
		super(controller);
		addComponents();
		addListener();
	}
	
	@Override
	public void setCorrectInfoText(String info) {
		resultHint.setText(info);
	}

	@Override
	public void setMeaningText(String meaning) {
		paraphrase.setText(meaning);
	}
	
	@Override
	public void emptyInputField(){
		inputField.setText("");
		resultHint.setText("");
		inputField.setEditable(true);
		inputField.repaint();
		resultHint.repaint();
	}

	protected void addComponents() {
		setHeadLineText(ScriptConstants.CHIN_RECITING);
		hideTip();

		paraphrase = new WMLabel("", WMLabel.LABEL_MIDDLE);
		inputField = new JTextField();
		resultHint = new WMLabel("", WMLabel.LABEL_SMALL);

		inputField.setBackground(UI_Constants.LIGHTGREEN);
		inputField.setForeground(Color.WHITE);
		inputField.setBorder(new LineBorder(Color.WHITE, 2));
		inputField.setFont(new Font(WMLabel.LABEL_FONT, Font.PLAIN,
				WMLabel.LABEL_MIDDLE));
		inputField.setHorizontalAlignment(JTextField.CENTER);

		centerPanel.add(paraphrase);
		centerPanel.add(inputField);
		centerPanel.add(resultHint);

		paraphrase.setBounds(0, PADDING, UI_Constants.GLOBAL_WIDTH,
				UI_Constants.UNITHEIGHT);
		inputField.setBounds(UI_Constants.GLOBAL_WIDTH / 2
				- (int) (UI_Constants.UNITHEIGHT * 1.5), UI_Constants.UNITHEIGHT
				+ PADDING, 3 * UI_Constants.UNITHEIGHT, WMLabel.LABEL_LARGE);
		resultHint.setBounds(0, 3 * UI_Constants.UNITHEIGHT,
				UI_Constants.GLOBAL_WIDTH, UI_Constants.UNITHEIGHT);

	}

	protected void addListener() {
		inputField.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent evt) {
				if(evt.getKeyChar() == KeyEvent.VK_ENTER){
					inputField.setEditable(false);
					controller.checkCorrect(inputField.getText());
				}
			}
		});
	}

}
