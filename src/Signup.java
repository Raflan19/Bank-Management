import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;

public class Signup extends JFrame implements ActionListener{
	JPasswordField p1;
	JTextField t1,t2,t3,t4,t5,t6,t7;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
	JButton b1,b2;
	JRadioButton r1,r2;
	JDateChooser dateChooser;

	Signup(){
		getContentPane().setBackground(Color.WHITE);
		l1=new JLabel("CREATE NEW ACCOUNT");
		l1.setFont(new Font("Sans Serif",Font.BOLD,15));
		l1.setBounds(146,22,250,55);
		getContentPane().add(l1);
		l2=new JLabel("Name:");
		l2.setBounds(47,78,63,41);
		getContentPane().add(l2);
		t1=new JTextField();
		t1.setBounds(184,88,189,20);
		getContentPane().add(t1);
		
		l3=new JLabel("Father Name:");
		l3.setBounds(47,104,88,40);
		getContentPane().add(l3);
	    t2 = new JTextField();
	    t2.setBounds(184, 114, 189, 20);
	    getContentPane().add(t2);
		
		l4 = new JLabel("DOB:");
		l4.setBounds(47, 145, 49, 14);
		getContentPane().add(l4);
		
	    dateChooser = new JDateChooser();
		//dateChooser.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		dateChooser.setForeground(new Color(105, 105, 105));
		dateChooser.setBounds(184, 142, 189, 20);
		getContentPane().add(dateChooser);
		
		
		l5 = new JLabel("Gender:");
		l5.setBounds(47, 170, 49, 14);
		getContentPane().add(l5);
		
		t4 = new JTextField();
		t4.setBounds(184, 167, 189, 20);
		getContentPane().add(t4);
		t4.setColumns(10);
		
		l6 = new JLabel("Phone No:");
		l6.setBounds(47, 195, 63, 14);
		getContentPane().add(l6);
		
		t5 = new JTextField();
		t5.setBounds(183, 192, 190, 20);
		getContentPane().add(t5);
		t5.setColumns(10);
		
		l7 = new JLabel("Account Type:");
		l7.setBounds(47, 226, 88, 14);
		getContentPane().add(l7);
		
		
        r1 = new JRadioButton("Saving Account");
        r1.setFont(new Font("Dialog", Font.PLAIN, 14));
        r1.setBackground(Color.WHITE);
        
        r2 = new JRadioButton("Current Account");
        r2.setFont(new Font("Dialog", Font.PLAIN, 14));
        r2.setBackground(Color.WHITE);
        
        ButtonGroup groupaccs = new ButtonGroup();
        groupaccs.add(r1);
        groupaccs.add(r2);
        
        r1.setBounds(178,216,135,30);
        getContentPane().add(r1);
        
        r2.setBounds(315,216,142,30);
        getContentPane().add(r2);
		
//		l8 = new JLabel("Card Number:");
//		l8.setBounds(47, 257, 88, 14);
//		getContentPane().add(l8);
//		t6 = new JTextField();
//		t6.setBounds(184, 253, 189, 20);
//		getContentPane().add(t6);
//		t6.setColumns(10);
//		
//		l9 = new JLabel("PIN:");
//		l9.setBounds(47, 284, 49, 14);
//		getContentPane().add(l9);
//		t7 = new JTextField();
//		t7.setBounds(185, 284, 189, 20);
//		getContentPane().add(t7);
//		t7.setColumns(10);
		
		b1 = new JButton("Submit");
	    b1.setBackground(Color.BLACK);
	    b1.setForeground(Color.WHITE);
	        
	    b2 = new JButton("Cancel");
	    b2.setBackground(Color.BLACK);
	    b2.setForeground(Color.WHITE);
	     
	     
	    b1.setBounds(90,289,100,30);
	    getContentPane().add(b1);
	        
	    b2.setBounds(270,289,100,30);
	    getContentPane().add(b2);
	    
	    	     
	    getContentPane().setLayout(null); 
	     setSize(500,400);
	     setVisible(true);
	     
	     b1.addActionListener(this);
	     b2.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae){
		
		String name = t1.getText();
		String fathername = t2.getText();
		String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
		String gender=t4.getText();
		String phoneno=t5.getText();
		
        String atype = null;
        if(r1.isSelected()){ 
            atype = "Saving Account";
        }
        else if(r2.isSelected()){ 
            atype = "Current Account";
        }
        
		
		
		Random ran = new Random();
        long first7 = (ran.nextLong() % 90000000L) + 5040936000000000L;
        String cardno = "" + Math.abs(first7);
        
        long first3 = (ran.nextLong() % 9000L) + 1000L;
        String pin = "" + Math.abs(first3);
        
        
        try{
            if(ae.getSource()==b1){
                
                if(atype==null){
                    JOptionPane.showMessageDialog(this, "Fill all the required fields");
                }else{
                    Conn c1 = new Conn();
                    String q1 = "insert into signup values('"+name+"','"+fathername+"','"+dob+"','"+gender+"','"+phoneno+"','"+atype+"','"+cardno+"','"+pin+"')";  
                    String q2 = "insert into login values('"+name+"','"+cardno+"','"+pin+"')";
                    String q3 = "insert into balcheck (cardnumber) values('"+cardno+"')";
                    c1.s.executeUpdate(q1);
                    c1.s.executeUpdate(q2);
                    c1.s.executeUpdate(q3);
                    JOptionPane.showMessageDialog(this, "Card Number: " + cardno + "\n Pin:"+ pin);
                    new Login().setVisible(true);
                    setVisible(false);
                }
            
            }else if(ae.getSource()==b2){
                System.exit(0);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
		
		
	}
	
	
    public static void main(String[] args){
        new Signup().setVisible(true);
    }
}
