<%@ page import="java.util.Date, java.text.DateFormat" %>
<% Date now = new Date();
   DateFormat defaultDate = DateFormat.getDateInstance(DateFormat.LONG);
   String nowString = defaultDate.format(now);
%>
<p><%= nowString %> - Music Store CDs</p>
