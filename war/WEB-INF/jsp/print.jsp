<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head><title><fmt:message key="title"/></title></head>
  <body>
    <h1><fmt:message key="heading"/></h1>
    <p><fmt:message key="greeting"/> <c:out value="${model.now}"/></p>
    <h3>Cells</h3>
    <table border="1">
    	<thead>
    		<td><b>id</b></td>
    		<td><b>type</b></td>
    		<td><b>coord</b></td>
    	</thead>
    	<c:forEach items="${model.cells}" var="cellList">
    		<c:forEach items="${cellList}" var="cell">
   			<tr>
    			<td><c:out value="${cell.id}"/></td>
   				<td><c:out value="${cell.type}"/></td>
   				<td><c:out value="${cell.coord}"/></td>
   			</tr>
    		</c:forEach>
   	   	</c:forEach>
    </table>
  </body>
</html>
