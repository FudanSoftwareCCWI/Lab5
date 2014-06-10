/**
 * Software Engineer lab4
 */
package wm.controller;

import java.util.List;

/**
 * <b>IReciteProcessController</b>
 * <p>
 * Interface IReciteProcessController provides methods to manage a recite
 * process.
 * </p>
 * IReciteMainController takes charge of views:<br>
 * <ul>
 * <li>{@link wm.view.ReciteProcessView}:
 * <ul>
 * <li>{@link wm.view.StartSelectView}</li>
 * <li>{@link wm.view.StartWordDefineView}</li>
 * <li>{@link wm.view.SizeSelectView}</li>
 * </ul>
 * </li>
 * <li>{@link wm.view.ReciteWordView}</li>
 * <li>{@link wm.view.ReciteRecordView}</li>
 * </ul>
 * 
 * @author Maggie He
 * 
 */
public interface IReciteProcessController extends WMController {
	
	/**
	 * <b>switchToStartWordDefine</b>
	 * <pre><code>public void <b>switchToStartWordDefine</b>()</code></pre>
	 * <blockquote>
	 * User selects "StartByFirstWord". Set up recite configuration and invoke {@linkplain #switchToReciteProcess()}. <br></br>
	 * </blockquote>
	 */
	public void switchToStartWordDefine();

	/**
	 * <b>startByFirstWord</b>
	 * <pre><code>public void <b>startByFirstWord</b>()</code></pre>
	 * <blockquote>
	 * User selects "StartByFirstWord". Set up recite configuration and invoke {@linkplain #switchToReciteProcess()}. <br></br>
	 * </blockquote>
	 */
	public void startByFirstWord();

	/**
	 * <b>startByLastTime</b>
	 * <pre><code>public void <b>startByLastTime</b>()</code></pre>
	 * <blockquote>
	 * User selects "startByLastTime". Set up recite configuration and invoke {@linkplain #switchToReciteProcess()}. <br></br>
	 * </blockquote>
	 */
	public void startByLastTime();

	/**
	 * <b>startByInput</b>
	 * <pre><code>public void <b>startByInput</b>(String key)</code></pre>
	 * <blockquote>
	 * User selects "startByInput". Set up recite configuration and invoke {@linkplain #switchToReciteProcess()}. 
	 * <br></br>
	 * @param key
	 * </blockquote>
	 */
	public void startByInput(String key);

	/**
	 * <b>getAvailableWordList</b>
	 * <p>
	 * 
	 * <pre>
	 * <code>public List&lt;<em>String</em>&gt; <b>getAvailableWordList</b>(String prefix)</code>
	 * </pre>
	 * 
	 * <blockquote>
	 * 
	 * <br>
	 * </br>
	 * 
	 * @param prefix
	 *            -prefix of a word
	 * @return list of Strings started by prefix
	 * </blockquote>
	 * 
	 * 
	 *         </p>
	 */
	public List<String> getAvailableWordList(String prefix);

	/**
	 * <b>getAvailableSize</b>
	 * <p>
	 * 
	 * <pre>
	 * <code>public int <b>getAvailableSize</b>()</code>
	 * </pre>
	 * 
	 * <blockquote> Returns available size of current dictionary. <br>
	 * 
	 * @return available size of current dictionary
	 * </blockquote>
	 *         </p>
	 */
	public int getAvailableSize();

	/**
	 * 
	 * @param size
	 */
	public void setReciteSize(int size);


	/**
	 * 
	 */
	public void checkCorrect(String input);


	/**
	 * 
	 */
	public void switchToHome();
}
