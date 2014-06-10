/**
 * Software Engineer lab4
 */
package wm.model;

import java.util.List;


/**
 * Interface Dictionary represents a particular dictionary in the recite process. It
 * provides users several methods to manage a recite process and fetch current
 * recite record.
 * 
 * @author Ariel Qian
 * 
 */
public interface IDictionary {
	
	/**
	 * Get the name of the dictionary.
	 * 
	 * @return The name of the dictionary
	 */
	public String getName();
	
	/**
	 * Get the size of the dictionary.
	 * 
	 * @return The size of the dictionary
	 */
	public int getSize();
	
	/**
	 * Get a list of words in the dictionary that has a particular prefix.
	 * 
	 * @param prefix
	 *            The particular prefix
	 * @return A list of words with this prefix
	 */
	public List<String> getMatchWords(String prefix);
	
	/**
	 * Get the presentWord.
	 * 
	 * @return {@code precentWord}
	 */
	public int getPresentWord();
	
	/**
	 * Get the key by the index.
	 * @param index
	 * 			the index of the word.
	 * @return	{@code String} The key of the word.
	 */
	public String getKey(int index);
	
	/**
	 * Get the meaning by the index.
	 * @param index
	 * 			the index of the word.
	 * @return	{@code String} The meaning of the word.
	 */
	public String getMeaning(int index);
	
	
	/**
	 * Get the word index according to the key
	 * @param key
	 * 			The key of the word
	 * @return	{@code int} The index of the word
	 */
	public int getWordIndex(String key);
	
	/**
	 * Set the index of the present word. Must be called when setStartWord() is
	 * called.
	 * 
	 * @param index
	 *            The index of the present word
	 */
	public void setPresentWord(int index);
	
	/**
	 * Set the present word is already recited
	 * 
	 */
	public void setWordRecited();
	
	/**
	 * Set the present word is already recited
	 * 
	 */
	public void setWordRecited(int index);
	
	/**
	 * Set the present word is correct or not. If the word is already correct, do not modify it.
	 * 
	 * @param correct
	 * 			a boolean show if the word is correct 
	 */
	public void setWordCorrect(boolean correct);
	
	/**
	 * Set the word if it is correct recited.
	 * @param index
	 * 			The index of the word
	 * @param correct
	 * 			The correct boolean
	 */
	public void setWordCorrect(int index,boolean correct);
	
	/**
	 * Calculate the available size.
	 * @param start
	 * 			The start word
	 * @return	{@code int} The available size
	 */
	public int calAvailableSize(int start);
	
	/**
	 * Produce a record for the whole dictionary.
	 * 
	 * @return A record
	 */
	public Record produceRecord();
	
	/**
	 * Produce a record with start and end(included).
	 * 
	 * @param start
	 *            The start index
	 * @param end
	 *            The end index
	 * @return A record
	 */
	public Record produceRecord(int start, int end);
	
	
}
