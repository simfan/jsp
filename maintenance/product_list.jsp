<html>
<!--
  product_list.htm
  Chris Glock
  4/24/2007
  This is the product list for Maintain Products Records
  4/16 update:  The records will be read from the products.txt file, and clicking on the edit links will pass the
  				appropriate code parameter onto product.jsp
  4/24 update:  The delete link now will take the user from this page to the DeleteServlet, where the product.txt file will            be updated.
  5/10 update:  Tags for reiteration are used instead of the for loop.
 --->

<head><title>Maintain Product Records Product List - Chris Glock</title></head>
<body>
	<%@ page import="business.*, data.*, java.util.*" %>

	<%@ taglib uri="../WEB-INF/tlds/maintenance.tld" prefix="cdg" %>

	<% String productFile = application.getInitParameter("fileName"); %>

	<%
  	   Vector products = new Vector();
	   products = ProductIO.readRecords(productFile);
	   session.setAttribute("products", products);
	   String encodedURL = response.encodeURL("/maintenance/servlet/servlets.ControllerServlet");
	 %>



    <h1>Maintain Product Records</h1>
    <form name = "productList" action = "<%=encodedURL%>" method = "post">
    <table border = "1">
       <tr>
          <td>Product Code</td>
          <td>Description</td>
          <td>Price</td>
          <td></td>
       </tr>

	   <cdg:list>
	   <tr>
          <td><%= code %></td>
          <td><%= artist %>-<%= title %></td>
          <td><%= priceCurrency %></td>
          <td><a href = "<%=encodedURL%>?code=<%= code %>&Add=Edit" name = "Add">Edit</a></td>
          <td><a href = "<%=encodedURL%>?code=<%= code %>&Delete=Delete" name = "Delete">Delete</a></td>
       </tr>
       </cdg:list>
    </table>
    <br />
    <input type=submit value="Back to Menu" name = "Index" ></input>
    </form>

    <jsp:include page="/includes/footer.jsp" flush ="true" />
</body>
</html>