package utc2.itk62.store.repositories;

import utc2.itk62.store.common.Paging;
import utc2.itk62.store.connection.ConnectionUtil;
import utc2.itk62.store.models.Position;
import utc2.itk62.store.models.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffRepo {
    public StaffRepo() {
    }

    public List<Staff> getAllStaff(Paging paging) {
        paging.checkPageLimit();
        List<Staff> staffList = new ArrayList<Staff>();
        String query = "SELECT * FROM staff LEFT JOIN position ON staff.id_position = position.id " +
                "WHERE staff.status = 1 AND staff.id != 1 ORDER BY staff.created_at DESC LIMIT ? OFFSET ?";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setInt(1,paging.getLimit());
            ptmt.setInt(2,paging.getOffset());
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Staff staff = new Staff();
                Position position = new Position();
                position.setName(rs.getString("name"));
                position.setId(rs.getInt("position.id"));
                staff.setId(rs.getInt("staff.id"));
                staff.setUsername(rs.getString("username"));
                staff.setPassword(rs.getString("password"));
                staff.setFullName(rs.getString("fullname"));
                staff.setPathAvatar(rs.getString("avatar"));
//                staff.setIdPosition(rs.getInt("id_position"));
                staff.setPosition(position);
                staff.setAddress(rs.getString("address"));
                staff.setEmail(rs.getString("email"));
                staff.setPhoneNumber(rs.getString("phone_number"));
                staff.setCccd(rs.getString("cccd"));
                staff.setGender(rs.getString("gender"));
                staff.setStatus(rs.getInt("status"));
                staff.setCreatedAt(rs.getTimestamp("created_at"));
                staff.setUpdatedAt(rs.getTimestamp("updated_at"));
                staffList.add(staff);
            }
            return staffList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }

    public Staff getStaffById(int id) {
        String query = "SELECT * FROM staff WHERE status = 1 AND id = ?";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setInt(1,  id);
            ResultSet rs = ptmt.executeQuery();
            Staff staff = null;
            while (rs.next()) {
                staff = new Staff();
                staff.setId(rs.getInt("id"));
//                staff.setIdPosition(rs.getInt("id_position"));
                staff.setUsername(rs.getString("username"));
                staff.setPassword(rs.getString("password"));
                staff.setFullName(rs.getString("fullname"));
                staff.setAddress(rs.getString("address"));
                staff.setEmail(rs.getString("email"));
                staff.setPhoneNumber(rs.getString("phone_number"));
                staff.setCccd(rs.getString("cccd"));
                staff.setGender(rs.getString("gender"));
                staff.setStatus(rs.getInt("status"));
                staff.setCreatedAt(rs.getTimestamp("created_at"));
                staff.setUpdatedAt(rs.getTimestamp("updated_at"));
            }
            return staff;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }

    public Staff getStaffByUsername(String username) {
        String query = "SELECT * FROM staff " +
                " INNER JOIN position ON position.id = staff.id_position " +
                "WHERE username=?";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setString(1,  username);
            ResultSet rs = ptmt.executeQuery();
            Staff staff = null;
            Position position = null;
            while (rs.next()) {
                staff = new Staff();
                position = new Position();
                position.setName(rs.getString("name"));
                position.setId(rs.getInt("position.id"));
                staff.setId(rs.getInt("id"));
                staff.setPosition(position);
                staff.setPathAvatar(rs.getString("avatar"));
                staff.setUsername(rs.getString("username"));
                staff.setPassword(rs.getString("password"));
                staff.setFullName(rs.getString("fullname"));
                staff.setAddress(rs.getString("address"));
                staff.setEmail(rs.getString("email"));
                staff.setPhoneNumber(rs.getString("phone_number"));
                staff.setCccd(rs.getString("cccd"));
                staff.setGender(rs.getString("gender"));
                staff.setStatus(rs.getInt("status"));
                staff.setCreatedAt(rs.getTimestamp("created_at"));
                staff.setUpdatedAt(rs.getTimestamp("updated_at"));
            }
            return staff;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }

    public int updateStaff(Staff staff){
        String query = "UPDATE staff SET" +
                " id_position = ?,  " +
                " username = ?," +
                " fullname = ?," +
                " address = ?," +
                " email = ?," +
                " phone_number = ?,"+
                " cccd = ?,"+
                " gender = ?," +
                " avatar = ?" +
                " WHERE id = ? AND status = 1";
        int result = 0;
        Connection conn = ConnectionUtil.getConnection();
        try{
            conn.setAutoCommit(false);
            PreparedStatement ptmt = conn.prepareStatement(query);

            ptmt.setInt(1, staff.getPosition().getId());
            ptmt.setString(2, staff.getUsername());
            ptmt.setString(3, staff.getFullName());
            ptmt.setString(4, staff.getAddress());
            ptmt.setString(5, staff.getEmail());
            ptmt.setString(6, staff.getPhoneNumber());
            ptmt.setString(7, staff.getCccd());
            ptmt.setString(8, staff.getGender());
            ptmt.setString(9, staff.getPathAvatar());
            ptmt.setInt(10, staff.getId());
            System.out.println(ptmt);
            result = ptmt.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        } finally{
            ConnectionUtil.closeConnection();
        }
        return result;
    }

    public int updatePasswordStaff(int idStaff, String password){
        String query = "UPDATE staff SET" +
                " password = ?"+
                " WHERE id = ?";
        int result = 0;
        Connection conn = ConnectionUtil.getConnection();
        try{
            conn.setAutoCommit(false);
            PreparedStatement ptmt = conn.prepareStatement(query);
            ptmt.setString(1, password);
            ptmt.setInt(2,idStaff);
            result = ptmt.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        } finally{
            ConnectionUtil.closeConnection();
        }
        return result;
    }

    public int createStaff(Staff staff) {
        String query = "INSERT INTO staff(" +
                "id_position," +
                "username, " +
                "password, " +
                "fullname, " +
                "address, " +
                "email, " +
                "phone_number, " +
                "cccd, " +
                "gender," +
                "avatar)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = 0;
        Connection conn = ConnectionUtil.getConnection();

        try{
            conn.setAutoCommit(false);
            PreparedStatement ptmt = conn.prepareStatement(query);
            ptmt.setInt(1, staff.getPosition().getId());
            ptmt.setString(2, staff.getUsername());
            ptmt.setString(3, staff.getPassword());
            ptmt.setString(4, staff.getFullName());
            ptmt.setString(5, staff.getAddress());
            ptmt.setString(6, staff.getEmail());
            ptmt.setString(7, staff.getPhoneNumber());
            ptmt.setString(8, staff.getCccd());
            ptmt.setString(9, staff.getGender());
            ptmt.setString(10, staff.getPathAvatar());
            result = ptmt.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionUtil.closeConnection();
        }
        return result;
    }

    public int deleteStaff(int staffId) {
        String query = "UPDATE staff SET status = 0 WHERE id = ?";
        int result = 0;
        Connection conn = ConnectionUtil.getConnection();

        try{
            conn.setAutoCommit(false);
            PreparedStatement ptmt = conn.prepareStatement(query);
            ptmt.setInt(1, staffId);
            result = ptmt.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionUtil.closeConnection();
        }
        return result;
    }

    public Staff getStaffByEmail(String email) {
        String query = "SELECT * FROM staff " +
                " INNER JOIN position ON position.id = staff.id_position " +
                "WHERE email=?";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setString(1,  email);
            ResultSet rs = ptmt.executeQuery();
            Staff staff = null;
            Position position = null;
            while (rs.next()) {
                staff = new Staff();
                position = new Position();
                position.setName(rs.getString("name"));
                position.setId(rs.getInt("position.id"));
                staff.setId(rs.getInt("id"));
                staff.setPosition(position);
                staff.setUsername(rs.getString("username"));
                staff.setPassword(rs.getString("password"));
                staff.setFullName(rs.getString("fullname"));
                staff.setAddress(rs.getString("address"));
                staff.setEmail(rs.getString("email"));
                staff.setPhoneNumber(rs.getString("phone_number"));
                staff.setCccd(rs.getString("cccd"));
                staff.setGender(rs.getString("gender"));
                staff.setStatus(rs.getInt("status"));
                staff.setCreatedAt(rs.getTimestamp("created_at"));
                staff.setUpdatedAt(rs.getTimestamp("updated_at"));
            }
            return staff;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }

    public Staff getStaffByCCCD(String cccd) {
        String query = "SELECT * FROM staff " +
                " INNER JOIN position ON position.id = staff.id_position " +
                "WHERE cccd=?";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setString(1,  cccd);
            ResultSet rs = ptmt.executeQuery();
            Staff staff = null;
            Position position = null;
            while (rs.next()) {
                staff = new Staff();
                position = new Position();
                position.setName(rs.getString("name"));
                position.setId(rs.getInt("position.id"));
                staff.setId(rs.getInt("id"));
                staff.setPosition(position);
                staff.setUsername(rs.getString("username"));
                staff.setPassword(rs.getString("password"));
                staff.setFullName(rs.getString("fullname"));
                staff.setAddress(rs.getString("address"));
                staff.setEmail(rs.getString("email"));
                staff.setPhoneNumber(rs.getString("phone_number"));
                staff.setCccd(rs.getString("cccd"));
                staff.setGender(rs.getString("gender"));
                staff.setStatus(rs.getInt("status"));
                staff.setCreatedAt(rs.getTimestamp("created_at"));
                staff.setUpdatedAt(rs.getTimestamp("updated_at"));
            }
            return staff;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }

    public Staff getStaffByPhoneNumber(String phoneNumber) {
        String query = "SELECT * FROM staff " +
                " INNER JOIN position ON position.id = staff.id_position " +
                "WHERE phone_number=?";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setString(1,  phoneNumber);
            ResultSet rs = ptmt.executeQuery();
            Staff staff = null;
            Position position = null;
            while (rs.next()) {
                staff = new Staff();
                position = new Position();
                position.setName(rs.getString("name"));
                position.setId(rs.getInt("position.id"));
                staff.setId(rs.getInt("id"));
                staff.setPosition(position);
                staff.setUsername(rs.getString("username"));
                staff.setPassword(rs.getString("password"));
                staff.setFullName(rs.getString("fullname"));
                staff.setAddress(rs.getString("address"));
                staff.setEmail(rs.getString("email"));
                staff.setPhoneNumber(rs.getString("phone_number"));
                staff.setCccd(rs.getString("cccd"));
                staff.setGender(rs.getString("gender"));
                staff.setStatus(rs.getInt("status"));
                staff.setCreatedAt(rs.getTimestamp("created_at"));
                staff.setUpdatedAt(rs.getTimestamp("updated_at"));
            }
            return staff;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }
}
