import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Transaction extends JFrame implements ActionListener{
	String accno;
	JButton b1,b2,b3,b4,b5;
	JLabel l1,l2;
	Transaction(String accno){
		this.accno=accno;
		getContentPane().setBackground(Color.BLUE);
		l1=new JLabel("RML BANKING");
		l1.setBounds(180,50,200,55);
		l1.setFont(new Font("Sans Serif",Font.BOLD,20));
		l1.setForeground(Color.WHITE);
		l2=new JLabel("SELECT TRANSACTION");
		l2.setBounds(180,100,200,55);
		l2.setForeground(Color.WHITE);
		l2.setFont(new Font("Sans Serif",Font.BOLD,12));
		add(l1);
		add(l2);
		b1=new JButton("Deposit");
		b1.setBounds(100,180,100,30);
		b1.setBackground(Color.RED);
		b1.setForeground(Color.WHITE);
		b1.addActionListener(this);
		add(b1);
		b2=new JButton("Withdraw");
		b2.setBounds(300,180,100,30);
		b2.setBackground(Color.RED);
		b2.setForeground(Color.WHITE);
		b2.addActionListener(this);
		add(b2);
		b3=new JButton("Statement");
		b3.setBounds(100,230,100,30);
		b3.setBackground(Color.RED);
		b3.setForeground(Color.WHITE);
		b3.addActionListener(this);
		add(b3);
		b4=new JButton("Balance");
		b4.setBounds(300,230,100,30);
		b4.setBackground(Color.RED);
		b4.setForeground(Color.WHITE);
		b4.addActionListener(this);
		add(b4);
		b5=new JButton("Exit");
		b5.setBounds(100,280,100,30);
		b5.setBackground(Color.RED);
		b5.setForeground(Color.WHITE);
		b5.addActionListener(this);
		add(b5);
		
		
		setLayout(null);
		setSize(500,400);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			setVisible(false);
			new Deposit(accno).setVisible(true);
		}
		else if(e.getSource()==b2) {
			setVisible(false);
			new Withdraw(accno).setVisible(true);
		}
		else if(e.getSource()==b3) {
			setVisible(false);
			new statement(accno).setVisible(true);
		}
		else if(e.getSource()==b4) {
			setVisible(false);
			new Balance(accno).setVisible(true);
		}
		else {
			System.exit(0);
		}
	}
	public static void main(String args[]) {
		new Transaction("").setVisible(true);
	}
}