<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head><title><fmt:message key="title"/></title></head>
  <body>
    <h1><fmt:message key="heading"/></h1>
    <p><fmt:message key="greeting"/> <c:out value="${model.now}"/></p>
    <h3>Cells</h3>
    <table border="1">
    	<thead>
    		<td><b>cellId</b></td>
    		<td><b>cellType</b></td>
    		<td><b>cellValue</b></td>
    	</thead>
    	<c:forEach items="${model.gridEntities}" var="gridEntitiesList">
    		<c:forEach items="${gridEntitiesList}" var="gridEntity">
   			<tr>
    			<td><c:out value="${gridEntity.cellId}"/></td>
   				<td><c:out value="${gridEntity.cellType}"/></td>
   				<td><c:out value="${gridEntity.cellValue}"/></td>
   			</tr>
    		</c:forEach>
   	   	</c:forEach>
    </table>
  </body>
</html>
