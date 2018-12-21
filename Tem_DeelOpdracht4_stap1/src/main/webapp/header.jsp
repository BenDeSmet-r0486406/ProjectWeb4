<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${param.title }</title>
	<link rel="stylesheet" type="text/css" href="css/sample.css">
	<script
			src="https://code.jquery.com/jquery-3.3.1.min.js"
			integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
			crossorigin="anonymous"></script>
</head>
<body>
<header role="banner">
<img alt="view" src="images\view.jpg">
<h1>
	<span>Chat App</span>
</h1>
<nav>
	<ul>
		<li ${param.title == 'Home' ?  "id=\"actual\"" : ""}><a href="Controller">Home</a></li>
		<li><a href="Controller?action=blog">Blog</a></li>
		<c:if test="${user!=null}">
			<li><a href="Controller?action=logout">Log Out</a></li>
		</c:if>
		<c:if test="${user==null}">
			<li ${param.title == 'Sign Up' ?  "id=\"actual\"" : ""}><a href="Controller?action=signUp">Sign Up</a></li>
		</c:if>
	</ul>
</nav>
<h2>${param.title}</h2>

</header>
<aside id="statusaside">
	<c:if test="${user != null}">
	<div id="status" class="status ${user.state.toString().toLowerCase()}">
		<span id="statustext" class="statustext">
				${user.getStatusMessage()}
		</span>
	</div>
	<select id="statusdropdown">
		<c:forEach items="${statuslist}" var="status">
			<option ${user.state == status ? "selected=\"selected\"" : ""} value="${status}">${status.toString()}</option>
		</c:forEach>
	</select>
	<div id="statustextboxdiv">
		<input type="text" id="statustextbox" name="statustext" value="${user.message}"/>
	</div>
	<input type="button" id="statusbutton" value="Change Status"/>
	<script type="text/javascript" src="js/status.js"></script>
	</c:if>
</aside>
