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
<title>Books Dash Board</title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">

</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
    <div class="d-flex justify-content-between">
        <h1>Welcome <c:out value="${user.userName}"></c:out></h1>
        <a href="/">Log Out</a>
    </div>
    <div class="d-flex justify-content-between">
    <h4>Books from everyone</h4>
    <a href="/books/new">+ Add a book to my shelf!</a>
    </div>
    
    <div>
    <table class="table table-dark table-striped">
			<thead>
			    <tr>
			      
			      <th scope="col">ID</th>
			      <th scope="col">Title</th>
			      <th scope="col">Author Name</th>
			      <th scope="col">Posted By</th>
			    </tr>
  			</thead>
  			<c:forEach var="book" items="${books}">
  			<tbody>
			    <tr>
			      <td><c:out value="${book.id}"></c:out></td>
			      <td><a href="/books/${book.id}"><c:out value="${book.title}"></c:out></a></td>
			      <td><c:out value="${book.author}"></c:out></td>
			      <td><c:out value="${book.user.userName}"></c:out></td>
			    </tr>
  			</tbody>
  			</c:forEach>
		</table>
    </div>
    </div> <!-- End of Container -->
</body>
</html>