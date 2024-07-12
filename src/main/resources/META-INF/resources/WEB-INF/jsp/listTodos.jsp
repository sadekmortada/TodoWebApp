<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<h3>${name} Todos:</h3>
	<hr>
	<%@ include file="common/passedMessages.jspf" %>
	<table class="table table-dark table-bordered table-striped text-center">
		<thead>
			<tr>
				<th>id</th>
				<th>description</th>
				<th>target date</th>
				<th>is done?</th>
				<th/>
				<th/>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="todo">
			<tr>
				<td>${todo.id}</td>
				<td>${todo.description}</td>
				<td>${todo.targetDate}</td>
				<td>${todo.done}</td>
				<td>
					<form action="/edit-todo" method="get" class="d-inline">
					 	<input type="hidden" name="id" value="${todo.id}" />
					 	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<button type="submit" class="btn btn-warning">Edit</button>
					</form>
				</td>
				<td>
					<form action="/delete-todo" method="post" class="d-inline">
						 <input type="hidden" name="id" value="${todo.id}" />
						 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<button type="submit" class="btn btn-danger">Delete</button>
					</form>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/add-todo" type="submit" class="btn btn-success">Add Todo</a>
</div>
<%@ include file="common/footer.jspf" %>