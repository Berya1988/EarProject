<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="errorPage.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Learning Portal</title>
    <link href="css/template.css" rel="stylesheet" type="text/css">
</head>
    <body>

    <div class="wrapper">

        <header class="header">
            <div class="logo">
                <img src="images/portal.jpg" alt="Логотип"  width="130" height="130" name="logo" />
            </div>
            <div class="descr">
                <h1>Всесвітній навчальний онлайн-портал</h1>
            </div>

            <div class="welcome">
                Ласкаво просимо, <%=request.getSession().getAttribute("username")%> !<br/>
                <a href="DispatcherServlet?action=logOut">Вихід</a>
            </div>
        </header>

        <div class="middle">

            <div class="container">
                <main class="content">
                    <div>
                        <% String includedJSPName = (String)request.getSession().getAttribute("includedJSPName"); %>
                        <% if (includedJSPName != null) { %>
                            <jsp:include page = "<%= includedJSPName %>" ></jsp:include>
                        <% } %>
                    </div>
                </main>
            </div>

            <aside class="left-sidebar">
                <a href = "DispatcherServlet?action=showAllStudents&page=1"><p class="button">Студенти</p></a>
                <a href = "DispatcherServlet?action=showAllLocations&parentId=0"><p class="button">Країни</p></a>
            </aside>

        </div>

    </div>

    <footer class="footer">
        Розробник: Берест Олег, 2016 р.
    </footer>


    </body>
</html>