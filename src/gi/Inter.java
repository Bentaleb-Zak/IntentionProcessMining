package gi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Inter extends JFrame{
    private JPanel panel1;
    private JLabel label1;
    private JRadioButton femaleRadioButton;
    private JRadioButton maleRadioButton;
    private JRadioButton studentRadioButton1;
    private JRadioButton employeeRadioButton1;
    private JTextField otherTextField;
    private JRadioButton a20RadioButton;
    private JRadioButton a2029RadioButton;
    private JRadioButton a3039RadioButton;
    private JRadioButton a4049RadioButton;
    private JRadioButton a50RadioButton;
    private JTextField textField1;
    private JButton chooseFileButton;
    private JPanel panel2;
    private JButton confirmButton;
    private ImageIcon aa;

    public Inter() {

        setLayout(new FlowLayout());
        setSize(100,100);


        aa = new ImageIcon(getClass().getResource("aa.jpg"));
        JButton button1 = new JButton();


        label1.setIcon(aa);
        panel1.add(label1);



        add(panel1);
        validate();


        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int result = fc.showSaveDialog(panel2);

            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Done");
            }
        });
    }



    public static void main(String[] args) {
        Inter gui = new Inter();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(100, 100);
        gui.setVisible(true);
        gui.pack();
        gui.setTitle("Context Sensitive Ml");
    }

}


