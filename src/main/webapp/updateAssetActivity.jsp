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
    <title>Update Asset Activity</title>
</head>
<body>

<form action="updateAssetActivity_processing.jsp">
    <jsp:useBean id="Activity" class="actsmgt.AssetAct" scope='session'/>
    <jsp:useBean id="Transaction" class="actsmgt.ActTrans" scope="session"/>
    Select Asset:<select id="asset_id" name="asset_id">
    <%
        Activity.getUpdatableList();
        for (int i = 0; i < Activity.asset_idList.size(); i++){
    %>
        <option value="<%=Activity.asset_idList.get(i)%>,<%=Activity.activity_dateList.get(i)%>">
            <%=Activity.asset_idList.get(i)%> | <%=Activity.activity_dateList.get(i)%>
        </option>
    <%
        }
    %>
    </select><br>
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
    Approving Officer:
    <select id="approval_hoid" name="approval_hoid">
        <%
            Transaction.getPresHoid();
        %>
                <option value="null">None</option>
                <option value="<%=Transaction.approval_hoid%>,<%=Transaction.approval_position%>,<%=Transaction.approval_electiondate%>">
                    <%=Transaction.approval_hoid%> | <%=Transaction.approval_position%>
                </option>
    </select><br>
    OR Number:
    <select id="ornum" name="ornum">
        <%
            Transaction.getOrNumList();
            for (int i = 0; i < Transaction.ornumList.size() +1; i++){
                if (i == 0){
        %>
                    <option value="null">None</option>
        <%
                } else {
        %>
                    <option value="<%=Transaction.ornumList.get(i-1)%>">
                        <%=Transaction.ornumList.get(i-1)%>
                    </option>
        <%
                }
            }
        %>
    </select><br>

    <input type="submit" value="Submit Asset">
</form>

</body>
</html>
