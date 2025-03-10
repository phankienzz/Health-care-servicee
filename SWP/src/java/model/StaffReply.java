/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Hoang
 */
public class StaffReply {

    private int reply_id;
    private int comment_id;
    private int staffID;
    private Staff staffId;
    private String content;
    private int status;
    private String created_at;

    public StaffReply() {
    }
    

    public StaffReply(int reply_id, int comment_id, int staffID, String content, int status, String created_at) {
        this.reply_id = reply_id;
        this.comment_id = comment_id;
        this.staffID = staffID;
        this.content = content;
        this.status = status;
        this.created_at = created_at;
    }

    public StaffReply(int reply_id, int comment_id, Staff staffId, String content, int status, String created_at) {
        this.reply_id = reply_id;
        this.comment_id = comment_id;
        this.staffId = staffId;
        this.content = content;
        this.status = status;
        this.created_at = created_at;
    }

    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public Staff getStaffId() {
        return staffId;
    }

    public void setStaffId(Staff staffId) {
        this.staffId = staffId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "StaffReply{" + "reply_id=" + reply_id + ", comment_id=" + comment_id + ", staffID=" + staffID + ", content=" + content + ", status=" + status + ", created_at=" + created_at + '}';
    }   

}
