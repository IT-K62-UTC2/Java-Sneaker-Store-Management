package utc2.itk62.store.repositories;

import utc2.itk62.store.common.Paging;
import utc2.itk62.store.connection.ConnectionUtil;
import utc2.itk62.store.models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {
    public CustomerRepo() {
    }

    public List<Customer> getAllCustomer(Paging paging) {
        paging.checkPageLimit();
        List<Customer> customerList = new ArrayList<Customer>();
        String query = "SELECT * FROM customer WHERE status = 1 LIMIT ? OFFSET ? ";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setInt(1,paging.getLimit());
            ptmt.setInt(2,paging.getOffset());
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFullName(rs.getString("fullname"));
                customer.setAddress(rs.getString("address"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setCccd(rs.getString("cccd"));
                customer.setGender(rs.getString("gender"));
                customer.setStatus(rs.getInt("status"));
                customer.setCreatedAt(rs.getTimestamp("created_at"));
                customer.setUpdatedAt(rs.getTimestamp("updated_at"));
                customerList.add(customer);
            }
            return customerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }

    public Customer getCustomerById(int customerId) {
        String query = "SELECT * FROM customer WHERE status = 1 AND id = ?";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setInt(1,customerId);
            ResultSet rs = ptmt.executeQuery();
            Customer customer = null;
            while (rs.next()) {
                customer.setId(rs.getInt("id"));
                customer.setFullName(rs.getString("fullname"));
                customer.setAddress(rs.getString("address"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setCccd(rs.getString("cccd"));
                customer.setGender(rs.getString("gender"));
                customer.setStatus(rs.getInt("status"));
                customer.setCreatedAt(rs.getTimestamp("created_at"));
                customer.setUpdatedAt(rs.getTimestamp("updated_at"));
            }
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }

    public Customer getCustomerByPhoneNumber(String phoneNumber){
        String query = "SELECT * FROM customer WHERE status = 1 AND phone_number = ?";
        try {
            PreparedStatement ptmt = ConnectionUtil.getConnection().prepareStatement(query);
            ptmt.setString(1,phoneNumber);
            ResultSet rs = ptmt.executeQuery();
            Customer customer = null;
            while (rs.next()) {
                customer.setId(rs.getInt("id"));
                customer.setFullName(rs.getString("fullname"));
                customer.setAddress(rs.getString("address"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setCccd(rs.getString("cccd"));
                customer.setGender(rs.getString("gender"));
                customer.setStatus(rs.getInt("status"));
                customer.setCreatedAt(rs.getTimestamp("created_at"));
                customer.setUpdatedAt(rs.getTimestamp("updated_at"));
            }
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.closeConnection();
        }
    }

    public int createCustomer(Customer customer) {
        String query = "INSERT INTO customer(fullname, address, email, phone_number, gender, cccd) "
                + "VALUES(?, ?, ?, ?, ?, ?) ";
        int result = 0;
        Connection conn = ConnectionUtil.getConnection();

        try{
            conn.setAutoCommit(false);
            PreparedStatement ptmt = conn.prepareStatement(query);
            ptmt.setString(1, customer.getFullName());
            ptmt.setString(2, customer.getAddress());
            ptmt.setString(3, customer.getEmail());
            ptmt.setString(4, customer.getPhoneNumber());
            ptmt.setString(5, customer.getGender());
            ptmt.setString(6, customer.getCccd());
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

    public int updateCustomer(Customer customer) {
        String query = "UPDATE customer SET fullname = ?, address = ?, email = ?, phone_number = ?, gender = ?, cccd= ?" +
                " WHERE id = ?";
        int result = 0;
        Connection conn = ConnectionUtil.getConnection();

        try{
            conn.setAutoCommit(false);
            PreparedStatement ptmt = conn.prepareStatement(query);
            ptmt.setString(1, customer.getFullName());
            ptmt.setString(2, customer.getAddress());
            ptmt.setString(3, customer.getEmail());
            ptmt.setString(4, customer.getPhoneNumber());
            ptmt.setString(5, customer.getGender());
            ptmt.setString(6, customer.getCccd());
            ptmt.setInt(7, customer.getId());

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

    public int deleteCustomer(int idCustomer) {
        String query = "UPDATE customer SET status = 0" +
                " WHERE id = ?";
        int result = 0;
        Connection conn = ConnectionUtil.getConnection();

        try{
            conn.setAutoCommit(false);
            PreparedStatement ptmt = conn.prepareStatement(query);
            ptmt.setInt(1, idCustomer);

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
