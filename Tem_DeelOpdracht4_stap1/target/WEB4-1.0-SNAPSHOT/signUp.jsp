<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="Sign Up" />
</jsp:include>

<main>
	<c:if test="${errors != null}">
		<div class="alert-danger">
			<ul>
				<c:forEach var="error" items="${errors}">
					<li>${error}</li>
				</c:forEach>
			</ul>

		</div>
	</c:if>

	<form method="post" action="Controller?action=register"
		  novalidate="novalidate">
		<p>
			<label for="firstName">First Name</label>
			<input type="text" id="firstName" name="firstName" required value="<c:out value='${firstName}'/>"/>
		</p>
		<p>
			<label for="lastName">Last Name</label>
			<input type="text" id="lastName" name="lastName" required value="<c:out value='${lastName}'/>"/>
		</p>
		<p>
			<label for="email">Email</label>
			<input type="email" id="email" name="email" required value="<c:out value='${email}'/>"/>
		</p>
		<p>
			<label for="gender">Gender</label>
			<select name="gender" id="gender">
				<option value="Male">Male</option>
				<option value="Female">Female</option>
			</select>
		</p>
		<p>
			<label for="age">Age</label>
			<input type="number" id="age" name="age" required value="<c:out value='${age}'/>"/>
		</p>
		<p>
			<label for="password">Password</label>
			<input type="password" id="password" name="password" required>
		</p>
		<p>
			<label for="repeatPassword">Repeat Password</label>
			<input type="password" id="repeatPassword" name="repeatPassword" required>
		</p>
		<p>
			<input type="submit" id="signUp" value="Sign Up">
		</p>

	</form>
</main>

<jsp:include page="footer.jsp">
	<jsp:param name="title" value="Home" />
</jsp:include>
