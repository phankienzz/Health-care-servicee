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
import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 *
 * @author jaxbo
 */
public class CustomerDAO extends DBContext {

//    ValidFunction valid = new ValidFunction();
    public Customer customerLogin(String username) {
        String sql = "SELECT customerID, username, password, fullName, email, phone, address, accountStatus, "
                + "registrationDate, CONVERT(NVARCHAR, dateOfBirth, 103) AS dateOfBirth, gender, profilePicture "
                + "FROM Customer WHERE username = ? AND accountStatus = 'Active'";

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

    //cai nay de phan trang
    public List<Customer> getAllCustomerActive(int index, int pageSize) {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer where accountStatus = 'Active' order by customerID offset ? rows  fetch  next ? rows only";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int offset = (index - 1) * pageSize;
            st.setInt(1, offset);
            st.setInt(2, pageSize);
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

    //cai nay de lay so luong
    public List<Customer> getAllCustomerActive() {
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from Customer where accountStatus = 'Active'";
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

    public List<Customer> getAllCustomerInactive(int index, int pageSize) {
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from Customer where accountStatus = 'Inactive' order by customerID offset ? rows  fetch  next ? rows only";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int offset = (index - 1) * pageSize;
            st.setInt(1, offset);
            st.setInt(2, pageSize);
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

    public List<Customer> getAllCustomerInactive() {
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from Customer where accountStatus = 'Inactive'";
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

    public List<Customer> getAllCustomer(int index, int pageSize) {
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from Customer order by customerID offset ? rows  fetch  next ? rows only";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int offset = (index - 1) * pageSize;
            st.setInt(1, offset);
            st.setInt(2, pageSize);
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
    
    public Customer getCustomerByID(int customerID,String status) {
        String sql = "select * from Customer where customerID = ? and accountStatus = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customerID);
            st.setString(2, status);
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

    public List<Customer> getCustomerByName(String name, int index, int pageSize) {
        List<Customer> listCustomer = new ArrayList<>();
        String sql = "select * from Customer where fullName like ? order by customerID offset ? rows fetch next ? rows only";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int offset = (index - 1) * pageSize;
            st.setString(1, "%" + name + "%");
            st.setInt(2, offset);
            st.setInt(3, pageSize);
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
    
    public List<Customer> getCustomerByName(String name, int index, int pageSize,String status) {
        List<Customer> listCustomer = new ArrayList<>();
        String sql = "select * from Customer where fullName like ? and accountStatus = ? order by customerID offset ? rows fetch next ? rows only";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            int offset = (index - 1) * pageSize;
            st.setString(1, "%" + name + "%");
            st.setString(2, status);
            st.setInt(3, offset);
            st.setInt(4, pageSize);
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

    public Customer getCustomerByIdAndName(int id, String name) {
        String sql = "select * FROM Customer WHERE customerID = ? and fullName like ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            pre.setString(2, "%" + name + "%");
            ResultSet rs = pre.executeQuery();
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
    
    public Customer getCustomerByIdAndName(int id, String name,String status) {
        String sql = "select * FROM Customer WHERE customerID = ? and fullName like ? and accountStatus = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            pre.setString(2, "%" + name + "%");
            pre.setString(3, status);
            ResultSet rs = pre.executeQuery();
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

    public void updateCustomerProfile(String fullName, String email, String phone, String address, String dateOfBirth, String gender, InputStream profilePicture, int customerID) {
        String sql;

        if (profilePicture != null) {
            sql = "UPDATE Customer SET fullName = ?, email = ?, phone = ?, address = ?, dateOfBirth = CONVERT(DATETIME, ?, 103), gender = ?, profilePicture = ? WHERE customerID = ?";
        } else {
            sql = "UPDATE Customer SET fullName = ?, email = ?, phone = ?, address = ?, dateOfBirth = CONVERT(DATETIME, ?, 103), gender = ? WHERE customerID = ?";
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fullName);
            st.setString(2, email);
            st.setString(3, phone);
            st.setString(4, address);
            st.setString(5, dateOfBirth);
            st.setString(6, gender);

            if (profilePicture != null) {
                st.setBlob(7, profilePicture);
                st.setInt(8, customerID);
            } else {
                st.setInt(7, customerID);
            }

            int rowsUpdated = st.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Customer profile updated successfully.");
            } else {
                System.out.println("No customer found with the provided ID.");
            }
        } catch (SQLException e) {
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

    public String getCustomerHashedPassword(int customerID) {
        String sql = "SELECT password FROM Customer WHERE CustomerID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, customerID);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("password");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting hashed password: " + e.getMessage());
        }
        return null;
    }

    public void changeCustomerPassword(int customerID, String hashedPassword) {
        String sql = "UPDATE Customer SET password = ? WHERE CustomerID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, hashedPassword);
            st.setInt(2, customerID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error changing password: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        CustomerDAO dao = new CustomerDAO();
//        List<Customer> list = dao.getCustomerByName("Alice");
//        for (Customer customer : list) {
//            System.out.println(customer);
//        }
        Customer c = dao.getCustomerByIdAndName(2, "b");
        System.out.println(c);
    }

}
