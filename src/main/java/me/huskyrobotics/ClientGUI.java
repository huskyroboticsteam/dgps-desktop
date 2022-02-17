package me.huskyrobotics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame implements ActionListener {

    private JButton confirmButton;

    private JLabel title;

    private JLabel hostName;

    private JLabel port;

    private JTextField hostTextBox;

    private JTextField portTextBox;

    public static void main(String[] args) {
        new ClientGUI();
    }

    public ClientGUI() {
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultLookAndFeelDecorated(true);

        // Title for DGPS desktop
        title = new JLabel("DGPS Desktop");
        title.setFont(new Font("Papyrus", Font.PLAIN, 15));
        title.setBounds(40, 10, 200, 50);
        this.add(title);

        // Host Name Text Box
        hostName = new JLabel("Host Name: ");
        hostName.setBounds(25, 70, 100, 30);
        hostTextBox = new JTextField();
        hostTextBox.setBounds(120, 70, 200, 30);
        this.add(hostName);
        this.add(hostTextBox);

        // Port Number Text Box
        port = new JLabel("Port Number: ");
        port.setBounds(25, 110, 100, 30);
        portTextBox = new JTextField();
        portTextBox.setBounds(120, 110, 200, 30);
        this.add(port);
        this.add(portTextBox);

        // Button to confirm port and host
        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(230, 160, 100, 30);
        confirmButton.addActionListener(this);
        this.add(confirmButton);

        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            if (!hostTextBox.getText().equals("") && !portTextBox.getText().equals("")) {
                System.out.println("host: " + hostTextBox.getText());
                System.out.println("port: " + portTextBox.getText());
                hostTextBox.setText("");
                portTextBox.setText("");
            }
        }
    }
}
