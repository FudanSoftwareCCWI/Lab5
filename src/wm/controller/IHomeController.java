/**
 * Software Engineer lab4
 */
package wm.controller;

/**
 * Interface IHomeController provides method to manage home view.
 * 
 * @author Maggie He
 * 
 */
public interface IHomeController extends WMController {
	/**
	 * Switch to the recite main view
	 */
	public void switchToRecite();

	/**
	 * Switch to the record view
	 */
	public void switchToRecord();

}
