/**
 * Software Engineer lab4
 */
package wm.controller;

import wm.view.WMView;

/**
 * Interface WMController represents a concept of controllers.
 * 
 * @author Maggie He
 * 
 */
public interface WMController {
	public WMView getView();
	public void closeWindow();
}
