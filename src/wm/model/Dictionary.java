/**
 * Software Engineer lab4
 */
package wm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Class Dictionary represents a particular dictionary in the recite process. It
 * provides users several methods to manage a recite process and fetch current
 * recite record.It implements {@code IDictionary}
 * 
 * @author Ariel Qian
 * 
 */
public class Dictionary extends Observable implements IDictionary{

	private String name;
	private List<Word> words;
	private int presentWord;

	/**
	 * Create a dictionary with name, words and default presentWord.
	 * 
	 * @param name
	 *            The name of the dictionary
	 * @param words
	 *            The words of the dictionary
	 */
	public Dictionary(String name, List<Word> words) {
		this(name, words, -1);
	}

	/**
	 * Create a dictionary with name, words and presentWord.
	 * 
	 * @param name
	 *            The name of the dictionary
	 * @param words
	 *            The words of the dictionary
	 * @param presentWord
	 *            The index of the present word
	 */
	public Dictionary(String name, List<Word> words, int presentWord) {
		super();
		this.name = name;
		this.words = words;
		this.presentWord = presentWord;
	}


	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getSize() {
		return words.size();
	}

	@Override
	public List<String> getMatchWords(String prefix) {
		List<String> match = new ArrayList<String>();
		for (Word w : words) {
			if (w.getKey().startsWith(prefix))
				match.add(w.getKey());
		}
		return match;
	}

	@Override
	public int getPresentWord() {
		return presentWord;
	}

	@Override
	public String getKey(int index){
		if(index < 0)
			throw new IllegalArgumentException();
		return words.get(index).getKey();
	}
	
	@Override
	public String getMeaning(int index){
		if(index < 0)
			throw new IllegalArgumentException();
		return words.get(index).getMeaning();
	}

	
	/**
	 * Get the word if recited and correct. This function is used to record the
	 * info.
	 * 
	 * @param index
	 *            The index of the word
	 * @return A String: {@code recited} + "\t" + {@code correct}
	 */

	public String getWordEntry(int index) {
		return words.get(index).toString();
	}
	
	
	@Override
	public int getWordIndex(String key) {
		int index = 0;
		for (int i = 0; i < words.size(); i++) {
			if (words.get(i).getKey().equals(key)) {
				index = i;
				break;
			}
		}
		return index;
	}

	@Override
	public void setPresentWord(int index) {
		if(index < 0)
			throw new IllegalArgumentException();
		presentWord = index;
	}

	@Override
	public void setWordRecited() {
		words.get(presentWord).setRecited(true);
	}

	@Override
	public void setWordRecited(int index) {
		words.get(index).setRecited(true);
	}
	
	@Override
	public void setWordCorrect(boolean correct) {
		boolean isCorrect=words.get(presentWord).isCorrect();
		if(!isCorrect)
			words.get(presentWord).setCorrect(correct);
	}
	
	@Override
	public void setWordCorrect(int index,boolean correct) {
		boolean isCorrect=words.get(index).isCorrect();
		if(!isCorrect)
			words.get(index).setCorrect(correct);
	}
	
	@Override
	public int calAvailableSize(int start) {
		return words.size() - start;
	}

	@Override
	public Record produceRecord() {
		return produceRecord(0,getSize()-1);
	}

	@Override
	public Record produceRecord(int start, int end) {
		if(start > end)
			throw new IllegalArgumentException();
		String recordName = name;
		int totalSize = end - start + 1;
		int recitedSize = 0;
		int correctSize = 0;
		Word w = null;
		for (int i = start; i <= end; i++) {
			w = words.get(i);
			if (w.isRecited())
				recitedSize++;
			if (w.isCorrect())
				correctSize++;
		}
		return new Record(recordName, totalSize, recitedSize, correctSize);
	}

}
