<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<body>
<head>
    <meta charset="UTF-8">
</head>
<h2>Редактировать форму</h2>
<form action="edit" method="POST">
<p>Артист: </p>
    <select name="artist">
        <c:forEach items="${artists}" var="item">
            <option value="<c:out value="${item.key}"/>"><c:out value="${item.value}"/></option>
        </c:forEach>
    </select>
<p>Заменить на: </p>
<input type="text" name="newArtist" placeholder="Заменить на"><br>
<br>
<p>Жанр: </p>
    <select name="genre">
        <c:forEach items="${genres}" var="item">
            <option value="<c:out value="${item.key}"/>"><c:out value="${item.value}"/></option>
        </c:forEach
    </select>
<p>Заменить на: </p>
<input type="text" name="newGenre" placeholder="Заменить на"><br>
<br>
<input type='submit' value='ОК'>
</form>
<br>

</body>
</html>