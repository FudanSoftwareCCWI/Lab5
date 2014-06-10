/**
 * Software Engineer lab4
 */
package wm.model;

/**
 * Class Word represents a word. It has an English key and Chinese meaning. Some
 * statistic information is also included in class Word, such as recited and
 * correctness. It provides get and set methods to manage the word. record.
 * It implements {@link IWord}
 * 
 * @author Ariel Qian
 * 
 */
public class Word implements IWord{
	private String key;
	private String meaning;
	private boolean recited;
	private boolean correct;

	/**
	 * The constructor. It needs 4 parameters, including key, meaning, if the
	 * word is recited and if the word is correctly answered.
	 * 
	 * @param key
	 *            The english expression of the word
	 * @param meaning
	 *            The chinese meaning of the word
	 * @param recited
	 *            If the word is recited, it will be {@code true}, else it will
	 *            be {@code false}
	 * @param correct
	 *            If the user answer the meaning correct, it will be
	 *            {@code true}, else it will be {@code false};
	 */
	public Word(String key, String meaning, boolean recited, boolean correct) {
		super();
		this.key = key;
		this.meaning = meaning;
		this.recited = recited;
		this.correct = correct;
	}

	@Override
	public boolean isRecited() {
		return recited;
	}

	@Override
	public void setRecited(boolean recited) {
		this.recited = recited;
	}

	@Override
	public boolean isCorrect() {
		return correct;
	}

	@Override
	public void setCorrect(boolean correct) {
		if(!this.correct)
			this.correct = correct;
	}

	@Override
	public String getKey() {
		return key;
	}


	@Override
	public String getMeaning() {
		return meaning;
	}
	
	public String toString(){
		String c = "";
		String r = "";
		if (recited)
			r = "1";
		else
			r = "0";

		if (correct)
			c = "1";
		else
			c = "0";
		return key.substring(0,1) + "\t" + r + "\t" + c;
	}

}
