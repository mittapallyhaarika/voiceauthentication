<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

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

<c:forEach items="${phrases}" var="phrase">
    <p>Phrase: ${phrase}</p>
</c:forEach>

<form action="UserVerifyEnrollController" method="get">
<button type="submit" value="Start Enrolling" class="btn_ulogin">PROCEED</button>
</form>
</div></div></div></div></div></div>
</body>
</html>