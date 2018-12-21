<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>

<main>
    <c:if test="${errors.size()>0 }">
        <div class="danger">
            <ul>
                <c:forEach var="error" items="${errors }">
                    <li>${error }</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <c:choose>
        <c:when test="${user!=null}">
            <p>Welcome ${user.getFirstName()}!</p>
            <div id="friends"></div>
            <div id="search"></div>
            <input type="text" id="searchfield">
            <input type="button" id="searchbutton" value="Search"/>
            <script type="module" src="js/friends.js"></script>
            <script type="module" src="js/message.js"></script>
        </c:when>
        <c:otherwise>
            <form method="post" action="Controller?action=login">
                <p>
                    <label for="email">Your email </label>
                    <input type="text" id="email" name="email" value="jan@ucll.be">
                </p>
                <p>
                    <label for="password">Your password</label>
                    <input type="password" id="password" name="password" value="t">
                </p>
                <p>
                    <input type="submit" id="loginbutton" value="Log in">
                </p>
            </form>
        </c:otherwise>
    </c:choose>

</main>

<aside>
    <div id="messages">

    </div>
</aside>

<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
