<%@page import="com.voicerecog.core.CreateProfile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New User</title>
  
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
<div class="cont_ba_opcitiy">


	<h3><center>Create your
		profile</center></h3>

	<form action="UserController" method="post">

<!-- 		<fieldset>
			<div>
				<center>
					<label for="name">Name</label> <input type="text" name="name" />
				</center>
			</div>

			<div>
				<center>
					<label for="sid">SID</label> <input type="text" name="sid" />
				</center>
			</div>

			<center>
				<input type="submit" value="Submit" />
			</center>

		</fieldset> -->

<table style="padding: 15px;" cellspacing="15">
<tr>
	<th>Enter Name</th>
	<td><input type="text" name="name"/></td>
</tr>
<tr>
	<th>Enter SID</th>
	<td> <input type="text" name="sid"/></td>
</tr>
<tr>
<td colspan="2"><button type="submit" value="submit" class="btn_ulogin">PROCEED</button></td>
</tr>  
</table>

	</form>
 </div></div></div></div></div></div>
</body>
</html>

 