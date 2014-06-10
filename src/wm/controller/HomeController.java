package wm.controller;

import wm.SwitchDelegate;
import wm.view.HomeView;
import wm.view.IHomeView;
import wm.view.WMView;

/**
 * HomeController responsible for homeView control.
 * @author Sidney Fan
 *
 */
public class HomeController implements IHomeController {
	SwitchDelegate delegate;
	IHomeView view;
	
	
	
	public HomeController(SwitchDelegate delegate) {
		super();
		this.delegate=delegate;
		this.view = new HomeView(this);
	}

	@Override
	public void switchToRecite() {
		delegate.getReciteMain();
	}

	@Override
	public void switchToRecord() {
		delegate.getRecord();
	}

	@Override
	public WMView getView() {
		return (HomeView) view;
	}

	@Override
	public void closeWindow() {
		System.exit(0);
	}

	
}
