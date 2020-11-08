<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Task</title>
    <style>
        <%@include file='profile.css' %>
    </style>
</head>
<body>
<%@include file="_script.jsp"%>
<navbar:NavbarTag/>
<div class="profile_info">
    <div class="row">
        <dt class="col-sm-1">Имя <br> <br>
            <div class="dropdown update">
                <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink20" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    update
                </a>

                <div class="dropdown-menu" aria-labelledby="dropdownMenuLink20">
                    <form method="post">
                    <input type="text" name="name" placeholder="name">
                    </form>
                </div>
            </div>
        </dt>
        <dd class="col-sm-1">${UserName}</dd>

        <dt class="col-sm-1">Email</dt>
        <dd class="col-sm-2">
            <p>${UserEmail}</p>
        </dd>

        <dt class="col-sm-2">Мои записи</dt>
        <ul class="col-sm-2">
        <c:forEach var="a" items="${Appointment}">
            <li><c:out value="${a.getAllName()}"/> <c:out value="${a.getDate()}"/> <c:out value="${a.getTime()}"/></li>
            <form action="${pageContext.request.contextPath}/capsule" method="post">
                <p><input type="image" src="${pageContext.request.contextPath}/images/trash.png" alt="ОК"></p>
                <input type="hidden" name="date" value="<c:out value="${a.getDate()}"/>">
                <input type="hidden" name="time" value="<c:out value="${a.getTime()}"/>">
                <input type="hidden" name="action" value="deleteApp"/>
            </form>
        </c:forEach>
        </ul>
        <dd class="col-sm-1">
            <div class="dropdown update">
                <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    delete
                </a>

                <div class="dropdown-menu" aria-labelledby="dropdownMenuLink2">
                    <form method="post">
                        <input type="text" name="delete" placeholder="Say Yes">
                    </form>
                </div>
            </div>
        </dd>
        <dd class="col-sm-2">
                <a href="<navbar:ContextTag/>/signout" class="btn btn-secondary btn-lg active" role="button" aria-pressed="true">Sign Out</a>
        </dd>
    </div>
</div>
<div class="row">
    <c:forEach var="capsule" items="${capsules}">
        <div class="card col-sm-4" style="width: 18rem;">
            <c:if test="${capsule.getProducts().size() != 0}">
            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                    <c:forEach var="product" items="${capsule.getProducts()}">
                        <c:if test="${capsule.getProducts().indexOf(product) == 0}">
                            <div class="carousel-item active">
                                <img src="${pageContext.request.contextPath}<c:out value="${product.getPath()}" />" class="d-block w-100" alt="...">
                            </div>
                        </c:if>
                        <c:if test="${capsule.getProducts().indexOf(product) != 0}">
                            <div class="carousel-item">
                                <img src="${pageContext.request.contextPath}<c:out value="${product.getPath()}" />" class="d-block w-100" alt="...">
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

        </c:if>
        <div class="card-body">
                <h5 class="card-title"><c:out value="${capsule.getName()}"/></h5>
            </div>
            <c:forEach var="product" items="${capsule.getProducts()}">
                <div class="card-body">
                    <p class="card-text"><c:out value="${product.getName()}" /></p>
                    <form action="${pageContext.request.contextPath}/capsule" method="post">
                    <p><input type="image" src="${pageContext.request.contextPath}/images/trash.png" alt="ОК"></p>
                        <input type="hidden" name="idCap" value="<c:out value="${capsule.getId()}"/>">
                        <input type="hidden" name="idProd" value="<c:out value="${product.getId()}"/>">
                        <input type="hidden" name="action" value="deleteProd"/>
                    </form>
                </div>
            </c:forEach>
            <div class="card-body">
                <div class="dropdown update">
                    <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        update name
                    </a>

                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink4">
                        <form action="${pageContext.request.contextPath}/capsule" method="post">
                            <input  type="text" name="nameCap" placeholder="name">
                            <input type="hidden" name="action" value="name"/>
                            <input type="hidden" name="idCap" value="<c:out value="${capsule.getId()}"/>">
                        </form>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <div class="dropdown update">
                    <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        delete
                    </a>
                    <div class="dropdown-menu update" aria-labelledby="dropdownMenuLink3">
                        <form action="${pageContext.request.contextPath}/capsule" method="post" >
                            <input type="text" name="deleteCap" placeholder="Say Yes">
                            <input type="hidden" name="action" value="deleteCap"/>
                            <input type="hidden" name="idCap" value="<c:out value="${capsule.getId()}"/>">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>