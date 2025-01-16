/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 *
 * @author jaxbo
 */
public class CustomerDAO extends DBContext {

    public Customer getCustomerAccount(String username, String password) {
        String sql = "SELECT * FROM Customer WHERE username = ? AND password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Customer(
                        rs.getInt("customerID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fullName"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("accountStatus"),
                        rs.getString("registrationDate"),
                        rs.getString("dateOfBirth"),
                        rs.getString("gender"),
                        rs.getString("profilePicture"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Customer> getAllCustomerAccount() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt("customerID");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullName = rs.getString("fullName");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String accountStatus = rs.getString("accountStatus");
                String registrationDate = rs.getString("registrationDate");
                String dateOfBirth = rs.getString("dateOfBirth");
                String gender = rs.getString("gender");
                String profilePicture = rs.getString("profilePicture");

                Customer customer = new Customer(customerID, username, password, fullName, email, phone, address,
                        accountStatus, registrationDate, dateOfBirth, gender, profilePicture);
                customers.add(customer); // Thêm customer vào danh sách
            }

        } catch (SQLException e) {
        }
        return customers; // Trả về danh sách khách hàng
    }

    public Customer checkCustomerAccountExist(String username, String email) {
        String sql = "select * from Customer where username = ? or email = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, email);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Customer(
                        rs.getInt("customerID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fullName"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("accountStatus"),
                        rs.getString("registrationDate"),
                        rs.getString("dateOfBirth"),
                        rs.getString("gender"),
                        rs.getString("profilePicture"));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    //Đăng nhập
    public Customer customerLogin(String usernameOrEmail, String password) {
        String sql = "select * from Customer where (username = ? OR email = ?) and password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, usernameOrEmail);
            st.setString(2, usernameOrEmail);
            st.setString(3, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Customer(
                        rs.getInt("customerID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fullName"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("accountStatus"),
                        rs.getString("registrationDate"),
                        rs.getString("dateOfBirth"),
                        rs.getString("gender"),
                        rs.getString("profilePicture"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //Đăng ký
    public void customerSignup(String username, String password, String fullname, String email, String phone, String address, String dateOfBirth, String gender) {
        String sql = "insert into Customer ([username], [password], [fullname],[email],[phone],[address],[accountStatus],[dateOfBirth],[gender]) values(?,?,?,?,?,?,'Active',?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, fullname);
            st.setString(4, email);
            st.setString(5, phone);
            st.setString(6, address);
            st.setString(7, dateOfBirth);
            st.setString(8, gender);

            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    //Chỉnh sửa profile của Customer
    public void updateCutomerProfile(String fullname, String email, String phone, String address, String dateOfBirth, String gender, int customerID) {
        String sql = "UPDATE Customer SET fullName = ?, email = ?, phone = ?, address = ?, dateOfBirth = ?, gender = ? WHERE customerID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fullname);
            st.setString(2, email);
            st.setString(3, phone);
            st.setString(4, address);
            st.setString(5, dateOfBirth);
            st.setString(6, gender);
            st.setInt(7, customerID);
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public static void main(String[] args) {
        CustomerDAO dao = new CustomerDAO();
        Customer customer = dao.getCustomerAccount("test2", "test");
        System.out.println(customer);
//        for (Customer a : customer) {
//            System.out.println(a);
//        }
    }

}
