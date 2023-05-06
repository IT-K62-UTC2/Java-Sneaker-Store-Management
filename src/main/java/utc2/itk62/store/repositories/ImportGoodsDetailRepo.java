package utc2.itk62.store.repositories;

import utc2.itk62.store.connection.ConnectionUtil;
import utc2.itk62.store.models.ImportGoodsDetail;
import utc2.itk62.store.models.InvoiceDetail;
import utc2.itk62.store.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImportGoodsDetailRepo {
    public int createImportGoodsDetail(ImportGoodsDetail importGoodsDetail) {
        String query = "INSERT INTO import_goods_detail(id_import_goods, id_product, quantity, money_total)" +
                "VALUES(?, ?, ?, ?)";
        int result = 0;
        Connection conn = ConnectionUtil.getConnection();

        try{
            conn.setAutoCommit(false);
            PreparedStatement ptmt = conn.prepareStatement(query);
            ptmt.setInt(1, importGoodsDetail.getImportGoods().getId());
            ptmt.setInt(2, importGoodsDetail.getProduct().getId());
            ptmt.setInt(3, importGoodsDetail.getQuantity());
            ptmt.setDouble(4, importGoodsDetail.getMoneyTotal());
            result = ptmt.executeUpdate();
            conn.commit();
        }catch (SQLException e){
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

    public List<ImportGoodsDetail> getAllInvoicesDetailByImportGoodsId(int id) {
        List<ImportGoodsDetail> importGoodsDetailList = new ArrayList<ImportGoodsDetail>();
        String query = "SELECT import_goods_detail.*, product.import_price, product.name, product.id  FROM import_goods_detail " +
                "LEFT JOIN product ON product.id = import_goods_detail.id_product WHERE id_import_goods = ?";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setInt(1, id);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                ImportGoodsDetail importGoodsDetail =new ImportGoodsDetail();
                importGoodsDetail.setId(rs.getInt("id"));
                importGoodsDetail.setQuantity(rs.getInt("quantity"));
                importGoodsDetail.setMoneyTotal(rs.getDouble("money_total"));
                Product product = new Product();
                product.setName(rs.getString("product.name"));
                product.setId(rs.getInt("product.id"));
                product.setImportPrice(rs.getDouble("product.import_price"));
                importGoodsDetail.setProduct(product);
                importGoodsDetailList.add(importGoodsDetail);
            }
            return importGoodsDetailList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }
}
