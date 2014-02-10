package tags;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import business.Product;
import data.ProductIO;

/*
	Category.java
	Chris Glock
	5/10/2007
	This servlet will be called by the category tag.  This will determine which music category will be selected on product.jsp
*/
public class Category extends BodyTagSupport
{


	public int doStartTag()
	{
		Product product = (Product) pageContext.findAttribute("product");
		pageContext.setAttribute("cSelect",  "");
		pageContext.setAttribute("fSelect",  "");
		pageContext.setAttribute("rSelect",  "");
		pageContext.setAttribute("oSelect",  "");


		if (product.getCategory().equalsIgnoreCase("Country"))
		{
			pageContext.setAttribute("cSelect", "selected");
		}

		else if(product.getCategory().equalsIgnoreCase("Folk"))
		{
			pageContext.setAttribute("fSelect", "selected");
		}

		else if (product.getCategory().equalsIgnoreCase("Rock"))
		{
			pageContext.setAttribute("rSelect", "selected");
   		}
		else if (product.getCategory().equalsIgnoreCase("Other"))
		{
			pageContext.setAttribute("oSelect", "selected");
		}
		else
		{
			pageContext.setAttribute("rSelect", "selected");
   	 	}

   	 	return EVAL_BODY_INCLUDE;
   	}

}