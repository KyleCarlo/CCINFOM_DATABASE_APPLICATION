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
    <title>Update Asset Activity</title>
</head>
<body>
<jsp:useBean id="Transaction" class="actsmgt.ActTrans" scope="session"/>
<jsp:useBean id="Activity" class="actsmgt.AssetAct" scope="session"/>
<%
    try {
            Transaction.getTransHoidList();
            String asset_id = request.getParameter("asset_id");
            String[] asset = asset_id.split(",", 2);
            Activity.asset_id = Integer.valueOf(asset[0]);
            Transaction.asset_id = Integer.valueOf(asset[0]);

            Activity.activity_date = asset[1];
            Transaction.transaction_date = asset[1];

            String activity_description = request.getParameter("activity_description");
            Activity.activity_description = activity_description;

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

            String trans_hoid = request.getParameter("trans_hoid");
            String[] sep = trans_hoid.split(",", 3);
            Transaction.trans_hoid = Integer.valueOf(sep[0]);
            Transaction.trans_position = sep[1];
            Transaction.trans_electiondate = sep[2];

            String approval_hoid = request.getParameter("approval_hoid");
            sep = approval_hoid.split(",", 3);
            Transaction.approval_hoid = Integer.valueOf(sep[0]);
            Transaction.approval_position = sep[1];
            Transaction.approval_electiondate = sep[2];

            String ornum = request.getParameter("ornum");
            Transaction.ornum = Integer.valueOf(ornum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        int result = Transaction.updateAssetActivity();
        int result1 = 0;

        if (result == 1){
            result1 = Activity.updateAssetActivity();
        }

        if (result == 1 && result1 == 1) {
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
