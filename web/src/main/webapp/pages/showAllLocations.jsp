<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ua.berest.lab3.model.Location" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page errorPage="errorPage.jsp"%>

<script>
    function validateForm3() {
        var len = document.locationForm.locations.length;
        if(len == undefined && document.getElementById("locations0") != null){
            len = 1;
        }
        for (var i = 0; i < len; i++) {
            if (document.getElementById("locations" + i).checked == true) {
                return true;
            }
        }
        alert("You should choose at least one item!");
        return false;
    }
</script>

<% Map<Integer,String> mapOfAllLocations = (Map<Integer,String>) request.getSession().getAttribute("mapOfAllLocationsById"); %>
<form name="locationForm" method="POST">
    <table border="1" width="100%" cellpadding="5" bgcolor="#ffcc00">
        <tr>
            <th id="col1" >
                <% int parent = 0; %>
                <% for (Map.Entry<Integer, String> entry : mapOfAllLocations.entrySet()) { %>
                    <a href="<%= "DispatcherServlet?action=showAllLocations&parentId=" + entry.getKey() %>"><%= entry.getValue() %></a> :
                    <% parent = entry.getKey(); %>
                <% } %>
            </th>
            <th id="col2">
                <div style="float:right">
                    <button type="submit" formaction="<%= "DispatcherServlet?action=showFormAddLocation&parentId=" + parent %>" >NEW</button>
                    <button type="submit" formaction="<%= "DispatcherServlet?action=showFormEditLocation&parentId=" + parent %>" onclick="return validateForm3()">EDIT</button>
                    <button type="submit" formaction="DispatcherServlet?action=deleteLocation" onclick="return validateForm3()">DELETE</button>
                </div>
            </th>
        </tr>
    </table>

    <% List<Location> listOfAllLocations = (List<Location>) request.getSession().getAttribute("listOfAllLocations"); %>
    <p>
        <% for (int i = 0; i < listOfAllLocations.size(); i++) { %>
                <input type="checkbox" name="locations" id="<%= "locations" + i%>" value="<%= listOfAllLocations.get(i).getLocationId() %>"><img src="images/location_icon.png" width="20" height="20" alt="location"><a href="<%= "DispatcherServlet?action=showAllLocations&parentId=" + listOfAllLocations.get(i).getLocationId() %>"><%= listOfAllLocations.get(i).getName()%></a><br/>
        <% } %>
    </p>
</form>