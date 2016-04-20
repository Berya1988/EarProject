<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Онлайн портал::Вхід</title>
        <link href="css/welcome.css" rel="stylesheet" type="text/css">
    </head>
    <script>
        function validateForm() {
            if(document.username.username.value == "") {
                alert("User Name should not be blank..");
                document.username.username.focus();
                return false;
            }
        }
    </script>
    <body>
    <%if (request.getParameter("action")!=null && request.getParameter("action").equals("logOut")) {
        request.getSession(true);
    } %>
        <div class="main">

            <div class="content">
                <p class="title"><span class="text"><img src="images/portal2.jpg" width="100" height="100" hspace="10" vspace="10" align="middle"></span></p>
                <p class="title">Портал</p>
                <p class="text">Запрошуємо до навчального онлайн-порталу, де Ви зможете знайти необхідну інформацію про навчальні досягнення будь-якого студента.</p>
                <p class="text">Проект знаходиться в розробці, тому дизайн і функціонал буде постійно оновлюватися.</p>
                <p class="text">За всіма питаннями звертайтесь за адресою: <a href="mailto:Berest_Oleg@mail.ru">Berest_Oleg@mail.ru</a></p>
                <p>&nbsp;</p>

            </div>

            <div class="login_div">
                <p class="title">Для користуванням порталом Вам необхідно ввести свої дані:</p>
                <form class="login_form" name="username" action="DispatcherServlet?action=mainPage" method="POST" onSubmit="return validateForm()">
                    Ім'я: <input type="text" name="username" value="" size="20" placeholder="Type your name"/>
                    <input type="submit" value="Ввійти" />
                </form>

            </div>

            <div class="footer">
                Розробник: Берест Олег, 2016 р.
            </div>
        </div>
    </body>
</html>
