package utc2.itk62.sneaker.repositories;

import utc2.itk62.sneaker.common.Paging;
import utc2.itk62.sneaker.connection.ConnectionUtil;
import utc2.itk62.sneaker.models.InvoiceDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDetailRepo {
    public InvoiceDetailRepo() {
    }

    public List<InvoiceDetail> getAllInvoicesDetailByInvoiceId(Paging paging, int invoiceId) {
        paging.checkPageLimit();
        List<InvoiceDetail> invoiceList = new ArrayList<InvoiceDetail>();
        String query = "SELECT invoice_detail.id, " +
                "invoice_detail.id_product, " +
                "invoice_detail.id_invoice, " +
                "invoice_detail.money_total," +
                "invoice_detail.status," +
                " invoice_detail.created_at," +
                " invoice_detail.updated_at " +
                "FROM invoice_detail " +
                "INNER JOIN invoice ON invoice_detail.id_invoice = invoice.id" +
                " WHERE invoice_detail.status = 1 AND invoice.status = 1 LIMIT ? OFFSET ?";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setInt(1,paging.getLimit());
            ptmt.setInt(2,paging.getOffset());
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                InvoiceDetail invoiceDetail = new InvoiceDetail();
                invoiceDetail.setId(rs.getInt("id"));
//                invoiceDetail.setIdProduct(rs.getInt("id_product"));
//                invoiceDetail.setIdInvoice(rs.getInt("id_invoice"));
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
