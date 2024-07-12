<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
 	<%-- <c:if test="${result.hasErrors()}">
       <div class="alert alert-danger">
           <ul>
               <c:forEach items="${result.allErrors}" var="error">
                   <li><spring:message code="${error.code}" />: <c:out value="${error.defaultMessage}"/></li>
               </c:forEach>
           </ul>
       </div>
   </c:if> --%>
   <div class="container">
	<%@ include file="common/passedMessages.jspf" %>
	<form method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<div class="row mb-2 mt-2">
			<label for="inputUsername" class="col-sm-2">Username</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="inputUsername"
					name="username" value="<c:out value='${todo.username}'/>">
			</div>
		</div>
		<div class="row mb-2">
			<label for="inputDescription" class="col-sm-2">Description</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="inputDescription"
					name="description" value="<c:out value='${todo.description}'/>">
			</div>
		</div>
		<div class="row mb-2">
			<label for="inputDate" class="col-sm-2">Target Date</label>
			<div class="col-sm-10">
				<input type="date" class="form-control" id="inputDate"
					name="targetDate" value="<c:out value='${todo.targetDate}'/>">
			</div>
		</div>
		<div class="row mb-2">
            <label for="inputDone" class="col-sm-2">Done?</label>
            <div class="col-sm-10">
                <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="inputDone" name="done" 
                    <c:if test="${todo.done != null && todo.done}">checked</c:if>>
                </div>
            </div>
        </div>
		<div class="row">
			<div class="col-sm-3">
				<button type="submit" class="btn btn-primary">Save</button>
			</div>
			<div class="col-sm-6"></div>
			<div class="col-sm-3">
				<a href="/list-todos" class="btn btn-success">Go to Todos</a>
			</div>
		</div>
	</form>
</div>
<%@ include file="common/footer.jspf" %>
<!-- <script type="text/javascript">
	$('#inputDate').datepicker({
		format: 'yyy-mm-dd'
	});
</script> -->