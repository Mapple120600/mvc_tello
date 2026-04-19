package controllers;

import javax.swing.JOptionPane;

import core.Controller;
import models.Guest;
import models.GuestIO;
import views.GuestView;

public class GuestController extends Controller
{
    private GuestView guestView;

    @Override
    public void run() 
    {
        guestView = new GuestView(this);
    }

    public void saveGuest(Guest guest)
    {
        try {
            GuestIO guestIO = new GuestIO();
            guestIO.attach(guestView);
            guestIO.saveGuest(guest);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public GuestView getView()
    {
        return guestView;
    }
}