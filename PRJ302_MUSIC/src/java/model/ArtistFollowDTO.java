/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NQ9
 */
public class ArtistFollowDTO {
    private String userID;
    private int artistID;

    public ArtistFollowDTO() {
    }

    public ArtistFollowDTO(String userID, int artistID) {
        this.userID = userID;
        this.artistID = artistID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }
    
    
}
