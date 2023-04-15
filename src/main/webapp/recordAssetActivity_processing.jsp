<%--
  Created by IntelliJ IDEA.
  User: KOI
  Date: 15/04/2023
  Time: 12:14 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Record an Asset Activity</title>
</head>
<body>

    <jsp:useBean id="A" class="assetsmgt.Assets" scope="session"/>
    <%
        try {

            String hoa_name = request.getParameter("hoa_name");
            A.hoa_name = hoa_name;
            int asset_id = Integer.valueOf(request.getParameter("asset_id"));
            A.asset_id = asset_id;

            String activity_date = request.getParameter("activity_date");
            A.activity_date = activity_date;
            String activity_description = request.getParameter("activity_description");
            A.activity_description = activity_description;
            String auth_officer = request.getParameter("auth_officer");
            A.auth_officer = auth_officer;
            String tent_start = request.getParameter("tent_start");
            A.tent_start = tent_start;
            String tent_end = request.getParameter("tent_end");
            A.tent_end = tent_end;
            String act_start = request.getParameter("act_start");
            A.act_start = act_start;
            String act_end = request.getParameter("act_end");
            A.act_end = act_end;
            int cost = Integer.valueOf(request.getParameter("cost"));
            A.cost = cost;
            int ornum = Integer.valueOf(request.getParameter("ornum"));
            String status = request.getParameter("status");
            A.status = status;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        int result = A.registerAsset();

        if (result == 1) {
    %>
    <h1>Asset Successfully Registered!</h1>
    <%
    } else {
    %>
    <h1>Asset Registration Failed!</h1>
    <%
        }
    %>
    <form action="index.html">
        <input type="submit" value="Return to Menu">
    </form>

</body>
</html>
