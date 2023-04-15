<%--
  Created by IntelliJ IDEA.
  User: Kyle Carlo C. Lasala
  Date: 14/04/2023
  Time: 4:34 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Asset Registration</title>
</head>
<body>
    <jsp:useBean id="A" class="assetsmgt.Assets" scope="session"/>
    <%
        try {
            String asset_name = request.getParameter("asset_name");
            A.asset_name = asset_name;
            String asset_description = request.getParameter("asset_description");
            A.asset_description = asset_description;
            String acquisition_date = request.getParameter("acquisition_date");
            A.acquisition_date = acquisition_date;

            if (request.getParameter("forrent") == "1") {
                int forrent = 1;
                A.forrent = forrent;
            } else if (request.getParameter("forrent") == "0") {
                int forrent = 0;
                A.forrent = forrent;
            }
            String asset_value = request.getParameter("asset_value");
            A.asset_value = Double.valueOf(asset_value);
            String type_asset = request.getParameter("type_asset");
            A.type_asset = type_asset;
            String status = request.getParameter("status");
            A.status = status;
            Double loc_lattitude = Double.valueOf(request.getParameter("loc_lattitude"));
            A.loc_lattitude = loc_lattitude;
            Double loc_longiture = Double.valueOf(request.getParameter("loc_longiture"));
            A.loc_longiture = loc_longiture;

            int asset_id = Integer.valueOf(request.getParameter("asset_id"));
            A.enclosing_asset = asset_id;
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
