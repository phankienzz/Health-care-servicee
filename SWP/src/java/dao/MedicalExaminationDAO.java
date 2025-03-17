/*
 * Click nbfs://SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import model.MedicalRecord;
import model.Professional;
import model.Service;

public class MedicalExaminationDAO extends DBContext {

    public MedicalExamination getMedicalExaminationByID(int examinationID) {
        ServiceDAO dao = new ServiceDAO();
        CustomerDAO cusDAO = new CustomerDAO();
        ProfessionalDAO proDAO = new ProfessionalDAO();
        String sql = "SELECT examinationID, "
                + "FORMAT(examinationDate, 'dd/MM/yyyy HH:mm') AS examinationDate, "
                + "customerID, status, consultantID, notes, "
                + "FORMAT(createdAt, 'dd/MM/yyyy HH:mm') AS createdAt "
                + "FROM MedicalExamination WHERE examinationID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, examinationID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
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
            e.printStackTrace();
        }
        return null;
    }

    public int getTotalFilteredRecords(String patientName, String ageSort, String doctorName,
            String appointmentDate, String timeCreatedSort, String status) {
        StringBuilder sql = new StringBuilder(
                "SELECT COUNT(*) AS total "
                + "FROM MedicalExamination m "
                + "JOIN Customer c ON m.customerID = c.customerID "
                + "JOIN Staff s ON m.consultantID = s.staffID "
                + "WHERE 1=1"
        );

        List<Object> params = new ArrayList<>();

        if (patientName != null && !patientName.trim().isEmpty()) {
            sql.append(" AND c.fullName LIKE ?");
            params.add("%" + patientName + "%");
        }
        if (doctorName != null && !doctorName.trim().isEmpty()) {
            sql.append(" AND s.fullName = ?");
            params.add(doctorName);
        }
        if (appointmentDate != null && !appointmentDate.trim().isEmpty()) {
            sql.append(" AND CAST(m.examinationDate AS DATE) = ?");
            params.add(appointmentDate);
        }
        if (status != null && !status.trim().isEmpty()) {
            sql.append(" AND m.status = ?");
            params.add(status);
        }

        try {
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<MedicalExamination> getFilteredExaminations(String patientName, String ageSort, String doctorName,
            String appointmentDate, String timeCreatedSort, String status, int page, int pageSize) {
        List<MedicalExamination> medicalExaminationList = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "WITH FilteredExaminations AS ( "
                + "    SELECT m.examinationID, "
                + "           FORMAT(m.examinationDate, 'dd/MM/yyyy HH:mm') AS examinationDate, "
                + "           m.customerID, m.status, m.consultantID, m.notes, "
                + "           FORMAT(m.createdAt, 'dd/MM/yyyy HH:mm') AS createdAt, "
                + "           c.fullName AS customerName, "
                + "           DATEDIFF(YEAR, c.dateOfBirth, GETDATE()) AS age, "
                + "           s.fullName AS staffName, "
                + "           ROW_NUMBER() OVER ("
        );

        if ("asc".equals(ageSort)) {
            sql.append("ORDER BY age ASC");
        } else if ("desc".equals(ageSort)) {
            sql.append("ORDER BY age DESC");
        } else if ("latest".equals(timeCreatedSort)) {
            sql.append("ORDER BY m.createdAt DESC");
        } else if ("oldest".equals(timeCreatedSort)) {
            sql.append("ORDER BY m.createdAt ASC");
        } else {
            sql.append("ORDER BY m.examinationID");
        }

        sql.append(") AS RowNum "
                + "    FROM MedicalExamination m "
                + "    JOIN Customer c ON m.customerID = c.customerID "
                + "    JOIN Staff s ON m.consultantID = s.staffID "
                + "    WHERE 1=1"
        );

        List<Object> params = new ArrayList<>();

        if (patientName != null && !patientName.trim().isEmpty()) {
            sql.append(" AND c.fullName LIKE ?");
            params.add("%" + patientName + "%");
        }
        if (doctorName != null && !doctorName.trim().isEmpty()) {
            sql.append(" AND s.fullName = ?");
            params.add(doctorName);
        }
        if (appointmentDate != null && !appointmentDate.trim().isEmpty()) {
            sql.append(" AND CONVERT(date, m.examinationDate, 103) = CONVERT(date, ?, 103)");
            params.add(appointmentDate);
        }
        if (status != null && !status.trim().isEmpty()) {
            sql.append(" AND m.status = ?");
            params.add(status);
        }

        sql.append(") SELECT * FROM FilteredExaminations "
                + "WHERE RowNum BETWEEN ? AND ?");

        int startRow = (page - 1) * pageSize + 1;
        int endRow = page * pageSize;
        params.add(startRow);
        params.add(endRow);

        try {
            if (connection == null) {
                System.out.println("Database connection is null!");
                return medicalExaminationList;
            }

            System.out.println("SQL Query: " + sql.toString());
            System.out.println("Params: " + params);

            PreparedStatement ps = connection.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            ResultSet rs = ps.executeQuery();
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
                exam.setExaminationDate(rs.getString("examinationDate"));
                exam.setCustomerId(customer);
                exam.setStatus(rs.getString("status"));
                exam.setConsultantId(professional);
                exam.setNote(rs.getString("notes"));
                exam.setCreatedAt(rs.getString("createdAt"));
                exam.setList(getServicesByExaminationId(rs.getInt("examinationID")));

                medicalExaminationList.add(exam);
            }
            System.out.println("Records retrieved: " + medicalExaminationList.size());
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
        return medicalExaminationList;
    }

    public int getTotalFilteredRecords2(String patientName, String ageSort, int doctorName,
            String appointmentDate, String timeCreatedSort, String status) {
        StringBuilder sql = new StringBuilder(
                "SELECT COUNT(*) AS total "
                + "FROM MedicalExamination m "
                + "JOIN Customer c ON m.customerID = c.customerID "
                + "JOIN Staff s ON m.consultantID = s.staffID "
                + "WHERE 1=1"
        );

        List<Object> params = new ArrayList<>();

        if (patientName != null && !patientName.trim().isEmpty()) {
            sql.append(" AND c.fullName LIKE ?");
            params.add("%" + patientName + "%");
        }

        sql.append(" AND s.staffID = ?");
        params.add(doctorName);

        if (appointmentDate != null && !appointmentDate.trim().isEmpty()) {
            sql.append(" AND CAST(m.examinationDate AS DATE) = ?");
            params.add(appointmentDate);
        }
        if (status != null && !status.trim().isEmpty()) {
            sql.append(" AND m.status = ?");
            params.add(status);
        }

        try {
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<MedicalExamination> getFilteredExaminations2(String patientName, String ageSort, int doctorName,
            String appointmentDate, String timeCreatedSort, String status, int page, int pageSize) {
        List<MedicalExamination> medicalExaminationList = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "WITH FilteredExaminations AS ( "
                + "    SELECT m.examinationID, "
                + "           FORMAT(m.examinationDate, 'dd/MM/yyyy HH:mm') AS examinationDate, "
                + "           m.customerID, m.status, m.consultantID, m.notes, "
                + "           FORMAT(m.createdAt, 'dd/MM/yyyy HH:mm') AS createdAt, "
                + "           c.fullName AS customerName, "
                + "           DATEDIFF(YEAR, c.dateOfBirth, GETDATE()) AS age, "
                + "           s.fullName AS staffName, "
                + "           ROW_NUMBER() OVER ("
        );

        if ("asc".equals(ageSort)) {
            sql.append("ORDER BY age ASC");
        } else if ("desc".equals(ageSort)) {
            sql.append("ORDER BY age DESC");
        } else if ("latest".equals(timeCreatedSort)) {
            sql.append("ORDER BY m.createdAt DESC");
        } else if ("oldest".equals(timeCreatedSort)) {
            sql.append("ORDER BY m.createdAt ASC");
        } else {
            sql.append("ORDER BY m.examinationID");
        }

        sql.append(") AS RowNum "
                + "    FROM MedicalExamination m "
                + "    JOIN Customer c ON m.customerID = c.customerID "
                + "    JOIN Staff s ON m.consultantID = s.staffID "
                + "    WHERE 1=1"
        );

        List<Object> params = new ArrayList<>();

        if (patientName != null && !patientName.trim().isEmpty()) {
            sql.append(" AND c.fullName LIKE ?");
            params.add("%" + patientName + "%");
        }

        sql.append(" AND s.staffID = ?");
        params.add(doctorName);

        if (appointmentDate != null && !appointmentDate.trim().isEmpty()) {
            sql.append(" AND CONVERT(date, m.examinationDate, 103) = CONVERT(date, ?, 103)");
            params.add(appointmentDate);
        }
        if (status != null && !status.trim().isEmpty()) {
            sql.append(" AND m.status = ?");
            params.add(status);
        }

        sql.append(") SELECT * FROM FilteredExaminations "
                + "WHERE RowNum BETWEEN ? AND ?");

        int startRow = (page - 1) * pageSize + 1;
        int endRow = page * pageSize;
        params.add(startRow);
        params.add(endRow);

        try {
            if (connection == null) {
                System.out.println("Database connection is null!");
                return medicalExaminationList;
            }

            System.out.println("SQL Query: " + sql.toString());
            System.out.println("Params: " + params);
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            ResultSet rs = ps.executeQuery();
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
                exam.setExaminationDate(rs.getString("examinationDate"));
                exam.setCustomerId(customer);
                exam.setStatus(rs.getString("status"));
                exam.setConsultantId(professional);
                exam.setNote(rs.getString("notes"));
                exam.setCreatedAt(rs.getString("createdAt"));
                exam.setList(getServicesByExaminationId(rs.getInt("examinationID")));

                medicalExaminationList.add(exam);
            }
            System.out.println("Records retrieved: " + medicalExaminationList.size());
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
        return medicalExaminationList;
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
                + "FORMAT(m.examinationDate, 'dd/MM/yyyy HH:mm') AS examinationDate, "
                + "m.customerID, m.status, m.consultantID, m.notes, "
                + "FORMAT(m.createdAt, 'dd/MM/yyyy HH:mm') AS createdAt, "
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

            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = outputFormat.format(inputFormat.parse(examination.getExaminationDate()));
            ps.setString(1, formattedDate);
            ps.setString(2, examination.getStatus());
            ps.setInt(3, examination.getConsultantId().getStaffID());
            ps.setString(4, examination.getNote());
            ps.setInt(5, examination.getExaminationID());

            System.out.println("SQL Query: " + ps.toString());

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

    public boolean deleteMedicalExamination(int examinationId) {
        String sql = "DELETE FROM MedicalExamination WHERE examinationID = ?";
        try {
            deleteExaminationServices(examinationId);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, examinationId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<MedicalExamination> getFilteredExaminations(String patientName, String doctorName,
            String appointmentDate, String timeCreatedSort, String status, int page, int pageSize) {
        List<MedicalExamination> medicalExaminationList = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "WITH FilteredExaminations AS ( "
                + "    SELECT m.examinationID, "
                + "           FORMAT(m.examinationDate, 'dd/MM/yyyy HH:mm') AS examinationDate, "
                + "           m.customerID, m.status, m.consultantID, m.notes, "
                + "           FORMAT(m.createdAt, 'dd/MM/yyyy HH:mm') AS createdAt, "
                + "           c.fullName AS customerName, "
                + "           DATEDIFF(YEAR, c.dateOfBirth, GETDATE()) AS age, "
                + "           s.fullName AS staffName, "
                + "           ROW_NUMBER() OVER ("
        );

        // Bỏ điều kiện ageSort, chỉ giữ timeCreatedSort
        if ("latest".equals(timeCreatedSort)) {
            sql.append("ORDER BY m.createdAt DESC");
        } else if ("oldest".equals(timeCreatedSort)) {
            sql.append("ORDER BY m.createdAt ASC");
        } else {
            sql.append("ORDER BY m.examinationID");
        }

        sql.append(") AS RowNum "
                + "    FROM MedicalExamination m "
                + "    JOIN Customer c ON m.customerID = c.customerID "
                + "    JOIN Staff s ON m.consultantID = s.staffID "
                + "    WHERE 1=1"
        );

        List<Object> params = new ArrayList<>();

        if (patientName != null && !patientName.trim().isEmpty()) {
            sql.append(" AND c.fullName LIKE ?");
            params.add("%" + patientName + "%");
        }
        if (doctorName != null && !doctorName.trim().isEmpty()) {
            sql.append(" AND s.fullName = ?");
            params.add(doctorName);
        }
        if (appointmentDate != null && !appointmentDate.trim().isEmpty()) {
            sql.append(" AND CONVERT(date, m.examinationDate, 103) = CONVERT(date, ?, 103)");
            params.add(appointmentDate);
        }
        if (status != null && !status.trim().isEmpty()) {
            sql.append(" AND m.status = ?");
            params.add(status);
        }

        sql.append(") SELECT * FROM FilteredExaminations "
                + "WHERE RowNum BETWEEN ? AND ?");

        int startRow = (page - 1) * pageSize + 1;
        int endRow = page * pageSize;
        params.add(startRow);
        params.add(endRow);

        try {
            if (connection == null) {
                System.out.println("Database connection is null!");
                return medicalExaminationList;
            }

            System.out.println("SQL Query: " + sql.toString());
            System.out.println("Params: " + params);

            PreparedStatement ps = connection.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            ResultSet rs = ps.executeQuery();
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
                exam.setExaminationDate(rs.getString("examinationDate"));
                exam.setCustomerId(customer);
                exam.setStatus(rs.getString("status"));
                exam.setConsultantId(professional);
                exam.setNote(rs.getString("notes"));
                exam.setCreatedAt(rs.getString("createdAt"));
                exam.setList(getServicesByExaminationId(rs.getInt("examinationID")));

                medicalExaminationList.add(exam);
            }
            System.out.println("Records retrieved: " + medicalExaminationList.size());
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
        return medicalExaminationList;
    }

    public int getTotalFilteredRecords(String patientName, String doctorName,
            String appointmentDate, String timeCreatedSort, String status) {
        StringBuilder sql = new StringBuilder(
                "SELECT COUNT(*) AS total "
                + "FROM MedicalExamination m "
                + "JOIN Customer c ON m.customerID = c.customerID "
                + "JOIN Staff s ON m.consultantID = s.staffID "
                + "WHERE 1=1"
        );

        List<Object> params = new ArrayList<>();

        if (patientName != null && !patientName.trim().isEmpty()) {
            sql.append(" AND c.fullName LIKE ?");
            params.add("%" + patientName + "%");
        }
        if (doctorName != null && !doctorName.trim().isEmpty()) {
            sql.append(" AND s.fullName = ?");
            params.add(doctorName);
        }
        if (appointmentDate != null && !appointmentDate.trim().isEmpty()) {
            sql.append(" AND CAST(m.examinationDate AS DATE) = ?");
            params.add(appointmentDate);
        }
        if (status != null && !status.trim().isEmpty()) {
            sql.append(" AND m.status = ?");
            params.add(status);
        }

        try {
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean addMedicalExamination(MedicalExamination exam) {
        String sql = "INSERT INTO MedicalExamination (examinationID, examinationDate, customerID, status, consultantID, notes, createdAt) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            int newId = getNextExaminationId();
            exam.setExaminationID(newId);

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, exam.getExaminationID());

            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedExaminationDate = outputFormat.format(inputFormat.parse(exam.getExaminationDate()));
            String formattedCreatedAt = outputFormat.format(inputFormat.parse(exam.getCreatedAt()));
            ps.setTimestamp(2, Timestamp.valueOf(formattedExaminationDate));
            ps.setInt(3, exam.getCustomerId().getCustomerID());
            ps.setString(4, exam.getStatus());
            ps.setInt(5, exam.getConsultantId().getStaffID());
            ps.setString(6, exam.getNote());
            ps.setTimestamp(7, Timestamp.valueOf(formattedCreatedAt));

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                // Lưu danh sách dịch vụ nếu có
                if (exam.getList() != null && !exam.getList().isEmpty()) {
                    saveExaminationServices(newId, exam.getList());
                }
                return true;
            }
            return false;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getNextExaminationId() {
        String sql = "SELECT MAX(examinationID) FROM MedicalExamination";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int getCustomerIdByName(String fullName) {
        String sql = "SELECT customerID FROM Customer WHERE fullName = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, fullName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("customerID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getStaffIdByName(String fullName) {
        String sql = "SELECT staffID FROM Staff WHERE fullName = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, fullName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("staffID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT customerID, fullName FROM Customer";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("customerID"));
                customer.setFullName(rs.getString("fullName"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public MedicalRecord getMedicalRecordByExamId(int examId) {
        String sql = "SELECT examinationID, diagnosis, treatmentPlan, medicationsPrescribed, "
                + "FORMAT(createdAt, 'dd/MM/yyyy HH:mm') AS createdAt, notes "
                + "FROM MedicalRecord WHERE examinationID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, examId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                MedicalRecord record = new MedicalRecord();
                record.setExaminationID(rs.getInt("examinationID"));
                record.setDiagnosis(rs.getString("diagnosis"));
                record.setTreatmentPlan(rs.getString("treatmentPlan"));
                record.setMedicationsPrescribed(rs.getString("medicationsPrescribed"));
                record.setCreatedAt(rs.getString("createdAt"));
                record.setNotes(rs.getString("notes"));
                return record;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean cancelAppointment(int examId) {
        String sql = "UPDATE MedicalExamination SET status = 'Rejected' WHERE examinationID = ? AND status = 'Pending'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, examId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<MedicalExamination> getAppointmentsByCustomerId(int customerId) {
        List<MedicalExamination> appointments = new ArrayList<>();
        String sql = "SELECT m.examinationID, FORMAT(m.examinationDate, 'dd/MM/yyyy HH:mm') AS examinationDate, "
                + "m.customerID, m.status, m.consultantID, m.notes, FORMAT(m.createdAt, 'dd/MM/yyyy HH:mm') AS createdAt, "
                + "c.fullName AS customerName, s.fullName AS staffName "
                + "FROM MedicalExamination m "
                + "JOIN Customer c ON m.customerID = c.customerID "
                + "JOIN Staff s ON m.consultantID = s.staffID "
                + "WHERE m.customerID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
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
                exam.setList(getServicesByExaminationId(rs.getInt("examinationID")));
                appointments.add(exam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

}
