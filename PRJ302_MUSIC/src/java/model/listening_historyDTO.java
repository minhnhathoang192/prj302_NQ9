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
public class listening_historyDTO {
    private String userID;
    private int songID;
    private Timestamp listenedAt;

    public listening_historyDTO() {
    }

    public listening_historyDTO(String userID, int songID, Timestamp listenedAt) {
        this.userID = userID;
        this.songID = songID;
        this.listenedAt = listenedAt;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public Timestamp getListenedAt() {
        return listenedAt;
    }

    public void setListenedAt(Timestamp listenedAt) {
        this.listenedAt = listenedAt;
    }
    
    
}
