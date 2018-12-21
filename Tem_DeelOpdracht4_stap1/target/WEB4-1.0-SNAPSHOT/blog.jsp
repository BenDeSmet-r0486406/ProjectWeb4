<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header.jsp">
	<jsp:param name="title" value="Blog" />
</jsp:include>

<main>
	<c:forEach items="${posts}" var="post">
		<div id="${post.id}">
			<div class="body">
				<div class="title">
					<H3>${post.title}</H3>
				</div>
				<div class="text">
					<c:forEach items="${post.paragraphs}" var="par">
						<p>${par}</p>
					</c:forEach>
				</div>
			</div>
			<div id="${post.id}_comments" class="comments">
				<c:forEach var="comment" items="${post.comments}">
					<div class="comment">
						<div class="username">
							<p>${comment.name}</p>
						</div>
						<div class="text">
							<p>${comment.text}</p>
						</div>
						<div class="rating">
							<p>${comment.rating}</p>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="entercomment">
				<input type="text" class="comment" name="commentname" id="${post.id}_comment_name" value="Name">
				<input type="text" class="comment" name="commenttext" id="${post.id}_comment_text" value="Comment">
				<input type="number" class="comment" name="rating" id="${post.id}_comment_rating" value="5" min="0" max="10">
				<input type="submit" class="submitcomment" id="${post.id}_comment" value="Send">
			</div>
		</div>
	</c:forEach>

	<script type="text/javascript" src="js/comments.js"></script>
</main>

<aside>
	<div id="messages">

	</div>
</aside>

<jsp:include page="footer.jsp">
	<jsp:param name="title" value="Blog" />
</jsp:include>
