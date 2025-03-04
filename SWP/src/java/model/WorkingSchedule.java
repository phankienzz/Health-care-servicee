package model;

import java.sql.Time;

public class WorkingSchedule {

    private int scheduleID;
    private int professionalID;
    private int dayOfWeek;
    private Time startTime;
    private Time endTime;
    private String shift;

    // Constructor đầy đủ
    public WorkingSchedule(int scheduleID, int professionalID, int dayOfWeek, Time startTime, Time endTime, String shift) {
        this.scheduleID = scheduleID;
        this.professionalID = professionalID;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shift = shift;
    }

    // Constructor không có scheduleID (dùng khi thêm mới)
    public WorkingSchedule(int professionalID, int dayOfWeek, Time startTime, Time endTime, String shift) {
        this.professionalID = professionalID;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shift = shift;
    }

    // Constructor mặc định
    public WorkingSchedule() {
    }

    // Getters và Setters
    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public int getProfessionalID() {
        return professionalID;
    }

    public void setProfessionalID(int professionalID) {
        this.professionalID = professionalID;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    // Phương thức chuyển đổi dayOfWeek thành tên ngày
    public String getDayName() {
        switch (dayOfWeek) {
            case 0:
                return "Chủ nhật";
            case 1:
                return "Thứ hai";
            case 2:
                return "Thứ ba";
            case 3:
                return "Thứ tư";
            case 4:
                return "Thứ năm";
            case 5:
                return "Thứ sáu";
            case 6:
                return "Thứ bảy";
            default:
                return "Không xác định";
        }
    }

    @Override
    public String toString() {
        return "WorkingSchedule{" + "scheduleID=" + scheduleID + ", professionalID=" + professionalID
                + ", dayOfWeek=" + dayOfWeek + ", startTime=" + startTime
                + ", endTime=" + endTime + ", shift=" + shift + '}';
    }
}
