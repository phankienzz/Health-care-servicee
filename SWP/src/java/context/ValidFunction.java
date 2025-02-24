/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Gigabyte
 */
public class ValidFunction {
    public boolean containsDigitOrSpecialChar(String str) {
        return str.matches(".*[^a-zA-Z\\s].*"); // Kiểm tra nếu có ký tự không phải chữ cái hoặc khoảng trắng
    }

    public  String normalizeName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "";
        }
        name = name.trim().toLowerCase(); // Loại bỏ khoảng trắng đầu cuối và chuyển về chữ thường
        String[] words = name.split("\\s+"); // Tách các từ dựa vào khoảng trắng
        StringBuilder normalized = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                normalized.append(Character.toUpperCase(word.charAt(0))) // Viết hoa chữ cái đầu
                        .append(word.substring(1)) // Giữ lại phần còn lại
                        .append(" "); // Thêm khoảng trắng giữa các từ
            }
        }

        return normalized.toString().trim(); // Loại bỏ khoảng trắng cuối cùng
    }

    public boolean isValidPhoneNumber(String input) {
        // Kiểm tra chuỗi có đúng 10 ký tự số không
        if (!input.matches("\\d{10}")) {
            return false;
        }

        // Kiểm tra chuỗi có bắt đầu bằng '0' không
        return input.startsWith("0");
    }

    public boolean isValidPassword(String password) {
        // Kiểm tra độ dài ít nhất 8 ký tự
        if (password.length() < 8) {
            return false;
        }

        // Biểu thức chính quy kiểm tra điều kiện
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

        return password.matches(regex);
    }
    public String formatDate(String input) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter);
        return dateTime.format(outputFormatter);
    }
    
    public String formatDateNews(String date) {
        // Chuyển từ chuỗi ngày ban đầu (yyyy-MM-dd HH:mm:ss) sang Timestamp
        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(date);
        // Định dạng Timestamp thành chuỗi dd/MM/yyyy
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(timestamp);
    }
}
