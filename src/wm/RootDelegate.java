/**
 * Software Engineer lab4
 */
package wm;

import wm.controller.HomeController;
import wm.controller.ReciteMainController;
import wm.controller.ReciteProcessController;
import wm.controller.RecordController;
import wm.controller.WMController;
import wm.model.Dictionaries;
import wm.model.dao.DictionaryDAO;
import wm.model.dao.DictionaryImpl;
import wm.model.dao.RecordDAO;
import wm.model.dao.RecordImpl;
import wm.view.RootWindow;

/**
 * Class RootDelegate manages the whole application. It implements
 * SwitchDelegate to switch controllers.
 * 
 * @author Maggie He
 * 
 */
public class RootDelegate implements SwitchDelegate {
	private RootWindow rootWindow;
	private WMController currentController;
	private Dictionaries preLoadModel;

	public RootDelegate() {
		super();
		this.rootWindow = new RootWindow();
		this.preLoadModel = preload();
	}

	public void start(){
		rootWindow.start();
		this.getHome();
	}
	
	
	/**
	 * 
	 * @return
	 */
	private Dictionaries preload() {
		DictionaryDAO dictionaryDAO=new DictionaryImpl();
		return dictionaryDAO.selectAllDictionay("dictionary.xml");
	}

	@Override
	public void getHome() {
		currentController=new HomeController(this);
		rootWindow.showView(currentController.getView());
	}

	@Override
	public void getReciteMain() {
		currentController=new ReciteMainController(this,this.preLoadModel);
		rootWindow.showView(currentController.getView());
	}

	@Override
	public void getRecord() {
		RecordDAO recordDAO=new RecordImpl();
		currentController=new RecordController(this, recordDAO.selectAllRecord(preLoadModel));
		rootWindow.showView(currentController.getView());
	}

	@Override
	public void getStartWordSelect(int index) {
		currentController=new ReciteProcessController(this, preLoadModel.getDictionary(index));
		rootWindow.showView(currentController.getView());
	}

	@Override
	public void getStartWordDefine() {
		rootWindow.showView(currentController.getView());
	}

	@Override
	public void getSizeSelect() {
		rootWindow.showView(currentController.getView());
	}

	@Override
	public void getReciteWord() {
		rootWindow.showView(currentController.getView());
	}

	@Override
	public void getReciteRecord() {
		rootWindow.showView(currentController.getView());
	}


}
