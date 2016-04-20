<%@ page import="ua.berest.lab3.model.Grade" %>
<%@ page import="ua.berest.lab3.model.Course" %>
<%@ page import="ua.berest.lab3.model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="errorPage.jsp"%>

<link href="css/form.css" rel="stylesheet" type="text/css">

<% Grade grade = (Grade) request.getSession().getAttribute("grade"); %>
<% Course course = (Course) request.getSession().getAttribute("course"); %>
<% Student student = (Student) request.getSession().getAttribute("student"); %>

<form id="myForm" method="POST" action="<%= grade!=null?"DispatcherServlet?action=editGrade":"DispatcherServlet?action=addGrade"%>">
    <fieldset>
        <legend><b><%= grade!=null?"ОЦІНКА:РЕДАГУВАТИ":"ОЦІНКА:СТВОРИТИ"%></b></legend>
        <input type="hidden" name="idName" value="<%= grade!=null?grade.getGradeId():""%>" />
        <input type="hidden" name="courseId" value="<%= course.getCourseId()%>" />
        <input type="hidden" name="studentId" value="<%= student.getStudentId()%>" />

        <div>
            <label for="descriptionName" >Опис:</label>
            <div class="col-lg-9">
                <input type="text" class="form-control" name="descriptionName"  value="<%= grade!=null?grade.getDescription():""%>" id="descriptionName" placeholder="Введіть опис роботи: тест №1" required/>
            </div>
        </div>

        <div>
            <div class="col-lg-9">
                <label for="currentGrade" >Виставте оцінку:</label>
                <select name="currentGrade" id="currentGrade">
                    <% for (int i = 0; i < 6; i++) { %>
                        <% if(grade != null && grade.getCredits() == i) { %>
                            <option selected value="<%= i%>"><%= i%></option>
                        <% } else {%>
                            <option value="<%= i%>"><%= i%></option>
                        <% } %>
                    <% } %>
                </select>
            </div>
        </div>

        <div>
            <button class="btn btn-default"><a href="<%= "DispatcherServlet?action=showAllGrades&courseId="+ course.getCourseId() + "&studentId=" + student.getStudentId() %>">Відміна</a></button>
            <button class="btn btn-primary" type="submit">Підтвердити</button>
        </div>
    </fieldset>
</form>

