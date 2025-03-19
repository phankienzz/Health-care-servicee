/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hoang
 */
public class VisitCounterDAO extends DBContext {

//    public int getVisitCount() {
//        int count = 0;
//        String sql = "SELECT visitCount FROM VisitCounter WHERE id = 1";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            if (rs.next()) {
//                count = rs.getInt("visitCount");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return count;
//    }
//
//    public void increaseVisitCount() {
//        String sql = "UPDATE VisitCounter SET visitCount = visitCount + 1 WHERE id = 1";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public void updateVisitCount() {
        String sql = "IF EXISTS (SELECT 1 FROM VisitCounter WHERE visitDate = CAST(GETDATE() AS DATE)) "
                + "UPDATE VisitCounter SET visitCount = visitCount + 1 WHERE visitDate = CAST(GETDATE() AS DATE) "
                + "ELSE INSERT INTO VisitCounter (visitDate, visitCount) VALUES (CAST(GETDATE() AS DATE), 1)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getVisitCount(String type) {
        String sql = "SELECT SUM(visitCount) FROM VisitCounter WHERE "
                + (type.equals("DAY") ? "visitDate = CAST(GETDATE() AS DATE)"
                : type.equals("MONTH") ? "YEAR(visitDate) = YEAR(GETDATE()) AND MONTH(visitDate) = MONTH(GETDATE())"
                : "YEAR(visitDate) = YEAR(GETDATE())");

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Map<String, Object>> getVisitStatistics() {
        List<Map<String, Object>> visitData = new ArrayList<>();
        String sql = "SELECT visitDate, SUM(visitCount) AS totalVisits FROM VisitCounter "
                + "GROUP BY visitDate ORDER BY visitDate ASC";

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> data = new HashMap<>();
                data.put("date", rs.getString("visitDate"));
                data.put("count", rs.getInt("totalVisits"));
                visitData.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return visitData;
    }

    //
//    public List<Map<String, Object>> getFilteredVisitStatistics(String filterType) {
//        List<Map<String, Object>> visitData = new ArrayList<>();
//        String sql = "";
//
//        if ("day".equals(filterType)) {
//            sql = "SELECT visitDate AS label, SUM(visitCount) AS totalVisits FROM VisitCounter "
//                    + "WHERE visitDate >= DATEADD(DAY, -7, GETDATE()) "
//                    + "GROUP BY visitDate ORDER BY visitDate ASC";
//        } else if ("month".equals(filterType)) {
//            sql = "SELECT FORMAT(visitDate, 'yyyy-MM') AS label, SUM(visitCount) AS totalVisits FROM VisitCounter "
//                    + "WHERE visitDate >= DATEADD(MONTH, -6, GETDATE()) "
//                    + "GROUP BY FORMAT(visitDate, 'yyyy-MM') ORDER BY label ASC";
//        } else if ("year".equals(filterType)) {
//            sql = "SELECT YEAR(visitDate) AS label, SUM(visitCount) AS totalVisits FROM VisitCounter "
//                    + "GROUP BY YEAR(visitDate) ORDER BY label ASC";
//        }
//
//        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                Map<String, Object> data = new HashMap<>();
//                data.put("date", rs.getString("label"));
//                data.put("count", rs.getInt("totalVisits"));
//                visitData.add(data);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return visitData;
//    }
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public List<Map<String, Object>> getFilteredVisitStatistics(String filterType) {
        List<Map<String, Object>> visitData = new ArrayList<>();
        String sql = "";

        if ("day".equals(filterType)) {
            sql = "SELECT visitDate, SUM(visitCount) AS totalVisits FROM VisitCounter "
                    + "WHERE visitDate >= DATEADD(DAY, -7, GETDATE()) "
                    + "GROUP BY visitDate ORDER BY visitDate ASC";
        } else if ("month".equals(filterType)) {
            sql = "SELECT FORMAT(visitDate, 'MM-yyyy') AS label, SUM(visitCount) AS totalVisits FROM VisitCounter "
                    + "WHERE visitDate >= DATEADD(MONTH, -6, GETDATE()) "
                    + "GROUP BY FORMAT(visitDate, 'MM-yyyy') ORDER BY label ASC";
        } else if ("year".equals(filterType)) {
            sql = "SELECT YEAR(visitDate) AS label, SUM(visitCount) AS totalVisits FROM VisitCounter "
                    + "GROUP BY YEAR(visitDate) ORDER BY label ASC";
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> data = new HashMap<>();
                if ("day".equals(filterType)) {
                    data.put("date", dateFormat.format(rs.getDate("visitDate"))); // Chuyển thành dd-MM-yyyy
                } else {
                    data.put("date", rs.getString("label")); // Giữ nguyên tháng/năm
                }
                data.put("count", rs.getInt("totalVisits"));
                visitData.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return visitData;
    }

    public static void main(String[] args) {
        try {

            VisitCounterDAO visitDAO = new VisitCounterDAO(); // Truyền connection vào DAO

            // Kiểm tra thống kê theo ngày
            System.out.println("📊 Thống kê theo ngày:");
            List<Map<String, Object>> dayStats = visitDAO.getFilteredVisitStatistics("day");
            dayStats.forEach(System.out::println);

            // Kiểm tra thống kê theo tháng
            System.out.println("\n📊 Thống kê theo tháng:");
            List<Map<String, Object>> monthStats = visitDAO.getFilteredVisitStatistics("month");
            monthStats.forEach(System.out::println);

            // Kiểm tra thống kê theo năm
            System.out.println("\n📊 Thống kê theo năm:");
            List<Map<String, Object>> yearStats = visitDAO.getFilteredVisitStatistics("year");
            yearStats.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
