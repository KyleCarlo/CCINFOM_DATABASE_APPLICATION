package assetsmgt;

import java.util.*;
import java.sql.*;

public class assets {
    public int asset_id;
    public String asset_name;
    public String asset_description;
    public String acquisition_date;
    public int forrent;
    public String status;
    public Double asset_value;
    public String type_asset;
    public Double loc_lattitude;
    public Double loc_longiture;
    public String hoa_name;
    public int enclosing_asset;

    public ArrayList<String> hoa_nameList = new ArrayList<>();
    public ArrayList<Integer> asset_idList = new ArrayList<>();
    public ArrayList<String> asset_nameList = new ArrayList<>();
    public assets(){}

    // Asset Activity
    public String activity_date;
    public String activity_description;
    public String tent_start;
    public String tent_end;
    public String act_start;
    public String act_end;
    public int cost;
    // public String status;
    public String auth_officer;
    public int ornum;

    public int getAssetList(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement stmt = conn.prepareStatement("SELECT asset_id, asset_name FROM assets;");
            ResultSet rs = stmt.executeQuery();

            asset_idList.clear();
            asset_nameList.clear();

            while(rs.next()){
                asset_id = rs.getInt("asset_id");
                asset_name = rs.getString("asset_name");
                asset_idList.add(asset_id);
                asset_nameList.add(asset_name);
            }
            stmt.close();
            conn.close();

            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int getHoaList(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement stmt = conn.prepareStatement("SELECT hoa_name FROM hoa");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                hoa_name = rs.getString("hoa_name");
                hoa_nameList.add(hoa_name);
            }
            stmt.close();
            conn.close();

            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int registerAsset(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement stmt = conn.prepareStatement("SELECT MAX(asset_id) AS 'maxID' FROM assets");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                asset_id = rs.getInt("maxID") + 1;
            }

            stmt = conn.prepareStatement("INSERT INTO assets (`asset_id`, `asset_name`, `asset_description`, `acquisition_date`, `forrent`, `asset_value`, `type_asset`, `status`, `loc_lattitude`, `loc_longiture`, `hoa_name`, `enclosing_asset`) " +
                                             "VALUES (?, ?, ?, DATE(?), ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, asset_id);
            stmt.setString(2, asset_name);
            stmt.setString(3, asset_description);
            stmt.setString(4, acquisition_date);
            stmt.setInt(5, forrent);
            stmt.setDouble(6, asset_value);
            stmt.setString(7, type_asset);
            stmt.setString(8, status);
            stmt.setDouble(9, loc_lattitude);
            stmt.setDouble(10, loc_longiture);
            stmt.setString(11, hoa_name);
            if (enclosing_asset == -1) {
                stmt.setNull(12, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(12, enclosing_asset);
            }
            stmt.executeUpdate();

            System.out.println("Entered2");
            stmt.close();
            conn.close();
            return 1;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int updateAsset(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM assets WHERE hoa_name='hoa_name' AND asset_id='asset_id'");
            ResultSet rs = stmt.executeQuery();

            stmt = conn.prepareStatement("INSERT INTO assets (`hoa_name`, `asset_id`, `asset_name`, `asset_description`, `acquisition_date`, `forrent`, `asset_value`, `type_asset`, `status`, `loc_lattitude`, `loc_longiture`, `enclosing_asset`) " +
                    "VALUES (?, ?, ?, ?, DATE(?), ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, hoa_name);
            stmt.setInt(2, asset_id);
            stmt.setString(3, asset_name);
            stmt.setString(4, asset_description);
            stmt.setString(5, acquisition_date);
            stmt.setInt(6, forrent);
            stmt.setDouble(7, asset_value);
            stmt.setString(8, type_asset);
            stmt.setString(9, status);
            stmt.setDouble(10, loc_lattitude);
            stmt.setDouble(11, loc_longiture);
            if (enclosing_asset == -1) {
                stmt.setNull(12, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(12, enclosing_asset);
            }
            stmt.executeUpdate();

            System.out.println("Entered2");
            stmt.close();
            conn.close();
            return 1;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
    public int recordAssetActivity(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement stmt = conn.prepareStatement("SELECT aa.*, at. ornum, CONCAT(p.lastname + ',' + p.firstname) AS auth_officer" +
                                                                "FROM asset_activity aa" +
                                                                "    JOIN asset_transactions at ON aa.asset_id=at.asset_id" +
                                                                "    JOIN officer o ON at.trans_position=o.position" +
                                                                "    JOIN homeowner ho ON o.ho_id=ho.ho_id" +
                                                                "    JOIN people p ON ho.ho_id=p.peopleid" +
                                                                ";");
            ResultSet rs = stmt.executeQuery();

            stmt = conn.prepareStatement("INSERT INTO assets (`hoa_name`, `asset_id`, `activity_date`, `activity_description`, `auth_officer`, `tent_start`, `tent_end`,`act_start`, `act_end`, `cost`, `status`) " +
                    "VALUES (?, ?, DATE(?), ?, ?, DATE(?), DATE(?), DATE(?), DATE(?), ?, ?)");

            stmt.setString(1, hoa_name);
            stmt.setInt(2, asset_id);
            stmt.setString(3, activity_date);
            stmt.setString(4, activity_description);
            stmt.setString(5, auth_officer);
            stmt.setString(6, tent_start);
            stmt.setString(7, tent_end);
            stmt.setString(8, act_start);
            stmt.setString(9, act_end);
            stmt.setInt(10, cost);
            stmt.setString(11, status);

            stmt.executeUpdate();

            System.out.println("Entered2");
            stmt.close();
            conn.close();
            return 1;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int updateAssetActivity(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement stmt = conn.prepareStatement("SELECT aa.asset_id, aa.status, aa.tent_start, aa.tent_end, aa.cost, at.ornum " +
                    "FROM asset_activity aa " +
                    "    JOIN asset_transactions at ON aa.asset_id=at.asset_id " +
                    ";");
            ResultSet rs = stmt.executeQuery();

            stmt = conn.prepareStatement("INSERT INTO assets (`asset_id`, `status`,`tent_start`, `tent_end`) " +
                    "VALUES (?, ?, DATE(?), DATE(?))");

            stmt.setInt(1, asset_id);
            stmt.setString(2, status);
            stmt.setString(3, tent_start);
            stmt.setString(4, tent_end);

            stmt.executeUpdate();

            System.out.println("Entered2");
            stmt.close();
            conn.close();
            return 1;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static void main(String args[]){
        assets a = new assets();
        a.hoa_name = "Test HOA";
        a.asset_name = "Test Asset";
        a.asset_description = "Test Asset Description";
        a.acquisition_date = "2020-01-01";
        a.forrent = 0;
        a.asset_value = 100.00;
        a.type_asset = "W";
        a.status = "P";
        a.loc_lattitude = 0.00;
        a.loc_longiture = 0.00;
        a.hoa_name = "SJH";
        a.enclosing_asset = 5001;
        a.registerAsset();
        System.out.println("sample");
    }
}
