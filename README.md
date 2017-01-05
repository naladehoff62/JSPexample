# JSPexample
JSP FORM example

     <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
     <%@ page import="java.io.*"%>
     <%@ page import="java.sql.*"%>
     <%@ page import="oracle.jdbc.driver.*"%>
     <%@page import="bean.Name"%>
     <%@ taglib prefix="s" uri="WEB-INF/SaveTagHandler.tld"%>

     <jsp:useBean id="obj" class="bean.Name" />
     <jsp:setProperty property="*" name="obj" />

     <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
     <html>
     	<head>
     		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
     		<title>
     			Employee Information
     		</title>
		
     		<link rel="stylesheet" type="text/css" href="main.css">
     		</head>

     	<body>
     	<%
     		if (request.getMethod().equals("GET")) {
     	%>

     	<form method="post" action="/nlad/FormPost5.jsp">
     		<label for="fname">First name:</label> <input type="text" name="fname"><br /><br />
     		<label for="lname">Last name:</label> <input type="text" name="lname"><br /><br />
     		<label for="uname">User Name:</label> <input type="text" name="uname"><br /><br />
     		<input type="submit">
     	</form>

     	<%
     		} 
		
     		if (request.getMethod().equals("POST")) {
     	%>
     			<s:save fname="<%=obj.getFname()%>" lname="<%=obj.getLname()%>" uname="<%=obj.getUname()%>" />	
     		<%	
     			PrintWriter pw = response.getWriter();
     			Connection con = null;
     			Statement st = null;
			
     			try {
     				DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
     				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "user1", "pass");
     				st = con.createStatement();
     			} catch (Exception e) {
     			}
			
     			try {
	     			ResultSet rs = st.executeQuery("Select * from EMPLOYEE_TABLE");

	     			pw.println("<link rel='stylesheet' type='text/css' href='main.css'>");
			     	pw.println("<form method='post' action='/nlad/FormPost5.jsp'>");
			     	pw.println("<label for='fname'>First Name:</label> <input type='text' name='fname'><br /><br />");
				    pw.println("<label for='lname'>Last Name:</label> <input type='text' name='lname'><br /><br />");
		     		pw.println("<label for='uname'>User Name:</label> <input type='text' name='uname'><br /><br />");
		     		pw.println("<input type='submit'>");
		     		pw.println("</form><br /><br />");
				
		     		pw.println("<table style='border-collapse: collapse;'>");
		     		pw.println("<tr style='background-color: #00FFFF; color: #FFF;'>");
		     		pw.println("<th style='height: 20px;'>First Name</th>");
		     		pw.println("<th>Last Name</th>");
			     	pw.println("<th>User Name</th>");
		     		pw.println("</tr>");
					
		     		while (rs.next()) {
	     				pw.println("<tr style='background-color: lightyellow;'><td>" + rs.getString(1)
	     					+ "</td><td>" + rs.getString(2) + "</td>"
	     					+ "<td>" + rs.getString(3) + "</td></tr>");
	     			}
				
	     			pw.println("</table>");
	     			pw.close();
	     			con.close();
	     		} catch (Exception e) {
     			}
     		}
     	%>

     </body>
     </html>
