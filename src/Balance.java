import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Balance extends JFrame implements ActionListener{
	JLabel l1,l2;
	JButton b1;
	String accno;
	String bal;
	Balance(String accno){
		this.accno=accno;
		getContentPane().setBackground(Color.BLUE);
		l1=new JLabel("Your Current Balance is ");
		l1.setFont(new Font("Sans Serif",Font.BOLD,15));
		l1.setBounds(100,100,200,50);
		l1.setForeground(Color.WHITE);
		add(l1);
		l2=new JLabel();
		l2.setFont(new Font("Sans Serif",Font.BOLD,15));
		l2.setBounds(290,100,200,50);
		l2.setForeground(Color.WHITE);
		add(l2);
		b1=new JButton("Back");
		b1.setBounds(300,300,100,30);
		b1.setBackground(Color.RED);
		b1.setForeground(Color.WHITE);
		b1.addActionListener(this);
		add(b1);
		Conn c=new Conn();
		String q="SELECT bal FROM balcheck WHERE cardnumber='"+accno+"'";
		try {
			ResultSet rs=c.s.executeQuery(q);
			if(rs.next()) {
				bal=rs.getString("bal");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		l2.setText(bal);
		
		
		
		setLayout(null);
		setSize(500,400);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==b1) {
			setVisible(false);
			new Transaction(accno).setVisible(true);
		}
	}
	public static void main(String args[]) {
		new Balance("").setVisible(true);
	}
}