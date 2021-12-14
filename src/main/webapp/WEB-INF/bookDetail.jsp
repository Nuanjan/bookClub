<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <!-- c:out ; c:forEach ; c:if -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   <!-- Formatting (like dates) -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
   <!-- form:form -->
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
   <!-- for rendering errors on PUT routes -->
 <%@ page isErrorPage="true" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Detail</title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">

</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
    	<div class="row">
    		<div class="col">
		        <h1><c:out value="${book.title}"></c:out></h1>
		        <div>
		        	<h4>
		        	<c:choose>
			        	<c:when test="${sessionScope.current_user == 'true'}">
			        		<span>You </span>
			        	</c:when>
			        	<c:when test="${sessionScope.current_user == 'false'}">
				        	<c:out value="${book.user.userName}"></c:out> 
			        	</c:when>
		        	</c:choose>
		        	read 
		        	<c:out value="${book.title }"></c:out>
		        	 by 
		        	 <c:out value="${book.author}"></c:out> 
		        	 </h4>
		        	 <h5>
		        	 Here are 
		        	 <c:choose>
			        	<c:when test="${sessionScope.current_user == 'true'}">
			        		<span>You </span>
			        	</c:when>
			        	<c:when test="${sessionScope.current_user == 'false'}">
				        	<c:out value="${book.user.userName}"></c:out> 
			        	</c:when>
		        	</c:choose>
		        	  thoughts:
		        	 </h5>
		        	 <hr />
		        </div>
		        <h5><c:out value="${book.thoughts}"></c:out></h5>
    		</div>
    		<div class="col">
    			<a href="/books">back to the shelves</a>
    		</div>
    	</div>
    	<div>
    		<c:if test="${sessionScope.current_user == 'true'}">
    		<div class="d-flex">
    			<a href="/books/${book.id}/edit" class="btn btn-warning mx-3">Edit</a>
    			<form action="/delete/${book.id}" method="post">
    				<input type="hidden" name="_method" value="delete">
    				<input type="submit" value="Delete" class="btn btn-danger" />
    			</form>
    		</div>
    		</c:if>
    	</div>
    </div> <!-- End of Container -->
</body>
</html>