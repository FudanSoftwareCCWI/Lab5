/**
 * 
 */
package wm.test.view;

import static org.junit.Assert.fail;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.event.CaretEvent;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import wm.controller.IReciteProcessController;
import wm.view.IStartWordDefineView;
import wm.view.StartWordDefineView;

/**
 * @author Sidney Fan
 * 
 */
public class StartWordDefineViewTest extends WMViewTestCase {

	IStartWordDefineView view;
	IReciteProcessController controller;

	static ArrayList<String> result;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		setUpFrame();
		getData();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		context = new Mockery();
		controller = context.mock(IReciteProcessController.class);
		view = new StartWordDefineView(controller);
		frame.getContentPane().removeAll();
		frame.getContentPane().add((Component) view);
		frame.repaint();
		frame.validate();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/*
	 * Test type VL_ENTER.
	 */
	@Test
	public void test_type_enter() {
		// expect
		context.checking(new Expectations() {
			{
				oneOf(controller).getAvailableWordList(with(any(String.class)));
			}
		});
		// get private button
		JTextField field = null;
		Method method;
		try {
			method = StartWordDefineView.class.getDeclaredMethod("getField");
			method.setAccessible(true);
			field = (JTextField)method.invoke(view);
		} catch (SecurityException e) {
			fail("Cannot init");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		field.getCaretListeners()[0].caretUpdate(new CaretEvent(field) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 7466182497566543826L;

			@Override
			public int getMark() {
				return 0;
			}
			
			@Override
			public int getDot() {
				return 0;
			}
		}); 

		context.assertIsSatisfied();
	}

	private static void getData() {
		result = new ArrayList<String>();
		result.add("abandon");
		result.add("abase");
		result.add("abash");
		result.add("abate");
		result.add("abbreviate");
		result.add("abdicate");
	}
}
