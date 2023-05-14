package utc2.itk62.store.repositories;

import utc2.itk62.store.common.FromAndToDate;
import utc2.itk62.store.connection.ConnectionUtil;
import utc2.itk62.store.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImportGoodsRepo {

    public List<ImportGoods> getAllImportGoods(FromAndToDate fromAndToDate) {
        List<ImportGoods> importGoodsList = new ArrayList<ImportGoods>();
        String query = "SELECT import_goods.*,staff.fullname, staff.id FROM import_goods " +
                " LEFT JOIN staff ON staff.id = import_goods.id_staff" +
                " WHERE import_goods.status = 1 AND import_goods.created_at >= ? AND import_goods.created_at <= ? ORDER BY import_goods.created_at DESC";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setObject(1, fromAndToDate.getFromDate());
            ptmt.setObject(2, fromAndToDate.getToDate());
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                ImportGoods importGoods = new ImportGoods();
                importGoods.setId(rs.getInt("import_goods.id"));
                importGoods.setQuantity(rs.getInt("import_goods.quantity"));
                importGoods.setMoneyTotal(rs.getDouble("import_goods.money_total"));
                importGoods.setCreatedAt(rs.getTimestamp("import_goods.created_at"));
                Staff staff = new Staff();
                staff.setFullName(rs.getString("staff.fullname"));
                staff.setId(rs.getInt("staff.id"));
                importGoods.setStaff(staff);
                importGoodsList.add(importGoods);
            }
            return importGoodsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }

    public ImportGoods createImportGoods(ImportGoods importGoods) {
        String query = "INSERT INTO import_goods(id_staff, quantity, money_total) "
                + "VALUES(?, ?, ?) ";
        int result = 0;
        Connection conn = ConnectionUtil.getConnection();

        try {
            conn.setAutoCommit(false);
            PreparedStatement ptmt = conn.prepareStatement(query,  Statement.RETURN_GENERATED_KEYS);
            ptmt.setInt(1, importGoods.getStaff().getId());
            ptmt.setInt(2, importGoods.getQuantity());
            ptmt.setDouble(3, importGoods.getMoneyTotal());
            result = ptmt.executeUpdate();
            ResultSet rs = ptmt.getGeneratedKeys();
            ImportGoods importGoodsResult = new ImportGoods();
            if (rs.next()) {
                importGoodsResult.setId(rs.getInt(1));
            }
            conn.commit();
            return importGoodsResult ;
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
        return null;
    }
}
