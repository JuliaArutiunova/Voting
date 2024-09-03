<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<head>
<meta charset="utf-8">
</head>
<body>
<h1>Результаты:</h1>
<h3>Любимая группа</h3>
<table style="width:30%">
               <tr>
                 <th>Исполнитель</th>
                 <th>Набрано голосов</th>
               </tr>
             <c:forEach items="${artists}" var="item">
               <tr>
                 <td style="text-align:center;">${item.getKey()}</td>
                 <td style="text-align:center;">${item.getValue()}</td>
               </tr>
             </c:forEach>
</table>
<h3>Любимый жанр</h3>
<table style="width:30%">
               <tr>
                 <th>Жанр</th>
                 <th>Набрано голосов</th>
               </tr>
             <c:forEach items="${genres}" var="item">
               <tr>
                  <td style="text-align:center;">${item.getKey()}</td>
                  <td style="text-align:center;">${item.getValue()}</td>
               </tr>
             </c:forEach>
</table>
 <h3>Комментарии:</h3>
 <c:forEach items="${comments}" var="item">
    <p>${item.getAuthor()} пишет:<br>${item.getText()}</p>
 </c:forEach>

</body>
</html>
