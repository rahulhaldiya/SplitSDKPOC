<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<link rel="stylesheet" type="text/css"
 href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
<script type="text/javascript">
function generateToken()
{
	var time = new Date();
	var userkey="User_"+time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds();
	document.getElementById("key").value=userkey;
	
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", "http://localhost:8080/user/generate?key="+userkey, false);
	xhttp.onreadystatechange = function () {
	        if (this.readyState == 4 && this.status == 200) {
	        	document.getElementById("token").value = this.responseText;
	        }
	    }
	  xhttp.send();
	  
	  alert('Key= '+userkey);
	  alert('Token= '+document.getElementById("token").value);
}


</script>

</head>
<body>
 <div class="container">
  <header>
   <h1>Welcome in Iframe </h1>
  </header>
  <div class="starter-template">
   <form  method="post" action="/user/validate">
        <input type="Submit" value="Generate" onclick="generateToken()">
        <input type="hidden" id="token" name="token">
        <input type="hidden" id="key" name="key">
  </form>
  </br>
    ${message}
   </div>

 </div>
</body>

</html>