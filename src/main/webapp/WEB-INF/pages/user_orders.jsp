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
                <li class="nav-item">
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
                <li class="nav-item active">
                    <a href="/contacts" class="nav-link">CONTACTS</a>
                </li>
            </ul>
            <form action="/find" method="post" class="d-none d-lg-block form-inline my-2 my-lg-0">
                <input type="text" name="pattern" class="form-control mr-sm-2" placeholder="search mobile by model"
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
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-6 align-self-center">
            <div class="alert alert-success" role="alert">
                <p class="h5">Executed orders!</p>
            </div>
            <table class="table table-hover table-dark">
                <thead>
                <tr>
                    <td>№</td>
                    <td>Model</td>
                    <td>Quantity</td>
                    <td>Price</td>
                    <td>№ Delcaration</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${executed}" var="order">
                    <tr>
                        <td><span class="badge badge-danger">${order.id}</span></td>
                        <td>
                            <c:forEach items="${order.basket.phones}" var="phone">
                                <span class="badge badge-pill badge-success">${phone.mobile.name}</span>
                            </c:forEach>
                        </td>
                        <td>${order.basket.totalQuantity}</td>
                        <td>${order.basket.totalPrice} UAH</td>
                        <td>
                            <span class="badge badge-secondary">${order.declarationNumber}</span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-6 align-self-center">
            <div class="alert alert-success" role="alert">
                <p class="h5">Not executed orders!</p>
            </div>
            <table class="table table-hover table-dark">
                <thead>
                <tr>
                    <td>№</td>
                    <td>Model</td>
                    <td>Quantity</td>
                    <td>Price</td>
                    <td>№ Delcaration</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${notexecuted}" var="order">
                    <tr>
                        <td><span class="badge badge-danger">${order.id}</span></td>
                        <td>
                            <c:forEach items="${order.basket.phones}" var="phone">
                                <span class="badge badge-pill badge-success">${phone.mobile.name}</span>
                            </c:forEach>
                        </td>
                        <td>${order.basket.totalQuantity}</td>
                        <td>${order.basket.totalPrice} UAH</td>
                        <td>
                            <span class="badge badge-secondary">${order.declarationNumber}</span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div id="np-tracking" class="np-w-br-0 np-widget-hz" style="background-color: rgb(241, 241, 241);">
        <div id="np-first-state">
            <div id="np-tracking-logo"></div>
            <div id="np-title">
                <div class="np-h1"><span class="np-h1-span1">ВІДСТЕЖЕННЯ</span> <br> <span
                        class="np-h1-span2">ПОСИЛОК</span></div>
            </div>
            <div id="np-input-container">
                <div id="np-clear-input"></div>
                <input id="np-user-input" type="text" name="number" placeholder="Номер посилки"></div>
            <div id="np-warning-message"></div>
            <button id="np-submit-tracking" type="button" disabled=""
                    style="background-color: rgb(209, 213, 218); border: 1px solid rgb(196, 196, 196); cursor: pointer;">
                <span id="np-text-button-tracking">ВІДСТЕЖИТИ</span>
                <div id="np-load-image-tracking"></div>
            </button>
            <div id="np-error-status"></div>
        </div>
        <div id="np-second-state">
            <div id="np-status-icon"></div>
            <div id="np-status-message"></div>
            <div class="np-track-mini-logo">
                <div class="np-line-right"></div>
                <div class="np-line-left"></div>
            </div>
            <a href="#" id="np-more">Детальніше на сайті</a>
            <div id="np-return-button"><span id="np-return-button-span">Інша посилка</span></div>
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