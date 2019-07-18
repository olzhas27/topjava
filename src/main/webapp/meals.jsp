<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Список блюд</title>
</head>
<body>
    <table class="table">
    <thead>
        <th>Дата записи</th>
        <th>Описание</th>
        <th>Калорийность</th>
    </thead>
    <tbody>
        <c:forEach var="meal" items="${mealList}">
            <tr class="<c:out value="${meal.excess ? 'table-danger' : 'table-success'}"/>">
                <td>
                    <javatime:parseLocalDateTime value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm:ss.SSS" var="parsedDateTime"/>
                    <javatime:format value="${parsedDateTime}" pattern="yyyy-MM-dd HH:mm" style="MS" />
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
            </tr>
        </c:forEach>
    </tbody>
    </table>
</body>
</html>