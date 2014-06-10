/**
 * Software Engineer lab4
 */
package wm;


/**
 * Interface SwitchDelegate manages the switches between different controllers.
 * 
 * @author Maggie He
 * 
 */
public interface SwitchDelegate {
	/**
	 * 
	 */
	public void getHome();

	/**
	 * 
	 */
	public void getReciteMain();

	/**
	 * 
	 * @param index
	 */
	public void getStartWordSelect(int index);

	/**
	 * 
	 * @param model
	 */
	public void getStartWordDefine();

	/**
	 * 
	 * @param model
	 */
	public void getSizeSelect();

	/**
	 * 
	 * @param model
	 */
	public void getReciteWord();

	/**
	 * 
	 * @param model
	 */
	public void getReciteRecord();

	/**
	 * 
	 */
	public void getRecord();
}
