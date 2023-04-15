package assetsmgt;

import java.util.*;
import java.sql.*;

public class Assets {
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

    public ArrayList<Integer> asset_idList = new ArrayList<>();
    public ArrayList<String> asset_nameList = new ArrayList<>();
    public ArrayList<String> asset_descriptionList = new ArrayList<>();
    public ArrayList<String> acquisition_dateList = new ArrayList<>();
    public ArrayList<Integer> forrentList = new ArrayList<>();
    public ArrayList<String> statusList = new ArrayList<>();
    public ArrayList<Double> asset_valueList = new ArrayList<>();
    public ArrayList<String> type_assetList = new ArrayList<>();
    public ArrayList<Double> loc_lattitudeList = new ArrayList<>();
    public ArrayList<Double> loc_longitureList = new ArrayList<>();
    public ArrayList<String> hoa_nameList = new ArrayList<>();
    public ArrayList<Integer> enclosing_assetList = new ArrayList<>();

    public Assets(){}
    public int disposeAsset(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement stmt = conn.prepareStatement("UPDATE assets SET status = 'X' WHERE asset_id = ?");
            stmt.setInt(1, asset_id);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return 1;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    public int getDisposableList(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement stmt = conn.prepareStatement("SELECT *\n" +
                    "FROM assets a\n" +
                    "WHERE a.status = 'S';");
            ResultSet rs = stmt.executeQuery();
            asset_idList.clear();
            asset_nameList.clear();
            asset_descriptionList.clear();
            acquisition_dateList.clear();
            forrentList.clear();
            statusList.clear();
            asset_valueList.clear();
            type_assetList.clear();
            loc_lattitudeList.clear();
            loc_longitureList.clear();
            hoa_nameList.clear();
            enclosing_assetList.clear();
            while(rs.next()){
                asset_id = rs.getInt("asset_id");
                asset_idList.add(asset_id);
                asset_name = rs.getString("asset_name");
                asset_nameList.add(asset_name);
                asset_description = rs.getString("asset_description");
                asset_descriptionList.add(asset_description);
                acquisition_date = rs.getString("acquisition_date");
                acquisition_dateList.add(acquisition_date);
                forrent = rs.getInt("forrent");
                forrentList.add(forrent);
                status = rs.getString("status");
                statusList.add(status);
                asset_value = rs.getDouble("asset_value");
                asset_valueList.add(asset_value);
                type_asset = rs.getString("type_asset");
                type_assetList.add(type_asset);
                loc_lattitude = rs.getDouble("loc_lattitude");
                loc_lattitudeList.add(loc_lattitude);
                loc_longiture = rs.getDouble("loc_longiture");
                loc_longitureList.add(loc_longiture);
                hoa_name = rs.getString("hoa_name");
                hoa_nameList.add(hoa_name);
                enclosing_asset = rs.getInt("enclosing_asset");
                enclosing_assetList.add(enclosing_asset);
            }
            stmt.close();
            conn.close();
            return 1;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
    public int deleteAsset(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement stmt = conn.prepareStatement("UPDATE assets SET enclosing_asset = NULL WHERE enclosing_asset = ?");
            stmt.setInt(1, asset_id);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("DELETE FROM assets WHERE asset_id = ?");
            stmt.setInt(1, asset_id);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    public int getDeletableList(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT *\n" +
                    "FROM assets a \n" +
                    "WHERE a.asset_id NOT IN (SELECT at.asset_id FROM asset_transactions at) " +
                    "AND a.asset_id NOT IN (SELECT da.asset_id FROM donated_assets da);");
            ResultSet rs = stmt.executeQuery();
            asset_idList.clear();
            asset_nameList.clear();
            asset_descriptionList.clear();
            acquisition_dateList.clear();
            forrentList.clear();
            statusList.clear();
            asset_valueList.clear();
            type_assetList.clear();
            loc_lattitudeList.clear();
            loc_longitureList.clear();
            hoa_nameList.clear();
            enclosing_assetList.clear();
            while(rs.next()){
                asset_id = rs.getInt("asset_id");
                asset_idList.add(asset_id);
                asset_name = rs.getString("asset_name");
                asset_nameList.add(asset_name);
                asset_description = rs.getString("asset_description");
                asset_descriptionList.add(asset_description);
                acquisition_date = rs.getString("acquisition_date");
                acquisition_dateList.add(acquisition_date);
                forrent = rs.getInt("forrent");
                forrentList.add(forrent);
                status = rs.getString("status");
                statusList.add(status);
                asset_value = rs.getDouble("asset_value");
                asset_valueList.add(asset_value);
                type_asset = rs.getString("type_asset");
                type_assetList.add(type_asset);
                loc_lattitude = rs.getDouble("loc_lattitude");
                loc_lattitudeList.add(loc_lattitude);
                loc_longiture = rs.getDouble("loc_longiture");
                loc_longitureList.add(loc_longiture);
                hoa_name = rs.getString("hoa_name");
                hoa_nameList.add(hoa_name);
                enclosing_asset = rs.getInt("enclosing_asset");
                enclosing_assetList.add(enclosing_asset);
            }
            stmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    public int updateAsset(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement stmt = conn.prepareStatement("UPDATE assets SET asset_name = ?, asset_description = ?, acquisition_date = DATE(?), forrent = ?, asset_value = ?, type_asset = ?, status = ?, loc_lattitude = ?, loc_longiture = ?, hoa_name = ?, enclosing_asset = ? WHERE asset_id = ?");
            stmt.setString(1, asset_name);
            stmt.setString(2, asset_description);
            stmt.setString(3, acquisition_date);
            stmt.setInt(4, forrent);
            stmt.setDouble(5, asset_value);
            stmt.setString(6, type_asset);
            stmt.setString(7, status);
            stmt.setDouble(8, loc_lattitude);
            stmt.setDouble(9, loc_longiture);
            stmt.setString(10, hoa_name);
            if (enclosing_asset == -1) {
                stmt.setNull(11, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(11, enclosing_asset);
            }
            stmt.setInt(12, asset_id);
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

            hoa_nameList.clear();
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
            stmt.close();
            conn.close();
            return 1;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static void main(String args[]){
        Assets a = new Assets();
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
