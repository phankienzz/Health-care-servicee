package dao;

import context.DBContext;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Professional;
import model.Staff;

public class ProfessionalDAO {

    private Connection conn;
    DBContext db = new DBContext();

    public ProfessionalDAO() {
        this.conn = db.connection; // Lấy connection từ DBContext
        if (this.conn == null) {
            throw new RuntimeException("Không thể kết nối CSDL");
        }
    }

    public static Date convertStringToSqlDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng phải khớp với dữ liệu
        try {
            java.util.Date utilDate = sdf.parse(dateString); // Chuyển String thành java.util.Date
            return new Date(utilDate.getTime()); // Chuyển java.util.Date thành java.sql.Date
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Hoặc xử lý ngoại lệ theo logic của bạn
        }
    }

    public List<String> getallSpecialization() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT DISTINCT specialization \n"
                + "FROM Professional\n"
                + "WHERE specialization IS NOT NULL\n"
                + "ORDER BY specialization;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(rs.getString(1));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    // CREATE Professional
    public boolean addProfessional(Professional professional) {
        String sql = "INSERT INTO Staff (fullName, email, password, phone, gender, dateOfBirth, address, hireDate, roleID, status, profilePicture)\n"
                + "VALUES (?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?);"
                + "INSERT INTO Professional (specialization, officeHours, qualification, biography, profilePicture, status, createdAt, staffID)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, GETDATE(), SCOPE_IDENTITY());";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, professional.getFullName());
            stmt.setString(2, professional.getEmail());
            stmt.setString(3, professional.getPassword());
            stmt.setDate(6, convertStringToSqlDate(professional.getDateOfBirth()));
            stmt.setString(5, professional.getGender());
            stmt.setString(7, professional.getAddress());
            stmt.setString(4, professional.getPhone());
            stmt.setDate(8, convertStringToSqlDate(professional.getHireDate()));
            stmt.setInt(9, professional.getRoleID());
            stmt.setString(10, professional.getStatus());
            stmt.setBytes(11, professional.getProfilePicture().getBytes());
            stmt.setString(12, professional.getSpecialization());
            stmt.setString(13, professional.getOfficeHours());
            stmt.setString(14, professional.getQualification());
            stmt.setString(15, professional.getBiography());
            stmt.setBytes(16, professional.getProfilePicture().getBytes());
            stmt.setString(17, professional.getStatus());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Professional getProfessionalbyID(int id){
        Professional pro = null;
        String sql = "SELECT \n"
                + "    s.staffID,\n"
                + "    s.fullName,\n"
                + "    s.email,\n"
                + "    s.password,\n"
                + "    s.phone,\n"
                + "    s.gender,\n"
                + "    s.dateOfBirth,\n"
                + "    s.address,\n"
                + "    s.hireDate,\n"
                + "    s.roleID,\n"
                + "    s.status AS staffStatus,\n"
                + "    s.profilePicture AS staffProfilePicture,\n"
                + "    p.professionalID,\n"
                + "    p.specialization,\n"
                + "    p.officeHours,\n"
                + "    p.qualification,\n"
                + "    p.biography,\n"
                + "    p.profilePicture AS professionalProfilePicture,\n"
                + "    p.status AS professionalStatus,\n"
                + "    p.createdAt\n"
                + "FROM Staff s\n"
                + "JOIN Professional p ON s.staffID = p.staffID where s.staffID = ?;";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                    pro = extractProfessional(rs);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return pro;
    }
    
    // READ - Get all Professionals
    public List<Professional> getAllProfessionals() {
        List<Professional> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    s.staffID,\n"
                + "    s.fullName,\n"
                + "    s.email,\n"
                + "    s.password,\n"
                + "    s.phone,\n"
                + "    s.gender,\n"
                + "    s.dateOfBirth,\n"
                + "    s.address,\n"
                + "    s.hireDate,\n"
                + "    s.roleID,\n"
                + "    s.status AS staffStatus,\n"
                + "    s.profilePicture AS staffProfilePicture,\n"
                + "    p.professionalID,\n"
                + "    p.specialization,\n"
                + "    p.officeHours,\n"
                + "    p.qualification,\n"
                + "    p.biography,\n"
                + "    p.profilePicture AS professionalProfilePicture,\n"
                + "    p.status AS professionalStatus,\n"
                + "    p.createdAt\n"
                + "FROM Staff s\n"
                + "JOIN Professional p ON s.staffID = p.staffID;";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(extractProfessional(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE Professional
    public boolean updateProfessional(Professional professional) {
        String sql = "UPDATE Professional\n"
                + "SET \n"
                + "    specialization = ?,\n"
                + "    officeHours = ?,\n"
                + "    qualification = ?,\n"
                + "    biography = ?,\n"
                + "    status = ?,\n"
                + "    profilePicture = ?\n"
                + "WHERE staffID = ?;\n"
                + "UPDATE Staff\n"
                + "SET \n"
                + "    fullName = ?,\n"
                + "    phone = ?,\n"
                + "    address = ?,\n"
                + "    status = ?,\n"
                + "    profilePicture = ?\n"
                + "WHERE staffID = ?;";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(8, professional.getFullName());
            stmt.setString(5, professional.getStatus());
            stmt.setString(10, professional.getAddress());
            stmt.setString(9, professional.getPhone());
            stmt.setString(11, professional.getStatus());
            stmt.setInt(13, professional.getStaffID());
            stmt.setString(1, professional.getSpecialization());
            stmt.setString(2, professional.getOfficeHours());
            stmt.setString(3, professional.getQualification());
            stmt.setString(4, professional.getBiography());
            stmt.setInt(7, professional.getStaffID());
            stmt.setBytes(6, professional.getProfilePicture().getBytes());
            stmt.setBytes(12, professional.getProfilePicture().getBytes());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE Professional
    public boolean deleteProfessional(int staffID) {
        String sql = "DELETE FROM Professional WHERE staffID = ?;DELETE FROM Staff WHERE staffID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, staffID);
            stmt.setInt(2, staffID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper method to extract Professional from ResultSet
    private Professional extractProfessional(ResultSet rs) throws SQLException {
        byte[] staffProfilePic = rs.getBytes("staffProfilePicture");
        byte[] professionalProfilePic = rs.getBytes("professionalProfilePicture");
        return new Professional(
                rs.getInt("staffID"),
                rs.getString("fullName"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getDate("dateOfBirth"),
                rs.getString("gender"),
                rs.getString("address"),
                rs.getString("phone"),
                rs.getDate("hireDate"),
                rs.getString("staffStatus"),
                professionalProfilePic != null ? professionalProfilePic : new byte[0], // Tránh lỗi NULL
                rs.getString("specialization"),
                rs.getString("officeHours"),
                rs.getString("qualification"),
                rs.getString("biography"),
                rs.getDate("createdAt")
        );
    }

    
    public List<Professional> getAllDoctors() {
    List<Professional> list = new ArrayList<>();
    String sql = "SELECT \n"
            + "    s.staffID,\n"
            + "    s.fullName,\n"
            + "    s.email,\n"
            + "    s.password,\n"
            + "    s.phone,\n"
            + "    s.gender,\n"
            + "    s.dateOfBirth,\n"
            + "    s.address,\n"
            + "    s.hireDate,\n"
            + "    s.roleID,\n"
            + "    s.status AS staffStatus,\n"
            + "    s.profilePicture AS staffProfilePicture,\n"
            + "    p.professionalID,\n"
            + "    p.specialization,\n"
            + "    p.officeHours,\n"
            + "    p.qualification,\n"
            + "    p.biography,\n"
            + "    p.profilePicture AS professionalProfilePicture,\n"
            + "    p.status AS professionalStatus,\n"
            + "    p.createdAt\n"
            + "FROM Staff s\n"
            + "JOIN Professional p ON s.staffID = p.staffID\n"
            + "WHERE p.status = 'Active';"; // Chỉ lấy bác sĩ có trạng thái Active

    try (PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            Professional doctor = extractProfessional(rs);
            list.add(doctor);
            System.out.println("Doctor Found: " + doctor.getFullName());
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

    
    
    
    
    
   public static void main(String[] args) {
    // Khởi tạo DAO
    ProfessionalDAO dao = new ProfessionalDAO();
    
    // Kiểm tra kết nối CSDL trước khi truy vấn
    if (dao.conn == null) {
        System.out.println("❌ Lỗi kết nối đến CSDL! Vui lòng kiểm tra lại DBContext.");
        return;
    } else {
        System.out.println("✅ Kết nối CSDL thành công!");
    }

    // Lấy danh sách bác sĩ từ database
    List<Professional> doctors = dao.getAllDoctors();

    // Kiểm tra danh sách bác sĩ có dữ liệu hay không
    if (doctors == null || doctors.isEmpty()) {
        System.out.println("⚠️ Không tìm thấy bác sĩ nào! Hãy kiểm tra dữ liệu trong bảng Professional.");
    } else {
        System.out.println("✅ Danh sách bác sĩ:");
        for (Professional doc : doctors) {
            System.out.println("🆔 ID: " + doc.getStaffID()+ 
                               " | 👨‍⚕️ Tên: " + doc.getFullName() + 
                               " | 📞 SĐT: " + doc.getPhone() + 
                               " | 🎓 Chuyên môn: " + doc.getSpecialization());
        }
    }
}

}
