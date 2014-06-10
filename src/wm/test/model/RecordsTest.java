/**
 * Software Engineer lab4
 */
package wm.test.model;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;

import org.junit.Before;
import org.junit.Test;


import wm.model.IRecords;
import wm.model.Record;


/**
 * @author ArielQian
 * 
 */
public class RecordsTest {
	IRecords recordsmock;
	Mockery context;
	Record r;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		r = new Record("test", 10, 10, 8);

		context = new Mockery();
		recordsmock = context.mock(IRecords.class);

		context.checking(new Expectations() {
			{
				allowing(recordsmock).getSingleRecord(0);
				will(returnValue(r));
			}
		});
		context.checking(new Expectations() {
			{
				allowing(recordsmock).getAllRecord();
				will(returnValue(r));
			}
		});
		context.checking(new Expectations() {
			{
				allowing(recordsmock).getSingleRecord("test");
				will(returnValue(r));
			}
		});
	}

	/**
	 * Test method for {@link wm.model.Records#getSingleRecord(int)}.
	 */
	@Test
	public void testGetSingleRecordInt() {
		assertEquals(r, recordsmock.getSingleRecord(0));
	}

	/**
	 * Test method for
	 * {@link wm.model.Records#getSingleRecord(java.lang.String)}.
	 */
	@Test
	public void testGetSingleRecordString() {
		assertEquals(r, recordsmock.getSingleRecord("test"));
	}

	/**
	 * Test method for {@link wm.model.Records#getAllRecord()}.
	 */
	@Test
	public void testGetAllRecord() {
		assertEquals(r, recordsmock.getAllRecord());
	}

}
