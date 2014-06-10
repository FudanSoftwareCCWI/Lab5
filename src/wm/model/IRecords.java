/**
 * Software Engineer lab4
 */
package wm.model;

import java.util.List;

/**
 * Interface Records contains the record of the whole dictionary. And the list of
 * the record of each dictionary.
 * 
 * @author ArielQian
 * 
 */
public interface IRecords {

	/**
	 * Get the record list of each record.
	 * @return {@code List<Record>}
	 */
	public List<Record> getRecords();
	
	/**
	 * Get the record of the whole record.
	 * @return {@code Record}
	 */
	public Record getAllRecord();
	
	/**
	 * Get the single record by the record name
	 * @param name
	 * 			The record name
	 * @return	{@code Record}
	 */
	public Record getSingleRecord(String name);
	
	/**
	 * Get the single record by the record name
	 * @param index
	 * 			The record index in the list
	 * @return	{@code Record}
	 */
	public Record getSingleRecord(int index);
}
