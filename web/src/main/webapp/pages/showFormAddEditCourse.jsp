<%@ page import="ua.berest.lab3.model.Course" %>
<%@ page import="ua.berest.lab3.model.Location" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="errorPage.jsp"%>

<link href="css/form.css" rel="stylesheet" type="text/css">

<% Course course = (Course) request.getSession().getAttribute("course"); %>
<% Location parentLocation = (Location)request.getSession().getAttribute("parentLocation"); %>
<form id="myForm" method="POST" action="<%= course!=null?"DispatcherServlet?action=editCourse":"DispatcherServlet?action=addCourse"%>">
    <fieldset>
        <legend><b><%= course!=null?"КУРС:РЕДАГУВАТИ":"КУРС:СТВОРИТИ"%></b></legend>
        <input type="hidden" name="idName" value="<%= course!=null?course.getCourseId():""%>" />
        <input type="hidden" name="location_id" value="<%= course!=null?course.getLocationId():parentLocation.getLocationId()%>" />
        <div>
            <label for="courseNameInput" >Назва курсу:</label>
            <div class="col-lg-9">
                <input type="text" class="form-control" name="courseName" id="courseNameInput" value="<%= course!=null?course.getName():""%>" placeholder="Введіть назву курсу: Art" required/>
            </div>
        </div>

        <div>
            <label for="courseDescriptionInput" >Опис:</label>
            <div class="col-lg-9">
                <input type="text" class="form-control" name="courseDescription" id="courseDescriptionInput" value="<%= course!=null?course.getDescription():""%>" placeholder="Опишіть курс: найкращий курс" required/>
            </div>
        </div>

        <div>
            <label for="teacherInput" >Викладач:</label>
            <div class="col-lg-9">
                <input type="text" class="form-control" name="teacherName" id="teacherInput" value="<%= course!=null?course.getTeacher():""%>" placeholder="Визначити викладача: Lulev O.V." required/>
            </div>
        </div>

        <div>
            <button class="btn btn-default"><a href = "<%="DispatcherServlet?action=showAllLocations&parentId=" + parentLocation.getLocationId() %>">Відміна</a></button>
            <button class="btn btn-primary" type="submit">Підтвердити</button>
        </div>
    </fieldset>
</form>

