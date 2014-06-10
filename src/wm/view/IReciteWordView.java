package wm.view;

/**
 * Interface IReciteWordView present the chinese meaning of the word. The user
 * input the spelling into the input area and get the feedback, then goto next
 * word.
 * 
 * @author Sidney Fan
 * 
 */
public interface IReciteWordView {
	/**
	 * Set the result info text and set visible.
	 * 
	 * @param info
	 */
	public void setCorrectInfoText(String info);

	/**
	 * Set the paraphrase Chinese meaning. Simultaneously hide the correct info
	 * text.
	 * 
	 * @param meaning
	 */
	public void setMeaningText(String meaning);

	/**
	 * Empty the input field.
	 */
	public void emptyInputField();
}
