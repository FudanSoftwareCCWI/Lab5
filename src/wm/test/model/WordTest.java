/**
 * Software Engineer lab4
 */
package wm.test.model;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import wm.model.Word;

/**
 * @author ArielQian
 * 
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WordTest {
	Word word;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		word = new Word("abandon", "放弃", false, false);
	}

	
	/**
	 * Test method for {@link wm.model.Word#isRecited()}.
	 */
	@Test
	public void testIsRecited() {
		word.setRecited(true);;
		assertEquals(true, word.isRecited());
	}

	/**
	 * Test method for {@link wm.model.Word#setRecited(boolean)}.
	 */
	@Test
	public void testSetRecited() {
		word.setRecited(false);
		
		assertEquals(false, word.isRecited());
	}

	/**
	 * Test method for {@link wm.model.Word#isCorrect()}.
	 */
	@Test
	public void testIsCorrect() {
		word.setCorrect(false);
		assertEquals(false, word.isCorrect());
	}
	
	/**
	 * Test method for {@link wm.model.Word#setCorrect(boolean)}.
	 */
	@Test
	public void testSetCorrectCase1() {
		word.setCorrect(false);
		assertEquals(false, word.isCorrect());
	}

	/**
	 * Test method for {@link wm.model.Word#setCorrect(boolean)}.
	 * Test if set the word correct true and then set it false.
	 * Hope the correct still be true.
	 */
	@Test
	public void testSetCorrectCase2() {
		word.setCorrect(true);
		word.setCorrect(false);
		assertEquals(true, word.isCorrect());
	}

	/**
	 * Test method for {@link wm.model.Word#getKey()}.
	 */
	@Test
	public void testGetKey() {
		assertEquals("abandon", word.getKey());
	}

	/**
	 * Test method for {@link wm.model.Word#getMeaning()}.
	 */
	@Test
	public void testGetMeaning() {
		assertEquals("放弃", word.getMeaning());
	}

}
