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
    <title>Record Asset Activity</title>
    <link rel="stylesheet" type="text/css" href="styles/success_style.css"/>
</head>
<body>

    <jsp:useBean id="Transaction" class="actsmgt.ActTrans" scope="session"/>
    <jsp:useBean id="Activity" class="actsmgt.AssetAct" scope="session"/>
    <%
        try {
            Transaction.getTransHoidList();
            int asset_id = Integer.valueOf(request.getParameter("asset_id"));
            Activity.asset_id = asset_id;
            Transaction.asset_id = asset_id;
            String activity_date = request.getParameter("activity_date");
            Activity.activity_date = activity_date;
            Transaction.transaction_date = activity_date;

            String activity_description = request.getParameter("activity_description");
            Activity.activity_description = activity_description;

            String trans_hoid = request.getParameter("trans_hoid");
            String[] sep = trans_hoid.split(",", 3);
            Transaction.trans_hoid = Integer.valueOf(sep[0]);
            Transaction.trans_position = sep[1];
            Transaction.trans_electiondate = sep[2];

            String tent_start = request.getParameter("tent_start");
            Activity.tent_start = tent_start;

            String tent_end = request.getParameter("tent_end");
            Activity.tent_end = tent_end;

            String act_start = request.getParameter("act_start");
            Activity.act_start = act_start;

            String act_end = request.getParameter("act_end");
            Activity.act_end = act_end;

            String cost = request.getParameter("cost");
            if (cost == "") {
                Activity.cost = -1.0;
            } else {
                Activity.cost = Double.valueOf(cost);
            }

            String status = request.getParameter("status");
            Activity.status = status;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        int result = Transaction.recordAssetActivity();
        int result1 = 0;

        if (result == 1){
            result1 = Activity.recordAssetActivity();
            if (result1 == 0) {
                Transaction.revertTrans();
            }
        }

        if (result == 1 && result1 == 1) {
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
