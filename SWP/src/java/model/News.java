/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author jaxbo
 */
public class News {

    private int postId;
    private String title;
    private String content;
    private int createdBy;
    private int categoryId;
    private String status;
    private String image;
    private String detail;
    private Date createdAt;
    private Date updatedAt;

    public News() {
    }

    public News(int postId, String title, String content, int createdBy, int categoryId, String status, String image, String detail, Date createdAt, Date updatedAt) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.categoryId = categoryId;
        this.status = status;
        this.image = image;
        this.detail = detail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "News{" + "postId=" + postId + ", title=" + title + ", content=" + content + ", createdBy=" + createdBy + ", categoryId=" + categoryId + ", status=" + status + ", image=" + image + ", detail=" + detail + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

}
