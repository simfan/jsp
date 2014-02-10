<html>
<!--
  product.htm
  Chris Glock
  4/2/2007
  This is where users can add or update product information for Maintain Products Records
 --->

<head><title>Maintanin Product Records Product - Add/Update Product Information- Chris Glock</title>
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
								errorField.focus();
								firstError = true;
							}
							//return;
							continue;
						}
						//if the field being checked has a numeric property, then perform the following check.
						if(e.numeric)
						{
							var v = e.value;
							if (isNaN(v))
							{
								errors = true;
								alert("The price must be numeric.");
								e.select();
							}
							else
							{
								if (v <= 0)
								{
									errors = true;
									alert("The price cannot be 0 or lower.");
									e.select();
								}
								else
								{
									if (invalidDecimals(v))
									{
										errors = true;
										alert("There cannot be more than 2 decimal places.");
										e.select();
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

		</script>
</head>
<body>
	<%@ page import="business.*, data.*, java.util.*" %>
	<%! String productFile = "../webapps/maintenance/WEB-INF/etc/product.txt"; %>
	<%
		String code = request.getParameter("code");

		if (code != null)
		{
			for (int i = 0; i <products.size()
		<%		ProductIO.readRecord("code", "../webapps/maintenance/WEB-INF/etc/product.txt");

		}
		else
		{

			ProductIO.readRecord("code", productFile);
		}
	%>
    <h1>Maintain Product Records</h1>
    <br />
    <form name = "product" action = "product_list.jsp" method = "post" onsubmit= "this.price.numeric = true; return verify(this);">

    <table border = "0">

    <tr><td align = right>Product Code:</td><td><input type = "text" name = "pcode" id = "pcode" size = "20" maxlength = "4" value = "<%= code %>"></input></td></tr>

    <tr><td align = right>Artist:</td><td><input type = "text" name = "artist" id = "artist" size = "50" maxlength = "50" value = "<%= product.getArtist() %>"></input></td></tr>

    <tr><td align = right>Album Title:</td><td><input type = "text" name = "album" id = "album" size = "50" maxlength = "50" value = "<%= product.getTitle() %>"></input></td></tr>

    <tr><td align = right>Product Category:</td><td><select name = "category" id ="category">
                                        <option value = "Country">Country</option>
                                        <option value = "Folk">Folk</option>
                                        <option value = "Rock" selected>Rock</option>
                                        <option value = "Other">Other</option>
                                     </select></td></tr>

    <tr><td align = right>Product Description:</td><td><textarea name = "description" id = "description" rows = "5" cols = "70" ><%= product.getDescripton() %></textarea></td></tr>

    <tr><td align = right>Product Price:</td><td><input type = "text" name = "price" id = "price" size = "20" maxlength = "6" value = <%= product.getPrice() %>></input></td></tr>

    <tr></tr>
    <tr></tr>

    </table>
    <input type = "submit" value = "Update Product"></input>
    <input type = "button" value = "View All Products" onclick = "location.href = 'product_list.jsp'"></input>
    </form>

</body>
</html>