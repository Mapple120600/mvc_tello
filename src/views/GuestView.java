package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controllers.GuestController;
import core.Model;
import core.View;
import models.Guest;

@SuppressWarnings("serial")
public class GuestView extends JPanel implements View
{
    private GuestController guestController;

    private JTextField tf_name;
    private JTextField tf_cellphone;
    private JTextField tf_address;
    private JCheckBox cb_terms;
    private JRadioButton rb_male;
    private JRadioButton rb_female;
    private JComboBox<String> cb_day;
    private JComboBox<String> cb_month;
    private JComboBox<String> cb_year;

    public GuestView(GuestController guestController)
    {
        this.guestController = guestController;

        setLayout(null);

        makeNameField();
        makeCellphoneField();
        makeGenderField();
        makeBirthDateField();
        makeAddressField();
        makeTermsField();
        makeSaveButton();
        makeCleanButton();
    }

    @Override
    public void update(Model model, Object data)
    {
        if (data != null) {
            JOptionPane.showMessageDialog(this, data.toString());
        }
    }

    private void makeNameField()
    {
        JLabel lbl = new JLabel("Ingrese Nombre:");
        lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl.setBounds(30, 35, 150, 20);
        add(lbl);

        tf_name = new JTextField();
        tf_name.setBounds(200, 35, 200, 25);
        add(tf_name);
    }

    private void makeCellphoneField()
    {
        JLabel lbl = new JLabel("Ingrese número celular:");
        lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl.setBounds(30, 80, 160, 20);
        add(lbl);

        tf_cellphone = new JTextField();
        tf_cellphone.setBounds(200, 80, 200, 25);
        add(tf_cellphone);
    }

    private void makeGenderField()
    {
        JLabel lbl = new JLabel("Género:");
        lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl.setBounds(30, 125, 100, 20);
        add(lbl);

        rb_male = new JRadioButton("Masculino");
        rb_male.setBounds(200, 125, 100, 25);
        rb_male.setSelected(true);
        add(rb_male);

        rb_female = new JRadioButton("Femenino");
        rb_female.setBounds(310, 125, 100, 25);
        add(rb_female);

        ButtonGroup group = new ButtonGroup();
        group.add(rb_male);
        group.add(rb_female);
    }

    private void makeBirthDateField()
    {
        JLabel lbl = new JLabel("Fecha de Nacimiento:");
        lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl.setBounds(30, 170, 150, 20);
        add(lbl);

        String[] days = new String[31];
        for (int i = 0; i < 31; i++) days[i] = String.valueOf(i + 1);

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                           "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        String[] years = new String[60];
        int start = 1980;
        for (int i = 0; i < 60; i++) years[i] = String.valueOf(start + i);

        cb_day = new JComboBox<>(days);
        cb_day.setBounds(200, 170, 60, 25);
        add(cb_day);

        cb_month = new JComboBox<>(months);
        cb_month.setBounds(270, 170, 70, 25);
        add(cb_month);

        cb_year = new JComboBox<>(years);
        cb_year.setBounds(350, 170, 80, 25);
        add(cb_year);
    }

    private void makeAddressField()
    {
        JLabel lbl = new JLabel("Dirección:");
        lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl.setBounds(30, 215, 100, 20);
        add(lbl);

        tf_address = new JTextField();
        tf_address.setBounds(200, 215, 200, 25);
        add(tf_address);
    }

    private void makeTermsField()
    {
        cb_terms = new JCheckBox("Acepta Términos y Condiciones");
        cb_terms.setBounds(30, 260, 220, 25);
        add(cb_terms);
    }

    private void makeSaveButton()
    {
        JButton btnSave = new JButton("Guardar");
        btnSave.setBounds(200, 300, 100, 30);
        add(btnSave);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Guest guest = new Guest();
                guest.setName(tf_name.getText());
                guest.setCellphone(tf_cellphone.getText());
                guest.setGender(rb_male.isSelected() ? "Masculino" : "Femenino");
                guest.setBirthDate(cb_day.getSelectedItem() + "/" + cb_month.getSelectedItem() + "/" + cb_year.getSelectedItem());
                guest.setAddress(tf_address.getText());
                guest.setAcceptedTerms(cb_terms.isSelected());

                guestController.saveGuest(guest);
                cleanFields();
            }
        });
    }

    private void makeCleanButton()
    {
        JButton btnClean = new JButton("Limpiar");
        btnClean.setBounds(320, 300, 100, 30);
        add(btnClean);

        btnClean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cleanFields();
            }
        });
    }

    private void cleanFields()
    {
        tf_name.setText("");
        tf_cellphone.setText("");
        tf_address.setText("");
        rb_male.setSelected(true);
        cb_day.setSelectedIndex(0);
        cb_month.setSelectedIndex(0);
        cb_year.setSelectedIndex(0);
        cb_terms.setSelected(false);
    }
}