/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.MedicalExamination;
import model.Role;

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
                        cusDAO.getCustomerByID(rs.getInt("customerID")) ,
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
    
    public static void main(String[] args) {
        MedicalExaminationDAO m = new MedicalExaminationDAO();
        
        System.out.println(m.getMedicalExaminationByID(1).getList().get(0).getPackageName());
    }
}
