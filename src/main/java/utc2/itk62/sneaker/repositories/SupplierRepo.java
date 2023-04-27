package utc2.itk62.sneaker.repositories;

import utc2.itk62.sneaker.common.Paging;
import utc2.itk62.sneaker.connection.ConnectionUtil;
import utc2.itk62.sneaker.models.Category;
import utc2.itk62.sneaker.models.Supplier;

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
            ptmt.setInt(1,paging.getLimit());
            ptmt.setInt(2,paging.getOffset());
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
}
