package wm.test.view;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import wm.controller.IReciteMainController;
import wm.view.IReciteMainView;
import wm.view.ReciteMainView;
import wm.view.component.WMLabel;


/**
 * 
 * @author SidneyFan
 * 
 */

public class ReciteMainViewTest extends WMViewTestCase {

	private static IReciteMainView reciteMainView;
	private static IReciteMainController controller;

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
		controller = context.mock(IReciteMainController.class);
		reciteMainView = new ReciteMainView((IReciteMainController) controller);
		reciteMainView.setListPanelContent(getNameList());
		frame.getContentPane().removeAll();
		frame.getContentPane().add((Component) reciteMainView);
		frame.repaint();
		frame.validate();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test setPieIcon() function.
	 */
	@Test
	public void test_setPieIcon() {
		// expect
		int recited = 237;
		int total = 2000;
		// actual
		int currRecited = 0;
		int currTotal = 0;
		// action
		reciteMainView.setPieIcon(recited, total);

		Field fieldrecite = null;
		Field fieldtotal = null;
		try {
			fieldrecite = ReciteMainView.class
					.getDeclaredField("dictSizeLabel");
			fieldtotal = ReciteMainView.class.getDeclaredField("totalNumLabel");

			fieldrecite.setAccessible(true);
			fieldtotal.setAccessible(true);

			currRecited = Integer.parseInt(((WMLabel) (fieldrecite
					.get(reciteMainView))).getText());
			currTotal = Integer.parseInt(((WMLabel) (fieldtotal
					.get(reciteMainView))).getText());
		} catch (SecurityException e) {
			fail("Cannot init");
		} catch (NoSuchFieldException e) {
			fail("Cannot init");
		} catch (IllegalArgumentException e) {
			fail("Cannot init");
		} catch (IllegalAccessException e) {
			fail("Cannot init");
		}
		assertEquals(recited, currRecited);
		assertEquals(total, currTotal);
	}

	/**
	 * Set setCurrentDictIndex() function.
	 */
	@Test
	public void test_setCurrentDictIndex() {
		// expect
		int index = 1;
		// actual
		int currDictIndex = 0;
		// action
		reciteMainView.setCurrentDictIndex(index);

		Field fieldindex = null;
		try {
			fieldindex = ReciteMainView.class
					.getDeclaredField("currentDictIndex");

			fieldindex.setAccessible(true);

			currDictIndex = (Integer) fieldindex.get(reciteMainView);
		} catch (SecurityException e) {
			fail("Cannot init");
		} catch (NoSuchFieldException e) {
			fail("Cannot init");
		} catch (IllegalArgumentException e) {
			fail("Cannot init");
		} catch (IllegalAccessException e) {
			fail("Cannot init");
		}

		assertEquals(index, currDictIndex);
	}

	/**
	 * Test setNameLabelText() function.
	 */
	@Test
	public void test_setNameLabelText() {
		// expect
		String name = "Dictionary A";
		// actual
		String currName = "";
		// action
		reciteMainView.setNameLabelText(name);

		Field fieldname = null;
		try {
			fieldname = ReciteMainView.class
					.getDeclaredField("dictionaryNameLabel");

			fieldname.setAccessible(true);

			currName = ((WMLabel) (fieldname.get(reciteMainView))).getText();
		} catch (SecurityException e) {
			fail("Cannot init");
		} catch (NoSuchFieldException e) {
			fail("Cannot init");
		} catch (IllegalArgumentException e) {
			fail("Cannot init");
		} catch (IllegalAccessException e) {
			fail("Cannot init");
		}

		assertEquals(name, currName);
	}

	/**
	 * Test showDictionaryDetail() function
	 */
	@Test
	public void test_showDictionaryDetail() {
		context.checking(new Expectations() {
			{
				oneOf(controller).showDictionaryDetail(with(any(int.class)));
			}
		});
		// get private WMBlock
		Component cmp = null;
		JPanel list = null;
		Field fieldList = null;
		try {
			fieldList = ReciteMainView.class.getDeclaredField("listPanel");
			fieldList.setAccessible(true);
			list = (JPanel) fieldList.get(reciteMainView);
			cmp = list.getComponents()[1];
		} catch (SecurityException e) {
			fail("Cannot init");
		} catch (NoSuchFieldException e) {
			fail("Cannot init");
		} catch (IllegalArgumentException e) {
			fail("Cannot init");
		} catch (IllegalAccessException e) {
			fail("Cannot init");
		}
		cmp.getMouseListeners()[1].mouseClicked(new MouseEvent(cmp,
				MouseEvent.MOUSE_CLICKED, 0, MouseEvent.NOBUTTON, 10,
				10, 1, false));
		context.assertIsSatisfied();

	}

	private static List<String> getNameList() {
		ArrayList<String> r = new ArrayList<String>();
		for (int i = 0; i < 26; i++) {
			r.add(String.format("Dictionary %c", (char) ('A' + i)));
		}
		return r;
	}
}
