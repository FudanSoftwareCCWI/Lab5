/**
 * Software Engineer lab4
 */
package wm.model.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import wm.model.Dictionaries;
import wm.model.Dictionary;
import wm.model.Word;

public class DictionaryImpl implements DictionaryDAO {
	private final static int DICNUMBER = 26;

	@Override
	public Dictionaries selectAllDictionay(String filename) {
		List<Dictionary> dic = new ArrayList<Dictionary>();
		List<List<Word>> tempWords = new ArrayList<List<Word>>();
		for (int i = 0; i < DICNUMBER; i++) {
			tempWords.add(new ArrayList<Word>());
		}
		int index = 0;

		try {
			File allDic = new File("material/" + filename);
			File logfile = new File("material/log.txt");
			File latestRicite = new File("material/latestRicite.txt");
			String[] entry;
			String[] flag;
			String[] latestEntry;
			String d = "";
			String log = "";
			String latest = "";
			boolean recited = false;
			boolean correct = false;
			@SuppressWarnings("resource")
			BufferedReader dicReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(allDic), "UTF-8"));

			if (!logfile.exists()) {
				createLogfile(filename);
			}
			if (!latestRicite.exists()) {
				createLatestFile();
			}

			@SuppressWarnings("resource")
			BufferedReader logReader = new BufferedReader(new FileReader(
					logfile));

			while ((d = dicReader.readLine()) != null
					&& (log = logReader.readLine()) != null) {

				entry = d.split("\\s+");
				flag = log.split("\t");
				if (flag[1].equals("1")) {
					recited = true;
				} else {
					recited = false;
				}

				if (flag[2].equals("1")) {
					correct = true;
				} else {
					correct = false;
				}

				index = entry[0].charAt(0) - 'a';

				tempWords.get(index).add(
						new Word(entry[0], entry[1], recited, correct));
			}

			@SuppressWarnings("resource")
			BufferedReader latestReader = new BufferedReader(new FileReader(
					latestRicite));
			int i = 0;
			while ((latest = latestReader.readLine()) != null) {
				latestEntry = latest.split("\t");
				String first = latestEntry[0].substring(0, 1);
				int present = Integer.parseInt(latestEntry[1]);

				Dictionary tempD = new Dictionary("Dictionary "
						+ first.toUpperCase(), tempWords.get(i++), present);
				dic.add(tempD);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Dictionaries(dic);
	}

	@Override
	public boolean updateAllDictionary(Dictionaries dictionaries) {
		Dictionary dic;
		int wordSize;

		String record = "";

		try {
			File logfile = new File("material/log.txt");
			if (!logfile.exists()) {
				logfile.createNewFile();
			}
			PrintWriter logWriter = new PrintWriter(logfile);
			for (int i = 0; i < dictionaries.getDicNumber(); i++) {
				dic = dictionaries.getDictionary(i);
				wordSize = dic.getSize();
				for (int j = 0; j < wordSize; j++) {
					record = dic.getWordEntry(j);
					logWriter.append(record + "\n");
				}
			}
			logWriter.close();
			return true;
		} catch (Exception e) {
			e.getStackTrace();
		}

		return false;
	}

	@Override
	public Dictionary selectDictionary(String name) {
		return null;
	}

	@Override
	public boolean insertDictionary(Dictionary dictionary) {
		return false;
	}

	@Override
	public boolean updateDictionary(Dictionary dictionary) {
		String prefix = dictionary.getKey(0).substring(0, 1);
		String logline = "";
		String latestline = "";
		String record = "";
		int k = 0;
		try {
			File logfile = new File("material/log.txt");
			File newLogfile = new File("material/newlog.txt");
			File latestfile = new File("material/latestRicite.txt");
			File newLatestfile = new File("material/newLatestRicite.txt");
			if (!newLogfile.exists()) {
				newLogfile.createNewFile();
			}
			if (!newLatestfile.exists()) {
				newLatestfile.createNewFile();
			}
			String[] entry;
			PrintWriter logWriter = new PrintWriter(newLogfile);
			PrintWriter latestWriter = new PrintWriter(newLatestfile);
			@SuppressWarnings("resource")
			BufferedReader logReader = new BufferedReader(new FileReader(
					logfile));
			@SuppressWarnings("resource")
			BufferedReader latestReader = new BufferedReader(new FileReader(
					latestfile));
			while ((logline = logReader.readLine()) != null) {
				entry = logline.split("\t");

				if (entry[0].substring(0, 1).equals(prefix)) {
					record = dictionary.getWordEntry(k);
					logWriter.append(record + "\n");
					k++;
				} else {
					logWriter.append(logline + "\n");
				}
			}
			logWriter.close();
			while ((latestline = latestReader.readLine()) != null) {
				entry = latestline.split("\t");

				if (entry[0].substring(0, 1).equals(prefix)) {
					int present = dictionary.getPresentWord();
					latestWriter.append(prefix + "\t" + present + "\n");
				} else {
					latestWriter.append(latestline + "\n");
				}
			}
			latestWriter.close();
			logfile.delete();
			newLogfile.renameTo(new File("material/log.txt"));
			latestfile.delete();
			newLatestfile.renameTo(new File("material/latestRicite.txt"));
			return true;

		} catch (Exception e) {
			e.getStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteDictionary(String name) {
		return false;
	}

	private void createLogfile(String filename) {
		String dicEntry = "";
		String first = "";

		try {
			File dictionary = new File("material/" + filename);
			File logfile = new File("material/log.txt");
			if (!logfile.exists()) {
				logfile.createNewFile();
			}
			PrintWriter logWriter = new PrintWriter(logfile);
			@SuppressWarnings("resource")
			BufferedReader dicReader = new BufferedReader(new FileReader(
					dictionary));
			while ((dicEntry = dicReader.readLine()) != null) {
				first = dicEntry.substring(0, 1);
				logWriter.append(first + "\t" + 0 + "\t" + 0 + "\n");
			}
			logWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void createLatestFile() {
		try {
			File latestRicited = new File("material/latestRicite.txt");
			if (!latestRicited.exists()) {
				latestRicited.createNewFile();
			}
			PrintWriter latestwriter = new PrintWriter(latestRicited);
			for (int i = 0; i < DICNUMBER; i++) {
				char first = (char) ('a' + i);
				latestwriter.append(first + "\t-1\n");
			}
			latestwriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
