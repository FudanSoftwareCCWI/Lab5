package wm.test.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Component;
import java.lang.reflect.Field;

import javax.swing.JTextField;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import wm.controller.IReciteProcessController;
import wm.view.IReciteWordView;
import wm.view.ReciteWordView;

import wm.view.component.WMLabel;

/**
 * 
 * @author SidneyFan
 * 
 */

public class ReciteWordViewTest extends WMViewTestCase {

	IReciteWordView view;
	IReciteProcessController controller;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		setUpFrame();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		context = new Mockery();
		controller = context.mock(IReciteProcessController.class);
		context.checking(new Expectations() {
			{
				allowing(controller).checkCorrect(with(any(String.class)));
			}
		});
		view = new ReciteWordView(controller);
		frame.getContentPane().removeAll();
		frame.getContentPane().add((Component) view);
		frame.repaint();
		frame.validate();
	}

	@After
	public void tearDown() throws Exception {
	}


	/**
	 * test setCorrectInfoText() function.
	 */
	@Test
	public void test_setCorrectInfoText() {
		// expect
		String info = "正确";
		// actual
		String currInfo = "";
		// action
		view.setCorrectInfoText(info);

		Field field = null;
		try {
			field = ReciteWordView.class.getDeclaredField("resultHint");
			field.setAccessible(true);
			currInfo = ((WMLabel) (field.get(view))).getText();
		} catch (SecurityException e) {
			fail("Cannot init");
		} catch (NoSuchFieldException e) {
			fail("Cannot init");
		} catch (IllegalArgumentException e) {
			fail("Cannot init");
		} catch (IllegalAccessException e) {
			fail("Cannot init");
		}

		assertEquals(info, currInfo);
	}

	/**
	 * test setMeaningText() function.
	 */
	@Test
	public void test_setMeaningText() {
		// expect
		String info = "中文意思";
		// actual
		String currInfo = "";
		// action
		view.setMeaningText(info);

		Field field = null;
		try {
			field = ReciteWordView.class.getDeclaredField("paraphrase");
			field.setAccessible(true);
			currInfo = ((WMLabel) (field.get(view))).getText();
		} catch (SecurityException e) {
			fail("Cannot init");
		} catch (NoSuchFieldException e) {
			fail("Cannot init");
		} catch (IllegalArgumentException e) {
			fail("Cannot init");
		} catch (IllegalAccessException e) {
			fail("Cannot init");
		}

		assertEquals(info, currInfo);
	}

	/**
	 * test emptyInputField() function.
	 */
	@Test
	public void test_emptyInputField() {
		// expect
		String info1 = "";
		String info2 = "";
		// actual
		String currInfo1 = "NotEmpty";
		String currInfo2 = "NotEmpty";
		// action
		view.emptyInputField();

		Field field1 = null;
		Field field2 = null;
		try {
			field1 = ReciteWordView.class.getDeclaredField("inputField");
			field1.setAccessible(true);
			currInfo1 = ((JTextField) (field1.get(view))).getText();
			field2 = ReciteWordView.class.getDeclaredField("resultHint");
			field2.setAccessible(true);
			currInfo2 = ((WMLabel) (field2.get(view))).getText();
		} catch (SecurityException e) {
			fail("Cannot init");
		} catch (NoSuchFieldException e) {
			fail("Cannot init");
		} catch (IllegalArgumentException e) {
			fail("Cannot init");
		} catch (IllegalAccessException e) {
			fail("Cannot init");
		}

		assertEquals(info1, currInfo1);
		assertEquals(info2, currInfo2);
	}

}
