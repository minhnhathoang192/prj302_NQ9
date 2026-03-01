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
public class TopicSongDTO {
    private int topicID;
    private int songID;
    private Timestamp addedAt;

    public TopicSongDTO() {
    }

    public TopicSongDTO(int topicID, int songID, Timestamp addedAt) {
        this.topicID = topicID;
        this.songID = songID;
        this.addedAt = addedAt;
    }

    public int getTopicID() {
        return topicID;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public Timestamp getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }
    
    
}
