<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Menu Dock</title>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<script type="text/javascript">
	<%@ include file="/WEB-INF/jsp/start/hide_menu.js" %>
</script>
<script type="text/javascript">
	<%@ include file="/WEB-INF/jsp/start/game_logic.js" %>
</script>
<style type="text/css">
	<%@ include file="/WEB-INF/jsp/start/styles.css" %>
	
.bin {
	background-repeat: no-repeat;
	height: 50px;
	width: 50px;
	color: black;
	opacity: 1.0;
}

.target {
	background-image: url(http://old.parkaventura.ru/tmp/start/325px-Glass_button_red.svg.png);
	background-repeat: no-repeat;
	height: 50px;
	width: 50px;
	color: black;
}

</style>
</head>

<body>
<div id="hit_area" onmouseover="toggleDown();"></div>
   <div id="menu_holder">
      <div id="nav">
           <ul>
           <li><a id="start" onclick="ballStart()" href="#">Start</a></li>
           <li><a onclick="ballStop()" href="#">Stop</a></li>
		   <li><a href="">Restart</a></li>
		   <li><a href="">About</a></li>
           <li><a href=""></a></li>
		   </ul>
      </div>
</div>
<div id="hit_area2" onmouseover="toggleUp();">
<div id="content">
<h1 style="margin-left: 90px;">Place your mouse over the cap to browse the menu</h1></div>
</div>
<div style="margin-top: 10px;">
	<table id="table" style="margin-left:450px; margin-top:120px;">
<%--
	<%
		for (int i = 0; i < 10; ++i) {
			%><tr>
			<%
				for (int j = 0; j < 10; ++j) {
					%>	<td><img id="bin-<%= i*10 + j %>" src="http://old.parkaventura.ru/tmp/start/_bla628.jpg" 
								 class="bin"></td> <%
				} 
			%>
			</tr><%
		}
	%>
--%>
    	<c:forEach items="${model.gridEntities}" var="gridEntitiesList">
    		<c:forEach items="${gridEntitiesList}" var="gridEntity">
   			<tr>
   				<td><img id="bin-<c:out value="${gridEntity.cellId}"/>" 
   						 src="<c:out value="${gridEntity.cellValue}"/>|<c:out value="${gridEntity.cellType}"/>" 
						 class="bin"></td>
   			</tr>
    		</c:forEach>
   	   	</c:forEach>
	</table>
	<img id="target" height="45" width="45" src="http://old.parkaventura.ru/tmp/start/325px-Glass_button_red.svg.png"/>
	<p id="result"></p>
</div>

<script type="text/javascript">
	init();
</script>
</body>
</html>