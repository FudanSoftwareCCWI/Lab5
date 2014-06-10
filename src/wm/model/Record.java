/**
 * Software Engineer lab4
 */
package wm.model;

/**
 * Class Record represents a particular record in the recite process. It
 * provides users statistic information in the recite process, either of one
 * particular dictionary or all dictionaries.It implements the {@link IRecord}
 * 
 * @author Ariel Qian
 * 
 */
public class Record extends WMModel implements IRecord{
	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = 6614689523828436387L;

	private String name;
	private int totalSize;
	private int recitedSize;
	private int correct;

	/**
	 * Create a recited record of a particular dictionary.
	 * 
	 * @param name
	 *            The name of the dictionary
	 * @param totalSize
	 *            The number of words in the dictionary
	 * @param recitedSize
	 *            The number of words recited
	 * @param correct
	 *            The correct number of words recited
	 */
	public Record(String name, int totalSize, int recitedSize, int correct) {
		super();
		this.name = name;
		this.totalSize = totalSize;
		this.recitedSize = recitedSize;
		this.correct = correct;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getTotalSize() {
		return totalSize;
	}

	@Override
	public int getRecitedSize() {
		return recitedSize;
	}

	@Override
	public int getCorrect() {
		return correct;
	}

	@Override
	public int getWrong() {
		return recitedSize - correct;
	}

	@Override
	public double getCorrectRate() {
		if(recitedSize!=0)
			return (double) correct / (double) recitedSize;
		return 0;
	}

	/**
	 * The toString function. Used in the unit test.
	 * 
	 * @return The parametres of the current record.
	 */
	public String toString() {
		return name + " " + totalSize + " " + recitedSize + " " + correct;

	}

}
