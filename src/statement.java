import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class statement extends JFrame implements ActionListener{
	JTable tlb;
	Conn c2;
	JButton b1,b2;
	String accno;
	private JTable table;
	statement(String accno){
		this.accno=accno;
		getContentPane().setBackground(Color.BLUE);          
	    b1=new JButton("Print");
		b1.setBounds(10,50,100,30);
		b1.setBackground(Color.RED);
		b1.setForeground(Color.WHITE);
		b1.addActionListener(this);
		getContentPane().add(b1);
		
		   b2=new JButton("Back");
			b2.setBounds(10,100,100,30);
			b2.setBackground(Color.RED);
			b2.setForeground(Color.WHITE);
			b2.addActionListener(this);
			getContentPane().add(b2);
	getContentPane().setLayout(null);
	
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(130, 42, 334, 377);
	getContentPane().add(scrollPane);
	
	table = new JTable();
	scrollPane.setViewportView(table);
	setSize(500,500);
	setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
		try {
			c2=new Conn();
			String q="SELECT * FROM bank WHERE cardnumber='"+accno+"'";
			ResultSet rs=c2.s.executeQuery(q);
			ResultSetMetaData rsmd=rs.getMetaData();
			DefaultTableModel dft=(DefaultTableModel) table.getModel();
			int col=rsmd.getColumnCount();
			String[] colss=new String[col];
			for(int i=0;i<col;i++) {
				colss[i]=rsmd.getColumnName(i+1);
			
			}
			dft.setColumnIdentifiers(colss);
			String cardn;
			String tdate;
			String ttype;
			String tamount;
			while(rs.next()) {
				cardn=rs.getString(1);
				tdate=rs.getString(2);
				ttype=rs.getString(3);
				tamount=rs.getString(4);
				String [] row= {cardn,tdate,ttype,tamount};
				dft.addRow(row);
			}
			c2.s.close();
		}
			catch (SQLException ea) {
				// TODO Auto-generated catch block
				ea.printStackTrace();
			}
		}
		else {
			setVisible(false);
			new Transaction(accno).setVisible(true);
		}
	}
	public static void main(String args[]) {
		new statement("").setVisible(true);
	}
}
