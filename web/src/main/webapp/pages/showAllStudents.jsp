<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ua.berest.lab3.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.berest.lab3.controller.PaginationController" %>
<%@ page errorPage="errorPage.jsp"%>

<script>
    function validateForm2() {
        var len = document.studentForm.students.length;
        if(len == undefined && document.getElementById("students0") != null){
            len = 1;
        }
        for (var i = 0; i < len; i++) {
            if (document.getElementById("students" + i).checked == true) {
                return true;
            }
        }

        alert("You should choose at least one item!");
        return false;
    }
</script>
<form name="studentForm" method="POST">
    <table border="1" width="100%" cellpadding="5" bgcolor="#ffcc00">
        <tr>
            <th id="col1" >Студенти: всі</th>
            <th id="col2">
                <div style="float:right">
                    <button type="submit" formaction="DispatcherServlet?action=showFormAddStudent" >NEW</button>
                    <button type="submit" formaction="DispatcherServlet?action=showFormEditStudent" onclick="return validateForm2()">EDIT</button>
                    <button type="submit" formaction="DispatcherServlet?action=deleteStudent" onclick="return validateForm2()">DELETE</button>
                </div>
            </th>
        </tr>
    </table>

    <% List<Student> listOfAllStudents = (List<Student>) request.getSession().getAttribute("listOfAllStudents"); %>
    <p>
            <% for (int i = 0; i < listOfAllStudents.size(); i++) { %>
             <input type="checkbox" name ="students" id="<%= "students" + i %>"  value="<%= listOfAllStudents.get(i).getStudentId() %>"><img src="images/student_icon.jpg" width="20" height="20" alt="student"><a href="<%= "DispatcherServlet?action=showStudentCourses&studentId=" + listOfAllStudents.get(i).getStudentId() %>"><%= listOfAllStudents.get(i).getFio()%></a><br/>
            <% } %>
    </p>
    <p>

        <% PaginationController paginationController = (PaginationController) request.getSession().getAttribute("paginationController"); %>
        <%= paginationController.makePagingLinks("DispatcherServlet?action=showAllStudents", "")%>
    </p>
</form>