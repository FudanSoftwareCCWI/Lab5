package wm.view.component;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class WMButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1361934025073621202L;
	private int blockState;
	private WMButton thisWMButton;
	private Color localColor;
	private Color pressColor;
	
	public WMButton(ImageIcon icon) {
		super(icon);
		blockState = 0;
		thisWMButton = this;
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
			thisWMButton.setBackground(thisWMButton.localColor);
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
			thisWMButton.setBackground(thisWMButton.pressColor);
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
		thisWMButton = this;
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
}
