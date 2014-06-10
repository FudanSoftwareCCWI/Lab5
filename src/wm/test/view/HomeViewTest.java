package wm.test.view;

import static org.junit.Assert.fail;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import wm.controller.IHomeController;
import wm.view.HomeView;
import wm.view.IHomeView;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.States;

/**
 * <b>HomeViewTest</b>
 * 
 * <pre>
 * public class <b>HomeViewTest</b> extends {@link WMViewTestCase}
 * </pre>
 * 
 * <blockquote>
 * <p>
 * Tests if mouse click in HomeView invoke corresponding controller. Uses JMock.
 * </p>
 * </blockquote>
 * 
 * @author Sidney Fan
 * 
 */

public class HomeViewTest extends WMViewTestCase {

	private static IHomeView homeView;
	private static IHomeController controller;

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
		controller = context.mock(IHomeController.class);
		homeView = new HomeView(controller);
		frame.getContentPane().removeAll();
		frame.getContentPane().add((Component) homeView);
		frame.repaint();
		frame.validate();
	}

	@After
	public void tearDown() throws Exception {
		controller = null;
		homeView = null;
		context = null;
	}

	/**
	 * Test click recite panel button then home view disappear.
	 */
	@Test
	public void click_recite_and_disappear() {
		JPanel panel = null;
		Field buttonField = null;
		try {
			buttonField = HomeView.class.getDeclaredField("recitePanel");
			buttonField.setAccessible(true);
			panel = (JPanel) buttonField.get(homeView);
		} catch (SecurityException e) {
			fail("Cannot init panel");
		} catch (NoSuchFieldException e) {
			fail("Cannot init panel");
		} catch (IllegalArgumentException e) {
			fail("Cannot init panel");
		} catch (IllegalAccessException e) {
			fail("Cannot init panel");
		}
		final States viewState = context.states("view").startsAs("showing");
		context.checking(new Expectations() {

			{
				oneOf(controller).switchToRecite();
				when(viewState.is("showing"));
				then(viewState.is("disappear"));
			}

		});
		panel.getMouseListeners()[0].mouseClicked(new MouseEvent(panel,
				MouseEvent.MOUSE_CLICKED, 0, MouseEvent.BUTTON1_DOWN_MASK, 1,
				1, 1, false));
		context.assertIsSatisfied();
	}

	/**
	 * Test click record panel button then home view disappear.
	 */
	@Test
	public void click_record_and_disappear() {
		JPanel panel = null;
		Field buttonField = null;
		try {
			buttonField = HomeView.class.getDeclaredField("statisticPanel");
			buttonField.setAccessible(true);
			panel = (JPanel) buttonField.get(homeView);
		} catch (SecurityException e) {
			fail("Cannot init panel");
		} catch (NoSuchFieldException e) {
			fail("Cannot init panel");
		} catch (IllegalArgumentException e) {
			fail("Cannot init panel");
		} catch (IllegalAccessException e) {
			fail("Cannot init panel");
		}
		final States viewState = context.states("view").startsAs("showing");
		context.checking(new Expectations() {

			{
				oneOf(controller).switchToRecord();
				when(viewState.is("showing"));
				then(viewState.is("disappear"));
			}

		});
		panel.getMouseListeners()[0].mouseClicked(new MouseEvent(panel,
				MouseEvent.MOUSE_CLICKED, 0, MouseEvent.BUTTON1_DOWN_MASK, 1,
				1, 1, false));
		context.assertIsSatisfied();
	}

	/**
	 * Test click quit button then home view disappear.
	 */
	@Test
	public void click_close_and_disappear() {
		JButton btn = null;
		Field buttonField = null;
		try {
			buttonField = HomeView.class.getDeclaredField("quitBtn");
			buttonField.setAccessible(true);
			btn = (JButton) buttonField.get(homeView);
		} catch (SecurityException e) {
			fail("Cannot init btn");
		} catch (NoSuchFieldException e) {
			fail("Cannot init btn");
		} catch (IllegalArgumentException e) {
			fail("Cannot init btn");
		} catch (IllegalAccessException e) {
			fail("Cannot init btn");
		}
		final States viewState = context.states("view").startsAs("showing");
		context.checking(new Expectations() {

			{
				oneOf(controller).closeWindow();
				when(viewState.is("showing"));
				then(viewState.is("disappear"));
			}

		});
		btn.getActionListeners()[0].actionPerformed(new ActionEvent(btn,
				ActionEvent.ACTION_PERFORMED, "quitClick"));
		context.assertIsSatisfied();
	}

}
