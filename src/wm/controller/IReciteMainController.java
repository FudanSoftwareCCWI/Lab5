/**
 * Software Engineer lab4
 */
package wm.controller;


/**
 * <b>IReciteMainController</b>
 * <p>
 * Interface IReciteMainController provides methods to manage a recite main
 * view.
 * </p>
 * IReciteMainController takes charge of views:<br>
 * <ul>
 * <li>{@link wm.view.ReciteMainView}</li>
 * </ul>
 * 
 * @author Maggie He
 * 
 */
public interface IReciteMainController extends WMController {

	/**
	 * <b>showDictionaryDetail</b>
	 * <pre><code>public void <b>showDictionaryDetail</b>(int index)</code></pre>
	 * <blockquote>
	 * Display the details of a dictionary.<br></br>
	 * @param index
	 *            -index of a dictionary
	 * </blockquote>
	 */
	public void showDictionaryDetail(int index);

	/**
	 * 
	 * @param index
	 */
	public void switchToStartSelect(int index);

	/**
	 * <b>switchToHome</b>
	 * <pre><code>public void <b>switchToHome</b>()</code></pre>
	 * <blockquote>
	 * Switch to home view and controller to {@link wm.controller.IHomeController}<br></br>
	 * </blockquote>
	 */
	public void switchToHome();
	
}
