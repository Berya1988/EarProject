<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>

<html>
<head>
    <title>Помилка</title>
</head>
    <body bgcolor="white">
        <h3>
            Вибачте за незручність, виникли проблеми з сервером:
        </h3>
        <p>
            Причиною помилки було: <% exception.getCause(); %>
        </p>
        <p>
            Деталі:</br> <% exception.printStackTrace(response.getWriter()); %>
        </p>
    </body>
</html>