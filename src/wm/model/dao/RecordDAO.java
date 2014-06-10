/**
 * Software Engineer lab4
 */
package wm.model.dao;

import wm.model.Dictionaries;
import wm.model.Records;

/**
 * Do not need to implement yet.
 * 
 * Interface RecordDAO provides methods to visit a record, including select,
 * update, insert and delete operations.
 * 
 * @author Ariel Qian
 * 
 */
public interface RecordDAO {

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Records selectAllRecord(Dictionaries dic);

	/**
	 * 
	 * @param record
	 * @return
	 */
	public void updateAllRecord(Dictionaries dic);

	
	
}
