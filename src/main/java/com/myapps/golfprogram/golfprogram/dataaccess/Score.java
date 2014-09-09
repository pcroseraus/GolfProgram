/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapps.golfprogram.golfprogram.dataaccess;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "scores")
public class Score implements Serializable {
    
    private Long id;
    
    private int version;
    private String courseName;
    private int score;
    private Date scoreDate;

    private Contact contact;
    
    public Score() {
    }

    public Score(String courseName, int score, Date scoreDate) {
        this.courseName = courseName;
        this.score = score;
        this.scoreDate = scoreDate;
    }
    
    
   
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="ID")
    public Long getId(){
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return version;
    }
    
    public void setVersion(int version) {
        this.version = version;
    }
    
    public void setCourseName(String courseName){
        this.courseName = courseName;
    }
    
    @Column(name = "course_name")
    public String getCourseName(){
        return this.courseName;
    }

    @Column(name = "score")
    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Column(name="score_date")
    public Date getScoreDate() {
        return this.scoreDate;
    }

    public void setScoreDate(Date scoreDate) {
        this.scoreDate = scoreDate;
    } 

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "CONTACT_ID")
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Scores{" + "id=" + id + ", version=" + version + ", courseName=" + courseName + ", score=" + score + ", scoreDate=" + scoreDate + ", contact=" + contact + '}';
    }
    
    
}