/**
 * Software Engineer lab4
 */
package wm.model;

/**
 * Interface Word represents a word. It provides get and set methods to manage
 * the word. 
 * 
 * @author Ariel Qian
 * 
 */
public interface IWord {
	
	/**
	 * The get function of {@code recited}.
	 * 
	 * @return {@code recited}
	 */
	public boolean isRecited();
	
	/**
	 * The set function of {@code recited}.
	 * 
	 * @param recited
	 */
	public void setRecited(boolean recited);
	
	/**
	 * The get function of {@code correct}.
	 * 
	 * @return {@code correct}
	 */
	public boolean isCorrect();
	
	/**
	 * The set function of {@code correct}.
	 * 
	 * @param correct
	 */
	public void setCorrect(boolean correct);
	
	
	/**
	 * The get function of {@code key}.
	 * 
	 * @return {@code key}
	 */
	public String getKey();
	
	/**
	 * The get function of {@code meaning}.
	 * 
	 * @return {@code meaning}
	 */
	public String getMeaning();
	
	
}
