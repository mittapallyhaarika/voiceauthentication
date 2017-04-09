<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
  <meta charset="UTF-8">
  <title>WELCOME</title>
  
  
  <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Open+Sans:400,300'>
<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/icon?family=Material+Icons'>

      <link rel="stylesheet" href="css/style.css">

  
</head>

<body class="body_backg" background="img/2.jpg">
  <div class="cotn_principal">
<div class="cont_centrar"><br><br><br>
<H1 style="color:white;">Talk Me Through !</H1>
  <div class="cont_login">
<div class="cont_info_log_sign_up">
      <div class="col_md_login">
<div class="cont_ba_opcitiy">
        
        <h2>EXISTING USER</h2>  
  <p>Welcome Back !</p> 
  <button class="btn_login" onclick="cambiar_login()">LOGIN</button>
  </div>
  </div>
<div class="col_md_sign_up">
<div class="cont_ba_opcitiy">
  <h2>NEW USER</h2>

  
  <p>Click here to Sign Up</p>

  <button class="btn_sign_up" onclick="cambiar_sign_up()">SIGN UP</button>
</div>
  </div>
       </div>

    
    <div class="cont_back_info">
       <div class="cont_img_back">
       <img src="img/2.jpg" alt="HERE IS THE IMAGE">
       </div>
       
    </div>
<div class="cont_forms" >
    <div class="cont_img_back_">
       <img src="img/2.jpg" alt="HERE IS THE IMAGE" />
       </div>
 <FORM ACTION="UserController" METHOD="get">
 <div class="cont_form_login">
<a href="#" onclick="ocultar_login_sign_up()" ><i class="material-icons">&#xE5C4;</i></a>
   <h2>LOGIN</h2> <br/>  
<p>Welcome back</p>
<h5>You can get in without your password!</h5>
<h5> Awesome right?</h5> <br/>
<button TYPE="submit" class="btn_login" name="usertype" value="existinguser">GET STARTED</button>
  </div>
  
<div class="cont_form_sign_up">
<a href="#" onclick="ocultar_login_sign_up()"><i class="material-icons">&#xE5C4;</i></a>
     <br/> <br/><h2>SIGN UP</h2> <br/>
<p>Welcome</p> <br/>
<h5>Your voice is stronger than passwords!</h5> <br/>
<button TYPE="submit" class="btn_sign_up" name="usertype" value="newuser">GET STARTED</button>

  </div>
 </FORM>
    </div>
    
  </div>
 </div>
</div>
  
  <script src="js/index.js"></script>

</body>
</html>