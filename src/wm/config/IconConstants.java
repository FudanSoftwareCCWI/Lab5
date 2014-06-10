package wm.config;

import java.awt.Image;

import javax.swing.ImageIcon;

public class IconConstants {
	/* Icon */
	public final static int ICON_TINY = 32;
	public final static int ICON_SMALL = 64;
	public final static int ICON_RECORD = 96;
	public final static int ICON_MIDDLE = 200;
	public final static int ICON_LARGE = 512;
	public final static int ICON_BORDER = 10;
	public final static ImageIcon QUITICON = new ImageIcon("icon/quit-32.png");
	public final static ImageIcon HOMEICON = new ImageIcon("icon/home-32.png");
	public final static ImageIcon NEXTICON = new ImageIcon("icon/next-32.png");
	public final static ImageIcon PIEICON96 = new ImageIcon(new ImageIcon(
			"icon/pie-128.png").getImage().getScaledInstance(ICON_RECORD,
			ICON_RECORD, Image.SCALE_DEFAULT));
	public final static ImageIcon BARICON96 = new ImageIcon(new ImageIcon(
			"icon/bar-128.png").getImage().getScaledInstance(ICON_RECORD,
			ICON_RECORD, Image.SCALE_DEFAULT));
	public final static ImageIcon TABLEICON96 = new ImageIcon(new ImageIcon(
			"icon/table-128.png").getImage().getScaledInstance(ICON_RECORD,
			ICON_RECORD, Image.SCALE_DEFAULT));
	public final static ImageIcon PIEICON = new ImageIcon("icon/pie-128.png");
	public final static ImageIcon PIEICON256 = new ImageIcon(new ImageIcon(
			"icon/pie-512.png").getImage().getScaledInstance(ICON_MIDDLE,
			ICON_MIDDLE, Image.SCALE_DEFAULT));
}
