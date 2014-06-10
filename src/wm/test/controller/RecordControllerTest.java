/**
 * Software Engineer lab4
 */
package wm.test.controller;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import wm.SwitchDelegate;
import wm.controller.IRecordController;
import wm.controller.RecordController;
import wm.model.IRecords;
import wm.model.Record;
import wm.model.Records;
import wm.view.WMView;

/**
 * @author Maggie He
 *
 */
public class RecordControllerTest {
	private Mockery context;
	private IRecordController controller;
	private SwitchDelegate delegate;
	private IRecords model;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
		context=new Mockery();
		model=new Records(new ArrayList<Record>(), new Record("all", 0, 0, 0));
		delegate=context.mock(SwitchDelegate.class);
		controller=new RecordController(delegate, model);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link wm.controller.RecordController#showRecordByPie(int)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testShowRecordByPieInt() {
		
		controller.showRecordByBar(-1);
	}

	/**
	 * Test method for {@link wm.controller.RecordController#showRecordByBar(int)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testShowRecordByBarInt() {
		controller.showRecordByBar(-1);
	}

	/**
	 * Test method for {@link wm.controller.RecordController#showRecordByTable(int)}.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testShowRecordByTable() {
		controller.showRecordByTable(-1);
	}

	/**
	 * Test method for {@link wm.controller.RecordController#switchToHome()}.
	 */
	@Test
	public void testSwitchToHome() {
		context.checking(new Expectations(){
			{
				oneOf(delegate).getHome();
			}
		});
		controller.switchToHome();
	}

	/**
	 * Test method for {@link wm.controller.RecordController#getView()}.
	 */
	@Test
	public void testGetView() {
		WMView view=controller.getView();
		assertTrue(view!=null);
	}

}
