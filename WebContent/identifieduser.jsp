<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "com.voicerecog.core.Identify" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello, I found you</title>
<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Open+Sans:400,300'>
<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/icon?family=Material+Icons'>

      <link rel="stylesheet" href="css/style.css">

  
</head>

<body class="body_backg" background="img/2.jpg">
 <div class="cotn_principal">
<div class="cont_centrar">
<div class="cont_login">
<div class="cont_info_log_sign_up">
      <div class="col_login">
<div class="cont_ba_opcitiy" style="color:white;">
<center>Hi, <%= request.getAttribute("username") %> !!</center>
<br>
<br>

<center>This is your SID</center><br/>
<center><%= request.getAttribute("id") %></center> 
<center><br><a href="index.jsp" class="btn_ulogin">Home</a></center>
 </div></div></div></div></div></div>
</body>
</html>