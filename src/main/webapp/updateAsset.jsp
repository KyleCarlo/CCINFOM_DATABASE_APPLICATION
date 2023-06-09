<%--
  Created by IntelliJ IDEA.
  User: KOI
  Date: 14/04/2023
  Time: 10:28 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Asset Update</title>
    <link rel="stylesheet" type="text/css" href="styles/updateAsset_style.css"/>
</head>
<body>
    <form action="updateAsset_processing.jsp">

        <h1>Update Asset Activity</h1>

        Asset to be changed: <select id="asset_id" name="asset_id">
        <jsp:useBean id='A' class='assetsmgt.Assets' scope='session'/>
        <%
            A.getAssetList();
            A.getHoaList();
            for (int i = 0; i < A.asset_idList.size(); i++){
        %>
                <option value="<%=A.asset_idList.get(i)%>"><%=A.asset_idList.get(i)%> | <%=A.asset_nameList.get(i)%></option>
        <%
            }
        %></select><br>
        Asset New HOA: <select id="hoa_name" name="hoa_name">
        <%
            for (int i = 0; i < A.hoa_nameList.size(); i++){
        %>
                <option value="<%=A.hoa_nameList.get(i)%>"><%=A.hoa_nameList.get(i)%></option>
        <%
            }
        %></select><br>
        Asset New Name: <input type="text" id="asset_name" name="asset_name"><br>
        Asset New Description: <input type="text" id="asset_description" name="asset_description"><br>
        Acquisition Date: <input type="date" id="acquisition_date" name="acquisition_date"><br>

        Rental Status:
        <input type="radio" id="forrent" name="forrent" value="1"> <label for="forrent">For Rent</label>
        <input type="radio" id="notforrent" name="forrent" value="0"><label for="notforrent">Not For Rent</label><br>

        Asset Value: <input type="number" id="asset_value" name="asset_value" min="0" step=".01"><br>
        Asset Type:
        <select id="type_asset" name="type_asset">
            <option value="P">Property</option>
            <option value="E">Equipment</option>
            <option value="F">Furniture/Fixtures</option>
            <option value="O">Others</option>
        </select><br>
        Status:
        <select id="status" name="status">
            <option value="W">Working Condition</option>
            <option value="D">Deteriorated</option>
            <option value="P">For Repair</option>
            <option value="S">For Disposal</option>
        </select><br>
        Coordinates<br>
        Latitude: <input type="number" id="loc_lattitude" name="loc_lattitude" min="-90" max="90" step=".0001"><br>
        Longitude: <input type="number" id="loc_longiture" name="loc_longiture" min="-180" max="180" step=".0001"><br>
        Enclosed in: <select id="enclosed_asset_id" name="enclosed_asset_id">
        <%
            A.getPropertyList();
            for (int i = 0; i < A.asset_idList.size() + 1; i++) {
                if (i != A.asset_idList.size()) {
        %>
        <option value="<%=A.asset_idList.get(i)%>"><%=A.asset_idList.get(i)%>-<%=A.asset_nameList.get(i)%>
        </option>
        <%
        } else { %>
        <option value="-1"> None</option>
        <% }
        }
        %>
    </select><br><br>
        <input type="submit" value="Update Asset">
    </form>
</body>
</html>
