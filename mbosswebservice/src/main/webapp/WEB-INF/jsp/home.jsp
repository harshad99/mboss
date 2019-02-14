<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    
    <title>My JSP 'home.jsp' starting page Welcome to struts world</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
<link rel="stylesheet" type="text/css" href="css/blue/theme.css" /> 
  </head>
  
  <body>
  <TABLE align="center" width="96%" 	border="1" class="grid">
  <tr>
  <th>Empid<th><th>Empname<th><th>Cost<th>
  </tr>
  <tr>
  <td>500590</td><td>Yogesh M Patel</td><td>50000</td>
  </tr>
   <tr>
  <td>500589</td><td>Vishal Prajapati</td><td>5000</td>
  </tr>
   <tr>
  <td>500591</td><td>Shailesh Trivedi</td><td>5000</td>
  </tr>
   <tr>
  <td>500592</td><td>Harshad Chauhan</td><td>5000</td>
  </tr>
	</TABLE>			
   
  </body>
  
</html>
