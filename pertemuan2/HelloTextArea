import java.awt.event.*;
import javax.swing.*;

public class HelloTextArea extends JFrame {

  public HelloTextArea() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JLabel labelInput = new JLabel("Input Nama:");
    labelInput.setBounds(130, 40, 100, 30);

    JTextField textField = new JTextField();
    textField.setBounds(130, 80, 200, 30);

    JButton button = new JButton("Klik");
    button.setBounds(130, 120, 100, 40);

    JTextArea textArea = new JTextArea();
    textArea.setBounds(130, 170, 200, 100);
    textArea.setEditable(false);

    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String nama = textField.getText();
        textArea.setText("Hello " + nama);
      }
    });

    this.add(button);
    this.add(textField);
    this.add(labelInput);
    this.add(textArea);

    this.setSize(400, 400);
    this.setLayout(null);
  }

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        HelloTextArea h = new HelloTextArea();
        h.setVisible(true);
      }
    });
  }
}
