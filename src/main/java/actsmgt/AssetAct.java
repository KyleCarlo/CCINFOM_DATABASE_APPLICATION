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
    public int recordAssetActivity(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");

            PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO asset_activity (asset_id, activity_date, activity_description, tent_start, tent_end, act_start, act_end, cost, status) " +
                                    "VALUES (?,         DATE(?),        ?,                  DATE(?),    DATE(?),    ?,       ?,       ?,    ?);"
            );
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
