<%--
  Created by IntelliJ IDEA.
  User: Kyle Carlo C. Lasala
  Date: 14/04/2023
  Time: 1:44 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register an Asset</title>
    <link rel="stylesheet" type="text/css" href="registerAsset_style.css">

    <form action="registerAsset_processing.jsp">

        <h1>Register an Asset</h1>
        
        HOA Assigned:
        <jsp:useBean id='A' class='assetsmgt.assets' scope='session'/>
        <%
            A.getHoaList();
            for (int i = 0; i < A.hoa_nameList.size(); i++){
        %>
                <input type="radio" id="<%=A.hoa_nameList.get(i)%>" name="hoa_name" value="<%=A.hoa_nameList.get(i)%>"> <label for="<%=A.hoa_nameList.get(i)%>"><%=A.hoa_nameList.get(i)%></label>
        <%
            }
        %><br>
        Asset Name: <input type="text" id="asset_name" name="asset_name"><br>
        Asset Description: <input type="text" id="asset_description" name="asset_description"><br>
        Acquisition Date: <input type="date" id="acquisition_date" name="acquisition_date"><br>

        Rental Status: <input type="radio" id="forrent" name="forrent" value="1"> <label for="forrent">For Rent</label>
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
            <option value="X">Disposed</option>
        </select><br>
        Coordinates<br>
        Latitude: <input type="number" id="loc_lattitude" name="loc_lattitude" min="-90" max="90" step=".01"><br>
        Longitude: <input type="number" id="loc_longiture" name="loc_longiture" min="-180" max="180" step=".01"><br>
        <select id="asset_id" name="asset_id">
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
        </select><br>
        <input type="submit" value="Submit Asset">
    </form>
</head>
<body>

</body>
</html>
