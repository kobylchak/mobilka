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
    <link rel='stylesheet'
          href='https://apimgmtstorelinmtekiynqw.blob.core.windows.net/content/MediaLibrary/Widget/Tracking/styles/tracking.css'/>
    <title>MOBILKA</title>
</head>
<body>
<div class="container">
    <div id="top">
        <div class="row justify-content-around">
            <div class="col d-none d-sm-none d-md-none d-lg-block col-lg-4 col-xl-4 align-self-end">
                <p class="h5"><i class="fas fa-phone-volume"></i>+38(098)50-14-393</p>
            </div>
            <div class="col d-none d-sm-none d-md-none d-lg-block col-lg-4 col-xl-4 align-self-end">
                <p class="h5"><i class="fab fa-weixin"></i>beonconnection@gmail.com</p>
            </div>
            <div class="col col-xs-1 col-sm-1 col-md-1 align-self-center">
                <a class="h4" href="/user/office"><i class="fas fa-user-circle"></i></a>
            </div>
            <div class="col col-xs-1 col-sm-1 col-md-1 align-self-center"><a class="h4"
                                                                             href="/basket/show/${basket.id}">
                <i class="fas fa-cart-arrow-down"></i></a>
            </div>
            <div class="col col-xs-1 col-sm-1 col-md-1 align-self-center">
                <c:url value="/logout" var="logoutUrl"/>
                <a class="h4" href="${logoutUrl}"><i class="fas fa-sign-out-alt"></i></a>
            </div>
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
        <a href="/" class="navbar-brand">
            <img src="<c:url value="/static/mob.png"/>" height="32" width="160" alt="logo">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">

                <li class="nav-item active ">
                    <a href="/" class="nav-link">HOME</a>
                </li>
                <li class="nav-item">
                    <a href="/user/office" class="nav-link">OFFICE</a>
                </li>
                <li class="nav-item">
                    <a href="/new" class="nav-link">NEW PHONES</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        BRANDS
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/">All</a>
                        <c:forEach items="${brands}" var="brand">
                            <a class="dropdown-item" href="/${brand.id}">${brand.name}</a>
                        </c:forEach>
                    </div>
                </li>
                <li class="nav-item">
                    <a href="/contacts" class="nav-link">CONTACTS</a>
                </li>
            </ul>
            <form action="/mobile/search" method="post" class="d-none d-lg-block form-inline my-2 my-lg-0">
                <input type="text" name="pattern" class="form-control mr-sm-2" placeholder="search mobiles by model"
                       aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0">Search</button>
            </form>
        </div>
    </nav>
    <nav row class="navbar navbar-expand-lg navbar-light bg-light justify-content-around">
        <a class="btn btn-outline-secondary" href="/user/update" role="button">Update data</a>
        <a class="btn btn-outline-secondary" href="/user/orders" role="button">Orders</a>
        <a class="btn btn-outline-secondary" href="/user/cash" role="button">Refill</a>
    </nav>
    <div class="row justify-content-around">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-4 col-xl-4 align-self-center">
            <table class="table table-hover table-dark">
                <thead>
                <tr>
                    <th scope="col">login</th>
                    <th scope="col">${user.login}</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>name</td>
                    <td>${user.name}</td>
                </tr>
                <tr>
                    <td>surname</td>
                    <td>${user.surname}</td>
                </tr>
                <tr>
                    <td>email</td>
                    <td>${user.email}</td>
                </tr>
                <tr>
                    <td>phone</td>
                    <td>${user.phone}</td>
                </tr>
                <tr>
                    <td>address</td>
                    <td>${user.address}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <c:if test="${update}">
            <div class="update col-xs-12 col-sm-12 col-md-6 col-lg-4 col-xl-4 align-self-center">
                <form role="form" class="form-horizontal" content="application/x-www-form-urlencoded"
                      accept-charset="UTF-8"
                      action="/user/update" method="post">
                    <fieldset>
                        <legend>Contact Information</legend>
                        <label><input type="email" name="email" value="${user.email}">E-mail</label>
                        <label><input type="text" name="phone" value="${user.phone}">Phone</label>
                        <label><input type="text" name="address" value="${user.address}">Address</label>
                    </fieldset>
                    <div class="form-group"><input type="submit" class="btn btn-primary" value="Update"></div>
                </form>
            </div>
        </c:if>
        <c:if test="${cashback}">
            <div class="update col-xs-12 col-sm-12 col-md-6 col-lg-4 col-xl-4 align-self-center">
                <form role="form" action="/user/cash" method="POST">
                    <c:if test="${notnumber}">
                        <div align="center" class="alert alert-danger" role="alert">
                            Value is not numeric! Try again.
                        </div>
                    </c:if>
                    <h6>Refill Account</h6>
                    <input type="text" name="quanto" required>
                    <input type="submit" class="btn btn-success" value="Refill">
                </form>
            </div>
        </c:if>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-4 col-xl-4 align-self-center">
            <table class="table table-hover table-dark">
                <thead>
                <tr>
                    <th scope="col">money:</th>
                    <th scope="col">${UAH.money} UAH</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>cashback:</td>
                    <td>${cash.money} UAH</td>
                </tr>
                </tbody>
            </table>
        </div>
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
<script type='text/javascript' id='track' charset='utf-8' data-lang='ua' apiKey='d8a141409c1b2cf3ffa6c5ebe2b3387c'
        data-town='city-not-default' data-town-name='undefined' data-town-id='undefined'
        src='https://apimgmtstorelinmtekiynqw.blob.core.windows.net/content/MediaLibrary/Widget/Tracking/dist/track.min.js'></script>
</body>
</html>