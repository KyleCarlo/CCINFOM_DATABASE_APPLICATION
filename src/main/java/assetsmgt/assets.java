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
