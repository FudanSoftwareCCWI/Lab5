package wm.controller;

import wm.SwitchDelegate;
import wm.model.IDictionaries;
import wm.view.IReciteMainView;
import wm.view.ReciteMainView;
import wm.view.WMView;

/**
 * ReciteMainController responsible for reciteMainView.
 * @author Sidney Fan
 *
 */
public class ReciteMainController implements IReciteMainController {
	SwitchDelegate delegate;
	IReciteMainView view;
	IDictionaries model;

	public ReciteMainController(SwitchDelegate delegate, IDictionaries model) {
		super();
		this.delegate = delegate;
		this.view = new ReciteMainView(this);
		this.model = model;
		view.setListPanelContent(model.getDictionaryNames());
		this.showDictionaryDetail(0);
	}

	@Override
	public void showDictionaryDetail(int index) {
		int size = model.getDictionary(index).getSize();
		int totalSize = model.getTotalSize();
		view.setNameLabelText(model.getDictionary(index).getName());
		view.setSizeLabelText(size);
		view.setTotalSizeLabelText(totalSize);
		view.setPieIcon(size, totalSize);
		view.setCurrentDictIndex(index);
	}

	@Override
	public void switchToStartSelect(int index) {
		delegate.getStartWordSelect(index);
	}

	@Override
	public void switchToHome() {
		delegate.getHome();
	}

	@Override
	public WMView getView() {
		return (ReciteMainView) view;
	}

	@Override
	public void closeWindow() {
		System.exit(0);
	}

}
