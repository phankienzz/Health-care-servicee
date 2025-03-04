package dao;

import model.WorkingSchedule;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkingScheduleDAO {

    private final String JDBC_URL = "jdbc:mysql://localhost:3306/hospital_db";
    private final String JDBC_USER = "root";
    private final String JDBC_PASSWORD = "yourpassword";

    public List<WorkingSchedule> getAllWorkingSchedules() {
        List<WorkingSchedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM WorkingSchedule";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                schedules.add(mapResultSetToSchedule(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

    public WorkingSchedule getScheduleByID(int scheduleID) {
        String sql = "SELECT * FROM WorkingSchedule WHERE scheduleID = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, scheduleID);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToSchedule(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<WorkingSchedule> getSchedulesByProfessionalID(int professionalID) {
        return getSchedulesByField("professionalID", professionalID);
    }

    public List<WorkingSchedule> getSchedulesByDayOfWeek(int dayOfWeek) {
        return getSchedulesByField("dayOfWeek", dayOfWeek);
    }

    public List<WorkingSchedule> getSchedulesByShift(String shift) {
        List<WorkingSchedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM WorkingSchedule WHERE shift = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, shift);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    schedules.add(mapResultSetToSchedule(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

    public boolean addWorkingSchedule(WorkingSchedule schedule) {
        String sql = "INSERT INTO WorkingSchedule (professionalID, dayOfWeek, startTime, endTime, shift) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, schedule.getProfessionalID());
            stmt.setInt(2, schedule.getDayOfWeek());
            stmt.setTime(3, schedule.getStartTime());
            stmt.setTime(4, schedule.getEndTime());
            stmt.setString(5, schedule.getShift());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateWorkingSchedule(WorkingSchedule schedule) {
        String sql = "UPDATE WorkingSchedule SET professionalID=?, dayOfWeek=?, startTime=?, endTime=?, shift=? WHERE scheduleID=?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, schedule.getProfessionalID());
            stmt.setInt(2, schedule.getDayOfWeek());
            stmt.setTime(3, schedule.getStartTime());
            stmt.setTime(4, schedule.getEndTime());
            stmt.setString(5, schedule.getShift());
            stmt.setInt(6, schedule.getScheduleID());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteWorkingSchedule(int scheduleID) {
        String sql = "DELETE FROM WorkingSchedule WHERE scheduleID=?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, scheduleID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private List<WorkingSchedule> getSchedulesByField(String field, int value) {
        List<WorkingSchedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM WorkingSchedule WHERE " + field + " = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, value);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    schedules.add(mapResultSetToSchedule(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

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
        Time startTime = Time.valueOf("08:00:00");
        Time endTime = Time.valueOf("12:00:00");
        WorkingSchedule schedule = new WorkingSchedule(5, 6, startTime, endTime, "MORNING");
        dao.addWorkingSchedule(schedule);
        
        
    }
}
