<%@ page import="java.util.PropertyPermission" %><%--
  Created by IntelliJ IDEA.
  User: Kyle Carlo C. Lasala
  Date: 15/04/2023
  Time: 11:13 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete an Asset</title>
</head>
<body>

    <table>
        <tr>
            <th>ID</th>
            <th>Asset Name</th>
            <th>Asset Description</th>
            <th>Asset Date Acquired</th>
            <th>Asset Value</th>
            <th>Asset Type</th>
            <th>Asset Status</th>
            <th>Asset Location</th>
            <th>HOA Name</th>
            <th>Enclosing Asset</th>
        </tr>
        <jsp:useBean id="A" class="assetsmgt.Assets" scope="session"/>
        <%
            A.getDeletableList();
            for(int i = 0; i < A.asset_idList.size(); i++){
        %>
            <tr>
                <td><%=A.asset_idList.get(i)%></td>
                <td><%=A.asset_nameList.get(i)%></td>
                <td><%=A.asset_descriptionList.get(i)%></td>
                <td><%=A.acquisition_dateList.get(i)%></td>
                <td><%=A.asset_valueList.get(i)%></td>
                <td><% String asset_type = null;
                    switch(A.type_assetList.get(i)) {
                        case "P":
                            asset_type = "Property";
                            break;
                        case "E":
                            asset_type = "Equipment";
                            break;
                        case "F":
                            asset_type = "Furniture & Fixtures";
                            break;
                        case "O":
                            asset_type = "Other";
                            break;
                    }
                %><%=asset_type%></td>

                <td><% String asset_status = null;
                    switch(A.statusList.get(i)) {
                        case "W":
                            asset_status = "Working";
                            break;
                        case "D":
                            asset_status = "Deteriorated";
                            break;
                        case "P":
                            asset_status = "For Repair";
                            break;
                        case "S":
                            asset_status = "For Disposal";
                            break;
                        case "X":
                            asset_status = "Disposed";
                            break;
                    }
                %><%=asset_status%></td>
                <td><%=A.loc_lattitudeList.get(i)%>, <%=A.loc_longitureList.get(i)%></td>
                <td><%=A.hoa_nameList.get(i)%></td>
                <td><%
                    String enclosing_asset = null;
                    if(A.enclosing_assetList.get(i) != 0) {
                        enclosing_asset = Integer.toString(A.enclosing_assetList.get(i));
                    } else {
                        enclosing_asset = "N/A";
                    }
                    %>
                <%=enclosing_asset%></td>
            </tr>
        <%
            }
        %>
    </table><br>
    <form action="deleteAsset_processing.jsp">
    Select Asset ID to Delete: <select id="delete_asset_id" name="delete_asset_id">
        <%
            for(int i = 0; i < A.asset_idList.size(); i++){
        %>
                <option value="<%=A.asset_idList.get(i)%>"><%=A.asset_idList.get(i)%></option>
        <%
            }
        %>
    </select> <br>
    <input type="submit" value="Delete Asset">
    </form>
</body>
</html>
