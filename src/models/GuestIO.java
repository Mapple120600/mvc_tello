package models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import core.Model;
import core.View;

public class GuestIO implements Model
{
    private static final String DIRECTORY = ".";
    private static final String FILE = "guests.txt";

    private List<View> views = new ArrayList<>();
    private String notice;

    @Override
    public void attach(View view) 
    {
        views.add(view);
    }

    @Override
    public void detach(View view) 
    {
        views.remove(view);
    }

    @Override
    public void notifyViews() 
    {
        for (View v : views) {
            v.update(this, notice);
        }
    }

    public void saveGuest(Guest guest) throws Exception
    {
        try {
            BufferedWriter writer = new BufferedWriter(
                new FileWriter(new File(DIRECTORY, FILE), true)
            );
            writer.write(guest.toString());
            writer.newLine();
            writer.close();

            notice = "Invitado registrado correctamente";
            notifyViews();
        } catch (FileNotFoundException fnfe) {
            notice = "Archivo no encontrado";
            notifyViews();
        } catch (Exception ex) {
            notice = "Error al guardar invitado";
            notifyViews();
        }
    }
}