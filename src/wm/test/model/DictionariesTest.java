/**
 * Software Engineer lab4
 */
package wm.test.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import wm.model.Dictionaries;
import wm.model.Dictionary;
import wm.model.IDictionaries;
import wm.model.Record;
import wm.model.Word;

/**
 * @author ArielQian
 *
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DictionariesTest {
	Dictionaries dic;
	IDictionaries dicmock;
	Dictionary dictionary1;
	Dictionary dictionary2;
	Mockery context;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		context = new Mockery();
		dicmock = context.mock(IDictionaries.class);
		
		List<Word> words1 = new ArrayList<Word>();
		List<Word> words2 = new ArrayList<Word>();
		words1.add(new Word("abandon", "抛弃，放弃", false, false));
		words1.add(new Word("abandonment", "放弃", false, false));
		words1.add(new Word("abbreviation", "缩写", false, false));
		words1.add(new Word("abeyance", "缓办，终止", false, false));
		words2.add(new Word("abide", "遵守", false, false));
		words2.add(new Word("ability", "能力", false, false));
		words2.add(new Word("able", "有能力的，能干的", false, false));
		words2.add(new Word("abnormal", "反常的", false, false));
		words2.add(new Word("aboard", "船（车）上", false, false));
		words2.add(new Word("abolish", "废除，取消", false, false));
		
		List<Dictionary> dictionaries = new ArrayList<Dictionary>();
		dictionary1 = new Dictionary("test1", words1);
		dictionary2 = new Dictionary("test2", words2, 4);
		dictionaries.add(dictionary1);
		dictionaries.add(dictionary2);
		dic = new Dictionaries(dictionaries);
	}

	/**
	 * Test method for {@link wm.model.Dictionaries#getDictionary(int)}.
	 */
	@Test
	public void testGetDictionaryInt() {
		context.checking(new Expectations(){
			{
				allowing(dicmock).getDictionary(0);
				will(returnValue(dictionary1));
			}
		});
		assertEquals(dictionary1, dicmock.getDictionary(0));
	}

	/**
	 * Test method for {@link wm.model.Dictionaries#getDictionary(java.lang.String)}.
	 */
	@Test
	public void testGetDictionaryString() {
		context.checking(new Expectations(){
			{
				allowing(dicmock).getDictionary("test2");
				will(returnValue(dictionary1));
			}
		});
		assertEquals(dictionary1, dicmock.getDictionary("test2"));
	}

	/**
	 * Test method for {@link wm.model.Dictionaries#getDictionaryNames()}.
	 */
	@Test
	public void testGetDictionaryNames() {
		List<String> names = dic.getDictionaryNames();
		assertEquals("test1", names.get(0));
		assertEquals("test2", names.get(1));
	}

	/**
	 * Test method for {@link wm.model.Dictionaries#getDicNumber()}.
	 */
	@Test	
	public void testGetTotalSize() {

		assertEquals(2, dic.getDicNumber());
	}

	/**
	 * Test method for {@link wm.model.Dictionaries#produceRecord()}.
	 */
	@Test
	public void testProduceRecord() {
		dictionary1.setPresentWord(2);
		dictionary1.setWordRecited();
		dictionary1.setWordCorrect(true);
		dictionary2.setPresentWord(3);
		dictionary2.setWordRecited();
		dictionary2.setWordCorrect(false);
		
		Record record = dic.produceRecord();
		
		assertEquals(10, record.getTotalSize());
		assertEquals(2, record.getRecitedSize());
		assertEquals(1, record.getWrong());
		assertEquals(0.5, record.getCorrectRate(),0);
		
		
	}

}
