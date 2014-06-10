package wm.test.view;

import java.awt.Dimension;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JFrame;

import wm.config.UI_Constants;
import wm.controller.*;
import wm.view.*;

/**
 * This is a Test case for eye watching of UI.
 * @author Sidney Fan
 *
 */

public class ViewFrameTest {
	
	static JFrame frame;

	private static RecordView view;
	private static IRecordController controller;
	
	public static void main(String args[]){
		frame = new JFrame();
		Dimension fixedDimension = new Dimension(UI_Constants.GLOBAL_WIDTH,
				UI_Constants.GLOBAL_HEIGHT+20);
		frame.setSize(fixedDimension);
		frame.setResizable(false);
		frame.setBackground(UI_Constants.NORMALGREEN);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		List<String> dictist = new ArrayList<String>();
		dictist.add("Dictionary A");
		dictist.add("Dictionary B");
		dictist.add("Dictionary C");
		dictist.add("Dictionary D");
		dictist.add("Dictionary E");
		dictist.add("Dictionary F");
		dictist.add("Dictionary G");
		
		view = new RecordView(controller);
		view.setDictist(dictist);
		view.setSizeText(376);
		view.setReciteSizeText(123);
		view.setCorrectText(111);
		view.setCorrectPercentage(0.86);
		view.setWrongText(12);
		view.showPiePanel();
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(view);
		frame.repaint();
		frame.validate();
		
	}
	
}
