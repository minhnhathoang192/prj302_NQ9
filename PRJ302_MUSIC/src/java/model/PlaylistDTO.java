package model;

import java.sql.Timestamp;

public class PlaylistDTO {
    private int playListID;
    private String playListName;
    private int userID;
    private boolean isPublic;
    private Timestamp createDate;

    public PlaylistDTO() {}

    public PlaylistDTO(int playListID, String playListName, int userID, boolean isPublic, Timestamp createDate) {
        this.playListID = playListID;
        this.playListName = playListName;
        this.userID = userID;
        this.isPublic = isPublic;
        this.createDate = createDate;
    }

    public int getPlayListID() { return playListID; }
    public void setPlayListID(int playListID) { this.playListID = playListID; }

    public String getPlayListName() { return playListName; }
    public void setPlayListName(String playListName) { this.playListName = playListName; }

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public boolean isIsPublic() { return isPublic; }
    public void setIsPublic(boolean isPublic) { this.isPublic = isPublic; }

    public Timestamp getCreateDate() { return createDate; }
    public void setCreateDate(Timestamp createDate) { this.createDate = createDate; }
}
