/**
 * 
 */
package wm.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import wm.SwitchDelegate;
import wm.model.Dictionary;
import wm.model.IDictionary;
import wm.model.Record;
import wm.model.dao.DictionaryDAO;
import wm.model.dao.DictionaryImpl;
import wm.view.IReciteRecordView;
import wm.view.IReciteWordView;
import wm.view.ISizeSelectView;
import wm.view.IStartSelectView;
import wm.view.IStartWordDefineView;
import wm.view.ReciteRecordView;
import wm.view.ReciteWordView;
import wm.view.SizeSelectView;
import wm.view.StartSelectView;
import wm.view.StartWordDefineView;
import wm.view.WMView;

/**
 * ReciteProcessController responsible for reciteProcessView.
 * @author Maggie He
 * 
 */
public class ReciteProcessController implements IReciteProcessController {
	SwitchDelegate delegate;
	IStartSelectView startSelectView;
	IStartWordDefineView startWordDefineView;
	ISizeSelectView sizeSelectView;
	IReciteWordView reciteWordView;
	IReciteRecordView reciteRecordView;
	WMView currentView;
	IDictionary model;

	// recite word control
	private int startWord;
	private int presentWord;
	private int reciteSize;

	public ReciteProcessController(SwitchDelegate delegate, IDictionary model) {
		super();
		this.delegate = delegate;
		this.model = model;
		this.startSelectView = new StartSelectView(this);
		this.currentView = (WMView) startSelectView;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see wm.controller.IReciteProcessController#startByFirstWord()
	 */
	@Override
	public void startByFirstWord() {
		this.startWord=0;
		this.presentWord=this.startWord-1;
		this.switchToSizeSelect();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see wm.controller.IReciteProcessController#startByLastTime()
	 */
	@Override
	public void startByLastTime() {
		this.startWord=model.getPresentWord()+1;
		this.presentWord=this.startWord-1;
		this.switchToSizeSelect();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see wm.controller.IReciteProcessController#startByInput(java.lang.String)
	 */
	@Override
	public void startByInput(String key) {
		this.startWord=model.getWordIndex(key);
		this.presentWord=this.startWord-1;
		this.switchToSizeSelect();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see wm.controller.IReciteProcessController#switchToStartWordDefine()
	 */
	@Override
	public void switchToStartWordDefine() {
		startWordDefineView=new StartWordDefineView(this);
		this.currentView = (WMView) startWordDefineView;
		delegate.getStartWordDefine();
	}
	
	/**
	 * (non-Javadoc)
	 * 
	 * @see wm.controller.IReciteProcessController#switchToSizeSelect()
	 */
	private void switchToSizeSelect() {
		sizeSelectView=new SizeSelectView(this);
		this.currentView = (WMView) sizeSelectView;
		delegate.getSizeSelect();
	}
	
	/**
	 * (non-Javadoc)
	 * 
	 * @see wm.controller.IReciteProcessController#getAvailableWordList(java.lang.String)
	 */
	@Override
	public List<String> getAvailableWordList(String prefix) {
		List<String> words = model.getMatchWords(prefix);
		return words;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see wm.controller.IReciteProcessController#getAvailableSize()
	 */
	@Override
	public int getAvailableSize() {
		return model.calAvailableSize(startWord);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see wm.controller.IReciteProcessController#setReciteSize(int)
	 */
	@Override
	public void setReciteSize(int size) {
		this.reciteSize = size;
		this.switchToReciteWord();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see wm.controller.IReciteProcessController#checkCorrect()
	 */
	@Override
	public void checkCorrect(String input) {
		String key = model.getKey(presentWord);
		if (key.equalsIgnoreCase(input)) {
			model.setWordRecited(this.presentWord);
			model.setWordCorrect(this.presentWord,true);
			reciteWordView.setCorrectInfoText("对");
		} else {
			model.setWordRecited(this.presentWord);
			model.setWordCorrect(this.presentWord,false);
			reciteWordView.setCorrectInfoText("不对");
		}
		//wait for one minute to recite next word
		Timer timer = new Timer(500, null);
		timer.addActionListener(new SecondListener(timer){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				reciteNextWord();
				t.stop();
			}
		});
		timer.start();
	}

	private void reciteNextWord() {
		if (this.presentWord-this.startWord+1 == reciteSize) {// check whether meets the reciteSize 
			this.switchToReciteRecord();
		} else {// else
			presentWord++;
			model.setPresentWord(presentWord);
			reciteWordView.setMeaningText(model.getMeaning(presentWord));
			reciteWordView.emptyInputField();
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see wm.controller.IReciteProcessController#switchToReciteWord()
	 */
	private void switchToReciteWord() {
		reciteWordView = new ReciteWordView(this);
		this.currentView = (WMView) reciteWordView;
		this.reciteNextWord();
		delegate.getReciteWord();//MODIFY NIGHT
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see wm.controller.IReciteProcessController#switchToReciteRecord()
	 */
	private void switchToReciteRecord() {
		reciteRecordView=new ReciteRecordView(this);
		this.currentView = (WMView) reciteRecordView;
		Record record=model.produceRecord(this.startWord,this.presentWord);
		reciteRecordView.setNameText(model.getName());
		reciteRecordView.setRecitedSizeText(this.reciteSize);
		reciteRecordView.setCorrectCountText(record.getCorrect());
		reciteRecordView.setIncorrectCountText(record.getWrong());
		reciteRecordView.setCorrectPrecentageText(record.getCorrectRate());
		reciteRecordView.showTablePanel();
		delegate.getReciteRecord();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see wm.controller.IReciteProcessController#switchToHome()
	 */
	@Override
	public void switchToHome() {
		//when switch to home in these two views, should store the dictionary state
		if(currentView==reciteWordView||currentView==reciteRecordView){
			DictionaryDAO dictionaryDAO=new DictionaryImpl();
			dictionaryDAO.updateDictionary((Dictionary)model);
		}
		delegate.getHome();
	}

	@Override
	public WMView getView() {
		return currentView;
	}

	@Override
	public void closeWindow() {
		//when closing in these two views, should store the dictionary state
		if(currentView==reciteWordView||currentView==reciteRecordView){
			DictionaryDAO dictionaryDAO=new DictionaryImpl();
			dictionaryDAO.updateDictionary((Dictionary)model);
		}
		System.exit(0);
	}
	
	private class SecondListener implements ActionListener{

		Timer t;
		
		SecondListener(Timer timer){
			t = timer;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// nothing
		}
		
	}

}
