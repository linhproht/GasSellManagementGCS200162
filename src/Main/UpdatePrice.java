package Main;

import Lib.XFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePrice extends JFrame{
    private JTextField txtElf;
    private JTextField txtPetrol;
    private JTextField txtTotal;
    private JTextField txtFamily;
    private JButton updateButton;
    private JButton cancelButton;
    private JLabel Petrol;
    private JLabel Totalgas;
    private JLabel Familygas;
    private JPanel mainPanel;
    private JLabel eflgas;
    String filePatch2 = "price.dat";
    Double[] priceList;

    public UpdatePrice(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        priceList = new Double[4];
        boolean file = loadFile();
        if(file){
            fillToTextField();
        }else{
            showMess("Nothing to do in here");
        }
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePrice();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
    }

    private void exit() {
        this.dispose();
    }

    private void showMess(String mess) {
        JOptionPane.showMessageDialog(this, mess);
    }

    private void fillToTextField() {
        txtElf.setText(String.valueOf(priceList[1]));
        txtPetrol.setText(String.valueOf(priceList[2]));
        txtTotal.setText(String.valueOf(priceList[3]));
        txtFamily.setText(String.valueOf(priceList[4]));
    }

    private boolean loadFile() {
        if(XFile.readObject(filePatch2)==null){
            return false;
        }
        priceList =
                (Double[]) XFile.readObject(filePatch2);
        return true;
    }

    private void updatePrice() {

         priceList[1] = Double.parseDouble(txtElf.getText());
         priceList[2] = Double.parseDouble(txtPetrol.getText());
         priceList[3] = Double.parseDouble(txtTotal.getText());
         priceList[4] = Double.parseDouble(txtFamily.getText());

         saveFile();

         exit();
    }

    private void saveFile() {
        XFile.writeObject(filePatch2, priceList);
    }
}
