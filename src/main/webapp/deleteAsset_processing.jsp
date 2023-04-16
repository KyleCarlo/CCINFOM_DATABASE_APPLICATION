<%--
  Created by IntelliJ IDEA.
  User: Kyle Carlo C. Lasala
  Date: 15/04/2023
  Time: 12:25 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Asset Deletion</title>
    <link rel="stylesheet" type="text/css" href="styles/success_style.css"/>
</head>
<body>
    <jsp:useBean id="A" class="assetsmgt.Assets" scope="session"/>
    <%
        int result = 1;
        try {
            String asset_id = request.getParameter("delete_asset_id");
            if (asset_id == null){
                result = 0;
            } else {
                A.asset_id = Integer.valueOf(asset_id);
                result = A.deleteAsset();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        if (result == 1){
    %>
        <h1>Asset Deleted!</h1>
    <% } else { %>
        <h1>Error in Deletion!</h1>
    <% } %>
    <form action="index.html">
        <input type="submit" value="Return to Menu">
    </form>
</body>
</html>
