import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Login extends JFrame implements ActionListener{
	JButton b1,b2;
	JPasswordField p1;
	JTextField t1,t2;
	JLabel l1,l2,l3;
	Login(){
		getContentPane().setBackground(Color.WHITE);
		l1=new JLabel("WELCOME TO RML BANKING");
		l1.setFont(new Font("Sans Serif",Font.BOLD,15));
		l1.setBounds(150,50,250,55);
		add(l1);
		l2=new JLabel("ACCNO:");
		l2.setBounds(100,100,50,55);
		add(l2);
		t1=new JTextField();
		t1.setBounds(200,120,200,20);
		add(t1);
		
		l2=new JLabel("PIN:");
		l2.setBounds(100,150,50,55);
		add(l2);
		p1=new JPasswordField();
		p1.setBounds(200,170,200,20);
		add(p1);
		b1=new JButton("Login");
		b1.setBounds(100,220,100,30);
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);
		b1.addActionListener(this);
		add(b1);
		
		b2=new JButton("Sign up");
		b2.setBounds(300,220,100,30);
		b2.setBackground(Color.BLACK);
		b2.setForeground(Color.WHITE);
		b2.addActionListener(this);
		add(b2);
		
		setLayout(null);
		setSize(500,400);
		setVisible(true);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			if(p1.getPassword().length== 0){
				JOptionPane.showMessageDialog(this, "Please enter your pin");
			}
			else {
				String pin=String.valueOf(p1.getPassword());
				String acc=t1.getText();
				Conn c1=new Conn();
				String query="SELECT * FROM login where cardnumber='"+acc+"'";
				System.out.println(query);
				try {
					ResultSet rs=c1.s.executeQuery(query);
					if(!rs.next()) {
						JOptionPane.showMessageDialog(this, "Wrong details");
					}
					else {
						setVisible(false);
						new Transaction(acc).setVisible(true);
					}
				    
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		else {
			setVisible(false);
			new Signup().setVisible(true);
		}
	}
	public static void main(String args[]) {
		new Login().setVisible(true);
	}
}