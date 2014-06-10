/**
 * Software Engineer lab4
 */
package wm.model.dao;

import java.io.IOException;

import wm.model.Dictionaries;
import wm.model.Dictionary;

/**
 * Interface DictionaryDAO provides methods to visit a dictionary, including
 * select, update, insert and delete operations.
 * 
 * @author Ariel Qian
 * 
 */
public interface DictionaryDAO {
	/**
	 * 
	 * @return
	 * @throws IOException 
	 */
	public Dictionaries selectAllDictionay(String filename);
	
	/**
	 * 
	 * @param dictionaries
	 * @return
	 */
	public boolean updateAllDictionary(Dictionaries dictionaries);
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Dictionary selectDictionary(String name);
	
	/**
	 * 
	 * @param dictionary
	 * @return
	 */
	public boolean insertDictionary(Dictionary dictionary);
	
	/**
	 * 
	 * @param dictionary
	 * @return
	 */
	public boolean updateDictionary(Dictionary dictionary);
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean deleteDictionary(String name);
}
