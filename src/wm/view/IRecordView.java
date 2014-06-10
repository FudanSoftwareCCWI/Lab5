package wm.view;

import java.util.List;

/**
 * Interface IRecordView represents a record view and is managed by a record
 * view controller. It provides different methods to show the recite record.
 * 
 * @author Sidney Fan
 * 
 */
public interface IRecordView {

	/**
	 * Set dictionary list.
	 * 
	 * @param dictist
	 */
	public void setDictist(List<String> dictist);

	/**
	 * Set dictionary total size.
	 * 
	 * @param size
	 */
	public void setSizeText(int size);

	/**
	 * Set recited size.
	 * 
	 * @param recitedSize
	 */
	public void setReciteSizeText(int recitedSize);

	/**
	 * Set correct size.
	 * 
	 * @param correct
	 */
	public void setCorrectText(int correct);

	/**
	 * Set correct percentage.
	 * 
	 * @param percentage
	 */
	public void setCorrectPercentage(double percentage);

	/**
	 * Set wrong size.
	 * 
	 * @param wrong
	 */
	public void setWrongText(int wrong);

	/**
	 * Set correct pie icon data.
	 * 
	 * @param correct
	 * @param size
	 *            -recited size
	 */
	public void setPieCorrectIcon(int correct, int size);

	/**
	 * Set recited pie icon data.
	 * 
	 * @param recited
	 * @param size
	 *            -total size
	 */
	public void setPieRecitedIcon(int recited, int size);

	/**
	 * Set correct bar data.
	 * 
	 * @param list
	 *            -List of integers of percentage, e.g. {86,72,...}
	 */
	public void setBarCorrectIcon(List<Integer> list);

	/**
	 * Set recietd bar data.
	 * 
	 * @param list
	 *            -List of integers of percentage, e.g. {86,72,...}
	 */
	public void setBarRecitedIcon(List<Integer> list);

	/**
	 * Set head line text.
	 * 
	 * @param text
	 */
	public void setHeadLineText(String text);

	/**
	 * Get the head line text.
	 * 
	 * @return head line string text.
	 */
	public String getHeadLineText();

	public void showTablePanel();

	public void showPiePanel();

	public void showBarPanel();
}
