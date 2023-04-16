<%--
  Created by IntelliJ IDEA.
  User: KOI
  Date: 16/04/2023
  Time: 12:03 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Delete an Asset Activity </title>
    <link rel="stylesheet" type="text/css" href="deleteAssetActivity_style.css">

</head>
<body>

<form action="deleteAssetActivity_processing.jsp">


    <jsp:useBean id='A' class='assetsmgt.assets' scope='session'/>

    <h1>Delete an Asset Activity </h1>

    Select Asset to Delete:<select id="asset_id" name="asset_id">
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

    <input type="submit" value="Submit Asset">

</form>

</body>
</html>