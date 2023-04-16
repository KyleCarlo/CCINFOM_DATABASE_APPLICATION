<%--
  Created by IntelliJ IDEA.
  User: KOI
  Date: 15/04/2023
  Time: 1:11 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Record Asset Activity </title>
    <link rel="stylesheet" type="text/css" href="styles/recordAssetActivity_style.css"/>
</head>
<body>
    <form action="recordAssetActivity_processing.jsp">
        <jsp:useBean id='Assets' class='assetsmgt.Assets' scope='session'/>
        <jsp:useBean id='Transaction' class='actsmgt.ActTrans' scope='session'/>

        <h1>Record Asset Activity</h1>

        Select Asset:<select id="asset_id" name="asset_id">
        <%
            Assets.getAssetList();
            for (int i = 0; i < Assets.asset_idList.size(); i++){
        %>
                <option value="<%=Assets.asset_idList.get(i)%>"> <%=Assets.asset_idList.get(i)%>-<%=Assets.asset_nameList.get(i)%> </option>
        <%
            }
        %>
        </select><br>

        Activity Date: <input type="date" id="activity_date" name="activity_date"><br>
        Activity Description: <input type="text" id="activity_description" name="activity_description"><br>
        Tentative Start Date: <input type="date" id="tent_start" name="tent_start"><br>
        Tentative End Date: <input type="date" id="tent_end" name="tent_end"><br>
        Actual Start Date: <input type="date" id="act_start" name="act_start"><br>
        Actual End Date: <input type="date" id="act_end" name="act_end"><br>
        Activity Cost: <input type="number" id="cost" name="cost" step=".01"><br>
        Activity Status:
        <select id="status" name="status">
            <option value="S">Scheduled</option>
            <option value="O">Ongoing</option>
        </select><br>

        <%--Transaction Inputs--%>
        Authorized Officer:
        <select id="trans_hoid" name="trans_hoid">
        <%
            Transaction.getTransHoidList();
            for (int i = 0; i < Transaction.trans_hoidList.size(); i++){
        %>
            <option value="<%=Transaction.trans_hoidList.get(i)%>,<%=Transaction.trans_positionList.get(i)%>,<%=Transaction.trans_electiondateList.get(i)%>">
                <%=Transaction.trans_hoidList.get(i)%> | <%=Transaction.trans_positionList.get(i)%>
            </option>
        <%
            }
        %>
        </select><br>
        OR Number: <input type="number" id="ornum" name="ornum" step="1"><br>

        <input type="submit" value="Submit Asset">
    </form>
</body>
</html>