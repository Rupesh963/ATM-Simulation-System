package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MiniStatement extends JFrame{
    
    MiniStatement(String pinnumber) {
        setTitle("Mini Statement");
        
        setLayout(null);
        
        JLabel mini = new JLabel();
        add(mini);
        
        JLabel bank = new JLabel("Bharat Bank");
        bank.setBounds(150,20,100,20);
        add(bank);
        
        JLabel card = new JLabel();
        card.setBounds(20,80,300,20);
        add(card);
        
        JLabel balance = new JLabel();
        balance.setBounds(20,400,300,20);
        add(balance);
        
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from login where pin = '"+pinnumber+"'");
            while(rs.next()) {
                card.setText("Card Number: " + rs.getString("cardnumber").substring(0,4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        mini.setBounds(20,140,360,200);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
            String text = "<html>";
int bal = 0;

while(rs.next()) {
    text += rs.getString("date") + " &nbsp;&nbsp;&nbsp;&nbsp; "
          + rs.getString("type") + " &nbsp;&nbsp;&nbsp;&nbsp; "
          + rs.getString("amount") + "<br><br>";

    if(rs.getString("type").equalsIgnoreCase("deposit")) {
        bal += Integer.parseInt(rs.getString("amount"));
    } else {
        bal -= Integer.parseInt(rs.getString("amount"));
    }
}

text += "</html>";

mini.setText(text); 
balance.setText("Your current account balance is Rs " + bal);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        setSize(400,600);
        setLocation(20, 20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
        
    }
    
    public static void main(String args[]) {
        new MiniStatement("4814");
    }
    
}
