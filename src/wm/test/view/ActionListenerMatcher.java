package wm.test.view;

import java.awt.event.ActionListener;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 * This is a analog action matcher.
 * @author SidneyFan
 *
 */

public class ActionListenerMatcher extends BaseMatcher<ActionListener> {

	private ActionListener actionListener;

	public boolean matches(Object item) {
		actionListener = (ActionListener) item;
		return true;
	}

	public void fireActionPerformed() {
		actionListener.actionPerformed(null);
	}

	@Override
	public void describeTo(Description arg0) {
		
	}

}