<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Verify Main View</title>
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
<center>Enter your SID to continue</center>
<center>

<form action="UserVerifyController" method="post">
		<!-- <fieldset>
			<div>
				<center>
					<label for="name">SID</label> <input type="text" name="sid" />
				</center>
			</div>


			<center>
				<input type="submit" value="Submit" />
			</center>

		</fieldset>
 -->	

<table style="padding: 15px;" cellspacing="15">
<tr>
	<td> <input type="text" name="sid" /></td>
</tr>
<tr>
<td colspan="2"><button type="submit" value="Submit" class="btn_ulogin">PROCEED</button></td>
</tr>  
</table>
</form>
</center>
</div></div></div></div></div></div>
</body>
</html>