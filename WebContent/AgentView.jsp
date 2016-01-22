<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.google.gson.JsonArray" %>
<%@page import="com.google.gson.JsonElement" %>
<%@page import="net.sf.json.JSONArray" %>
<%@page import="com.google.gson.JsonObject" %>
<%@page import="edu.wpi.disco.Agenda.Plugin.Item" %>
<%@page import="java.util.Iterator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="net.sf.json.JSONArray" %>
<%@page isELIgnored="false"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agente</title>

<link rel="stylesheet" href="https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-pink.min.css">
<script src="https://storage.googleapis.com/code.getmdl.io/1.0.6/material.min.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
<style>
.demo-card-wide.mdl-card {
  width: 650px;
}
.demo-card-wide > .mdl-card__title {
  color: #2E2EFE;
  height: 350px;
  background: url("http://andres-ortega.com/wp-content/uploads/2013/02/5-claves-binomio-conversaciones-web-social.jpg") center / cover;
}
.demo-card-wide > .mdl-card__menu {
  color: #fff;
}
</style>
</head>
<body bgcolor="#CEECF5">
<jsp:scriptlet>
  String [] elementos = new String []{"Router","Thermostat","SmartTV"};
	pageContext.setAttribute("elementos", elementos);
	int lorena=0;
</jsp:scriptlet>

<center>
<div class="demo-card-wide mdl-card mdl-shadow--2dp">
  <div class="mdl-card__title">
 
    <h2 class="mdl-card__title-text">Agente para configurar una casa</h2>
  </div>
  <div class="mdl-card__supporting-text">
  
<div class="mdl-textfield mdl-js-textfield">
  <textarea name="agente" class="mdl-textfield__input" type="text" rows="4" id="address" onClick='agente.value=""'>
  <c:if test="${json.response==null}">
  
  <c:out value="What do you want to configure?"/>
  
 </c:if>
  <c:out value="${json.response}"/>
  </textarea>
  <label class="mdl-textfield__label" for="address"></label>
</div>
<center>

<c:if test="${json.menu==null}">
<form action="Agent" method="get">
<c:forEach var="menu" items="${elementos}" >
<button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" style="width:500px; height:25px" name="q" value="${menu}">
${menu}</button>
</c:forEach>
</form>
</c:if>
<% lorena=0; %>
<form action="Agent" method="get">

<c:forEach var="menu" items="${json.menu}" >




<button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" style="width:500px; height:25px"name="q" value="<%= lorena %>">
${menu}</button>
<% lorena= lorena+1; %>
</c:forEach>
</form>

</center>
   
  </div>
  </div>

  </body>
</html>
