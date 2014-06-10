package wm.test.view;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.jmock.Mockery;

import wm.config.UI_Constants;

/**
 * <b>WMViewTestCase</b>
 * <pre> public abstract class <b>WMViewTestCase</b> </pre>
 * <blockquote>
 * <p>An abstract class to settle JFrame for all the other test cases.</p>
 * </blockquote>
 * @author Sidney Fan
 *
 */

public abstract class WMViewTestCase {

	protected static JFrame frame;
	protected static Mockery context;
	
	/**
	 * <b>setUpFrame</b>
	 * <pre>protected static void <b>setUpFrame</b>()</pre>
	 * <blockquote>
	 * <p>Setup JFrame to be container of test case views.</p>
	 * </blockquote>
	 * 
	 */
	
	protected static void setUpFrame(){
		frame = new JFrame();
		Dimension fixedDimension = new Dimension(UI_Constants.GLOBAL_WIDTH,
				UI_Constants.GLOBAL_HEIGHT+20);
		frame.setSize(fixedDimension);
		frame.setResizable(false);
		frame.setBackground(UI_Constants.NORMALGREEN);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
