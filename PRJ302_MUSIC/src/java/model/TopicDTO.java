/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author NQ9
 */
public class TopicDTO {
    private int topicID;
    private String topicName;
    private String description;
    private String coverImage;
    private boolean isActive;
    private Timestamp createdAt;

    public TopicDTO() {
    }

    public TopicDTO(int topicID, String topicName, String description, String coverImage, boolean isActive, Timestamp createdAt) {
        this.topicID = topicID;
        this.topicName = topicName;
        this.description = description;
        this.coverImage = coverImage;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    public int getTopicID() {
        return topicID;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    
}
