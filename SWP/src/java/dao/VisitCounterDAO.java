/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Hoang
 */
public class VisitCounterDAO extends DBContext {
    public int getVisitCount() {
        int count = 0;
        String sql = "SELECT visitCount FROM VisitCounter WHERE id = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt("visitCount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public void increaseVisitCount() {
        String sql = "UPDATE VisitCounter SET visitCount = visitCount + 1 WHERE id = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
