package wm.view.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

public class WMTable extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7471855718324608502L;
	private List<Integer> list;
	private List<String> name;

	public WMTable(List<Integer> list, List<String> name) {
		super();
		createTable(list, name);
	}

	public void createTable(List<Integer> list, List<String> name) {
		this.list = list;
		this.name = name;
		this.setOpaque(false);
		this.repaint();
	}

	public void paint(Graphics g) {
		drawTable((Graphics2D) g, list, name);
	}

	void drawTable(Graphics2D g, List<Integer> list, List<String> name) {

		Rectangle rec = this.getBounds();

		g.setColor(Color.WHITE);
		g.drawLine(0, 0, rec.width, 0);
		g.drawLine(0, rec.height/2, rec.width, rec.height/2);
		g.drawLine(0, rec.height-1, rec.width, rec.height-1);
		int size = Math.max(list.size(), name.size());
		int unitWidth = rec.width / size;
		Iterator<Integer> il = list.iterator();
		Iterator<String> in = name.iterator();
		int value;
		String text;
		int currPos = 20;
		while (in.hasNext() && il.hasNext()) {
			text = in.next();
			value = il.next();
			g.drawString(text, currPos, rec.height / 3);
			g.drawString(String.valueOf(value), currPos+10, rec.height / 4 * 3);
			currPos += unitWidth;
		}

		super.paint(g);
	}

}
