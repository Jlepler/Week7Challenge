package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.Collection;
import java.util.Date;



@Entity
@Table(name="Message_Data")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=4, max=20)
    @Column(name="title")
    private String title;

    @NotNull
    @Size(min=1,max=200)
    @Column(name="content")
    private String content;

    @NotNull
    @Column(name="posted_date")
    private String postedDate;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

    public Message(@NotNull @Size(min = 4, max = 20) String title, @NotNull @Size(min = 1, max = 200) String content, @NotNull String postedDate, @NotNull @Size(min = 3, max = 20) User user) {
        this.title = title;
        this.content = content;
        this.postedDate = postedDate;
        this.user = user;
    }

    public Message() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

//    public String getPostedBy() {
//        return postedBy;
//    }
//
//    public void setPostedBy(String postedBy) {
//        this.postedBy = postedBy;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
