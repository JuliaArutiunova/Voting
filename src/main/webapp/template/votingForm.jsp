<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<body>

<h2>Анкета голосования</h2>
<head>
    <meta charset="UTF-8">
</head>

<form action="voting" method="POST">
    Ваше имя:<br>
    <input type="text" name="firstname" placeholder="Имя" required>
    <br>
    <fieldset>
        <legend>Любимый исполнитель</legend>
        <c:forEach items="${artists}" var="item">
            <input type="radio" name="artist" value="<c:out value="${item.key}"/>"/><span>${item.value}</span><br>
        </c:forEach>
    </fieldset>
    <fieldset>
        <legend>Любиме жанры</legend>
        <p>Выберите 3-5 жанров:</p>
        <c:forEach items="${genres}" var="item">
            <input type="checkbox" name="genre" value="<c:out value="${item.key}"/>"/><span>${item.value}</span></br>
        </c:forEach>
    </fieldset>
    <fieldset>
        <legend>Ваш комментарий</legend>
        <textarea name="txtpole" id="txtpole" cols="70" rows="5" required> </textarea><br>
    </fieldset>
    <input type='submit' value='Отправить'>
</form>

</body>
</html>
