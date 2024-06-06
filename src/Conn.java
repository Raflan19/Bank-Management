import java.sql.*;
public class Conn{
	Connection c;
	Statement s;
	PreparedStatement ps;
	public Conn(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmng","root","root");
			s=c.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}