/**
 * Software Engineer lab4
 */
package wm.view;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import wm.config.UI_Constants;
import wm.config.IconConstants;
import wm.controller.IHomeController;
import wm.view.component.WMLabel;

/**
 * Class HomeView represents a home view and is managed by a home view
 * controller. It is the beginning view of the application.
 * 
 * @author Sidney Fan
 * 
 */
public class HomeView extends WMView implements IHomeView{

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = 113232243106457859L;

	private IHomeController controller;

	private WMLabel logoLabel;
	private WMLabel statisticLabel;
	private WMLabel reciteLabel;
	private WMLabel statisticNote;
	private WMLabel reciteNote;

	private JPanel selectPanel;
	private JPanel statisticPanel;
	private JPanel recitePanel;

	private JButton quitBtn; // should be extracted

	/**
	 * <b>HomeView</b>
	 * 
	 * <pre>
	 * <code>public HomeView(IHomeController controller)</code>
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
	public HomeView(IHomeController controller) {
		super();
		this.setBackground(UI_Constants.NORMALGREEN);
		this.controller = controller;
		initComponents();
		initListener();
	}
	
	@Override
	public void refreshHome() {
		statisticPanel.setBackground(UI_Constants.LIGHTGREEN);
		recitePanel.setBackground(UI_Constants.NORMALGREEN);
	}

	@Override
	protected void initComponents() {
		// init labels
		logoLabel = new WMLabel(UI_Constants.APPNAME, WMLabel.LABEL_LARGE);
		statisticLabel = new WMLabel("统计", WMLabel.LABEL_MIDDLE);
		reciteLabel = new WMLabel("选词书", WMLabel.LABEL_MIDDLE);
		statisticNote = new WMLabel("包含每一个单元和整个词库的统计信息", WMLabel.LABEL_TINY);
		reciteNote = new WMLabel("点此进入单元的选择", WMLabel.LABEL_TINY);
		quitBtn = new JButton(IconConstants.QUITICON);

		// set note color and alignment
		statisticNote.setForeground(UI_Constants.NOTEALPHA);
		reciteNote.setForeground(UI_Constants.NOTEALPHA);
		statisticLabel.setVerticalAlignment(JLabel.BOTTOM);
		reciteLabel.setVerticalAlignment(JLabel.BOTTOM);
		quitBtn.setBorderPainted(false);
		quitBtn.setBackground(UI_Constants.NORMALGREEN);

		// select panel
		selectPanel = new JPanel();
		selectPanel.setLayout(null);
		selectPanel.setOpaque(false);
		// left
		statisticPanel = new JPanel();
		statisticPanel.setLayout(new GridLayout(2, 1));
		statisticPanel.setOpaque(false);
		// right
		recitePanel = new JPanel();
		recitePanel.setLayout(new GridLayout(2, 1));
		recitePanel.setOpaque(false);

		// add left & right to select
		selectPanel.add(statisticPanel);
		statisticPanel.setBounds(0, 0, UI_Constants.GLOBAL_WIDTH / 2,
				UI_Constants.GLOBAL_HEIGHT / 3);
		selectPanel.add(recitePanel);
		recitePanel.setBounds(UI_Constants.GLOBAL_WIDTH / 2, 0,
				UI_Constants.GLOBAL_WIDTH / 2, UI_Constants.GLOBAL_HEIGHT / 3);

		// left add static
		statisticPanel.add(statisticLabel);
		statisticPanel.add(statisticNote);

		// right add recite
		recitePanel.add(reciteLabel);
		recitePanel.add(reciteNote);

		// add all to frame
		this.setLayout(null);
		this.add(logoLabel);
		logoLabel.setBounds(0, 20, UI_Constants.GLOBAL_WIDTH,
				UI_Constants.GLOBAL_HEIGHT / 2);
		this.add(selectPanel);
		selectPanel.setBounds(0, UI_Constants.GLOBAL_HEIGHT / 2,
				UI_Constants.GLOBAL_WIDTH, UI_Constants.GLOBAL_HEIGHT / 2);
		this.add(quitBtn);
		quitBtn.setBounds(UI_Constants.GLOBAL_WIDTH - IconConstants.ICON_SMALL - 10,
				10, IconConstants.ICON_SMALL, IconConstants.ICON_SMALL);
	}

	@Override
	protected void initListener() {

		// Mouse Listener
		statisticPanel.addMouseListener(new MouseListener() {
			boolean pressed = false;
			boolean in = false;

			@Override
			public void mouseEntered(MouseEvent e) {
				statisticPanel.setOpaque(true);
				statisticPanel.setBackground(UI_Constants.DARKGREEN);
				in = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!pressed) {
					statisticPanel.setOpaque(false);
					statisticPanel.setBackground(UI_Constants.LIGHTGREEN);
				}
				in = false;
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				statisticPanel.setOpaque(true);
				statisticPanel.setBackground(UI_Constants.DARKGREEN);
				pressed = false;
				// fire property changed
//				System.out.println("HomeView: Fire homeToStatisticViewCommand");
//				thisView.firePropertyChange("homeToStatisticViewCommand", null,	null);
				controller.switchToRecord();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				statisticPanel.setOpaque(true);
				statisticPanel.setBackground(UI_Constants.DARKGREEN.darker());
				pressed = true;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				statisticPanel.setOpaque(true);
				if (!in) {
					statisticPanel.setBackground(UI_Constants.LIGHTGREEN);
				} else {
					statisticPanel.setBackground(UI_Constants.DARKGREEN);
				}
				pressed = false;
			}
		});

		recitePanel.addMouseListener(new MouseListener() {
			boolean pressed = false;
			boolean in = false;

			@Override
			public void mouseEntered(MouseEvent e) {
				recitePanel.setOpaque(true);
				recitePanel.setBackground(UI_Constants.DARKGREEN);
				in = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!pressed) {
					recitePanel.setOpaque(false);
					recitePanel.setBackground(UI_Constants.NORMALGREEN);
				}
				in = false;
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				recitePanel.setOpaque(true);
				recitePanel.setBackground(UI_Constants.DARKGREEN);
				pressed = false;
				// fire property changed
//				System.out.println("HomeView: Fire homeToUnitViewCommand");
//				thisView.firePropertyChange("homeToUnitViewCommand", null, null);
				controller.switchToRecite();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				recitePanel.setOpaque(true);
				recitePanel.setBackground(UI_Constants.DARKGREEN.darker());
				pressed = true;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				recitePanel.setOpaque(true);
				if (!in) {
					recitePanel.setBackground(UI_Constants.NORMALGREEN);
				} else {
					recitePanel.setBackground(UI_Constants.DARKGREEN);
				}
				pressed = false;
			}
		});

		quitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.closeWindow();
			}

		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(UI_Constants.LIGHTGREEN);
		g.fillRect(0, 0, UI_Constants.GLOBAL_WIDTH / 2, UI_Constants.GLOBAL_HEIGHT);
		g.setColor(UI_Constants.NORMALGREEN);
		g.fillRect(UI_Constants.GLOBAL_WIDTH / 2, 0, UI_Constants.GLOBAL_WIDTH / 2,
				UI_Constants.GLOBAL_HEIGHT);
		g.fillPolygon(new int[] { 0, 0, UI_Constants.GLOBAL_WIDTH / 8 },
				new int[] { 0, UI_Constants.GLOBAL_WIDTH / 8, 0 }, 3);
		g.setColor(UI_Constants.LIGHTGREEN);
		g.fillPolygon(new int[] { UI_Constants.GLOBAL_WIDTH,
				UI_Constants.GLOBAL_WIDTH,
				UI_Constants.GLOBAL_WIDTH - UI_Constants.GLOBAL_WIDTH / 8 },
				new int[] { UI_Constants.GLOBAL_HEIGHT,
						UI_Constants.GLOBAL_HEIGHT - UI_Constants.GLOBAL_WIDTH / 8,
						UI_Constants.GLOBAL_HEIGHT }, 3);
	}

}
