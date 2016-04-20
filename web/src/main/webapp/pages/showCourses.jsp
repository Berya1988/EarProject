<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.berest.lab3.model.Course" %>
<%@ page import="java.util.Map" %>
<%@ page import="ua.berest.lab3.model.Location" %>
<%@ page errorPage="errorPage.jsp"%>

<script>
    function validateForm4() {
        var len = document.locationForm.courses.length;
        if(len == undefined && document.getElementById("courses0") != null){
            len = 1;
        }
        for (var i = 0; i < len; i++) {
            if (document.getElementById("courses" + i).checked == true) {
                return true;
            }
        }
        alert("You should choose at least one item!");
        return false;
    }
</script>

<% Map<Integer,String> mapOfAllLocations = (Map<Integer,String>) request.getSession().getAttribute("mapOfAllLocationsById"); %>
<% Location parentLocation = (Location)request.getSession().getAttribute("parentLocation"); %>
<form name="locationForm" method="POST">
    <table border="1" width="100%" cellpadding="5" bgcolor="#ffcc00">
        <tr>
            <th id="col1" >
                <% for (Map.Entry<Integer, String> entry : mapOfAllLocations.entrySet()) { %>
                    <a href="<%= "DispatcherServlet?action=showAllLocations&parentId=" + entry.getKey() %>"><%= entry.getValue() %></a> :
                <% } %>
            </th>

            <th id="col2">
                <div style="float:right">
                    <button type="submit" formaction="<%= "DispatcherServlet?action=showFormAddCourse&parentLocation=" + parentLocation.getLocationId()%>">NEW</button>
                    <button type="submit" formaction="<%= "DispatcherServlet?action=showFormEditCourse&parentLocation=" + parentLocation.getLocationId()%>" onclick="return validateForm4()">EDIT</button>
                    <button type="submit" formaction="<%= "DispatcherServlet?action=deleteCourse&parentLocation=" + parentLocation.getLocationId()%>" onclick="return validateForm4()">DELETE</button>
                </div>
            </th>
        </tr>
    </table>

    <% List<Course> listOfCourses = (List<Course>) request.getSession().getAttribute("listOfCourses"); %>
    <p>
            <% for (int i = 0; i < listOfCourses.size(); i++) { %>
                <input type="checkbox" name ="courses"  id="<%= "courses" + i %>" value="<%= listOfCourses.get(i).getCourseId() %>"><img src="images/course_icon.jpg" width="20" height="20" alt="course"><a href="<%= "DispatcherServlet?action=showAllStudentsInCourse&courseId=" + listOfCourses.get(i).getCourseId() + "&locationId=" + parentLocation.getLocationId()%>"><%= listOfCourses.get(i).getName()%></a><br/>
            <% } %>
    </p>
</form>