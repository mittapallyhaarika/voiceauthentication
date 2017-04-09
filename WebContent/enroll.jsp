<%@page import="com.voicerecog.core.CreateProfile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Enroll Page</title>
<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Open+Sans:400,300'>
<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/icon?family=Material+Icons'>

      <link rel="stylesheet" href="css/style.css">
<script language="javascript">
    function play() {
	document.getElementById("black").src = "img/micblue.png";
	var timer = 0;
	timer = setTimeout(function(){document.getElementById('black').src = "img/micred.png";},60000);
	}
</script>

  
</head>

<body class="body_backg" background="img/2.jpg">
 <div class="cotn_principal">
<div class="cont_centrar">
<div class="cont_ba_opcitiy">


<table style="padding: 5px;" cellspacing="5">
<tr>
<td><img id="black" src="img/micplain.png" alt="Pic not found" width="80px" height="80px"></td>
<td align="center"><p style="color:white;">Record your voice. Please read the text. Be slow and clear.Stop when the mic turns Red !</p></td>
</tr>
</table>

	 
	<img src="img/story.jpg" height="350px" width="600px" >

	<form action="UserSoundRecord" method="post">

<button type="submit" value="Record" class="btn_ulogin" onmouseup="play()">RECORD</button>

	</form>
</div></div></div>
</body>
</html>