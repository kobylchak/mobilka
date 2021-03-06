<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/main.css">
    <title>MOBILKA</title>
</head>
<body>
<div class="container">
    <div id="top">
        <div class="row justify-content-around">
            <div class="col-xs-12 align-self-end"><p class="h5"><i class="fas fa-phone-volume"></i>
                +38(098)50-14-393
            </p></div>
            <div class="col-xs-12 align-self-end"><p class="h5"><i class="fab fa-weixin"></i>beonconnection@gmail.com
            </p></div>
        </div>
    </div>
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="<c:url value="/static/podarok.png"/>" class="d-block w-100" alt="">
            </div>
            <div class="carousel-item">
                <img src="<c:url value="/static/cashback.png"/>" class="d-block w-100" alt="">
            </div>
            <div class="carousel-item">
                <img src="<c:url value="/static/dostavka.png"/>" class="d-block w-100" alt="">
            </div>
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
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
        <a href="#" class="navbar-brand">
            <img src="<c:url value="/static/mob.png"/>" height="32" width="160" alt="logo">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a href="#" class="nav-link">HOME</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">OFFICE</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">NEW PHONES</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">BRANDS</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">CONTACTS</a>
                </li>
            </ul>
            <form action="#" method="post" class="d-none d-lg-block form-inline my-2 my-lg-0">
                <input type="text" name="pattern" class="form-control mr-sm-2"
                       placeholder="search mobiles by model"
                       aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0">Search</button>
            </form>
        </div>
    </nav>
    <div align="center">
        <c:url value="/j_spring_security_check" var="loginUrl"/>
        <form action="${loginUrl}" method="POST">
            Login:<br/><input type="text" name="j_login"><br/>
            Password:<br/><input type="password" name="j_password"><br/>
            <input type="submit" value="Login" class="btn btn-outline-success"/>
            <a class="btn btn-outline-success" href="/register" role="button">Register</a>
            <c:if test="${param.error ne null}">
                <p class="text-danger">Wrong login or password!</p>
            </c:if>
            <c:if test="${exists ne null}">
                <p class="text-danger">User already exists!</p>
            </c:if>
            <c:if test="${param.logout ne null}">
                <p>Chao!</p>
            </c:if>
        </form>
    </div>
    <div class="container footer fixed-bottom sticky-bottom">
        <div class="row justify-content-between">
            <div class="col d-none d-sm-none d-md-block">
                <p>&copy; 2019</p>
            </div>
            <div class="col">
                <p><i class="fab fa-java"></i> java</p>
            </div>
            <div class="col d-none d-sm-none d-md-block">
                <p>v.kobylchak@ukr.net</p>
            </div>
            <div class="col">
                <p>Kobylchak</p>
            </div>
            <div class="col">
                <p><strong>+380985014393</strong></p>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>