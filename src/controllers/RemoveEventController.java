package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import core.Controller;
import models.SchedulerIO;
import views.RemoveEventView;

public class RemoveEventController extends Controller
{
    private RemoveEventView removeEventView;
    private EventListController eventListController;
    private JTable table;

    public RemoveEventController(EventListController eventListController)
    {
        this.eventListController = eventListController;
    }

    @Override
    public void run()
    {
        table = new JTable(getDataColumns(), getNameColumns()) {
            @Override
            public Class<?> getColumnClass(int column)
            {
                if (column == 5) return Boolean.class;
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column)
            {
                return column == 5;
            }
        };

        removeEventView = new RemoveEventView(this, table);
    }

    public Vector<String> getNameColumns()
    {
        Vector<String> nameColumns = new Vector<>();
        nameColumns.add("Date");
        nameColumns.add("Description");
        nameColumns.add("Frequency");
        nameColumns.add("E-mail");
        nameColumns.add("Alarm");
        nameColumns.add("Boolean");
        return nameColumns;
    }

    public Vector<Vector<Object>> getDataColumns()
    {
        Vector<Vector<Object>> dataColumns = new Vector<>();

        try {
            SchedulerIO schedulerIO = new SchedulerIO();
            Vector<Vector<Object>> events = schedulerIO.getEvents();

            for (Vector<Object> row : events) {
                Vector<Object> newRow = new Vector<>(row);
                newRow.add(false);
                dataColumns.add(newRow);
            }
        } catch (Exception ex) { }

        return dataColumns;
    }

    public void selectAll()
    {
        for (int i = 0; i < table.getRowCount(); i++) {
            table.setValueAt(true, i, 5);
        }
    }

    public void removeSelectedEvents()
    {
        List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < table.getRowCount(); i++) {
            Boolean checked = (Boolean) table.getValueAt(i, 5);
            if (checked != null && checked) {
                indexes.add(i);
            }
        }

        try {
            SchedulerIO schedulerIO = new SchedulerIO();
            schedulerIO.removeEvents(indexes);
        } catch (Exception ex) { }

        reloadTable();
        eventListController.reloadTable();
    }

    public void reloadTable()
    {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        Vector<Vector<Object>> rows = getDataColumns();
        for (Vector<Object> row : rows) {
            model.addRow(row);
        }
    }

    public RemoveEventView getView()
    {
        return removeEventView;
    }
}