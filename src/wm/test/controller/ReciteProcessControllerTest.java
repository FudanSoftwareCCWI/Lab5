/**
 * Software Engineer lab4
 */
package wm.test.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import wm.SwitchDelegate;
import wm.controller.IReciteProcessController;
import wm.controller.ReciteProcessController;
import wm.model.IDictionary;
import wm.view.WMView;

/**
 * @author Maggie He
 *
 */
public class ReciteProcessControllerTest {
	private Mockery context;
	private IReciteProcessController controller;
	private SwitchDelegate delegate;
	private IDictionary model;
	
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
		delegate=context.mock(SwitchDelegate.class);
		model=context.mock(IDictionary.class);
		controller=new ReciteProcessController(delegate, model);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link wm.controller.ReciteProcessController#switchToStartWordDefine()}.
	 */
	@Test
	public void testSwitchToStartWordDefine() {
		context.checking(new Expectations(){
			{
				oneOf(delegate).getStartWordDefine();
			}
		});
		controller.switchToStartWordDefine();
	}

	/**
	 * Test method for {@link wm.controller.ReciteProcessController#getAvailableWordList(java.lang.String)}.
	 */
	@Test
	public void testGetAvailableWordList() {
		context.checking(new Expectations(){
			{
				oneOf(model).getMatchWords("prefix");
			}
		});
		List<String> list=controller.getAvailableWordList("prefix");
		assertTrue(list!=null);
	}

	/**
	 * Test method for {@link wm.controller.ReciteProcessController#switchToHome()}.
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
	 * Test method for {@link wm.controller.ReciteProcessController#getView()}.
	 */
	@Test
	public void testGetView() {
		WMView view=controller.getView();
		assertTrue(view!=null);
	}


}
