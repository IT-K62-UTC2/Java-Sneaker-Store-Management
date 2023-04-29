package utc2.itk62.sneaker.repositories;

import utc2.itk62.sneaker.common.Paging;
import utc2.itk62.sneaker.connection.ConnectionUtil;
import utc2.itk62.sneaker.models.InvoiceDetail;
import utc2.itk62.sneaker.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDetailRepo {
    public InvoiceDetailRepo() {
    }

    public List<InvoiceDetail> getAllInvoicesDetailByInvoiceId(int invoiceId) {
        List<InvoiceDetail> invoiceList = new ArrayList<InvoiceDetail>();
            String query = "SELECT invoice_detail.*, product.price, product.name  FROM invoice_detail " +
                "LEFT JOIN product ON product.id = invoice_detail.id_product WHERE id_invoice = ?";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setInt(1, invoiceId);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                InvoiceDetail invoiceDetail = new InvoiceDetail();
                invoiceDetail.setId(rs.getInt("id"));
                Product product = new Product();
                product.setName(rs.getString("product.name"));
                product.setPrice(rs.getDouble("product.price"));
                invoiceDetail.setProduct(product);
                invoiceDetail.setProductQuantity(rs.getInt("product_quantity"));
                invoiceDetail.setMoneyTotal(rs.getDouble("money_total"));
                invoiceDetail.setStatus(rs.getInt("status"));
                invoiceDetail.setCreatedAt(rs.getTimestamp("created_at"));
                invoiceDetail.setUpdatedAt(rs.getTimestamp("updated_at"));
                invoiceList.add(invoiceDetail);
            }
            return invoiceList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }

    public int createInvoiceDetail(InvoiceDetail invoiceDetail) {
        String query = "INSERT INTO invoice_detail(id_invoice, id_product, product_quantity, money_total)" +
                "VALUES(?, ?, ?, ?)";
        int result = 0;
        Connection conn = ConnectionUtil.getConnection();

        try{
            conn.setAutoCommit(false);
            PreparedStatement ptmt = conn.prepareStatement(query);
            ptmt.setInt(1, invoiceDetail.getInvoice().getId());
            ptmt.setInt(2, invoiceDetail.getProduct().getId());
            ptmt.setInt(3, invoiceDetail.getProductQuantity());
            ptmt.setDouble(4, invoiceDetail.getMoneyTotal());
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
}
