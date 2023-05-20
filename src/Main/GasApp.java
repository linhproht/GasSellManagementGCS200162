package Main;

import Lib.XFile;
import Model.GasSellManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class GasApp extends JFrame{
    private JPanel mainPanel;
    private JButton updateInTableButton;
    private JButton deleteButton;
    private JButton addButton;
    private JTextField txtID;
    private JTextField txtDate;
    private JCheckBox ckboxUp;
    private JCheckBox ckboxDown;
    private JButton resetButton;
    private JComboBox comboBoxGasType;
    private JTextField txtNameUser;
    private JTable table1;
    private JButton totalRevenueInDayButton;
    private JTextField txtPrice;
    private JButton updateGasPriceButton;
    private JComboBox cbType;
    private JButton sortByRevenueButton;
    private JTable tbGas;
    JFrame frontScreen;
    ArrayList<GasSellManagement> gasList;
    DefaultTableModel tbModel;
    DefaultComboBoxModel cbModel = new DefaultComboBoxModel();
    String filePatch = "gas.dat";
    String filePatch2 = "price.dat";

    int currentRow;
    Double[] price2;

    public GasApp(String title, String name, Login aThis){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        frontScreen = aThis;

        initTable();
        loadCb();
        txtNameUser.setText(name);

        gasList = new ArrayList<>();
        boolean file = loadFile();
        if(file){
            fillToTable();
        }else{
            showMess("Nothing to do in here");
        }

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGas();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });
        updateInTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBill();
            }
        });
        cbType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int index = cbType.getSelectedIndex();
                txtPrice.setText(String.valueOf(price2[index]));

            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                currentRow = table1.getSelectedRow();
                showInfor();
            }
        });
        updateGasPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    UpdatePrice u = new UpdatePrice("Update");
                    u.setVisible(true);
                    u.setLocationRelativeTo(null);
            }
        });
        sortByRevenueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sort();
                fillToTable();
            }
        });
    }

    private  void sort(){
        Collections.sort(gasList, new Comparator<GasSellManagement>() {
            @Override
            public int compare(GasSellManagement o1, GasSellManagement o2) {
                o1.getName().compareTo(o2.getName());
                return o1.getPrice()>o2.getPrice()?1:-1;
            }
        });
    }

    private void showInfor() {
        GasSellManagement g = gasList.get(currentRow);

        txtID.setText(g.getID());
        txtDate.setText(g.getDate());
        cbModel.setSelectedItem(g.getGasType());
        cbType.setModel(cbModel);
    }

    private void updateBill() {
        GasSellManagement g = gasList.get(currentRow);

        String id = txtID.getText();
        g.setID(id);
        String date = txtDate.getText();
        g.setDate(date);
        String gastype = cbType.getSelectedItem().toString();
        g.setGasType(gastype);
        Double price = Double.parseDouble(txtPrice.getText());
        g.setPrice(price);
        showMess(String.valueOf(id));

        fillToTable();

        saveFile();
    }

    private void delete() {
        int re = JOptionPane.showConfirmDialog(this, ""+" Are u sure about that", "Djt me m muon xoa a",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if(re==JOptionPane.YES_OPTION){
            gasList.remove(currentRow);
            fillToTable();
        }
    }

    private void reset() {
        txtID.setText("");
        txtDate.setText("");

        ckboxUp.setSelected(false);
        ckboxDown.setSelected(false);

        cbModel.setSelectedItem("Choose your gas type");
        cbType.setModel(cbModel);

        loadFile();
    }

    private void addGas() {
        addToList();

        fillToTable();

        saveFile();
    }

    private void saveFile() {
        XFile.writeObject(filePatch, gasList);
    }

    private void addToList() {
        String id = txtID.getText();
        Double price = Double.parseDouble(txtPrice.getText());
        String date = txtDate.getText();
        double total = 0.0;
        String name = "";
        String img = "";

        String type = cbType.getSelectedItem().toString();
        GasSellManagement g = new GasSellManagement(type, id, price, date, total, name);
        gasList.add(g);
    }

    private void fillToTable() {
        tbModel.setRowCount(0);
        for (GasSellManagement g : gasList) {
            Object[] row = new Object[]{
                    g.getGasType(), g.getID(), g.getPrice(), g.getDate()
            };
            tbModel.addRow(row);
        }

    }

    private void showMess(String mess) {
        JOptionPane.showMessageDialog(this, mess);
    }

    private boolean loadFile() {
        if(XFile.readObject(filePatch)==null){
            return false;
        }
        if(XFile.readObject(filePatch2)==null){
            return false;
        }
        gasList = (ArrayList<GasSellManagement>)
                XFile.readObject(filePatch);
        price2 = (Double[]) XFile.readObject(filePatch2);
        return true;
    }


    private void loadCb() {
        String[] typeLst = {"Choose your gas type","ELF gas", "Petrol gas", "Total gas", "Family gas"};
        for (String t:typeLst){
            cbModel.addElement(t);
        }
        cbType.setModel(cbModel);
    }

    private void initTable() {
        String[] columnNames = {"Gas type","ID", "Price", "Date"};
        tbModel = new DefaultTableModel(columnNames, 0);
        table1.setModel(tbModel);
    }
}
