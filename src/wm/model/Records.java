/**
 * Software Engineer lab4
 */
package wm.model;

import java.util.List;

/**
 * Class Records contains the record of the whole dictionary. And the list of
 * the record of each dictionary. It implements the {@link IRcords}
 * 
 * @author ArielQian
 * 
 */
public class Records extends WMModel implements IRecords {
	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -434440532466459449L;
	private Record allRecord;
	private List<Record> records;

	public Records(List<Record> records, Record allRecord) {
		super();
		this.records = records;
		this.allRecord = allRecord;
	}

	@Override
	public List<Record> getRecords() {

		return records;
	}

	@Override
	public Record getSingleRecord(int index) {
		if (index < 0) {
			throw new IllegalArgumentException();
		}
		return records.get(index);
	}

	@Override
	public Record getSingleRecord(String name) {
		for (Record r : records) {
			if (r.getName().equals(name)) {
				return r;
			}
		}
		return null;
	}

	@Override
	public Record getAllRecord() {
		return allRecord;
	}
}
