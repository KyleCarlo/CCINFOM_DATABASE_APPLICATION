<%--
  Created by IntelliJ IDEA.
  User: Kyle Carlo C. Lasala
  Date: 16/04/2023
  Time: 7:48 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Asset Activity</title>
    <link rel="stylesheet" type="text/css" href="styles/deleteAssetActivity_style.css"/>
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>Activity Date</th>
        <th>Activity Description</th>
        <th>Tentative Start Date</th>
        <th>Tentative End Date</th>
        <th>Actual Start Date</th>
        <th>Actual End Date</th>
        <th>Activity Cost</th>
        <th>Status</th>
    </tr>
    <jsp:useBean id="Activity" class="actsmgt.AssetAct" scope="session"/>
    <%
        Activity.getDeletableActivityList();
        for (int i = 0; i < Activity.asset_idList.size(); i++){
    %>
    <tr>
            <td><%=Activity.asset_idList.get(i)%></td>
            <td><%=Activity.activity_dateList.get(i)%></td>
            <td><%=Activity.activity_descriptionList.get(i)%></td>
            <td><%=Activity.tent_startList.get(i)%></td>
            <td><%=Activity.tent_endList.get(i)%></td>
            <td><%=Activity.act_startList.get(i)%></td>
            <td><%=Activity.act_endList.get(i)%></td>
            <td><%=Activity.costList.get(i)%></td>
            <td><% String status = "";
            switch(Activity.statusList.get(i)){
                case "S":
                    status = "Scheduled";
                    break;
                case "O":
                    status = "Ongoing";
                    break;
                case "C":
                    status = "Completed";
                    break;
            }
            %><%=status%></td>
    </tr>
    <%
        }
    %>
</table><br>
<form action="deleteAssetActivity_processing.jsp">

    <h1>Delete Asset Activity</h1>

    Select Activity to Delete: <select id="delete_activity" name="delete_activity">
    <%
        for (int i = 0; i < Activity.asset_idList.size(); i++){
    %>
            <option value="<%=Activity.asset_idList.get(i)%>,<%=Activity.activity_dateList.get(i)%>">
                <%=Activity.asset_idList.get(i)%> | <%=Activity.activity_dateList.get(i)%>
            </option>
    <%
        }
    %>
    </select><br>
    <input type="submit" value="Delete Activity">
</form>

</body>
</html>
