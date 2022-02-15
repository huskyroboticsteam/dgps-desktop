package me.huskyrobotics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    public static void main(String[] args) {
        new ClientGUI();
    }

    public ClientGUI() {
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultLookAndFeelDecorated(true);

        // Title for DGPS desktop
        JLabel title = new JLabel("DGPS Desktop");
        title.setFont(new Font("Papyrus", Font.PLAIN, 15));
        title.setBounds(40, 10, 200, 50);
        this.add(title);

        // Host Name Text Box
        JLabel hostName = new JLabel("Host Name: ");
        hostName.setBounds(25, 70, 100, 30);
        JTextField hostTextBox = new JTextField();
        hostTextBox.setBounds(120, 70, 200, 30);
        this.add(hostName);
        this.add(hostTextBox);

        // Port Number Text Box
        JLabel port = new JLabel("Port Number: ");
        port.setBounds(25, 110, 100, 30);
        JTextField portTextBox = new JTextField();
        portTextBox.setBounds(120, 110, 200, 30);
        this.add(port);
        this.add(portTextBox);

        // Button to confirm port and host
        JButton confirm = new JButton("Confirm");
        confirm.setBounds(230, 160, 100, 30);
        this.add(confirm);

        this.setLayout(null);
        this.setVisible(true);
    }
}
