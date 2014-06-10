/**
 * Software Engineer lab4
 */
package wm.view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import wm.config.UI_Constants;
import wm.config.IconConstants;
import wm.controller.IReciteProcessController;
import wm.view.component.WMLabel;

/**
 * Abstract Class ReciteProcessView represents a recite process view, which
 * shows the recite process. Any views of each step for the recite process
 * should extends this view. It is managed by a recite process view controller.
 * 
 * @author Sidney Fan
 * 
 */
public abstract class ReciteProcessView extends WMView implements IReciteProcessView {

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -5105958811645184995L;

	protected IReciteProcessController controller;
	/* Button */
	protected JButton quitBtn;
	protected JButton homeBtn;
	/* Label */
	protected WMLabel headLine;
	protected WMLabel tipLabel;
	/* Panel */
	protected JPanel navPanel;
	protected JPanel centerPanel;
	/* Padding */
	static int PADDING = 50;

	public ReciteProcessView(IReciteProcessController controller) {
		super();
		this.controller = controller;
		initComponents();
		initListener();
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
		headLine = new WMLabel("Dictionary", WMLabel.LABEL_NORMAL);
		navPanel.setLayout(null);
		navPanel.add(homeBtn);
		navPanel.add(quitBtn);
		navPanel.add(headLine);
		homeBtn.setBounds(0, 0, UI_Constants.UNITHEIGHT, UI_Constants.UNITHEIGHT);
		quitBtn.setBounds(UI_Constants.GLOBAL_WIDTH - UI_Constants.UNITHEIGHT, 0,
				UI_Constants.UNITHEIGHT, UI_Constants.UNITHEIGHT);
		headLine.setBounds(UI_Constants.UNITHEIGHT, 0, UI_Constants.GLOBAL_WIDTH - 2
				* UI_Constants.UNITHEIGHT, UI_Constants.UNITHEIGHT);
		// center
		centerPanel.setLayout(null);
		tipLabel = new WMLabel("", WMLabel.LABEL_SMALL);
		centerPanel.add(tipLabel);
		tipLabel.setBounds(0, PADDING/2, UI_Constants.GLOBAL_WIDTH,
				UI_Constants.UNITHEIGHT);
	}

	/**
	 * Sets the top head line of recite process view.
	 * @param text
	 */
	protected void setHeadLineText(String text){
		headLine.setText(text);
	}
	
	protected String getHeadLineText(){
		return headLine.getText();
	}
	
	protected void setTip(String text){
		tipLabel.setText(text);
	}
	
	protected void hideTip(){
		centerPanel.remove(tipLabel);
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
	}

	public JButton getQuitBtn() {
		return quitBtn;
	}

	public JButton getHomeBtn() {
		return homeBtn;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(UI_Constants.LIGHTGREEN);
		g.fillRect(0, 0, UI_Constants.GLOBAL_WIDTH, UI_Constants.GLOBAL_HEIGHT);
		g.setColor(UI_Constants.NORMALGREEN);
		g.fillRect(0, 0, UI_Constants.GLOBAL_WIDTH, UI_Constants.UNITHEIGHT);
	}

}
