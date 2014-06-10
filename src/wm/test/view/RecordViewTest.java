package wm.test.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Component;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import wm.controller.IRecordController;
import wm.view.IRecordView;
import wm.view.RecordView;


/**
 * 
 * @author SidneyFan
 * 
 */

public class RecordViewTest extends WMViewTestCase {

	private static IRecordView view;
	private static IRecordController controller;
	List<String> dictist;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		setUpFrame();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dictist = new ArrayList<String>();
		dictist.add("Dictionary A");
		dictist.add("Dictionary B");
		dictist.add("Dictionary C");
		dictist.add("Dictionary D");
		dictist.add("Dictionary E");
		dictist.add("Dictionary F");
		dictist.add("Dictionary G");

		context = new Mockery();
		controller = context.mock(IRecordController.class);

		context.checking(new Expectations() {

			{
				allowing(controller).showRecordByPie(with(any(int.class)));
			}

		});
		view = new RecordView(controller);
		frame.getContentPane().removeAll();
		frame.getContentPane().add((Component) view);
		frame.repaint();
		frame.validate();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test: view.setSizeText(); view.setReciteSizeText();
	 * view.setCorrectText(); view.setWrongText(); view.setCorrectPercentage();
	 */
	@Test
	public void test_setInfo() {
		// expect
		int total = 2000;
		int recited = 237;
		int correct = 200;
		int wrong = 37;
		// actual
		int currtotal = 0;
		int currrecited = 0;
		int currcorrect = 0;
		int currwrong = 0;
		// action
		view.setSizeText(total);
		view.setReciteSizeText(recited);
		view.setCorrectText(correct);
		view.setWrongText(wrong);

		Field field1 = null;
		Field field2 = null;
		Field field3 = null;
		Field field4 = null;
		try {
			field1 = RecordView.class.getDeclaredField("dictSizeValue");
			field2 = RecordView.class.getDeclaredField("dictRecitedValue");
			field3 = RecordView.class.getDeclaredField("dictCorrectValue");
			field4 = RecordView.class.getDeclaredField("dictWrongValue");

			field1.setAccessible(true);
			field2.setAccessible(true);
			field3.setAccessible(true);
			field4.setAccessible(true);

			currtotal = (Integer) field1.get(view);
			currrecited = (Integer) field2.get(view);
			currcorrect = (Integer) field3.get(view);
			currwrong = (Integer) field4.get(view);
		} catch (SecurityException e) {
			fail("Cannot init");
		} catch (NoSuchFieldException e) {
			fail("Cannot init");
		} catch (IllegalArgumentException e) {
			fail("Cannot init");
		} catch (IllegalAccessException e) {
			fail("Cannot init");
		}

		assertEquals(total, currtotal);
		assertEquals(recited, currrecited);
		assertEquals(correct, currcorrect);
		assertEquals(wrong, currwrong);
	}
	
	/**
	 * Test: setPieCorrectIcon(); setPieRecitedIcon();
	 */
	@Test
	public void testBar() {
		// action
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(23);
		a.add(42);
		a.add(345);
		a.add(23);
		a.add(54);
		a.add(324);
		a.add(123);
		a.add(222);
		view.setBarCorrectIcon(a);
		view.setBarRecitedIcon(a);
		
		// while(true);
		// watch the bar by eye!
	}
	
	/**
	 * Test: setPieCorrectIcon(); setPieRecitedIcon();
	 */
	@Test
	public void testPie() {
		// action
		view.setPieCorrectIcon(47, 56);
		view.setPieRecitedIcon(56, 373);
		
		// while(true);
		// watch the pie by eye!
	}


}
