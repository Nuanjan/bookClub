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
<title>New Book</title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">

</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
    	<h1>Add a Book to Your Shelf!</h1>
 		<form:form action="/books/create" method="post" modelAttribute="book">
	 		<div>
	        	<form:errors path="title" class="text-danger"></form:errors><br />
	        	<form:label path="title" class="w-25">Title: </form:label>
	        	<form:input type="text" path="title"></form:input>
	 		</div>
	 		<div>
	        	<form:errors path="author" class="text-danger"></form:errors><br />
	        	<form:label path="author" class="w-25">Author: </form:label>
	        	<form:input type="text" path="author"></form:input>
	 		</div>
	 		<div>
        		<form:errors path="thoughts"  class="text-danger"/><br />
	        	<form:label path="thoughts" class="w-25">Your Thoughts:</form:label>
       	 		<form:textarea path="thoughts"/>
	 		</div>
	 		<input name="user" type="hidden" value="${user.id}">
        	<input type="submit" value="Create" class="btn btn-primary" />
        </form:form>  
    </div> <!-- End of Container -->
</body>
</html>
