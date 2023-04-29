package utc2.itk62.sneaker.repositories;

import utc2.itk62.sneaker.common.Paging;
import utc2.itk62.sneaker.connection.ConnectionUtil;
import utc2.itk62.sneaker.models.Category;
import utc2.itk62.sneaker.models.Invoice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceRepo {
    public InvoiceRepo() {
    }

    public List<Invoice> getAllInvoices(Paging paging) {
        paging.checkPageLimit();
        List<Invoice> invoiceList = new ArrayList<Invoice>();
        String query = "SELECT * FROM invoice WHERE status = 1 LIMIT ? OFFSET ? ";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setInt(1,paging.getLimit());
            ptmt.setInt(2,paging.getOffset());
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
//                invoice.setIdStaff(rs.getInt("id_staff"));
//                invoice.setIdCustomer(rs.getInt("id_customer"));
                invoice.setMoneyTotal(rs.getDouble("money_total"));
                invoice.setDeliveryAddress(rs.getString("delivery_address"));
                invoice.setDeliveryPhoneNumber(rs.getString("delivery_phone_number"));
                invoice.setStatus(rs.getInt("status"));
                invoice.setCreatedAt(rs.getTimestamp("created_at"));
                invoice.setUpdatedAt(rs.getTimestamp("updated_at"));
                invoiceList.add(invoice);
            }
            return invoiceList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }

    public Invoice getInvoiceById(int id) {
        String query = "SELECT * FROM invoice WHERE status = 1 WHERE id = ? ";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setInt(1, id);
            ResultSet rs = ptmt.executeQuery();
            Invoice invoice = null;
            while (rs.next()) {
                invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
//                invoice.setIdStaff(rs.getInt("id_staff"));
//                invoice.setIdCustomer(rs.getInt("id_customer"));
                invoice.setMoneyTotal(rs.getDouble("money_total"));
                invoice.setDeliveryAddress(rs.getString("delivery_address"));
                invoice.setDeliveryPhoneNumber(rs.getString("delivery_phone_number"));
                invoice.setStatus(rs.getInt("status"));
                invoice.setCreatedAt(rs.getTimestamp("created_at"));
                invoice.setUpdatedAt(rs.getTimestamp("updated_at"));
            }
            return invoice;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }

    public Invoice getCategoryByIdStaff(int idStaff) {
        String query = "SELECT * FROM invoice WHERE status = 1 AND id_staff = ?";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setInt(1, idStaff);
            ResultSet rs = ptmt.executeQuery();
            Invoice invoice = null;
            while (rs.next()) {
                invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
//                invoice.setIdStaff(rs.getInt("id_staff"));
//                invoice.setIdCustomer(rs.getInt("id_customer"));
                invoice.setMoneyTotal(rs.getDouble("money_total"));
                invoice.setDeliveryAddress(rs.getString("delivery_address"));
                invoice.setDeliveryPhoneNumber(rs.getString("delivery_phone_number"));
                invoice.setStatus(rs.getInt("status"));
                invoice.setCreatedAt(rs.getTimestamp("created_at"));
                invoice.setUpdatedAt(rs.getTimestamp("updated_at"));
            }
            return invoice;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }

    public Invoice getCategoryByIdCustomer(int idCustomer) {
        String query = "SELECT * FROM invoice WHERE status = 1 AND id_customer = ? ";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setInt(1, idCustomer);
            ResultSet rs = ptmt.executeQuery();
            Invoice invoice = null;
            while (rs.next()) {
                invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
//                invoice.setIdStaff(rs.getInt("id_staff"));
//                invoice.setIdCustomer(rs.getInt("id_customer"));
                invoice.setMoneyTotal(rs.getDouble("money_total"));
                invoice.setDeliveryAddress(rs.getString("delivery_address"));
                invoice.setDeliveryPhoneNumber(rs.getString("delivery_phone_number"));
                invoice.setStatus(rs.getInt("status"));
                invoice.setCreatedAt(rs.getTimestamp("created_at"));
                invoice.setUpdatedAt(rs.getTimestamp("updated_at"));
            }
            return invoice;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }

    public int deleteInvoice(int idInvoice) {
        String query = "UPDATE invoice SET status = 1" +
                " WHERE id = ?";
        int result = 0;
        Connection conn = ConnectionUtil.getConnection();

        try{
            conn.setAutoCommit(false);
            PreparedStatement ptmt = conn.prepareStatement(query);
            ptmt.setInt(1, idInvoice);

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

    public Invoice createInvoice(Invoice invoice) {
        String query = "INSERT INTO invoice(id_staff, id_customer, money_total, delivery_address, delivery_phone_number, total_quantity) "
                + "VALUES(?, ?, ?, ?, ?, ?) ";
        int result = 0;
        Connection conn = ConnectionUtil.getConnection();

        try{
            conn.setAutoCommit(false);
            PreparedStatement ptmt = conn.prepareStatement(query,  Statement.RETURN_GENERATED_KEYS);
            ptmt.setInt(1, invoice.getStaff().getId());
            ptmt.setInt(2, invoice.getCustomer().getId());
            ptmt.setDouble(3, invoice.getMoneyTotal());
            ptmt.setString(4, invoice.getDeliveryAddress());
            ptmt.setString(5,invoice.getDeliveryPhoneNumber());
            ptmt.setInt(6,invoice.getTotalQuantity());
            result = ptmt.executeUpdate();
            ResultSet rs = ptmt.getGeneratedKeys();
            Invoice invoiceInDb = new Invoice();
            if (rs.next()) {
                invoiceInDb.setId(rs.getInt(1));
                System.out.println(invoiceInDb.getId());
            }
            conn.commit();
            return invoiceInDb;
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
        return null;
    }



}
