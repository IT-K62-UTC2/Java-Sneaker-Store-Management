package utc2.itk62.sneaker.repositories;

import utc2.itk62.sneaker.common.Paging;
import utc2.itk62.sneaker.connection.ConnectionUtil;
import utc2.itk62.sneaker.models.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo {
    public SupplierRepo() {
    }

    public List<Supplier> getAllSupplier(Paging paging) {
        paging.checkPageLimit();
        List<Supplier> supplierList = new ArrayList<Supplier>();
        String query = "SELECT * FROM supplier " +
                "WHERE supplier.status = 1 LIMIT ? OFFSET ?";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setInt(1,paging.getLimit());
            ptmt.setInt(2,paging.getOffset());
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setId(rs.getInt("id"));
                supplier.setName(rs.getString("name"));
                supplier.setAddress(rs.getString("address"));
                supplier.setEmail(rs.getString("email"));
                supplier.setPhoneNumber(rs.getString("phone_number"));
                supplier.setStatus(rs.getInt("status"));
                supplier.setCreatedAt(rs.getTimestamp("created_at"));
                supplier.setUpdatedAt(rs.getTimestamp("updated_at"));
                supplierList.add(supplier);
            }
            return supplierList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }


    public Supplier getSupplierByUsername(String username) {
        String query = "SELECT * FROM supplier " +
                "WHERE name=?";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setString(1,  username);
            ResultSet rs = ptmt.executeQuery();
            Supplier supplier = null;
            while (rs.next()) {
                supplier = new Supplier();
                supplier.setId(rs.getInt("id"));
                supplier.setName(rs.getString("name"));
                supplier.setAddress(rs.getString("address"));
                supplier.setEmail(rs.getString("email"));
                supplier.setPhoneNumber(rs.getString("phone_number"));
                supplier.setStatus(rs.getInt("status"));
                supplier.setCreatedAt(rs.getTimestamp("created_at"));
                supplier.setUpdatedAt(rs.getTimestamp("updated_at"));

            }
            return supplier;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }
    public Supplier getSupplierByEmail(String email) {
        String query = "SELECT * FROM supplier " +
                "WHERE email=?";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setString(1,  email);
            ResultSet rs = ptmt.executeQuery();
            Supplier supplier = null;
            while (rs.next()) {
                supplier = new Supplier();

                supplier.setId(rs.getInt("id"));
                supplier.setName(rs.getString("name"));
                supplier.setAddress(rs.getString("address"));
                supplier.setEmail(rs.getString("email"));
                supplier.setPhoneNumber(rs.getString("phone_number"));
                supplier.setStatus(rs.getInt("status"));
                supplier.setCreatedAt(rs.getTimestamp("created_at"));
                supplier.setUpdatedAt(rs.getTimestamp("updated_at"));

            }
            return supplier;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }


    public Supplier getSupplierByPhoneNumber(String phoneNumber) {
        String query = "SELECT * FROM supplier " +
                "WHERE phone_number=?";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setString(1,  phoneNumber);
            ResultSet rs = ptmt.executeQuery();
            Supplier supplier = null;
            while (rs.next()) {
                supplier = new Supplier();
                supplier.setId(rs.getInt("id"));
                supplier.setName(rs.getString("name"));
                supplier.setAddress(rs.getString("address"));
                supplier.setEmail(rs.getString("email"));
                supplier.setPhoneNumber(rs.getString("phone_number"));
                supplier.setStatus(rs.getInt("status"));
                supplier.setCreatedAt(rs.getTimestamp("created_at"));
                supplier.setUpdatedAt(rs.getTimestamp("updated_at"));


            }
            return supplier;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }

    public int updateSupplier(Supplier supplier){
        String query = "UPDATE supplier SET" +
                " name = ?," +
                " address = ?," +
                " email = ?," +
                " phone_number = ?"+
                " WHERE id = ? AND status = 1";
        int result = 0;
        Connection conn = ConnectionUtil.getConnection();
        try{
            conn.setAutoCommit(false);
            PreparedStatement ptmt = conn.prepareStatement(query);

            ptmt.setString(1, supplier.getName());
            ptmt.setString(2, supplier.getAddress());
            ptmt.setString(3, supplier.getEmail());
            ptmt.setString(4, supplier.getPhoneNumber());
            ptmt.setInt(5, supplier.getId());
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

    public int createSupplier(Supplier supplier) {
        String query = "INSERT INTO supplier(" +
                "name, " +
                "address, " +
                "email, " +
                "phone_number) " +
                " VALUES(?, ?, ?, ?)";
        int result = 0;
        Connection conn = ConnectionUtil.getConnection();

        try{
            conn.setAutoCommit(false);
            PreparedStatement ptmt = conn.prepareStatement(query);
            ptmt.setString(1, supplier.getName());
            ptmt.setString(2, supplier.getAddress());
            ptmt.setString(3, supplier.getEmail());
            ptmt.setString(4, supplier.getPhoneNumber());

            System.out.println(ptmt.toString());

            result = ptmt.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionUtil.closeConnection();
        }
        return result;
    }

    public int deleteSupplier(int supplierId) {
        String query = "DELETE FROM supplier WHERE id = ?";
        int result = 0;
        Connection conn = ConnectionUtil.getConnection();

        try{
            conn.setAutoCommit(false);
            PreparedStatement ptmt = conn.prepareStatement(query);
            ptmt.setInt(1, supplierId);
            result = ptmt.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            ConnectionUtil.closeConnection();
        }
        return result;
    }


}
