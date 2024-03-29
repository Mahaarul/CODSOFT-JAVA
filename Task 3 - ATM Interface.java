
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterface extends JFrame {
    private float balance = 1000.0f;
    private int pin = 7345;
    private boolean pinVerified = false;

    private JLabel title, balanceLabel, label;
    private JTextField pinField;
    private JButton verifyButton, checkBalance, withdrawButton, depositButton;

    public ATMInterface() {
        setTitle("ATM Interface");
        setBounds(0, 0, 300,400);
        getContentPane().setBackground(new Color(21,66, 155));
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        title = new JLabel("ATM INTERFACE");
        title.setBounds(0,50,300, 50);
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        
        balanceLabel = new JLabel("");
        balanceLabel.setBounds(50, 150, 300, 30);
        balanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        balanceLabel.setForeground(new Color(255,0, 6));
        
        label = new JLabel("Enter PIN : ");
        label.setBounds(50, 100, 50, 30);
        label.setFont(new Font("Tahoma", Font.PLAIN, 24));
        label.setForeground(Color.WHITE);
        
        pinField = new JTextField(10);
        pinField.setBounds(50, 150, 50, 20);
        pinField.setFont(new Font("Tahoma", Font.PLAIN, 24));
        
        verifyButton = new JButton("Verify PIN");
        verifyButton.setBounds(30, 250, 50, 30);
        verifyButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        verifyButton.setBackground(new Color(241,8, 119));
        verifyButton.setForeground(Color.WHITE);
        
        checkBalance = new JButton("Check Balance");
        checkBalance.setBounds(80, 250, 50, 30);
        checkBalance.setFont(new Font("Tahoma", Font.PLAIN, 22));
        checkBalance.setBackground(new Color(8,186, 245));
        checkBalance.setForeground(Color.WHITE);
        
        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(30, 300, 50, 30);
        withdrawButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        withdrawButton.setBackground(new Color(8,186, 245));
        withdrawButton.setForeground(Color.WHITE);
        
        depositButton = new JButton("Deposit");
        depositButton.setBounds(80, 350, 50, 30);
        depositButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        depositButton.setBackground(new Color(8,186, 245));
        depositButton.setForeground(Color.WHITE);

        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int enteredPin = Integer.parseInt(pinField.getText());
                if (enteredPin == pin) {
                    pinVerified = true;
                    verifyButton.setEnabled(false);
                    withdrawButton.setEnabled(true);
                    depositButton.setEnabled(true);
                    checkBalance.setEnabled(true);
                    JOptionPane.showMessageDialog(ATMInterface.this, "PIN verified");
                } else {
                    JOptionPane.showMessageDialog(ATMInterface.this, "Invalid PIN");
                }
            }
        });
        checkBalance.setEnabled(false);
        checkBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int enteredPin = Integer.parseInt(pinField.getText());
                if (enteredPin == pin) {
                    JOptionPane.showMessageDialog(ATMInterface.this, "Current Balance: Rs" + balance);
                } else {
                    JOptionPane.showMessageDialog(ATMInterface.this, "Invalid PIN");
                }
                pinField.setText("");
            }
        });

        withdrawButton.setEnabled(false);
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog(ATMInterface.this, "Enter amount to withdraw:");
                float amount = Float.parseFloat(amountStr);
                if (amount <= balance) {
                    balance -= amount;
                    balanceLabel.setText("Balance: Rs " + balance);
                    JOptionPane.showMessageDialog(ATMInterface.this, "Withdrawal successful");
                } else {
                    JOptionPane.showMessageDialog(ATMInterface.this, "Insufficient balance");
                }
            }
        });

        depositButton.setEnabled(false);
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog(ATMInterface.this, "Enter amount to deposit:");
                float amount = Float.parseFloat(amountStr);
                balance += amount;
                balanceLabel.setText("Balance: Rs " + balance);
                JOptionPane.showMessageDialog(ATMInterface.this, "Deposit successful");
            }
        });

        setLayout(new FlowLayout());
        add(title);
        add(balanceLabel);
        add(label);
        add(pinField);
        add(verifyButton);
        add(checkBalance);
        add(withdrawButton);
        add(depositButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ATMInterface atm = new ATMInterface();
                atm.setVisible(true);
            }
        });
    }

}