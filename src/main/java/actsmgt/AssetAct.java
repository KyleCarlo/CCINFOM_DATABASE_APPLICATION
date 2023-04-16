package actsmgt;

import java.sql.*;
import java.util.*;

public class AssetAct {
    public int asset_id;
    public String activity_date;
    public String activity_description;
    public String tent_start;
    public String tent_end;
    public String act_start;
    public String act_end;
    public Double cost;
    public String status;
    public ArrayList<Integer> asset_idList = new ArrayList<>();
    public ArrayList<String> activity_dateList = new ArrayList<>();
    public ArrayList<String> activity_descriptionList = new ArrayList<>();
    public ArrayList<String> tent_startList = new ArrayList<>();
    public ArrayList<String> tent_endList = new ArrayList<>();
    public ArrayList<String> act_startList = new ArrayList<>();
    public ArrayList<String> act_endList = new ArrayList<>();
    public ArrayList<Double> costList = new ArrayList<>();
    public ArrayList<String> statusList = new ArrayList<>();
    public int deleteAssetActivity(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement stmt = conn.prepareStatement(
            "DELETE FROM asset_activity WHERE asset_id = ? AND activity_date = DATE(?);"
            );
            stmt.setInt(1, asset_id);
            stmt.setString(2, activity_date);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("UPDATE asset_transactions SET isdeleted = 1 WHERE asset_id = ? AND transaction_date = DATE(?);");
            stmt.setInt(1, asset_id);
            stmt.setString(2, activity_date);
            stmt.executeUpdate();

            return 1;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
    public int getDeletableActivityList(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT aa.*\n" +
                    "FROM asset_activity aa\n" +
                    "JOIN asset_transactions at\n" +
                    "ON aa.asset_id = at.asset_id AND aa.activity_date = at.transaction_date\n" +
                    "WHERE at.approval_position = 'President';"
            );

            ResultSet rs = stmt.executeQuery();

            asset_idList.clear();
            activity_dateList.clear();
            activity_descriptionList.clear();
            tent_startList.clear();
            tent_endList.clear();
            act_startList.clear();
            act_endList.clear();
            costList.clear();
            statusList.clear();

            while(rs.next()){
                int asset_id = rs.getInt("asset_id");
                String activity_date = rs.getString("activity_date");
                String activity_description = rs.getString("activity_description");
                String tent_start = rs.getString("tent_start");
                String tent_end = rs.getString("tent_end");
                String act_start = rs.getString("act_start");
                String act_end = rs.getString("act_end");
                Double cost = rs.getDouble("cost");
                String status = rs.getString("status");
                asset_idList.add(asset_id);
                activity_dateList.add(activity_date);
                activity_descriptionList.add(activity_description);
                tent_startList.add(tent_start);
                tent_endList.add(tent_end);
                act_startList.add(act_start);
                act_endList.add(act_end);
                costList.add(cost);
                statusList.add(status);
            }
            stmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    public int updateAssetActivity(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE asset_activity SET activity_description = ?, tent_start = ?, tent_end = ?, act_start = ?, act_end = ?, cost = ?, status = ? " +
                            "WHERE asset_id = ? AND activity_date = DATE(?);"
            );

            stmt.setString(1, activity_description);
            if (tent_start.equals("")) {
                stmt.setNull(2, Types.VARCHAR);
            } else {
                stmt.setString(2, tent_start);
            }
            if (tent_end.equals("")) {
                stmt.setNull(3, Types.VARCHAR);
            } else {
                stmt.setString(3, tent_end);
            }
            if (act_start.equals("")) {
                stmt.setNull(4, Types.VARCHAR);
            } else {
                stmt.setString(4, act_start);
            }
            if (act_end.equals("")) {
                stmt.setNull(5, Types.VARCHAR);
            } else {
                stmt.setString(5, act_end);
            }
            if (cost == -1.0) {
                stmt.setNull(6, Types.DOUBLE);
            } else {
                stmt.setDouble(6, cost);
            }
            stmt.setString(7, status);
            stmt.setInt(8, asset_id);
            stmt.setString(9, activity_date);
            stmt.executeUpdate();
            System.out.println("Successful update");
            stmt.close();
            conn.close();

            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    public int getUpdatableList() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT asset_id, activity_date FROM asset_activity;");
            ResultSet rs = stmt.executeQuery();

            asset_idList.clear();
            activity_dateList.clear();

            while (rs.next()) {
                asset_id = rs.getInt("asset_id");
                asset_idList.add(asset_id);
                activity_date = rs.getString("activity_date");
                activity_dateList.add(activity_date);
            }
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int recordAssetActivity() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");

            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO asset_activity (asset_id, activity_date, activity_description, tent_start, tent_end, act_start, act_end, cost, status) "
                            +
                            "VALUES (?,         DATE(?),        ?,                  DATE(?),    DATE(?),    ?,       ?,       ?,    ?);");
            stmt.setInt(1, asset_id);
            stmt.setString(2, activity_date);

            if (activity_description == null) {
                stmt.setNull(3, Types.VARCHAR);
            } else {
                stmt.setString(3, activity_description);
            }
            if (tent_start == "") {
                stmt.setNull(4, Types.VARCHAR);
            } else {
                stmt.setString(4, tent_start);
            }
            if (tent_end == "") {
                stmt.setNull(5, Types.VARCHAR);
            } else {
                stmt.setString(5, tent_end);
            }
            stmt.setNull(6, Types.DATE);
            stmt.setNull(7, Types.DATE);
            if (cost == -1.0) {
                stmt.setNull(8, Types.DOUBLE);
            } else {
                stmt.setDouble(8, cost);
            }
            stmt.setString(9, status);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
