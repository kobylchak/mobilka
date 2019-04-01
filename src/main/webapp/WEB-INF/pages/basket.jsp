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
    <title>Basket page</title>
</head>
<body>
<div class="container">
    <div id="top">
        <div class="row justify-content-around">
            <div class="col-xs-12 align-self-end"><p class="h5"><i class="fas fa-phone-volume"></i> +38(098)50-14-393
            </p></div>
            <div class="col-xs-12 align-self-end"><p class="h5"><i class="fab fa-weixin"></i>beonconnection@gmail.com
            </p></div>
            <div class="col-xs-4 align-self-end">
                <c:url value="/logout" var="logoutUrl"/>
                <p class="h3"><a href="${logoutUrl}"><i class="fas fa-sign-out-alt"></i></a></p></div>
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
    <div align="center">
        <c:choose>
            <c:when test="${emptybasket}">
                <div class="alert alert-dark" align="center" role="alert">
                    Your basket is empty!
                </div>
                <a class="btn btn-outline-dark" href="/" role="button">Back</a>
            </c:when>
            <c:when test="${notEnoughMoney}">
                <div class="alert alert-danger" align="center" role="alert">
                    Not enought money !!! Please, go to your office and refill money!
                </div>
                <a class="btn btn-outline-dark" href="/" role="button">Back</a>
            </c:when>
            <c:otherwise>

                <div class="alert alert-dark" align="center" role="alert">
                    Mobiles in your basket:
                </div>
                <c:forEach items="${basket.phones}" var="phone">
                    <div class="card mb-3" style="max-width: 540px;">
                        <div class="row no-gutters">
                            <div class="col-md-4">
                                <img src="${phone.mobile.path}" class="card-img" alt="${phone.mobile.name}">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <h5 class="justify-content-between card-title">${phone.mobile.name}
                                        <a href="/basket/delete/${phone.id}"> <i class="far fa-times-circle"></i></a>
                                    </h5>
                                    <p class="card-text">${phone.mobile.description}</p>
                                    <p class="card-text">
                                        <small class="text-muted">There are ${phone.mobile.countForSale} for sale.
                                        </small>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">smart choice</small>
                        </div>
                    </div>
                    <hr/>
                </c:forEach>
                <div class="alert alert-success" role="alert">
                    total quantity: ${basket.totalQuantity} total price: ${basket.totalPrice} UAH
                </div>
                <a class="btn btn-dark" href="/" role="button">Back</a>
                <a class="btn btn-success" href="/basket/buy" role="button">Buy</a>
                <div class="alert alert-light" role="alert">
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="container footer fixed-bottom sticky-bottom">
        <div class="row justify-content-between">
            <div class="col d-none d-sm-none d-md-block">
                <p>&copy;</p>
            </div>
            <div class="col d-none d-sm-none d-md-block">
                <p>2019</p>
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