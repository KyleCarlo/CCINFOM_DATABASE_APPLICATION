<%--
  Created by IntelliJ IDEA.
  User: KOI
  Date: 15/04/2023
  Time: 1:11 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Record an Asset Activity </title>
</head>
<body>

    <form action="recordAssetActivity_processing.jsp">

      HOA Assigned:
      <jsp:useBean id='A' class='assetsmgt.Assets' scope='session'/>
      <%
        A.getHoaList();
        for (int i = 0; i < A.hoa_nameList.size(); i++){
      %>
      <input type="radio" id="<%=A.hoa_nameList.get(i)%>" name="hoa_name" value="<%=A.hoa_nameList.get(i)%>"> <label for="<%=A.hoa_nameList.get(i)%>"><%=A.hoa_nameList.get(i)%></label>
      <%
        }
      %><br>
      Select Asset:<select id="asset_id" name="asset_id">
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

      Activity Date: <input type="date" id="activity_date" name="activity_date"><br>
      Activity Description: <input type="text" id="activity_description" name="activity_description"><br>
      Authorized Officer: <input type="text" id="auth_officer" name="auth_officer"><br>
      Tentative Start Date: <input type="date" id="tent_start" name="tent_start"><br>
      Tentative End Date: <input type="date" id="tent_end" name="tent_end"><br>
      Actual Start Date: <input type="date" id="act_start" name="act_start"><br>
      Actual End Date: <input type="date" id="act_end" name="act_end"><br>
      Activity Cost: <input type="number" id="cost" name="cost"><br>
      Official Receipt: <input type="number" id="ornum" name="ornum"><br>
      Activity Status:
      <select id="status" name="status">
        <option value="S">Scheduled</option>
        <option value="O">Ongoing</option>
        <option value="C">Completed</option>
      </select><br><br>

      <input type="submit" value="Submit Asset">

    </form>

</body>
</html>
