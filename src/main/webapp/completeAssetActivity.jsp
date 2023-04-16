<%--
  Created by IntelliJ IDEA.
  User: KOI
  Date: 16/04/2023
  Time: 11:39 am
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Complete Asset Activity</title>
    <link rel="stylesheet" type="text/css" href="completeAssetActivity_style.css">

</head>
<body>

<form action="completeAssetActivity_processing.jsp">

    <h1>Complete Asset Activity</h1>

    Select Asset:
    <jsp:useBean id='A' class='assetsmgt.assets' scope='session'/>
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
    <select id="status" name="status">
        <option value="">Select Status</option>
        <option value="C">Complete</option>
    </select><br>

    Actual Start Date: <input type="date" id="act_start" name="act_start"><br>
    Actual End Date: <input type="date" id="act_end" name="act_end"><br><br>

    Activity Cost: <input type="number" id="cost" name="cost"><br>
    Official Receipt: <input type="number" id="ornum" name="ornum"><br><br>

    <input type="submit" value="Submit Asset">

    </form>

</body>
</html>
