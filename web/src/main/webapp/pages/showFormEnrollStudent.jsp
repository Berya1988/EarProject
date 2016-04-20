<%@ page import="ua.berest.lab3.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.berest.lab3.model.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="errorPage.jsp"%>

<link href="../css/form.css" rel="stylesheet" type="text/css">

<% List<Student> listOfAllStudents = (List<Student>) request.getSession().getAttribute("listOfAllStudents"); %>
<% Course course = (Course) request.getSession().getAttribute("course"); %>

<script type="text/javascript">
    function closeAndReload() {
        window.close();
        window.opener.location.reload();
    }
    function onSubmitForm() {
        document.formEnrolled.action = "<%= "../DispatcherServlet?action=enrollStudent&courseId=" + course.getCourseId()%>";
        setTimeout(closeAndReload, 20);
    }Oracle - @localhost
</script>


<form name="formEnrolled" method="POST">
    <fieldset>
        <legend><b>ДOДАТИ СТУДЕНТА ДО КУРСУ <%= course.getName().toUpperCase()%></b></legend>

        <div>
            <div class="col-lg-9">
                <label>Виберіть студента, який буде зарахований:</label></br>
                    <% for (int i = 0; i < listOfAllStudents.size(); i++) { %>
                        <input type="checkbox" name ="students"  value="<%= listOfAllStudents.get(i).getStudentId() %>"><img src="../images/student_icon.jpg" width="20" height="20" alt="student"><%= listOfAllStudents.get(i).getFio()%><br/>
                    <% } %>
            </div>
        </div>

        <div>
            <button class="btn btn-default" type="reset" onclick="window.close()" >Відміна</button>
            <button class="btn btn-primary" type="submit" onclick="onSubmitForm()" >Підтвердити</button>
        </div>
    </fieldset>
</form>

