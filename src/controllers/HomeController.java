package controllers;

import core.Controller;
import views.EventListView;
//import views.HelloWorldView;
import views.HomeView;
import views.NewEventView;
import views.RemoveEventView;
import views.GuestView;

/**
 * Main controller. It will be responsible for program's main screen behavior.
 */
public class HomeController extends Controller
{
	//-----------------------------------------------------------------------
	//		Attributes
	//-----------------------------------------------------------------------
	private HomeView homeView;
	private EventListController eventListController = new EventListController();
	private NewEventController newEventController = new NewEventController(eventListController);
	private RemoveEventController removeEventController = new RemoveEventController(eventListController);
	private GuestController guestController = new GuestController();

	//-----------------------------------------------------------------------
	//		Methods
	//-----------------------------------------------------------------------
	@Override
	public void run()
	{
		// Initializes others controllers
		eventListController.run();
		newEventController.run();
		removeEventController.run();
		guestController.run();

		// Initializes HomeView
		homeView = new HomeView(this, mainFrame);
		addView("HomeView", homeView);

		// Displays the program window
		mainFrame.setVisible(true);
	}


	//-----------------------------------------------------------------------
	//		Getters
	//-----------------------------------------------------------------------
	public EventListView getEventListView()
	{
		return eventListController.getView();
	}

	public NewEventView getNewEventView()
	{
		return newEventController.getView();
	}
	
	public RemoveEventView getRemoveEventView()
    {
        return removeEventController.getView();
    }

	public GuestView getGuestView()
    {
        return guestController.getView();
    }


}
