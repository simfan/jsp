<html>
<!--
  index.jsp
  Chris Glock
  4/2/2007
  This is the index for Maintain Products Records
 --->

<head><title>Maintain Product Records Home Page - Chris Glock</title></head>
<body>

	<% String encodedURL = response.encodeURL("/maintenance/servlet/servlets.ControllerServlet"); %>

    <h1>Maintain Product Records</h1>
    <form name = "productUpdate" action = "<%=encodedURL%>?code=' 'method = 'post'">

    <input type=submit value="Add Product" name="Add"></input>
    <br />
    <br />
    <input type=submit value="View All Products" name="View"></input>
    </form>
</body>
</html>