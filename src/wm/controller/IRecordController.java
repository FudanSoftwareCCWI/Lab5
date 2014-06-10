/**
 * Software Engineer lab4
 */
package wm.controller;

/**
 * Interface IRecordController provides methods to manage records.
 * 
 * @author Maggie He
 * 
 */
public interface IRecordController extends WMController {
	public void showDictList();
	/**
	 * 
	 * @param index
	 */
	public void showRecordByPie(int index);
	
	/**
	 * show all dictionaries record
	 */
	public void showRecordByPie();
	
	/**
	 * show all dictionaries record
	 */
	public void showRecordByBar();
	
	/**
	 * show all dictionaries record
	 * @param index
	 */
	void showRecordByBar(int index);
	
	/**
	 * 
	 * @param index
	 */
	public void showRecordByTable(int index);
	
	/**
	 * 
	 */
	public void switchToHome();
	
}
