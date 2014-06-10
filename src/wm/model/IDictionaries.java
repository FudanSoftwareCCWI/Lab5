/**
 * Software Engineer lab4
 */
package wm.model;

import java.util.List;


/**
 * Interface IDictionaries represents all available dictionaries. It provides users
 * methods to get a particular dictionary and fetch total recite record.
 * 
 * @author Ariel Qian
 * 
 */
public interface IDictionaries {
	
	/**
	 * Get the dictionary by the index
	 * 
	 * @param index
	 *            The index of the dictionary
	 * @return {@code dictionary}
	 */
	public Dictionary getDictionary(int index);
	
	/**
	 * Get the dictionary by the name
	 * 
	 * @param name
	 *            The name of the dictionary
	 * @return {@code dictionary}
	 */
	public Dictionary getDictionary(String name);
	
	/**
	 * Get all dictionaries' name
	 * 
	 * @return a {@code List<String>} of the dictionaries' name
	 */
	public List<String> getDictionaryNames();
	
	/**
	 * Get the number of the dictionaries
	 * 
	 * @return {@code int}
	 */
	public int getDicNumber();
	
	/**
	 * Get the total size of the dictionaries
	 * 
	 * @return {@code int}
	 */
	public int getTotalSize();
	
	/**
	 * Get a Record of all dictionaries.
	 * 
	 * @return {@code Record}
	 */
	public Record produceRecord();
}
