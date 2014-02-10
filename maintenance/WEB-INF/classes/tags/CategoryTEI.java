package tags;

import javax.servlet.jsp.tagext.*;

/*
	CategoryTEI.java
	Chris Glock
	5/10/2007
	This servlet will be called by the category tag.  This will create the scripting variables for Category.java
*/
public class CategoryTEI extends TagExtraInfo
{
	public VariableInfo[] getVariableInfo(TagData data)
	{
		return new VariableInfo[]
		{
			new VariableInfo("cSelect", "String", true, VariableInfo.NESTED),
			new VariableInfo("fSelect", "String", true, VariableInfo.NESTED),
			new VariableInfo("rSelect", "String", true, VariableInfo.NESTED),
			new VariableInfo("oSelect", "String", true, VariableInfo.NESTED)
		};
	}
}