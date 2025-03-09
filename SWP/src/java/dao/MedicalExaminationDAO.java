/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.sun.jdi.connect.spi.Connection;
import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.MedicalExamination;
import model.Professional;
import model.Role;
import model.Service;

/**
 *
 * @author Gigabyte
 */
public class MedicalExaminationDAO extends DBContext {

    public MedicalExamination getMedicalExaminationByID(int examinationID) {
        ServiceDAO dao = new ServiceDAO();
        CustomerDAO cusDAO = new CustomerDAO();
        ProfessionalDAO proDAO = new ProfessionalDAO();
        String sql = "select * from MedicalExamination where examinationID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, examinationID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new MedicalExamination(
                        rs.getInt("examinationID"),
                        rs.getString("examinationDate"),
                        cusDAO.getCustomerByID(rs.getInt("customerID")),
                        rs.getString("status"),
                        proDAO.getProfessionalbyID(rs.getInt("consultantID")),
                        rs.getString("notes"),
                        rs.getString("createdAt"),
                        dao.getServiceExaminationID(examinationID));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<MedicalExamination> getAllMedicalExamination() {
        List<MedicalExamination> medicalExaminationList = new ArrayList<>();
        ServiceDAO dao = new ServiceDAO();
        CustomerDAO cusDAO = new CustomerDAO();
        ProfessionalDAO proDAO = new ProfessionalDAO();
        String sql = "SELECT * FROM MedicalExamination ";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                medicalExaminationList.add(new MedicalExamination(
                        rs.getInt("examinationID"),
                        rs.getString("examinationDate"),
                        cusDAO.getCustomerByID(rs.getInt("customerID")),
                        rs.getString("status"),
                        proDAO.getProfessionalbyID(rs.getInt("consultantID")),
                        rs.getString("notes"),
                        rs.getString("createdAt"),
                        dao.getServiceExaminationID(rs.getInt("examinationID"))));

            }

        } catch (SQLException e) {
        }
        return medicalExaminationList; 
    }

  

    // Lưu MedicalExamination mới
    public boolean saveMedicalExamination(MedicalExamination examination) {
        String sql = "INSERT INTO MedicalExamination (examinationDate, customerID, status, consultantID, notes, createdAt) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, examination.getExaminationDate());
            ps.setInt(2, examination.getCustomerId().getCustomerID());
            ps.setString(3, examination.getStatus());
            ps.setInt(4, examination.getConsultantId().getStaffID());
            ps.setString(5, examination.getNote());
            ps.setString(6, examination.getCreatedAt());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    saveExaminationServices(generatedId, examination.getList());
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lưu danh sách dịch vụ liên quan vào bảng MedicalService
    private void saveExaminationServices(int examinationId, List<Service> services) {
        String sql = "INSERT INTO MedicalService (examinationID, packageID) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            for (Service service : services) {
                ps.setInt(1, examinationId);
                ps.setInt(2, service.getPackageID());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
  public List<MedicalExamination> getAllExamination() {
        List<MedicalExamination> medicalExaminationList = new ArrayList<>();
        String sql = "SELECT m.*, c.fullName AS customerName, s.fullName AS staffName " +
                     "FROM MedicalExamination m " +
                     "JOIN Customer c ON m.customerID = c.customerID " +
                     "JOIN Staff s ON m.consultantID = s.staffID";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("customerID"));
                customer.setFullName(rs.getString("customerName"));

                Professional professional = new Professional();
                professional.setStaffID(rs.getInt("consultantID"));
                professional.setFullName(rs.getString("staffName"));

                MedicalExamination exam = new MedicalExamination();
                exam.setExaminationID(rs.getInt("examinationID"));
                exam.setExaminationDate(rs.getString("examinationDate"));
                exam.setCustomerId(customer);
                exam.setStatus(rs.getString("status"));
                exam.setConsultantId(professional);
                exam.setNote(rs.getString("notes"));
                exam.setCreatedAt(rs.getString("createdAt"));

                // Lấy danh sách dịch vụ của cuộc khám
                exam.setList(getServicesByExaminationId(rs.getInt("examinationID")));

                medicalExaminationList.add(exam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicalExaminationList;
    }

    public List<Service> getServicesByExaminationId(int examinationID) {
        List<Service> services = new ArrayList<>();
        String query = "SELECT ms.packageID, sp.packageName " +
                       "FROM MedicalService ms " +
                       "JOIN ServicePackage sp ON ms.packageID = sp.packageID " +
                       "WHERE ms.examinationID = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, examinationID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Service service = new Service();
                service.setPackageID(rs.getInt("packageID"));
                service.setPackageName(rs.getString("packageName"));
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }
    
    
    
    

   public static void main(String[] args) {
        MedicalExaminationDAO dao = new MedicalExaminationDAO();

        // Tạo dữ liệu mẫu
        Customer customer = new Customer();
        customer.setCustomerID(1); // Đảm bảo ID này tồn tại trong bảng Customer

        Professional consultant = new Professional();
        consultant.setStaffID(1);  // Đảm bảo ID này tồn tại trong bảng Professional

        List<Service> services = new ArrayList<>();
        Service service = new Service();
        service.setPackageID(1);   // Đảm bảo ID này tồn tại trong bảng Service
        services.add(service);

        MedicalExamination examination = new MedicalExamination(
                0,
                "2025-03-10 10:00:00",
                customer,
                "Pending",
                consultant,
                "Test appointment",
                "2025-03-04 15:30:00",
                services
        );

        // Gọi saveMedicalExamination và kiểm tra kết quả
        boolean success = dao.saveMedicalExamination(examination);
        if (success) {
            System.out.println("MedicalExamination saved successfully!");
            // Kiểm tra dữ liệu vừa lưu
            int latestId = dao.getLatestExaminationID();
            MedicalExamination saved = dao.getMedicalExaminationByID(latestId);
            if (saved != null) {
                System.out.println("Saved Examination ID: " + saved.getExaminationID());
                System.out.println("Examination Date: " + saved.getExaminationDate());
                System.out.println("Customer ID: " + saved.getCustomerId().getCustomerID());
                System.out.println("Status: " + saved.getStatus());
                System.out.println("Consultant ID: " + saved.getConsultantId().getStaffID());
                System.out.println("Note: " + saved.getNote());
                System.out.println("Created At: " + saved.getCreatedAt());
                List<Service> savedServices = saved.getList();
                if (savedServices != null && !savedServices.isEmpty()) {
                    System.out.println("Services:");
                    for (Service s : savedServices) {
                        System.out.println("- Package ID: " + s.getPackageID());
                    }
                } else {
                    System.out.println("No services found.");
                }
            } else {
                System.out.println("Could not retrieve the saved examination.");
            }
        } else {
            System.out.println("Failed to save MedicalExamination.");
        }
    }

    // Phương thức phụ để lấy ID mới nhất
    private int getLatestExaminationID() {
        String sql = "SELECT TOP 1 examinationID FROM MedicalExamination ORDER BY examinationID DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("examinationID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    
    
}