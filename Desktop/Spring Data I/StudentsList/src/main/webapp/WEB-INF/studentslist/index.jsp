<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>Index</title>
<script>
	$(document).ready(function() {
		$('.selectdorm').change(function() {
			var action = $(this).val();
			console.log(action);
			$(".form1").attr("action", "/dorms/" + action);
		})
	});
</script>
</head>
<body>
	<div class="container container-fluid" style="margin-top: 20px;">
		<a href="/students/new">New Student</a> 
		<a href="/contact/new" style="margin-left: 4%">Add contact info</a>
		<a href="/dorms/new" style="margin-left: 4%">New dorm</a>
		<h1 style="margin: 10px 0px 30px 0px;">All Students</h1>
		<div class="table-wrapper-scroll-y my-custom-scrollbar"
			style="height: 200px; overflow: auto; margin-bottom:20px;">
			<table
				class="table table-bordered table-hover table-fluid table-striped mb-0">
				<tr>
					<th>Name</th>
					<th>Age</th>
					<th>Address</th>
					<th>City</th>
					<th>State</th>
				</tr>
				<c:forEach var="student" items="${students}">
					<tr>
						<td><c:out value="${student.firstName}" /> <c:out
								value="${student.lastName}" /></td>
						<td><c:out value="${student.age}" /></td>
						<td><c:out value="${student.contact.address}" /></td>
						<td><c:out value="${student.contact.city}" /></td>
						<td><c:out value="${student.contact.state}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<h1>View dorm details</h1>
		<form action="/dorms/${id}" class="form1" style="width:20%;">
			<p class="form-group">
			<select class="selectdorm form-control">
				<c:forEach var="dorm" items="${dorms}">
					<option value="${dorm.id}" label="${dorm.name}"/>
				</c:forEach>
			</select>
			</p>
			<input type="submit" value="View details" class="btn btn-outline-primary form-control"/>
		</form>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>