<%--
  Created by IntelliJ IDEA.
  User: KOI
  Date: 16/04/2023
  Time: 9:24 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Asset Activity (Processing)</title>
</head>
<body>
    <jsp:useBean id="A" class="assetsmgt.assets" scope="session"/>
    <%
        try {

            int asset_id = Integer.valueOf(request.getParameter("asset_id"));
            A.asset_id = asset_id;

            String status = request.getParameter("status");
            A.status = status;

            String tent_start = request.getParameter("tent_start");
            A.tent_start = tent_start;
            String tent_end = request.getParameter("tent_end");
            A.tent_end = tent_end;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        int result = A.updateAssetActivity();

        if (result == 1) {
    %>
    <h1>Asset Activity Successfully Updated!</h1>
    <%
    } else {
    %>
    <h1>Asset Activity Update Failed!</h1>
    <%
        }
    %>
    <form action="index.html">
        <input type="submit" value="Return to Menu">
    </form>
</body>
</html>
