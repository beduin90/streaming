<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: beduin90
  Date: 04.06.2016
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="<c:url value="/resources/css/font-awesome/css/font-awesome.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/agency.css" />" rel="stylesheet">
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">


    <script type="text/javascript">
        function loadApplet()
        {

alert("Load Applet");
          //  document.write("<applet type='applet' codebase='.' code='applet.HelloWorld.class' height='800' width='600'></applet>");

        }
    </script>



</head>


<body id="page-top" class="index">

<!-- Navigation -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand page-scroll" href="#page-top">Streaming Website</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="hidden">
                    <a href="#page-top"></a>
                </li>
                <li>
                    <a  href="sign_in">Sign in </a>
                </li>
                <li>
                    <a  href="login">Login </a>
                </li>

                <li>
                    <a  href="streams">Yours Streams </a>
                </li>
                <li>
                    <a  href="contact">Contact</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>

<!-- Header -->


<!-- Services Section -->
<section id="services">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">

                <div class="form-inline" role="form">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span> </div>
                            <input class="form-control" placeholder="Enter nick" type="text">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-default">Search</button>
                </div>


            </div>
            <div class="col-sm-6">
                <table class="table">
                    <thead>
                    <tr>
                        <th></th>
                        <th>NICK
                        <th>Email</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="success" id="row1">
                        <td><img src="<c:url value="/resources/images/avatar.jpg"/>"></td>
                        <td><h4>Doe</h4></td>
                        <td>john@example.com</td>
                        <TD><button type="button" class="btn btn-success "><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
                       <button type="button" disabled class="btn btn-danger del"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span></button> </TD>
                    </tr>
                    <tr class="danger" id="row2" >
                        <td><img src="<c:url value="/resources/images/avatar.jpg"/>"></td>
                        <td class="vert-align"><h4>Moe</h4></td>
                        <td class="vert-align">mary@example.com</td>
                        <TD ><button type="button"  id="r" class="btn btn-success "><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
                            <button type="button" disabled class="btn btn-danger del "><span class="glyphicon glyphicon-minus" aria-hidden="true"></span></button> </TD>
                    </tr>
                    <tr class="success" id="row3">
                        <td><img src="<c:url value="/resources/images/avatar.jpg"/>"></td>
                        <td><h4>Dooley</h4></td>
                        <td>july@example.com</td>
                        <TD><button type="button"   class="btn btn-success "><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
                            <button type="button" disabled class="btn btn-danger del"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span></button> </TD>
                    </tr>
                    </tbody>
                </table>


            </div>
            <div class="col-sm-6" id="STREAMS">
                <ul id="draggablePanelList" style="list-style-type: none;"  class="list-unstyled">
                </ul>



            </div>

        </div>

    </div>

</section>

<jsp:plugin type="applet" codebase="." code="aplet.HelloWorld.class"
            width="400" height="400">
    <jsp:fallback>
        <p>Unable to load applet</p>
    </jsp:fallback>
</jsp:plugin>

<footer>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <span class="copyright">Copyright &copy; Your Website 2014</span>
            </div>

            <div class="col-md-6">
                <ul class="list-inline quicklinks">
                    <li><a href="#">Privacy Policy</a>
                    </li>
                    <li><a href="#">Terms of Use</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</footer>




<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

<script>

    jQuery( document ).ready(function( $ ) {


        document.onreadystatechange = function () {
            if (document.readyState == "interactive") {
                initApplication();
            }
        }

            var i=1;
        var accept=$('#STREAMS');
        $(".btn-success").click(function() {

            var tr = $(this).closest('tr').attr('id');
            var add=$('#draggablePanelList');



            add.before(' <li class="panel panel-info" style="list-style-type: none;"  id="'+tr+'c'+'"> '+
                    '<div class="panel-heading">'+i+'</div>'+
                    '<div class="panel-body">HEllo</div>'+
                    ' </li>');


            if (i==1)
            {
                accept.after('<button onclick="loadApplet()">Click here</button>');
        }

                    i++;
            $(this).attr('disabled','disabled');
            $(this).next().removeAttr('disabled');
        });



        $(".del").click(function() {
            var $tr = $(this).closest('tr').attr('id');

            var element=$('#'+$tr+'c');
            if (element!=null)
            {
                element.remove();
            }

            $(this).prev().removeAttr("disabled");
            $(this).attr('disabled','disabled');

        });





    });


</script>


</body>


</html>
