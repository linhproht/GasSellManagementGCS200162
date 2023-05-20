package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPanel mainPanel;
    private JTextField txtUsr;
    private JButton btnExit;
    private JButton btnLogin;
    private JPasswordField txtPass;

    public Login(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lgn();
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
    }

    private void exit() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void lgn() {
        String name = txtUsr.getText();
        String pass = String.valueOf(txtPass.getPassword());

        User admin = new User("linhproht", "1234");
        User checkUser = new User(name,pass);
        GasApp g =null;
        boolean login = false;

        if(admin.equals(checkUser)){
            g = new GasApp("Gas App",checkUser.getName(),this);
            login = true;
        }
        if(login){
            this.setVisible(false);
            g.setVisible(true);
            g.setLocationRelativeTo(null);
        }else{
            showMess("Login false");
        }

    }

    private void showMess(String mess) {
        JOptionPane.showMessageDialog(this, mess);
    }
}
