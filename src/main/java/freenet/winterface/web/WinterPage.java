package freenet.winterface.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

import freenet.winterface.web.nav.AbstractNavCallback;
import freenet.winterface.web.nav.NavCallbackInterface;
import freenet.winterface.web.nav.NavPanel;
import freenet.winterface.web.nav.NavigationContributer;
import freenet.winterface.web.nav.PageNavCallback;

/**
 * Base {@link WebPage} for all other WinterFace {@link Page}s.
 * <p>
 * This {@link Page} contains the logo and the navigation menu.<br />
 * This class also contains initial items of navigation menu (see {@link #getMainNav()})
 * </p>
 * 
 * @author pausb
 * @see NakedWinterPage
 */
public abstract class WinterPage extends WebPage implements NavigationContributer {

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = 7945130272222445826L;

	/**
	 * Initial list of navigation items
	 */
	protected static List<NavCallbackInterface> navs = new ArrayList<NavCallbackInterface>();

	static {
		// Add navigation here
		navs.add(new PageNavCallback(TestPage.class, "Menu 1"));
		navs.add(new PageNavCallback(TestPage2.class, "Helloooooow"));
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		// Logo link
		Link<String> logoLink = new Link<String>("logo-link") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(Dashboard.class);
			}
		};
		add(logoLink);

		// Navigation Panel
		LoadableDetachableModel<NavCallbackInterface> navModel = new LoadableDetachableModel<NavCallbackInterface>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected NavCallbackInterface load() {
				return getMainNav();
			}
		};
		add(new NavPanel("navigation", navModel));

		// Footer
		add(new Label("footer", Model.of("FOOTER")));

	}

	/**
	 * Serves as helper method to deliver initial navigation
	 * 
	 * @return {@link NavCallbackInterface} of top menu level
	 */
	public static NavCallbackInterface getMainNav() {
		return new AbstractNavCallback(null) {

			@Override
			public void onClick(Page page) {
				// This level is not shown so simply ignore this listener
			}

			@Override
			public List<NavCallbackInterface> getChilds(Page page) {
				return navs;
			}
		};
	}
	
	@Override
	public List<NavCallbackInterface> getNavigations() {
		return null;
	}
}
