<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agente</title>
<script src="http://code.jquery.com/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>




<style>

#principal{
	background: url("https://i.ytimg.com/vi/Bf3i8oJBNg0/maxresdefault.jpg"); 
	background-repeat: no-repeat;
	background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
}
#footer {
    width: 100%;
    height: 20px;
    position: absolute;
    bottom: 0;
    left: 0;
}
#fondo{
    width: 97%;
    height: 315px;
    top: -10;
    bottom: 0;
    left: 0;
	background: url("http://andres-ortega.com/wp-content/uploads/2013/02/5-claves-binomio-conversaciones-web-social.jpg") repeat-x top;
	background-repeat: no-repeat;
	background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
}
#etsit{
	float:left;
}

</style>

</head>

<body id="principal">
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header" width="100px">
      <a class="navbar-brand" href="http://www.upm.es/institucional"><span class="glyphicon glyphicon-education" aria-hidden="true"></span>   U.P.M   </a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="http://www.gsi.dit.upm.es/es/"><span class="glyphicon glyphicon-book" aria-hidden="true"></span>  G.S.I  </a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Prueba1
          <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
          </ul>
        </li>
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
      <li><a href="http://www.gsi.dit.upm.es/es/" ><img src="gsi.png" alt="GSI" width="30" height="30"></a></li>
       <!--  <li><a href="http://www.gsi.dit.upm.es/es/">  G.S.I  </a></li> -->
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>   Login   <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Entrar</a></li>
            <li><a >Registrar</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleLogin">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleLogin">Usuarios</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="recipient-name" class="control-label">Nombre:</label>
            <input type="text" class="form-control" id="recipient-name" placeholder="Nombre de usuario">
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label" >contraseña:</label>
            <input  class="form-control" id="message-text" placeholder="Contraseña de usuario"></input>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        <button type="button" class="btn btn-primary">Entrar</button>
      </div>
    </div>
  </div>
</div>



	
	
<div class="row" >
	<c:if test="${json.response==null}">
	<% response.sendRedirect("Agent");%>
	</c:if>
	<div class="col-md-3">
	<center>
	<!--  <a href="http://www.gsi.dit.upm.es/es/" ><img src="gsi.png" alt="GSI" width="85" height="85"></a><br>
	-->
	<br>
	<br>
	<img src="http://cdn.flaticon.com/png/256/33764.png" alt="GSI" width="260" height="260">
	</center>
	</div>


<div class="col-md-6">
<div id="fondo">
	<center>
		<h1>Agente para configurar una casa</h1>
		<br>
	</center>
     

	<center>
  		<textarea name="agente" type="text" cols="40" rows="7" id="address">
  	
  		<c:out value="${json.response}"/>
  		</textarea>
	</center>
	<br>
	<br>
	<br>
	<br>
	<br>
	<% int lorena=0; %>
	<form action="Agent" method="get">
	<center>
		<c:forEach var="menu" items="${json.menu}" >
		
			<button class="btn btn-primary dropdown-toggle" style="width:600px; height:40px; MARGIN-TOP: 4px; " name="q" value="<%= lorena %>">
    			${menu}
  			</button>
			<% lorena= lorena+1; %>
		</c:forEach>
	</center>
	</form>
	
	</div>
</div>
<div class="col-md-1"  >

<a href="http://www.etsit.upm.es/index.php/es/">
	<img src="http://satelec.etsit.upm.es/wp-content/uploads/2015/10/etsiteleco_new-1_1423581365.gif" alt="ETSIT" width="170" height="100%"></a>

</div>
</div>


<div id="footer">
     <center> <small>&copy; Estudiante. Universidad Politécnica de Madrid. Curso 2013-2014.</small></p>
</center>
</div>

</body>
</html>
