package wm.view.component;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * 
 * @author Sidney Fan
 * 
 */
public class WMLabel extends JLabel implements Cloneable {
	
	/* Label */
	public final static int LABEL_TINY = 15;
	public final static int LABEL_SMALL = 20;
	public final static int LABEL_MIDDLE = 30;
	public final static int LABEL_NORMAL = 40;
	public final static int LABEL_LARGE = 70;
	public final static int UNITLINEHEIGHT = 20;
	public final static String LABEL_FONT = "微软雅黑";

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -867913135824250509L;

	public WMLabel(String text, int size) {
		this.setText(text);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setFont(new Font(WMLabel.LABEL_FONT, Font.PLAIN, size));
		this.setForeground(Color.WHITE);
	}

	public WMLabel clone() {
		WMLabel o = null;
		try {
			o = (WMLabel) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
}
