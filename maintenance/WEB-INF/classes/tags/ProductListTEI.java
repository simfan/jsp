package tags;

import javax.servlet.jsp.tagext.*;

/*
	ProductListTEI.java
	Chris Glock
	5/10/2007
	This servlet will be called by the product list tag.  This will create the scripting variables for ProductList.java
*/

public class ProductListTEI extends TagExtraInfo
{
	public VariableInfo[] getVariableInfo(TagData data)
	{
		return new VariableInfo[]
		{
			new VariableInfo("code", "String", true, VariableInfo.NESTED),
			new VariableInfo("artist", "String", true, VariableInfo.NESTED),
			new VariableInfo("title", "String", true, VariableInfo.NESTED),
			new VariableInfo("priceCurrency", "String", true, VariableInfo.NESTED)
		};
	}
}