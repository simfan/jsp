<html>
<!--
  product.htm
  Chris Glock
  4/24/2007
  This is where users can add or update product information for Maintain Products Records
  4/16 update: The page will now retrieve the code parameter and if the code is not null, all parameters
               will be filled with the appropriate data from products.txt
  4/24 update: The page will send a valid request to UpdateProductServlet, where the product.txt file will either be written          to or updated.
  5/10 update: Tags are used to call the Product bean and to select the correct category.
 --->

<head><title>Maintain Product Records Product - Add/Update Product Information- Chris Glock</title>
      <script language="Javascript">
			if (top.location.href != self.location.href)
				top.location.href = self.location.href;

	  		//This JavaScript code will verify all required fields in this form.
			function verify(f)
			{
				var errors = false;
				var rField = new Array(6);
				var firstError = false;
				var errorField;
				rField[0] = "product code";
				rField[1] = "artist";
				rField[2] = "album title";
				rField[3] = "";
				rField[4] = "description";
				rField[5] = "price";

				for (var i = 0; i <f.length; i++)
				{
					var e = f.elements[i];
					//check to see if a field is required or optional
					if (e.type == "text" || e.type == "textarea")
					{
						if ((e.value == null) || (e.value == "") || isblank(e))
						{
							errors = true;
							if (!firstError)
							{
								errorField = e;
								alert("Please enter the " + rField[i]);
								errorField.select();
								firstError = true;
							}

							continue;
						}
						//if the field being checked has a numeric property, then perform the following check.
						if(e.numeric)
						{
							var v = e.value;
							if (isNaN(v))
							{
								errors = true;
								if (!firstError)
							    {
									alert("The price must be numeric.");
									e.select();
									firstError = true;
								}

							}
							else
							{
								if (invalidNumber(v))
								{
									errors = true;
									if (!firstError)
 								    {
										alert("The price must be numeric.");
										e.select();
										firstError = true;
									}

								}
								else
								{
									if (v <= 0)
									{
										errors = true;
										if (!firstError)
 								    	{
											alert("The price cannot be 0 or lower.");
											e.select();
											firstError = true;
										}

									}

									else
									{
										if (invalidDecimals(v))
										{
											errors = true;
											if (!firstError)
											{
												alert("There cannot be more than 2 decimal places.");
												e.select();
												firstError = true;
											}

										}
									}
								}
						   	}
						}
					}
				}
				if (!errors)
				{
					alert("The product information has been updated.");
	    			return true;
				}
				else
					{
						return false;
					}

			}
			//Check to see if a required field is blank.

			function isblank(e)
			{
				s = e.value;
				for (var b = 0; b < s.length; b++)
				{
					var c = s.charAt(b);
					if((c != " ") && (c != "\n") && (c != "\t")) {
						return false;
					}
				}
				return true;
			}

			function invalidDecimals(v)
			{

				if (v.indexOf('.') == -1)

				   v += ".";
				dectext = v.substring(v.indexOf('.')+1, v.length);

				if (dectext.length > 2)
					return true;
				else
				    return false;
			}

			function invalidNumber(v)

			{
				if (v.indexOf('e') != -1)
					return true;
				else
					return false;
			}
		</script>
</head>
<body>
	<%@ page import="business.*, data.*, java.util.*" %>

	<%  String productFile = application.getInitParameter("fileName"); %>
	<jsp:useBean id="product" scope="session" class="business.Product" />

	<%
		//String code = request.getParameter("code");
		//Product product = new Product();

		String encodedURL = response.encodeURL("/maintenance/servlet/servlets.ControllerServlet");
		//Determines what category should be selected



	%>
    <h1>Maintain Product Records</h1>
    <br />
    <form name = "product" action = "<%=encodedURL%>?code=<jsp:getProperty name='product' property='code'/>" method = "post" onsubmit="this.price.numeric = true; return verify(this);">

    <table border = "0">

    <tr><td align = right>Product Code:</td><td><input type = "text" name = "pcode" id = "pcode" size = "20" maxlength = "4" value = "<jsp:getProperty name='product' property='code' />"></input></td></tr>

    <tr><td align = right>Artist:</td><td><input type = "text" name = "artist" id = "artist" size = "50" maxlength = "50" value = "<jsp:getProperty name='product' property='artist' />"></input></td></tr>

    <tr><td align = right>Album Title:</td><td><input type = "text" name = "album" id = "album" size = "50" maxlength = "50" value = "<jsp:getProperty name='product' property='title' />"></input></td></tr>
	<%@ taglib uri="../WEB-INF/tlds/maintenance.tld" prefix="cdg" %>

    <tr><td align = right>Product Category:</td><td><select name = "category" id ="category"><cdg:category><option value = "Country"<%= cSelect %>>Country</option>
                                        <option value = "Folk"<%= fSelect %>>Folk</option>
                                        <option value = "Rock"<%= rSelect %>>Rock</option>
                                        <option value = "Other"<%= oSelect %>>Other</option></cdg:category>
                                     </select></td></tr>

    <tr><td align = right>Product Description:</td><td><textarea name = "description" id = "description" rows = "5" cols = "70" ><jsp:getProperty name='product' property='description' /></textarea></td></tr>

    <tr><td align = right>Product Price:</td><td><input type = "text" name = "price" id = "price" size = "20" maxlength = "6" value = "<jsp:getProperty name='product' property='priceNumber' />"></input></td></tr>

    <tr></tr>
    <tr></tr>

    </table>

    <input type = "submit" value = "Update Product" name="Update" ></input>
    <input type = "button" value = "View All Products" name="View" onclick = "location.href = '<%= encodedURL %>?View=View'"></input>
    </form>
    <jsp:include page="/includes/footer.jsp" flush ="true" />

</body>
</html>