/**
 * Software Engineer lab4
 */
package wm.test.model;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import wm.model.Dictionaries;
import wm.model.Dictionary;
import wm.model.IDictionaries;
import wm.model.dao.DictionaryImpl;

/**
 * @author ArielQian
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DictionaryImplTest {
	Mockery context;
	IDictionaries dicmock;
	DictionaryImpl reader;
	Dictionaries dic;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		reader = new DictionaryImpl();
		dic = reader.selectAllDictionay("dictionary.txt");
		context = new Mockery();
		dicmock = context.mock(IDictionaries.class);
		
		context.checking(new Expectations(){
			{
				allowing(dicmock).getTotalSize();
				will(returnValue(1));
			}
		});
		context.checking(new Expectations(){
			{
				allowing(dicmock).getDicNumber();
				will(returnValue(1));
			}
		});

		
	}

	/**
	 * Test method for {@link wm.model.dao.DictionaryImpl#selectAllDictionay(java.lang.String)}.
	 */
	@Test
	public void testSelectAllDictionay() {		
		Dictionary dictionary = dic.getDictionary(0);
		
		assertEquals(1, dicmock.getDicNumber());
		
		assertEquals("abandon", dictionary.getKey(0));
		dictionary.setPresentWord(0);
		dictionary.setWordRecited();
		dictionary.setWordCorrect(true);
		
	}

	/**
	 * Test method for {@link wm.model.dao.DictionaryImpl#updateAllDictionary(wm.model.Dictionaries)}.
	 */
	@Test
	public void testUpdateAllDictionary() {
		reader.updateAllDictionary(dic);
		dic = reader.selectAllDictionay("dictionary.txt");

		Dictionary dictionary = dic.getDictionary(0);
		assertNotNull(dictionary);

	}
	
	/**
	 * Test method for {@link wm.model.dao.DictionaryImpl#updateADictionary(wm.model.Dictionary)}.
	 */
	@Test
	public void testUpdateDictionary() {
		Dictionary dictionary = dic.getDictionary(1);
		
		reader.updateDictionary(dictionary);
	}

}
