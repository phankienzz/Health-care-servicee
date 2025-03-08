/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        }
        return null;
    }

    public Customer customerLogin(String username) {
        String sql = "select * from Customer where username = ? and accountStatus = 'Active'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
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

    
    public List<Customer> getAllCustomer() {
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
    
    public List<Customer> getCustomerByName(String name) {
        List<Customer> listCustomer = new ArrayList<>();
        String sql = "select * from Customer where fullName like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + name + "%");
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
                listCustomer.add(customer); // Thêm customer vào danh sách
            }

        } catch (SQLException e) {
        }
        return listCustomer; // Trả về danh sách khách hàng

    }

    public boolean isUsernameExist(String username) {
        String sql = "SELECT customerID FROM Customer WHERE username = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            return rs.next(); // Nếu có dữ liệu, username đã tồn tại
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isEmailExist(String email) {
        String sql = "SELECT customerID FROM Customer WHERE email = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            return rs.next(); // Nếu có dữ liệu, email đã tồn tại
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Đăng nhập
    public Customer customerLogin(String username, String password) {
        String sql = "select * from Customer where username = ? and password = ?";
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

    public void updateCustomerProfile(String fullName, String email, String phone, String address, String dateOfBirth, String gender, InputStream profilePicture, int customerID) throws ParseException {
        String sql = "UPDATE Customer SET fullName = ?, email = ?, phone = ?, address = ?, dateOfBirth = ?, gender = ?, profilePicture = ? WHERE customerID = ?";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = sdf.parse(dateOfBirth);
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fullName);
            st.setString(2, email);
            st.setString(3, phone);
            st.setString(4, address);
            st.setDate(5, sqlDate);
            st.setString(6, gender);
            if (profilePicture != null) {
                st.setBlob(7, profilePicture);
            } else {
                st.setNull(7, java.sql.Types.BLOB);
            }
            st.setInt(8, customerID);

            int rowsUpdated = st.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Customer profile updated successfully.");
            } else {
                System.out.println("No customer found with the provided ID.");
            }
        } catch (SQLException | ParseException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public Customer getCustomerByEmail(String email) {
        String sql = "select * from Customer where email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
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

    public Customer getCustomerByID(int customerID) {
        String sql = "select * from Customer where customerID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customerID);
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

    public void updatePassword(String email, String password) {
        String sql = "update Customer set password = ? where email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, password);
            st.setString(2, email);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean checkOldPassword(int customerID, String oldPassword) {
        String sql = "SELECT password FROM Customer WHERE customerID = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, customerID);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    // Here you may want to hash 'oldPassword' and compare with 'storedPassword'.
                    // Assuming passwords are stored as plain text (not recommended), we directly compare.
                    return storedPassword.equals(oldPassword);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error checking old password: " + e.getMessage());
        }

        return false; // return false if customer not found or any error occurs
    }

    public void changeCustomerPassword(int customerID, String password) {
        String sql = "UPDATE Customer SET password = ? WHERE customerID = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, password);
            st.setInt(2, customerID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error changing password: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        CustomerDAO dao = new CustomerDAO();
        List<Customer> list = dao.getCustomerByName("Alice");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
}
