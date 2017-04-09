<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Identify Main Menu</title>

 
  <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Open+Sans:400,300'>
<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/icon?family=Material+Icons'>

      <link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
<script language="javascript">
    function play() {
	document.getElementById("black").src = "img/micblue.png";
	var timer = 0;
	timer = setTimeout(function(){document.getElementById('black').src = "img/micred.png";},15000);
	}
</script>

</head>

<body class="body_backg" background="img/2.jpg">
 <div class="cotn_principal">
<div class="cont_centrar">
<div class="cont_login">
<div class="cont_info_log_sign_up">
      <div class="col_login">
<div class="cont_ba_opcitiy">

<h3 style="color: white"><center>Please read the below text</center></h3>
<h5><b>Stop when it turns red</b></h5>
	<form action="UserExistingController" method="post">
<table style="padding: 25px;" cellspacing="15">
<tr>
<td><img id="black" src="img/micplain.png" alt="Pic not found" width="80px" height="80px"></td>
</tr>

<tr>
<td align="center">
<p style="color: white"><br> Once, a hare saw a tortoise walking slowly with a heavy shell on his back. The hare was very proud of himself and he asked the tortoise. 'Shall we have a race?' 
The tortoise agreed. They started the running race. The hare ran very fast. But the tortoise walked very slowly. The proud hair rested under a tree and soon slept off. But the tortoise walked very fast, slowly and steadily and reached the goal. At last, the tortoise won the race. 
<br/><br/>
Moral : Pride goes before a fall.</p> 
</td>
</tr>
<tr>
	<td><button type="submit" value = "Start record" class="btn_ulogin" onclick="play()">Start Recording</button></td>
</tr>
</table>

</form>
 </div></div></div></div></div></div>
</body>
</html>