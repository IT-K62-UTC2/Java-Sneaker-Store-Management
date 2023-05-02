package utc2.itk62.store.repositories;

import utc2.itk62.store.common.Paging;
import utc2.itk62.store.connection.ConnectionUtil;
import utc2.itk62.store.models.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo {
    public List<Supplier> getAllSupplier(Paging paging) {
        List<Supplier> supplierList = new ArrayList<Supplier>();
        String query = "SELECT * FROM supplier WHERE status = 1 LIMIT ? OFFSET ?";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setInt(1, paging.getLimit());
            ptmt.setInt(2, paging.getOffset());
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setId(rs.getInt("id"));
                supplier.setAddress(rs.getString("address"));
                supplier.setPhoneNumber(rs.getString("phone_number"));
                supplier.setEmail(rs.getString("email"));
                supplier.setName(rs.getString("name"));
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

    public int createSupplier(Supplier supplier) {
        String query = "INSERT INTO supplier(`name`, address, `phone_number`, `email`)" +
                " VALUES(?, ?, ?, ?)";
        int result = 0;
        Connection conn = ConnectionUtil.getConnection();

        try {
            conn.setAutoCommit(false);
            PreparedStatement ptmt = conn.prepareStatement(query);
            ptmt.setString(1, supplier.getName());
            ptmt.setString(2, supplier.getAddress());
            ptmt.setString(3, supplier.getPhoneNumber());
            ptmt.setString(4, supplier.getEmail());
            result = ptmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.closeConnection();
        }
        return result;
    }

    public int updateSupplier(Supplier supplier) {
        String query = "UPDATE supplier SET" +
                " address = ?,  " +
                " phone_number = ?," +
                " `name` = ?," +
                " `email` = ?" +
                " WHERE id = ? AND status = 1";
        int result = 0;
        Connection conn = ConnectionUtil.getConnection();
        try {
            conn.setAutoCommit(false);
            PreparedStatement ptmt = conn.prepareStatement(query);
            ptmt.setString(1, supplier.getAddress());
            ptmt.setString(2, supplier.getPhoneNumber());
            ptmt.setString(3, supplier.getName());
            ptmt.setString(4, supplier.getEmail());
            ptmt.setInt(5, supplier.getId());

            System.out.println(ptmt);
            result = ptmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        } finally {
            ConnectionUtil.closeConnection();
        }
        return result;
    }
}
