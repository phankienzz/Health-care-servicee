package dao;

import context.DBContext;
import model.WorkingSchedule;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkingScheduleDAO {

    private Connection connection;

    public WorkingScheduleDAO() {
        try {
            DBContext db = new DBContext();
            this.connection = db.connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lấy tất cả lịch làm việc
    public List<WorkingSchedule> getAllSchedules() {
        List<WorkingSchedule> scheduleList = new ArrayList<>();
        String sql = "SELECT * FROM WorkingSchedule";

        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                scheduleList.add(mapResultSetToSchedule(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scheduleList;
    }

    // Thêm lịch làm việc mới
    public void addSchedule(WorkingSchedule schedule) {
        String sql = "INSERT INTO WorkingSchedule (professionalID, dayOfWeek, startTime, endTime, shift) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, schedule.getProfessionalID());
            st.setInt(2, schedule.getDayOfWeek());
            st.setTime(3, schedule.getStartTime());
            st.setTime(4, schedule.getEndTime());
            st.setString(5, schedule.getShift());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateScheduleByKey(WorkingSchedule schedule) {
        String sql = "UPDATE WorkingSchedule "
                + "SET startTime = ?, endTime = ? "
                + "WHERE professionalID = ? AND dayOfWeek = ? AND shift = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setTime(1, schedule.getStartTime());
            st.setTime(2, schedule.getEndTime());
            st.setInt(3, schedule.getProfessionalID());
            st.setInt(4, schedule.getDayOfWeek());
            st.setString(5, schedule.getShift());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cập nhật lịch làm việc
    public void updateSchedule(WorkingSchedule schedule) {
        String sql = "UPDATE WorkingSchedule SET dayOfWeek = ?, startTime = ?, endTime = ?, shift = ? WHERE scheduleID = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, schedule.getDayOfWeek());
            st.setTime(2, schedule.getStartTime());
            st.setTime(3, schedule.getEndTime());
            st.setString(4, schedule.getShift());
            st.setInt(5, schedule.getScheduleID());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disableSchedule(int professionalID, int dayOfWeek, String shift) {
        String sql = "UPDATE WorkingSchedule SET status = 'Off' WHERE professionalID = ? AND dayOfWeek = ? AND shift = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, professionalID);
            st.setInt(2, dayOfWeek);
            st.setString(3, shift);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa lịch làm việc
    public void deleteSchedule(int scheduleID) {
        String sql = "DELETE FROM WorkingSchedule WHERE scheduleID = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, scheduleID);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy danh sách chuyên gia theo roleID
    public List<String> getProfessionalsByRole(List<Integer> roleIds) {
        List<String> result = new ArrayList<>();
        String sql = "SELECT p.professionalID, s.staffID, s.fullName AS staffName, s.roleID "
                + "FROM MedicalSystem.dbo.Professional p "
                + "JOIN MedicalSystem.dbo.Staff s ON p.staffID = s.staffID "
                + "WHERE s.roleID IN (" + roleIds.toString().replace("[", "").replace("]", "") + ")";

        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                result.add("ProfessionalID: " + rs.getInt("professionalID") + ", "
                        + "StaffID: " + rs.getInt("staffID") + ", "
                        + "Staff Name: " + rs.getString("staffName") + ", "
                        + "RoleID: " + rs.getInt("roleID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public static List<String> extractProfessionalIDs(List<String> professionalData) {
//        List<String> professionalIDs = new ArrayList<>();
//
//        for (String data : professionalData) {
//            try {
//                // Tìm vị trí của "ProfessionalID: "
//                int startIndex = data.indexOf("ProfessionalID: ") + "ProfessionalID: ".length();
//                int endIndex = data.indexOf(",", startIndex);
//
//                // Cắt chuỗi để lấy ID dưới dạng String
//                if (startIndex != -1 && endIndex != -1) {
//                    String idStr = data.substring(startIndex, endIndex).trim();
//                    professionalIDs.add(idStr);
//                }
//            } catch (IndexOutOfBoundsException e) {
//                System.err.println("Lỗi khi phân tích ID từ chuỗi: " + data);
//            }
//        }
//
//        return professionalIDs;
//    }
    public static List<String> extractProfessionalInfo(List<String> professionalData) {
        List<String> professionalInfoList = new ArrayList<>();

        for (String data : professionalData) {
            try {
                // Tách ProfessionalID
                int idStart = data.indexOf("ProfessionalID: ") + "ProfessionalID: ".length();
                int idEnd = data.indexOf(", StaffID:", idStart);
                String professionalID = data.substring(idStart, idEnd).trim();

                // Tách Staff Name
                int nameStart = data.indexOf("Staff Name: ") + "Staff Name: ".length();
                int nameEnd = data.indexOf(", RoleID:", nameStart);
                String staffName = data.substring(nameStart, nameEnd).trim();

                // Gộp lại dưới dạng "ID - Name"
                professionalInfoList.add(professionalID + " - " + staffName);
            } catch (IndexOutOfBoundsException e) {
                System.err.println("Lỗi khi phân tích dữ liệu: " + data);
            }
        }
        return professionalInfoList;
    }

    public static int extractID_FromString(String professionalInfo) {
        try {
            // Tách phần ID trước dấu "-"
            String idPart = professionalInfo.split("-")[0].trim();
            return Integer.parseInt(idPart);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Lỗi khi tách ID từ chuỗi: " + professionalInfo);
            return -1; // Trả về -1 nếu lỗi
        }
    }

    public boolean isScheduleExists(int professionalID, int dayOfWeek, String shift) {
        String sql = "SELECT COUNT(*) FROM WorkingSchedule WHERE professionalID = ? AND dayOfWeek = ? AND shift = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, professionalID);
            st.setInt(2, dayOfWeek);
            st.setString(3, shift);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu count > 0, lịch đã tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Chuyển đổi ResultSet thành WorkingSchedule ------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------------------------------------
    private WorkingSchedule mapResultSetToSchedule(ResultSet rs) throws SQLException {
        return new WorkingSchedule(
                rs.getInt("scheduleID"),
                rs.getInt("professionalID"),
                rs.getInt("dayOfWeek"),
                rs.getTime("startTime"),
                rs.getTime("endTime"),
                rs.getString("shift")
        );
    }

    public static void main(String[] args) {
        WorkingScheduleDAO dao = new WorkingScheduleDAO();
        List<Integer> roles = new ArrayList<>();
        roles.add(4);
        roles.add(5);
        List<String> list = dao.getProfessionalsByRole(roles);
        for (String ws : list) {
            System.out.println(ws);
        }
    }
}
