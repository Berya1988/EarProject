<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ua.berest.lab3.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.berest.lab3.model.Location" %>
<%@ page import="ua.berest.lab3.model.Course" %>
<%@ page import="ua.berest.lab3.controller.OracleDataAccess" %>
<%@ page errorPage="errorPage.jsp"%>

<% Location location = (Location)request.getSession().getAttribute("location"); %>
<% Course course = (Course) request.getSession().getAttribute("course"); %>
<% List<Student> listOfAllStudents = OracleDataAccess.getInstance().getAllStudentsOutOfCourse(course.getCourseId()); %>
<% request.getSession().setAttribute("listOfAllStudents", listOfAllStudents); %>
<script>
    function newWindow() {
        var d = document.documentElement,
                h = 500,
                w = 500,
                myWindow = window.open("<%= "pages/showFormEnrollStudent.jsp?course=" + course.getCourseId() %>", 'EnrollmentForm', 'scrollbars=1,height='+Math.min(h, screen.availHeight)+',width='+Math.min(w, screen.availWidth)+',left='+Math.max(0, ((d.clientWidth - w)/2 + window.screenX))+',top='+Math.max(0, ((d.clientHeight - h)/2 + window.screenY)));
        if (myWindow.screenY >= (screen.availHeight - myWindow.outerHeight)) {
            myWindow.moveTo(myWindow.screenX, (screen.availHeight - myWindow.outerHeight))
        }
        if (myWindow.screenX >= (screen.availWidth - myWindow.outerWidth)) {
            myWindow.moveTo((screen.availWidth - myWindow.outerWidth), myWindow.screenY)
        }
    }
</script>


<form method="POST" name="enrollForm">
    <table border="1" width="100%" cellpadding="5" bgcolor="#ffcc00">
        <tr>
            <th id="col1" >Студенти кафедри <a href="<%="DispatcherServlet?action=showAllLocations&parentId=" + location.getLocationId()%>"><%= location.getName()%></a> : курс <%= course.getName()%></th>
            <th id="col2">
                <div style="float:right">
                    <button type="submit" onclick="newWindow()" >ENROLL</button>
                    <button type="submit" formaction="<%="DispatcherServlet?action=unenrollStudent&courseId=" + course.getCourseId()%>" >UNENROLL</button>
                </div>
            </th>
        </tr>
    </table>

    <% List<Student> listOfAllStudentsInCourse = (List<Student>) request.getSession().getAttribute("listOfAllStudentsInCourse"); %>
    <p>
            <% for (int i = 0; i < listOfAllStudentsInCourse.size(); i++) { %>
             <input type="checkbox" name ="students"  value="<%= listOfAllStudentsInCourse.get(i).getStudentId() %>"><img src="images/student_icon.jpg" width="20" height="20" alt="student"><a href="<%= "DispatcherServlet?action=showStudentCourses&studentId=" + listOfAllStudentsInCourse.get(i).getStudentId() %>"><%= listOfAllStudentsInCourse.get(i).getFio()%></a><br/>
            <% } %>
    </p>
</form>