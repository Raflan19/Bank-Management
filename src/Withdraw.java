import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
public class Withdraw extends JFrame implements ActionListener{
	String accno;
	JButton b1,b2;
	JTextField t1;
	JLabel l1;
	Withdraw(String accno){
		this.accno=accno;
		getContentPane().setBackground(Color.BLUE);
		l1=new JLabel("Enter amount to withdraw");
		l1.setBounds(150,50,200,55);
		l1.setForeground(Color.WHITE);
		add(l1);
		t1=new JTextField();
		t1.setBounds(120,100,200,15);
		add(t1);
		b1=new JButton("Withdraw");
		b1.setBounds(300,250,100,30);
		b1.setBackground(Color.RED);
		b1.setForeground(Color.WHITE);
		b1.addActionListener(this);
		add(b1);
		b2=new JButton("Back");
		b2.setBounds(300,300,100,30);
		b2.setBackground(Color.RED);
		b2.setForeground(Color.WHITE);
		b2.addActionListener(this);
		add(b2);
		setLayout(null);
		setSize(500,400);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==b1)
		{
			String balb="";
			String acctype=null;
			String witdate;
			int bala;
			String amt=t1.getText();
			try {
			Conn c=new Conn();
		    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		    LocalDateTime now = LocalDateTime.now();  
		    witdate=dtf.format(now);
		String qs2="INSERT INTO bank VALUES ('"+accno+"','"+witdate+"','Withdraw','"+amt+"')";
		c.s.executeUpdate(qs2);
			String query="SELECT * FROM balcheck WHERE cardnumber='"+accno+"'";
				ResultSet rs=c.s.executeQuery(query);
				if(rs.next()) {
					balb=rs.getString("bal");
				}
					bala=Integer.parseInt(balb)-Integer.parseInt(amt);
					String q="UPDATE balcheck SET bal='"+bala+"'WHERE cardnumber='"+accno+"'";
					c.s.executeUpdate(q);
					JOptionPane.showMessageDialog(this,amt+" is withdrawn");
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
		}
		else if(e.getSource()==b2) {
             setVisible(false);
             new Transaction(accno).setVisible(true);
		}
	}
	public static void main(String args[]) {
		new Withdraw("").setVisible(true);
	}
}