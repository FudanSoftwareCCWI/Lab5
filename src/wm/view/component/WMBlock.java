package wm.view.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Sidney Fan
 * 
 */
public class WMBlock extends JPanel {

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -1163413579217397402L;
	private int width;
	private Color localColor;
	private Color pressColor;
	private WMBlock thisWMBlock;
	private int blockState; // 1 is changeable background

	/**
	 * Constructor of WMBlcok.
	 * @param width
	 * @param height
	 * @param row
	 * @param col
	 */
	public WMBlock(int width, int height, int row, int col) {
		this.width = width;
		this.setSize(width, height);
		this.setBorder(null);
		this.setLayout(new GridLayout(row, col));
		thisWMBlock = this;
		blockState = 0;
	}

	public void addLabel(String text, int size) {
		WMLabel label = new WMLabel(text, size);
		label.setSize(width, label.getHeight());
		this.add(label);
	}
	
	public void addLeftLabel(String text, int size) {
		WMLabel label = new WMLabel(text, size);
		label.setSize(width, label.getHeight());
		label.setHorizontalAlignment(JLabel.LEFT);
		this.add(label);
	}

	public void addLabel(WMLabel label) {
		label.setSize(width, label.getHeight());
		this.add(label);
	}
	
	/**
	 * <b>paintLocal</b>
	 * 
	 * <pre>
	 * public void <b>paintLocal</b>()
	 * </pre>
	 * 
	 * <blockquote>
	 * <p>
	 * Set the block background to the origin local color.
	 * <p>
	 * </blockquote>
	 */
	public void paintLocal() {
		if (blockState == 0)
			thisWMBlock.setBackground(thisWMBlock.localColor);
	}

	/**
	 * <b>paintPress</b>
	 * 
	 * <pre>
	 * public void <b>paintPress</b>()
	 * </pre>
	 * 
	 * <blockquote>
	 * <p>
	 * Set the block background to the pressed color.
	 * <p>
	 * </blockquote>
	 */
	public void paintPress() {
		if (blockState == 0)
			thisWMBlock.setBackground(thisWMBlock.pressColor);
	}

	/**
	 * Fix the state of block to unchangeable background.
	 */
	public void fix() {
		blockState = 1;
	}

	/**
	 * Release the state of block to changeable background.
	 */
	public void release() {
		blockState = 0;
	}

	public void setColor(Color localColor, Color pressColor) {
		this.localColor = localColor;
		this.pressColor = pressColor;
		thisWMBlock = this;
		this.setBackground(localColor);

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				paintLocal();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				paintPress();
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

			}
		});

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// g.setColor(DARKGREEN);
		// g.fillPolygon(new int[] { 0, 10, 10, width, width, 10, 10 }, new
		// int[] {
		// height / 2, height / 2 - 10, height, height, 0, 0,
		// height / 2 + 10 }, 7);
	}

}
