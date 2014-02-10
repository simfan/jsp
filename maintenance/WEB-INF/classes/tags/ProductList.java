package tags;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;
import java.util.*;
import java.text.NumberFormat;
import business.Product;
import data.ProductIO;

/*
	ProductList.java
	Chris Glock
	5/10/2007
	This servlet will be called by the product list tag.  This will display all records without a loop.
*/
public class ProductList extends BodyTagSupport
{
	private Vector products;
	private int count;
	private Product product;

	private static NumberFormat currency = NumberFormat.getCurrencyInstance();

	public int doStartTag()
	{
		products = (Vector)(pageContext.findAttribute("products"));
		if (products.size() <= 0)
		{
			return SKIP_BODY;
		}
		else
		{
			return EVAL_BODY_BUFFERED;
		}
	}

	public void doInitBody()
	{
		count = 0;
		product = (Product) products.get(count);
		pageContext.setAttribute("code", product.getCode());
		pageContext.setAttribute("artist", product.getArtist());
		pageContext.setAttribute("title", product.getTitle());
		double price = product.getPrice();
		String priceAsString = currency.format(price);
		pageContext.setAttribute("priceCurrency", priceAsString);
		count++;
	}

	public int doAfterBody() throws JspException
	{
		try
		{
			if (count < products.size())
			{
				product = (Product) products.get(count);
				pageContext.setAttribute("code", product.getCode());
				pageContext.setAttribute("artist", product.getArtist());
				pageContext.setAttribute("title", product.getTitle());
				double price = product.getPrice();
				String priceAsString = currency.format(price);
				pageContext.setAttribute("priceCurrency", priceAsString);
				count++;
				return EVAL_BODY_AGAIN;
			}
			else
			{
				JspWriter out = bodyContent.getEnclosingWriter();
				bodyContent.writeOut(out);
				return SKIP_BODY;
			}
		}
		catch(IOException ioe)
		{
			System.err.println("IOException doAfterBody: " + ioe);
			return SKIP_BODY;
		}
	}
}