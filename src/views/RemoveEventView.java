package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controllers.RemoveEventController;
import core.Model;
import core.View;

@SuppressWarnings("serial")
public class RemoveEventView extends JPanel implements View
{
    private RemoveEventController removeEventController;
    private JTable table;

    public RemoveEventView(RemoveEventController removeEventController, JTable table)
    {
        this.removeEventController = removeEventController;
        this.table = table;

        setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel();

        JButton btnCancel = new JButton("Cancel");
        JButton btnRemove = new JButton("Remover");
        JButton btnSelectAll = new JButton("Seleccionar Todos");

        panelButtons.add(btnCancel);
        panelButtons.add(btnRemove);
        panelButtons.add(btnSelectAll);

        add(panelButtons, BorderLayout.SOUTH);

        btnSelectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEventController.selectAll();
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEventController.removeSelectedEvents();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < table.getRowCount(); i++) {
                    table.setValueAt(false, i, 5);
                }
            }
        });
    }

    @Override
    public void update(Model model, Object data)
    {
        if (data != null) {
            JOptionPane.showMessageDialog(this, data.toString());
        }
    }
}