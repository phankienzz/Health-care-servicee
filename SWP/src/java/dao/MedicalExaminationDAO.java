/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.MedicalExamination;
import model.Professional;
import model.Service;

public class MedicalExaminationDAO extends DBContext {

    public MedicalExamination getMedicalExaminationByID(int examinationID) {
        ServiceDAO dao = new ServiceDAO();
        CustomerDAO cusDAO = new CustomerDAO();
        ProfessionalDAO proDAO = new ProfessionalDAO();
        String sql = "SELECT examinationID, "
                + "FORMAT(examinationDate, 'dd/MM/yyyy HH:mm') AS examinationDate, " // Định dạng dd/MM/yyyy HH:mm
                + "customerID, status, consultantID, notes, "
                + "FORMAT(createdAt, 'dd/MM/yyyy HH:mm') AS createdAt " // Định dạng dd/MM/yyyy HH:mm
                + "FROM MedicalExamination WHERE examinationID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, examinationID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new MedicalExamination(
                        rs.getInt("examinationID"),
                        rs.getString("examinationDate"), // Đã được định dạng thành dd/MM/yyyy HH:mm
                        cusDAO.getCustomerByID(rs.getInt("customerID")),
                        rs.getString("status"),
                        proDAO.getProfessionalbyID(rs.getInt("consultantID")),
                        rs.getString("notes"),
                        rs.getString("createdAt"), // Đã được định dạng thành dd/MM/yyyy HH:mm
                        dao.getServiceExaminationID(examinationID));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi chi tiết để debug
        }
        return null;
    }

    public List<MedicalExamination> getAllMedicalExamination() {
        List<MedicalExamination> medicalExaminationList = new ArrayList<>();
        ServiceDAO dao = new ServiceDAO();
        CustomerDAO cusDAO = new CustomerDAO();
        ProfessionalDAO proDAO = new ProfessionalDAO();
        String sql = "SELECT * FROM MedicalExamination";
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
            e.printStackTrace();
        }
        return medicalExaminationList;
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

    // Lưu MedicalExamination mới - Loại bỏ createdAt khỏi câu INSERT
    public boolean saveMedicalExamination(MedicalExamination examination) {
        String sql = "INSERT INTO MedicalExamination (examinationDate, customerID, status, consultantID, notes) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, examination.getExaminationDate());
            ps.setInt(2, examination.getCustomerId().getCustomerID());
            ps.setString(3, examination.getStatus());
            ps.setInt(4, examination.getConsultantId().getStaffID());
            ps.setString(5, examination.getNote());

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

    public List<MedicalExamination> getAllExamination() {
        List<MedicalExamination> medicalExaminationList = new ArrayList<>();
        String sql = "SELECT m.examinationID, "
                + "FORMAT(m.examinationDate, 'dd/MM/yyyy HH:mm') AS examinationDate, " // Định dạng dd/MM/yyyy HH:mm
                + "m.customerID, m.status, m.consultantID, m.notes, "
                + "FORMAT(m.createdAt, 'dd/MM/yyyy HH:mm') AS createdAt, " // Định dạng dd/MM/yyyy HH:mm
                + "c.fullName AS customerName, "
                + "DATEDIFF(YEAR, c.dateOfBirth, GETDATE()) AS age, "
                + "s.fullName AS staffName "
                + "FROM MedicalExamination m "
                + "JOIN Customer c ON m.customerID = c.customerID "
                + "JOIN Staff s ON m.consultantID = s.staffID";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("customerID"));
                customer.setFullName(rs.getString("customerName"));
                customer.setDateOfBirth(String.valueOf(rs.getInt("age")));

                Professional professional = new Professional();
                professional.setStaffID(rs.getInt("consultantID"));
                professional.setFullName(rs.getString("staffName"));

                MedicalExamination exam = new MedicalExamination();
                exam.setExaminationID(rs.getInt("examinationID"));
                exam.setExaminationDate(rs.getString("examinationDate")); // Đã là dd/MM/yyyy HH:mm
                exam.setCustomerId(customer);
                exam.setStatus(rs.getString("status"));
                exam.setConsultantId(professional);
                exam.setNote(rs.getString("notes"));
                exam.setCreatedAt(rs.getString("createdAt")); // Đã là dd/MM/yyyy HH:mm
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
        String query = "SELECT ms.packageID, sp.packageName "
                + "FROM MedicalService ms "
                + "JOIN ServicePackage sp ON ms.packageID = sp.packageID "
                + "WHERE ms.examinationID = ?";
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

    public boolean updateMedicalExamination(MedicalExamination examination) {
        String sql = "UPDATE MedicalExamination SET examinationDate = ?, status = ?, consultantID = ?, notes = ? WHERE examinationID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            // Chuyển đổi định dạng ngày giờ từ dd/MM/yyyy HH:mm sang yyyy-MM-dd HH:mm:ss
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = outputFormat.format(inputFormat.parse(examination.getExaminationDate()));
            ps.setString(1, formattedDate); // Truyền chuỗi đã chuyển đổi

            ps.setString(2, examination.getStatus());
            ps.setInt(3, examination.getConsultantId().getStaffID());
            ps.setString(4, examination.getNote());
            ps.setInt(5, examination.getExaminationID());

            System.out.println("SQL Query: " + ps.toString()); // Debug câu lệnh SQL

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update successful for examinationID: " + examination.getExaminationID());
                deleteExaminationServices(examination.getExaminationID());
                saveExaminationServices(examination.getExaminationID(), examination.getList());
                return true;
            } else {
                System.out.println("Update failed: No rows affected for examinationID: " + examination.getExaminationID());
            }
            return false;
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + examination.getExaminationDate());
            return false;
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }

    private void deleteExaminationServices(int examinationId) {
        String sql = "DELETE FROM MedicalService WHERE examinationID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, examinationId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Service> getAllServices() {
        List<Service> serviceList = new ArrayList<>();
        String sql = "SELECT packageID, packageName FROM ServicePackage";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Service service = new Service();
                service.setPackageID(rs.getInt("packageID"));
                service.setPackageName(rs.getString("packageName"));
                serviceList.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviceList;
    }

    public List<Professional> getAllProfessionals() {
        List<Professional> professionalList = new ArrayList<>();
        String sql = "SELECT staffID, fullName FROM Staff";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Professional professional = new Professional();
                professional.setStaffID(rs.getInt("staffID"));
                professional.setFullName(rs.getString("fullName"));
                professionalList.add(professional);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professionalList;
    }

    // Trong class MedicalExaminationDAO.java
    public boolean deleteMedicalExamination(int examinationId) {
        String sql = "DELETE FROM MedicalExamination WHERE examinationID = ?";
        try {
            // Xóa các dịch vụ liên quan trước
            deleteExaminationServices(examinationId);

            // Xóa bản ghi MedicalExamination
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, examinationId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<MedicalExamination> getFilteredExaminations(String patientName, String ageSort, String doctorName,
            String appointmentDate, String timeCreatedSort, String status) {
        List<MedicalExamination> medicalExaminationList = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT m.examinationID, "
                + "FORMAT(m.examinationDate, 'dd/MM/yyyy HH:mm') AS examinationDate, "
                + "m.customerID, m.status, m.consultantID, m.notes, "
                + "FORMAT(m.createdAt, 'dd/MM/yyyy HH:mm') AS createdAt, "
                + "c.fullName AS customerName, "
                + "DATEDIFF(YEAR, c.dateOfBirth, GETDATE()) AS age, "
                + "s.fullName AS staffName "
                + "FROM MedicalExamination m "
                + "JOIN Customer c ON m.customerID = c.customerID "
                + "JOIN Staff s ON m.consultantID = s.staffID "
                + "WHERE 1=1"
        );

        List<Object> params = new ArrayList<>();

        // Search by Patient Name
        if (patientName != null && !patientName.trim().isEmpty()) {
            sql.append(" AND c.fullName LIKE ?");
            params.add("%" + patientName + "%");
        }

        // Filter by Doctor Name
        if (doctorName != null && !doctorName.trim().isEmpty()) {
            sql.append(" AND s.fullName = ?");
            params.add(doctorName);
        }

        // Filter by Appointment Date
        if (appointmentDate != null && !appointmentDate.trim().isEmpty()) {
            sql.append(" AND CAST(m.examinationDate AS DATE) = ?");
            params.add(appointmentDate); // Định dạng yyyy-MM-dd từ input type="date"
        }

        // Filter by Status
        if (status != null && !status.trim().isEmpty()) {
            sql.append(" AND m.status = ?");
            params.add(status);
        }

        // Sort by Age (nếu có)
        if ("asc".equals(ageSort)) {
            sql.append(" ORDER BY age ASC");
        } else if ("desc".equals(ageSort)) {
            sql.append(" ORDER BY age DESC");
        } // Sort by Time Created (nếu có, ưu tiên cao hơn ageSort)
        else if ("latest".equals(timeCreatedSort)) {
            sql.append(" ORDER BY m.createdAt DESC");
        } else if ("oldest".equals(timeCreatedSort)) {
            sql.append(" ORDER BY m.createdAt ASC");
        }

        try {
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("customerID"));
                customer.setFullName(rs.getString("customerName"));
                customer.setDateOfBirth(String.valueOf(rs.getInt("age"))); // Giả sử tuổi được tính từ dateOfBirth

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
                exam.setList(getServicesByExaminationId(rs.getInt("examinationID")));

                medicalExaminationList.add(exam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicalExaminationList;
    }

    public static void main(String[] args) {
        // Khởi tạo đối tượng DAO
        MedicalExaminationDAO dao = new MedicalExaminationDAO();

        // ID để kiểm tra - thay đổi theo dữ liệu thực tế trong database của bạn
        int testExaminationId = 15;

        // Gọi phương thức deleteMedicalExamination và kiểm tra kết quả
        boolean result = dao.deleteMedicalExamination(testExaminationId);

        // In kết quả
        if (result) {
            System.out.println("Xóa bản ghi MedicalExamination với ID "
                    + testExaminationId + " thành công!");
        } else {
            System.out.println("Không thể xóa bản ghi MedicalExamination với ID "
                    + testExaminationId + " hoặc bản ghi không tồn tại.");
        }

        // Kiểm tra thêm với một ID không tồn tại
        int nonExistentId = 9999;
        result = dao.deleteMedicalExamination(nonExistentId);

        if (result) {
            System.out.println("Xóa bản ghi MedicalExamination với ID "
                    + nonExistentId + " thành công!");
        } else {
            System.out.println("Không thể xóa bản ghi MedicalExamination với ID "
                    + nonExistentId + " hoặc bản ghi không tồn tại.");
        }

        // (Tùy chọn) In danh sách tất cả examinations sau khi xóa để kiểm tra
        List<MedicalExamination> examinations = dao.getAllMedicalExamination();
        System.out.println("\nDanh sách các MedicalExamination còn lại:");
        for (MedicalExamination exam : examinations) {
            System.out.println("ID: " + exam.getExaminationID()
                    + ", Date: " + exam.getExaminationDate()
                    + ", Status: " + exam.getStatus());
        }
    }
}
