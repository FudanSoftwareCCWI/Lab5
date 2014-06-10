package wm.test.view;

import static org.junit.Assert.fail;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import wm.controller.IReciteProcessController;
import wm.view.IStartSelectView;
import wm.view.StartSelectView;
import wm.view.StartSelectView.SelectButton;

/**
 * 
 * @author SidneyFan
 * 
 */

public class StartSelectViewTest extends WMViewTestCase {

	IStartSelectView view;
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

		view = new StartSelectView(controller);
		frame.getContentPane().removeAll();
		frame.getContentPane().add((Component) view);
		frame.repaint();
		frame.validate();
	}

	@After
	public void tearDown() throws Exception {
		context = null;
		controller = null;
	}

	/**
	 * Test start by first button.
	 */
	@Test
	public void test_start_by_first() {
		// expect
		context.checking(new Expectations() {
			{
				oneOf(controller).startByFirstWord();
			}
		});
		// get private button
		SelectButton btn = null;

		Field field1 = null;
		try {
			field1 = StartSelectView.class.getDeclaredField("startByFirstBtn");

			field1.setAccessible(true);

			btn = (SelectButton) field1.get(view);
		} catch (SecurityException e) {
			fail("Cannot init");
		} catch (NoSuchFieldException e) {
			fail("Cannot init");
		} catch (IllegalArgumentException e) {
			fail("Cannot init");
		} catch (IllegalAccessException e) {
			fail("Cannot init");
		}
		btn.getMouseListeners()[1].mouseClicked(new MouseEvent(btn,
				MouseEvent.MOUSE_CLICKED, 0, MouseEvent.BUTTON1_DOWN_MASK, 1,
				1, 1, false));
		context.assertIsSatisfied();
	}

	/**
	 * Test start by last button.
	 */
	@Test
	public void test_start_by_last() {
		// expect
		context.checking(new Expectations() {
			{
				oneOf(controller).startByLastTime();
			}
		});
		// get private button
		SelectButton btn = null;

		Field field1 = null;
		try {
			field1 = StartSelectView.class.getDeclaredField("startByLastBtn");

			field1.setAccessible(true);

			btn = (SelectButton) field1.get(view);
		} catch (SecurityException e) {
			fail("Cannot init");
		} catch (NoSuchFieldException e) {
			fail("Cannot init");
		} catch (IllegalArgumentException e) {
			fail("Cannot init");
		} catch (IllegalAccessException e) {
			fail("Cannot init");
		}
		btn.getMouseListeners()[1].mouseClicked(new MouseEvent(btn,
				MouseEvent.MOUSE_CLICKED, 0, MouseEvent.BUTTON1_DOWN_MASK, 1,
				1, 1, false));
		context.assertIsSatisfied();
	}

	/**
	 * Test start by custom button. 
	 */
	@Test
	public void test_start_by_custom() {
		// expect
		context.checking(new Expectations() {
			{
				oneOf(controller).switchToStartWordDefine();
			}
		});
		// get private button
		SelectButton btn = null;

		Field field1 = null;
		try {
			field1 = StartSelectView.class.getDeclaredField("startByCustomBtn");

			field1.setAccessible(true);

			btn = (SelectButton) field1.get(view);
		} catch (SecurityException e) {
			fail("Cannot init");
		} catch (NoSuchFieldException e) {
			fail("Cannot init");
		} catch (IllegalArgumentException e) {
			fail("Cannot init");
		} catch (IllegalAccessException e) {
			fail("Cannot init");
		}
		btn.getMouseListeners()[1].mouseClicked(new MouseEvent(btn,
				MouseEvent.MOUSE_CLICKED, 0, MouseEvent.BUTTON1_DOWN_MASK, 1,
				1, 1, false));
		context.assertIsSatisfied();
	}

}
