package background;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class login_background extends JFrame {
    
    public static void main(String[] args) {
    	 JFrame mainFrame = new JFrame();
         mainFrame.setTitle("Auto Pocket");
         mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
         mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         mainFrame.setLocationRelativeTo(null);

         ImagePanel loginPanel = new ImagePanel(new ImageIcon("C:/Project/AutoPokemon/src/Image/login_background.png").getImage());

         mainFrame.add(loginPanel);
         
         mainFrame.revalidate();
         mainFrame.repaint();
         
         mainFrame.setVisible(true);
    }
}