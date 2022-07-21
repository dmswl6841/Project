<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> <tiles:getAsString name="title"/> </title>
<style>

</style>
</head>
<body>
           



  	<tiles:insertAttribute name="header"/>
	<section>
	  <tiles:insertAttribute name="menu"/> 
	  <article>
	  	<tiles:insertAttribute name="body"/> 
	  </article>
	</section>
	<tiles:insertAttribute name="footer"/>
	
	 
</body>
</html>