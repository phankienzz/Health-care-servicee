/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newsController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Hoang
 */
public class formatDate {
    public String formatDate(String timestamp){
        if(timestamp == null){
            return "";
        }
        try {
            LocalDateTime dateTime = LocalDateTime.parse(timestamp.substring(0,23));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return dateTime.format(formatter);
        } catch (Exception e) {
            return timestamp;
        }
    }
}
