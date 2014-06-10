package wm.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import wm.config.UI_Constants;
import wm.config.IconConstants;
import wm.config.ScriptConstants;
import wm.controller.IReciteProcessController;
import wm.view.component.WMLabel;

/**
 * Class StartSelectView represents three ways to start recite, the user can
 * choose what he want and go to next step.
 * 
 * @author Sidney Fan
 * 
 */
public class StartSelectView extends ReciteProcessView implements IStartSelectView{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4531368146481645750L;

	/* Components */
	/* Button */
	private SelectButton startByFirstBtn;
	private SelectButton startByLastBtn;
	private SelectButton startByCustomBtn;

	/**
	 * <b>StartSelectView</b>
	 * 
	 * <pre>
	 * <code>public StartSelectView(IReciteProcessController controller)</code>
	 * </pre>
	 * 
	 * <blockquote>
	 * <p>
	 * Constructor of home view.
	 * </p>
	 * 
	 * @param controller
	 *            -controller of home view
	 * 
	 */
	public StartSelectView(IReciteProcessController controller) {
		super(controller);
		addComponents();
		addListener();
	}

	protected void addComponents() {
		// selector
		startByFirstBtn = new SelectButton(ScriptConstants.CHIN_STAETBYFIRST);
		startByLastBtn = new SelectButton(ScriptConstants.CHIN_STARTBYLAST);
		startByCustomBtn = new SelectButton(ScriptConstants.CHIN_STARTBYCUSTOM);
		
		centerPanel.add(startByFirstBtn);
		centerPanel.add(startByLastBtn);
		centerPanel.add(startByCustomBtn);
		
		startByFirstBtn.setBounds(PADDING, UI_Constants.UNITHEIGHT + PADDING,
				IconConstants.ICON_MIDDLE, IconConstants.ICON_MIDDLE);
		startByLastBtn.setBounds(
				(UI_Constants.GLOBAL_WIDTH - IconConstants.ICON_MIDDLE) / 2,
				UI_Constants.UNITHEIGHT + PADDING, IconConstants.ICON_MIDDLE,
				IconConstants.ICON_MIDDLE);
		startByCustomBtn.setBounds(UI_Constants.GLOBAL_WIDTH
				- IconConstants.ICON_MIDDLE - PADDING, UI_Constants.UNITHEIGHT
				+ PADDING, IconConstants.ICON_MIDDLE, IconConstants.ICON_MIDDLE);
		
		setTip(ScriptConstants.CHIN_SELECTSTARTMETHOD);

	}

	protected void addListener() {
		
		startByFirstBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.startByFirstWord();
				
			}
		});
		
		startByLastBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.startByLastTime();
			}

		});
		startByCustomBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.switchToStartWordDefine();
			}

		});
	}

	public class SelectButton extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7507522887537228688L;
		private boolean hover;
		private WMLabel textLabel;
		
		public SelectButton(String text) {
			super();
			textLabel = new WMLabel(text,WMLabel.LABEL_SMALL);
			textLabel.setForeground(UI_Constants.NORMALGREEN);
			this.setBackground(UI_Constants.LIGHTGREEN);
			this.setLayout(null);
			this.add(textLabel);
			textLabel.setBounds(0, 0, IconConstants.ICON_MIDDLE, IconConstants.ICON_MIDDLE);
			hover = false;
			setMouseAction();
		}

		private void setMouseAction() {
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseExited(MouseEvent arg0) {
					SelectButton.this.setForeground(UI_Constants.NORMALGREEN);
					hover = false;
					textLabel.setForeground(UI_Constants.NORMALGREEN);
					repaint();
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					SelectButton.this.setForeground(Color.WHITE);
					hover = true;
					textLabel.setForeground(Color.WHITE);
					repaint();
				}
			});
			
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (hover) {
				g.setColor(UI_Constants.NORMALGREEN);
				g.fillArc(0, 0, IconConstants.ICON_MIDDLE, IconConstants.ICON_MIDDLE, 0,
						360);
				g.setColor(UI_Constants.DARKGREEN);
				g.fillArc(IconConstants.ICON_BORDER, IconConstants.ICON_BORDER,
						IconConstants.ICON_MIDDLE - 2 * IconConstants.ICON_BORDER,
						IconConstants.ICON_MIDDLE - 2 * IconConstants.ICON_BORDER, 0, 360);
	        } else {
	        	g.setColor(UI_Constants.NORMALGREEN);
				g.fillArc(0, 0, IconConstants.ICON_MIDDLE, IconConstants.ICON_MIDDLE, 0,
						360);
				g.setColor(Color.WHITE);
				g.fillArc(IconConstants.ICON_BORDER, IconConstants.ICON_BORDER,
						IconConstants.ICON_MIDDLE - 2 * IconConstants.ICON_BORDER,
						IconConstants.ICON_MIDDLE - 2 * IconConstants.ICON_BORDER, 0, 360);
	        }
			
			
		}
	}

}
