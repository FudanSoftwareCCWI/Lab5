/**
 * Software Engineer lab4
 */
package wm.test.model;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

import wm.model.Dictionaries;
import wm.model.Records;
import wm.model.dao.DictionaryImpl;
import wm.model.dao.RecordImpl;

/**
 * 
 * @author ArielQian
 * 
 */
public class RecordImplTest {
	RecordImpl recordHolder;
	Dictionaries dic;
	Records r;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		DictionaryImpl reader = new DictionaryImpl();
		RecordImpl recordReader = new RecordImpl();
		dic = reader.selectAllDictionay("dictionary.txt");
		recordHolder = new RecordImpl();
		r = recordReader.selectAllRecord(dic);

	}

	/**
	 * Test method for {@link wm.model.dao.RecordImpl#RecordImpl()}.
	 */
	@Test
	public void testRecordImpl() {
		assertNotNull(recordHolder);
	}

	/**
	 * Test method for
	 * {@link wm.model.dao.RecordImpl#selectAllRecord(Dictionaries dic)}.
	 */
	@Test
	public void testSelectAllRecord() {
		assertEquals(dic.getDictionary(0).produceRecord().toString(), r
				.getSingleRecord(0).toString());
		assertEquals(dic.produceRecord().toString(), r
				.getAllRecord().toString());
		
	}

}
