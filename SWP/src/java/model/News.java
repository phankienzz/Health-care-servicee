/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author jaxbo
 */
public class News {
    private int post_id;
    private String title;
    private String content;
    private int created_by;
    private int category_id;
    private int status;
    private String created_at;
    private String updated_at;

    public News() {
    }

    public News(int post_id, String title, String content, int created_by, int category_id, int status, String created_at, String updated_at) {
        this.post_id = post_id;
        this.title = title;
        this.content = content;
        this.created_by = created_by;
        this.category_id = category_id;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
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

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
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

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "New{" + "post_id=" + post_id + ", title=" + title + ", content=" + content + ", created_by=" + created_by + ", category_id=" + category_id + ", status=" + status + ", created_at=" + created_at + ", updated_at=" + updated_at + '}';
    }

    
}
