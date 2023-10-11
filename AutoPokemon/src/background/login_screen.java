package background;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class login_screen {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login_screen window = new login_screen();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public login_screen() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Auto Pocket");

        ImagePanel loginPanel = new ImagePanel(new ImageIcon("C:/Project/AutoPokemon/src/Image/login_background.png").getImage());
        ImagePanel mainloginPanel = new ImagePanel(new ImageIcon("C:\\Project\\AutoPokemon\\src\\Image\\main2.png").getImage());
        mainloginPanel.setLocation(610, 540);

        loginPanel.setLayout(null);
        mainloginPanel.setLayout(null);

        loginPanel.setPreferredSize(loginPanel.getPreferredSize());
        
        loginPanel.add(mainloginPanel);
        
        frame.getContentPane().add(loginPanel);

        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
