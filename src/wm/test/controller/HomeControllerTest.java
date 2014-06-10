/**
 * Software Engineer lab4
 */
package wm.test.controller;

import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import wm.SwitchDelegate;
import wm.controller.HomeController;
import wm.controller.IHomeController;
import wm.view.WMView;

/**
 * @author Maggie He
 * 
 */
public class HomeControllerTest {
	private Mockery context;
	private SwitchDelegate delegate;
	private IHomeController controller;
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
		context = new Mockery();
		delegate=context.mock(SwitchDelegate.class);
		controller=new HomeController(delegate);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link wm.controller.HomeController#HomeController(wm.SwitchDelegate)}.
	 */
	@Test
	public void testHomeController() {
		assertTrue(controller.getView()!=null);
	}

	/**
	 * Test method for {@link wm.controller.HomeController#switchToRecite()}.
	 */
	@Test
	public void testSwitchToRecite() {
		context.checking(new Expectations(){
			{
				oneOf(delegate).getReciteMain();
			}
		});
		controller.switchToRecite();
	}

	/**
	 * Test method for {@link wm.controller.HomeController#switchToRecord()}.
	 */
	@Test
	public void testSwitchToRecord() {
		context.checking(new Expectations(){
			{
				oneOf(delegate).getRecord();
			}
		});
		controller.switchToRecord();
	}

	/**
	 * Test method for {@link wm.controller.HomeController#getView()}.
	 */
	@Test
	public void testGetView() {
		WMView view=controller.getView();
		assertTrue(view!=null);
	}

}
