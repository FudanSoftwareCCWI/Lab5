package wm.model.dao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import wm.model.Word;

public class DictionaryHandler extends DefaultHandler {
	private List<Word> words = null;
	private Word word = null;
	private String preTag = null;
	private String chinese = null;
	private String english = null;

	public List<Word> getWords(InputStream xmlStream) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		DictionaryHandler handler = new DictionaryHandler();
		parser.parse(xmlStream, handler);
		return handler.getWords();
	}

	public List<Word> getWords() {
		return words;
	}

	@Override
	public void startDocument() throws SAXException {
		words = new ArrayList<Word>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("word".equals(qName)) {

		}
		preTag = qName;// 将正在解析的节点名称赋给preTag
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("word".equals(qName)) {
			word = new Word(english, chinese, false, false);
			words.add(word);
			word = null;
			chinese = null;
			english = null;
		}
		preTag = null;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (preTag != null) {
			String content = new String(ch, start, length);
			if ("chinese".equals(preTag)) {
				chinese = content;
			} else if ("english".equals(preTag)) {
				english = content;
			}
		}
	}
}
