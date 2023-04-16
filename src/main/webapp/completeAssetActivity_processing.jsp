<%--
  Created by IntelliJ IDEA.
  User: KOI
  Date: 16/04/2023
  Time: 11:51 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Complete Asset Activity (Processing)</title>
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

            int cost = Integer.valueOf(request.getParameter("cost"));
            A.cost = cost;
            int ornum = Integer.valueOf(request.getParameter("ornum"));
            A.ornum = ornum;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        int result = A.updateAssetActivity();

        if (result == 1) {
    %>
    <h1>Asset Activity Successfully Marked as Complete!</h1>
    <%
    } else {
    %>
    <h1>Asset Activity Completion Failed!</h1>
    <%
        }
    %>
    <form action="index.html">
        <input type="submit" value="Return to Menu">
    </form>
</body>
</html>

