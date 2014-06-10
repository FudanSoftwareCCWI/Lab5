package wm.view;

import java.util.List;

/**
 * Interface IReciteMainView represents a recite main view, which shows a list
 * of all dictionaries and allows user to select one to recite. It is managed by
 * a recite main controller. It is the beginning view of the recite.
 * 
 * @author Sidney Fan
 * 
 */
public interface IReciteMainView {
	/**
	 * <b>setListPanelContent</b>
	 * 
	 * <pre>
	 * <code>public void <b>setListPanelContent</b>(List&lt;<em>String</em>&gt; nameList)</code>
	 * </pre>
	 * 
	 * <blockquote> Set the content of the dictionary list panel. <br>
	 * <br>
	 * 
	 * @param nameList
	 *            -The name list of all dictionaries, such as "Dictionary A" or
	 *            "Dictionary B". </blockquote>
	 */
	public void setListPanelContent(List<String> nameList);

	/**
	 * Set the text of the name label the name of the selected dictionary.
	 * 
	 * @param name
	 *            The name of the dictionary
	 */
	public void setNameLabelText(String name);

	/**
	 * Set the pie icon according the size of the selected dictionary and total
	 * size of all dictionaries.
	 * 
	 * @param size
	 *            The size of the selected dictionary
	 * @param totalSize
	 *            The total size of all dictionaries
	 */
	public void setPieIcon(int size, int totalSize);

	/**
	 * Set the text of the size label.
	 * 
	 * @param size
	 *            The size of the selected dictionary
	 */
	public void setSizeLabelText(int size);

	/**
	 * Set the text of the total size label.
	 * 
	 * @param size
	 *            The total size of all dictionaries
	 */
	public void setTotalSizeLabelText(int totalSize);

	/**
	 * Returns current clicked dictionary's index. Should be called when switch
	 * to recite process view.
	 * 
	 * @return currentDictIndex
	 */
	public int getCurrentDictIndex();

	/**
	 * Set the current dictionary. This method will paint the current index
	 * block simultaneously.
	 * 
	 * @param currentDictIndex
	 */
	public void setCurrentDictIndex(int currentDictIndex);
}
