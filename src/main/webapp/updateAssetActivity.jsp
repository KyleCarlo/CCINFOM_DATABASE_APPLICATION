<%--
  Created by IntelliJ IDEA.
  User: KOI
  Date: 15/04/2023
  Time: 1:49 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update an Asset Activity</title>
</head>
<body>

<jsp:useBean id="A" class="assetsmgt.assets" scope="session"/>

<form>
    action="updateAssetActivity_processing.jsp"

    Select Asset:
    <select id="asset_id" name="asset_id">
        <%
            A.getAssetList();
            for (int i = 0; i < A.asset_idList.size() + 1; i++){
                if (i != A.asset_idList.size()){
        %>
        <option value="<%=A.asset_idList.get(i)%>"> <%=A.asset_idList.get(i)%>-<%=A.asset_nameList.get(i)%> </option>
        <%
        }   else { %>
        <option value="-1"> None </option>
        <% }
        }
        %>
    </select><br><br>

    Update Information: <br>

    Activity Status:
    <select id="status" name="status" onchange="showActualDates(this)">
        <option value="">Select Status</option>
        <option value="S">Scheduled</option>
        <option value="O">Ongoing</option>

    </select><br>

    Tentative Start Date: <input type="date" id="tent_start" name="tent_start"><br>
    Tentative End Date: <input type="date" id="tent_end" name="tent_end"><br><br>

    <input type="submit" value="Submit Asset">

</form>

</body>
</html>
