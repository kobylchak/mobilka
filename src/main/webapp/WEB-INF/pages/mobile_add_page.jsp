<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>New Mobile</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form role="form" class="form-horizontal" content="application/x-www-form-urlencoded" accept-charset="UTF-8"
          action="/mobile" method="POST">
        <h3>New mobile</h3>
        <select class="selectpicker form-control form-group" name="brand" required>
            <c:forEach items="${brands}" var="brand">
                <option value="${brand.id}">${brand.name}</option>
            </c:forEach>
        </select>
        <input class="form-control form-group" type="text" name="name" required placeholder="mobile model">
        <input class="form-control form-group" type="text" name="price" required placeholder="enter price">
        <input class="form-control form-group" type="text" name="path" required
               placeholder="enter path of mobile's picture">
        <input class="form-control form-group" type="text" name="color" required placeholder="enter color">
        <input class="form-control form-group" type="text" name="description" value="description...">
        <select class="selectpicker form-control form-group" name="status" required>
            <option value="NOTNEW">NOT NEW MOBILE</option>
            <option value="NEWMOBILE">NEW MOBILE</option>
        </select>
        <input class="form-control form-group" type="text" name="discount" required value="0">
        <input type="submit" class="btn btn-primary" value="Add">
    </form>
    <a class="btn btn-outline-dark" href="/mobile" role="button">Back</a>
</div>
</body>
</html>