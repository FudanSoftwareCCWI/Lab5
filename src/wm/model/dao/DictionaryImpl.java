/**
 * Software Engineer lab4
 */
package wm.model.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import wm.model.Dictionaries;
import wm.model.Dictionary;
import wm.model.Word;

public class DictionaryImpl implements DictionaryDAO {
	private int DICNUMBER = 0;

	@Override
	public Dictionaries selectAllDictionay(String filename) {
		List<Dictionary> dic = new ArrayList<Dictionary>();
		List<List<Word>> tempWords = new ArrayList<List<Word>>();
		List<Word> words = new ArrayList<Word>();
		List<String> type = new ArrayList<String>();

		try {
			words = getWords(filename);
			type = getDicNumber(words);

			File logfile = new File("material/log.txt");
			File latestRicite = new File("material/latestRicite.txt");
			String[] flag;
			String[] latestEntry;
			String log = "";
			String latest = "";
			boolean recited = false;
			boolean correct = false;

			if (!logfile.exists()) {
				createLogfile(words);
			}
			if (!latestRicite.exists()) {
				createLatestFile(type);
			}

			for (int i = 0; i < DICNUMBER; i++) {
				tempWords.add(new ArrayList<Word>());
			}

			@SuppressWarnings("resource")
			BufferedReader logReader = new BufferedReader(new FileReader(
					logfile));

			int line = 0;
			while ((log = logReader.readLine()) != null) {
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

				words.get(line).setCorrect(correct);
				words.get(line).setRecited(recited);
				line++;
			}

			for (Word word : words) {
				for (int i = 0; i < type.size(); i++) {
					if (i == 1) {
						if (word.getMeaning().contains(type.get(i)) && !word.getMeaning().contains("adv")) {
							tempWords.get(i).add(word);
						}
					} else {
						if (word.getMeaning().contains(type.get(i))) {
							tempWords.get(i).add(word);
						}
					}
				}
			}

			@SuppressWarnings("resource")
			BufferedReader latestReader = new BufferedReader(new FileReader(
					latestRicite));
			int i = 0;
			while ((latest = latestReader.readLine()) != null) {
				latestEntry = latest.split("\t");
				String first = latestEntry[0];
				int present = Integer.parseInt(latestEntry[1]);

				Dictionary tempD = new Dictionary("Dictionary\t" + first,
						tempWords.get(i++), present);
				dic.add(tempD);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Dictionaries(dic);
	}

	@Override
	public boolean updateAllDictionary(Dictionaries dictionaries) {
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
		String[] prefix = dictionary.getName().split("\t");
		String p = prefix[1];
		String logline = "";
		String latestline = "";
		String record = "";
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
			int index = 0;
			while ((logline = logReader.readLine()) != null) {
				entry = logline.split("\t");
				if (index < dictionary.getSize()) {
					if (entry[0].equals(dictionary.getKey(index))) {
						record = dictionary.getWordEntry(index);
						logWriter.append(dictionary.getKey(index) + "\t"
								+ record + "\n");
						index++;
					} else {
						logWriter.append(logline + "\n");
					}
				}
			}
			logWriter.close();
			while ((latestline = latestReader.readLine()) != null) {
				entry = latestline.split("\t");

				if (entry[0].equals(p)) {
					int present = dictionary.getPresentWord();
					latestWriter.append(p + "\t" + present + "\n");
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

	private void createLogfile(List<Word> words) {
		String first = "";

		try {
			File logfile = new File("material/log.txt");
			if (!logfile.exists()) {
				logfile.createNewFile();
			}
			PrintWriter logWriter = new PrintWriter(logfile);
			for (Word word : words) {
				first = word.getKey();
				logWriter.append(first + "\t" + 0 + "\t" + 0 + "\n");
			}
			logWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void createLatestFile(List<String> type) {
		try {
			File latestRicited = new File("material/latestRicite.txt");
			if (!latestRicited.exists()) {
				latestRicited.createNewFile();
			}
			PrintWriter latestwriter = new PrintWriter(latestRicited);
			for (String first : type) {
				if (!first.equals(""))
					latestwriter.append(first + "\t-1\n");
			}
			latestwriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<Word> getWords(String filename) throws Exception {
		DictionaryHandler sax = new DictionaryHandler();
		InputStream allDic = new FileInputStream("material/" + filename);
		List<Word> words = sax.getWords(allDic);
		return words;
	}

	private List<String> getDicNumber(List<Word> words) {
		Set<String> type = new HashSet<String>();
		List<String> result = new ArrayList<String>();
		String[] temp;
		for (Word word : words) {
			System.out.println(word.getMeaning());
			temp = word.getMeaning().split("\\.");
			type.add(temp[0]);
		}
		DICNUMBER = type.size() - 1;
		for (String s : type) {
			if (!s.equals("")) {
				result.add(s);
			}
		}
		return result;
	}

//	public static void main(String[] args) throws Throwable {
//		DictionaryImpl d = new DictionaryImpl();
//		d.selectAllDictionay("dictionary.xml");
//	}

}
