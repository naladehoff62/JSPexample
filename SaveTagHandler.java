package tags;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.sql.*;

public class SaveTagHandler extends SimpleTagSupport{
	private String fname;
	private String lname;
	private String uname;

	public void setFname(String fname){
		this.fname = fname;
	}

	public void setLname(String lname){
		this.lname = lname;
	}

	public void setUname(String uname){
		this.uname = uname;
	}

	StringWriter sw = new StringWriter();

	public void doTag() throws JspException, IOException{
		Connection con = null;
		Statement stmt = null;

		try{
			DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "user1", "pass");
			stmt = con.createStatement();				
		} 
		catch(Exception e){
			System.out.println("<h1>Error</h1>");
			return;
		}

		try{
			stmt.executeUpdate("DROP TABLE EMPLOYEE_TABLE");
		} 
		catch(SQLException e){
		}

		try{
			stmt.executeUpdate("CREATE TABLE EMPLOYEE_TABLE(fname VARCHAR2(50), lname VARCHAR2(50),uname VARCHAR2(50))");
		} 
		catch(SQLException e){
		}
		
		try{
			PreparedStatement ps = null;

			try{
				ps = con.prepareStatement("INSERT into EMPLOYEE_TABLE values(?,?,?)");
				ps.setString(1, fname);
				ps.setString(2, lname);
				ps.setString(3, uname);

				int i = ps.executeUpdate();
			} 
			catch (Exception e2){
				System.out.println(e2);
			} 
			finally{
				ps.close();
			}
		} 
		catch (Exception e2){
			JspWriter out = getJspContext().getOut();
			out.println(e2);
		}
	}
}
