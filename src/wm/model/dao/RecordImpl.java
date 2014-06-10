/**
 * Software Engineer lab4
 */
package wm.model.dao;

import java.util.ArrayList;
import java.util.List;

import wm.model.Dictionaries;
import wm.model.Record;
import wm.model.Records;

public class RecordImpl implements RecordDAO {

	@Override
	public Records selectAllRecord(Dictionaries dic) {	
		List<Record> r = new ArrayList<Record>();
		int totalSize = 0;
		int recitedSize = 0;
		int correct = 0;
		for(int i = 0; i < dic.getDicNumber(); i++){
			Record record = dic.getDictionary(i).produceRecord();
			r.add(record);
			totalSize += record.getTotalSize();
			recitedSize += record.getRecitedSize();
			correct += record.getCorrect();
		}
		Record all = new Record("All Record", totalSize, recitedSize, correct);
		return new Records(r, all);
	}

	@Override
	public void updateAllRecord(Dictionaries dic) {
		
	}

	
}
