<%--
  Created by IntelliJ IDEA.
  User: KOI
  Date: 16/04/2023
  Time: 12:30 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Asset Activity</title>
</head>
<body>

      <jsp:useBean id="A" class="assetsmgt.assets" scope="session"/>
      <%
          try {

              int asset_id = Integer.valueOf(request.getParameter("asset_id"));
              A.asset_id = asset_id;

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
      <h1>Asset Activity Recorded!</h1>
      <%
        }
      %>
      <form action="index.html">
        <input type="submit" value="Return to Menu">
      </form>
</body>
</html>
