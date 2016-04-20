<%@ page import="ua.berest.lab3.model.Location" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="errorPage.jsp"%>

<link href="css/form.css" rel="stylesheet" type="text/css">

<% Location location = (Location) request.getSession().getAttribute("location"); %>
<% Map<Integer,String> mapOfAllLocations = (Map<Integer,String>) request.getSession().getAttribute("mapOfAllLocations"); %>

<form name="location" method="POST" action="<%= location!=null?"DispatcherServlet?action=editLocation":"DispatcherServlet?action=addLocation"%>" >
    <fieldset>
        <legend><b><%= location!=null?"РОЗМІЩЕННЯ:РЕДАГУВАТИ":"РОЗМІЩЕННЯ:СТВОРИТИ"%></b></legend>
        <input type="hidden" name="idName" value="<%= location!=null?location.getLocationId():""%>" />
        <div>
            <label for="locationNameInput" >Назва:</label>
            <div class="col-lg-9">
                <input type="text" class="form-control" name="locationName" id="locationNameInput" value="<%= location!=null?location.getName():""%>" placeholder="Введіть назву: Харківський політехнічний інститут" required/>
            </div>
        </div>

        <div>
            <label for="descriptionName" >Опис:</label>
            <div class="col-lg-9">
                <input type="text" class="form-control" name="descriptionName"  value="<%= location!=null?location.getDescription():""%>" id="descriptionName" placeholder="Введіть опис: найкращий ВУЗ в Харкові" required/>
            </div>
        </div>


        <div>
            <div class="col-lg-9">
                <label>Останній рівень:</label>
                    <% if(location == null) { %>
                        <input type="radio" name="course" value="true">Так
                        <input type="radio" name="course" value="false" checked>Ні</br>
                    <% } else {%>
                        <% if(location.getCourse() == true) { %>
                            <input type="radio" name="course" value="true" checked disabled>Так
                            <input type="radio" name="course" value="false" disabled>Ні</br>
                        <% } else { %>
                            <input type="radio" name="course" value="true">Так
                            <input type="radio" name="course" value="false" checked>Ні</br>
                        <% }
                    } %>
            </div>
        </div>

        <div>
            <div class="col-lg-9">
                <label for="parent" >Виберіть батьківський елемент:</label>
                <select name="parent" id="parent">
                    <% int parentElement = Integer.parseInt((String)request.getSession().getAttribute("parentId")); %>
                    <% for (Map.Entry<Integer, String> entry : mapOfAllLocations.entrySet()) { %>
                        <% if(parentElement == entry.getKey()) { %>
                            <option selected value="<%= entry.getKey()%>"><%= entry.getValue()%></option>
                        <% } else { %>
                            <option value="<%= entry.getKey()%>"><%= entry.getValue()%></option>
                        <% } %>
                    <% } %>
                </select>
            </div>
        </div>

        <div>
            <button class="btn btn-default"><a href="<%= "DispatcherServlet?action=showAllLocations&parentId=" + parentElement%>">Відміна</a></button>
            <button class="btn btn-primary" type="submit">Підтвердити</button>
        </div>
    </fieldset>
</form>

