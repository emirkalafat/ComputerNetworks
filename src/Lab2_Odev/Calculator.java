package Lab2_Odev;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JTextField textField1;
    private JPanel panel1;
    private JComboBox comboBox1;
    private JTextField textField2;
    private JButton button1;
    private JTextField textField3;


    Client c1 = new Client();

    public Calculator() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = FormulateQuestion();

                try {
                    c1.Connect("127.0.0.1", 5000);
                    c1.SendMessage(message);

                    String reciveMessage = c1.ReciveMessage();

                    textField3.setText(reciveMessage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public String FormulateQuestion() {
        String first = textField1.getText();
        String second = textField2.getText();
        String action = comboBox1.getSelectedItem().toString();

        return first + ":" + action + ":" + second;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
