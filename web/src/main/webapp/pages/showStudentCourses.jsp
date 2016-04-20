<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.berest.lab3.model.Course" %>
<%@ page import="ua.berest.lab3.model.Student" %>
<%@ page errorPage="errorPage.jsp"%>

<% Student student = (Student) request.getSession().getAttribute("student"); %>

<form method="POST">
    <table border="1" width="100%" cellpadding="5" bgcolor="#ffcc00">
        <tr>
            <th id="col1" ><a href="DispatcherServlet?action=showAllStudents&page=1">Студенти</a> :  <%= student.getFio()%></th>
            <th id="col2">
                <div style="float:right">
                    <button type="submit" formaction="DispatcherServlet?action=showFormAddStudent">NEW</button>
                    <button type="submit" formaction="DispatcherServlet?action=showFormEditStudent">EDIT</button>
                    <button type="submit" formaction="DispatcherServlet?action=deleteStudent">DELETE</button>
                </div>
            </th>
        </tr>
    </table>

    <% List<Course> listOfStudentCourses = (List<Course>) request.getSession().getAttribute("listOfStudentCourses"); %>
    <p>
            <% for (int i = 0; i < listOfStudentCourses.size(); i++) { %>
                <input type="checkbox" name ="courses"  value="<%= listOfStudentCourses.get(i).getCourseId() %>"><img src="images/course_icon.jpg" width="20" height="20" alt="course"><a href="<%= "DispatcherServlet?action=showAllGrades&courseId=" + listOfStudentCourses.get(i).getCourseId() + "&studentId=" + student.getStudentId()%>"><%= listOfStudentCourses.get(i).getName()%></a><br/>
            <% } %>
    </p>
</form>