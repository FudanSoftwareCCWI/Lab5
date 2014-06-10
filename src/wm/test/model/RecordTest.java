/**
 * Software Engineer lab4
 */
package wm.test.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import wm.model.Record;

/**
 * @author ArielQian
 *
 */
public class RecordTest {
	Record record;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		record = new Record("Test Record", 20, 10, 8);
	}


	/**
	 * Test method for {@link wm.model.Record#Record(java.lang.String, int, int, int)}.
	 */
	@Test
	public void testRecord() {
		assertNotNull(record);
	}

	/**
	 * Test method for {@link wm.model.Record#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals("Test Record",record.getName());
	}

	/**
	 * Test method for {@link wm.model.Record#getTotalSize()}.
	 */
	@Test
	public void testGetTotalSize() {
		assertEquals(20, record.getTotalSize());
	}

	/**
	 * Test method for {@link wm.model.Record#getRecitedSize()}.
	 */
	@Test
	public void testGetRecitedSize() {
		assertEquals(10, record.getRecitedSize());
	}

	/**
	 * Test method for {@link wm.model.Record#getCorrect()}.
	 */
	@Test
	public void testGetCorrect() {
		assertEquals(8, record.getCorrect());
	}

	/**
	 * Test method for {@link wm.model.Record#getWrong()}.
	 */
	@Test
	public void testGetWrong() {
		assertEquals(2,record.getWrong());
	}

	/**
	 * Test method for {@link wm.model.Record#getCorrectRate()}.
	 */
	@Test
	public void testGetCorrectRate() {
		assertEquals(0.8, record.getCorrectRate(), 0);
	}

}
