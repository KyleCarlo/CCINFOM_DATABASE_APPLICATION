package actsmgt;

import java.sql.*;
import java.util.*;

public class ActTrans {
    public int asset_id;
    public String transaction_date;
    public int trans_hoid;
    public String trans_position;
    public String trans_electiondate;

    public int isdeleted;
    public int approval_hoid;
    public String approval_position;
    public String approval_electiondate;
    public int ornum;
    public String transaction_type;

    public ArrayList<Integer> trans_hoidList = new ArrayList<>();
    public ArrayList<String> trans_positionList = new ArrayList<>();
    public ArrayList<String> trans_electiondateList = new ArrayList<>();

    public ArrayList<Integer> isdeletedList = new ArrayList<>();
    public ArrayList<Integer> approval_hoidList = new ArrayList<>();
    public ArrayList<String> approval_positionList = new ArrayList<>();
    public ArrayList<String> approval_electiondateList = new ArrayList<>();
    public ArrayList<Integer> ornumList = new ArrayList<>();
    public ArrayList<String> transaction_typeList = new ArrayList<>();

    public ActTrans() {}
    public int getOrNumList(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT r.ornum FROM ref_ornumbers r " +
                    "WHERE r.ornum NOT IN " +
                    "(SELECT ornum FROM asset_transactions WHERE ornum IS NOT NULL);"
            );
            ResultSet rs = stmt.executeQuery();
            ornumList.clear();
            while (rs.next()){
                int ornum = rs.getInt("ornum");
                ornumList.add(ornum);
            }
            stmt.close();
            conn.close();
            return 1;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
    public int updateAssetActivity(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            System.out.println("TARGETS INSIDE ACTSTRANS");
            System.out.println("asset_id: " + asset_id);
            System.out.println("transaction_date: " + transaction_date);

            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE asset_transactions SET " +
                        "trans_hoid = ?, " +
                        "trans_position = ?, " +
                        "trans_electiondate = DATE (?), " +
                        "approval_hoid = ?, " +
                        "approval_position = ?, " +
                        "approval_electiondate = DATE (?), " +
                        "ornum = ? " +
                        "WHERE asset_id = ? AND transaction_date = DATE(?);"
                );
            stmt.setInt(1, trans_hoid);
            stmt.setString(2, trans_position);
            stmt.setString(3, trans_electiondate);
            if (approval_hoid == 0) {
                stmt.setNull(4, Types.INTEGER);
                stmt.setNull(5, Types.VARCHAR);
                stmt.setNull(6, Types.VARCHAR);
            } else {
                System.out.println("approval null");
                stmt.setInt(4, approval_hoid);
                stmt.setString(5, approval_position);
                stmt.setString(6, approval_electiondate);
            }
            if (ornum == 0) {
                stmt.setNull(7, Types.INTEGER);
            } else {
                stmt.setInt(7, ornum);
            }

            stmt.setInt(8, asset_id);

            stmt.setString(9, transaction_date);



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
    public void revertTrans() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM asset_transactions WHERE asset_id = ? AND transaction_date = DATE(?)"
            );
            System.out.println("asset_id: " + asset_id);
            System.out.println("transaction_date: " + transaction_date);
            System.out.println(trans_hoid);
            stmt.setInt(1, asset_id);
            stmt.setString(2, transaction_date);
            stmt.executeUpdate();
            System.out.println("Successful Revert");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public int recordAssetActivity(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO asset_transactions (asset_id, transaction_date, trans_hoid, trans_position, trans_electiondate, isdeleted, approval_hoid, transaction_type) " +
                            "                    VALUES (?, DATE(?), ?, ?, DATE(?), ?, ?, ?);"
            );
            stmt.setInt(1, asset_id);
            stmt.setString(2, transaction_date);
            stmt.setInt(3, trans_hoid);
            stmt.setString(4, trans_position);
            stmt.setString(5, trans_electiondate);
            stmt.setInt(6, 0);
            stmt.setNull(7, Types.INTEGER);
            stmt.setString(8, "A");
            stmt.executeUpdate();
            System.out.println("Successful Insertion to Transaction");
            return 1;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
    public int getPresHoid(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT o.ho_id AS 'id', o.position AS 'position', o.election_date AS 'date'\n" +
                    "FROM officer o \n" +
                    "WHERE o.end_date >= DATE(NOW()) AND o.position = 'President';");
            ResultSet rs = stmt.executeQuery();

            approval_hoidList.clear();
            approval_positionList.clear();
            approval_positionList.clear();

            while(rs.next()) {
                approval_hoid = rs.getInt("id");
                approval_position = rs.getString("position");
                approval_electiondate = rs.getString("date");
            }
            stmt.close();
            conn.close();
            return 1;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    public int getTransHoidList(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoadb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement stmt = conn.prepareStatement("SELECT o.ho_id AS 'id', o.position AS 'position', o.election_date AS 'date'\n" +
                    "FROM officer o \n" +
                    "WHERE o.end_date >= DATE(NOW());");
            ResultSet rs = stmt.executeQuery();

            trans_hoidList.clear();
            trans_positionList.clear();
            trans_electiondateList.clear();

            while(rs.next()){
                int ho_id = rs.getInt("id");
                trans_hoidList.add(ho_id);
                String position = rs.getString("position");
                trans_positionList.add(position);
                String election_date = rs.getString("date");
                trans_electiondateList.add(election_date);
            }
            stmt.close();
            conn.close();
            return 1;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}


