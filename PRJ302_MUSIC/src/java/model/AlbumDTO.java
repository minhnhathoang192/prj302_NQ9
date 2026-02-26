package model;

import java.sql.Date;

public class AlbumDTO {
    private int albumID;
    private String albumName, coverImage;
    private Date releaseDate;
    private boolean isActive;

    public AlbumDTO() {}

    public AlbumDTO(int albumID, String albumName, String coverImage, Date releaseDate, boolean isActive) {
        this.albumID = albumID;
        this.albumName = albumName;
        this.coverImage = coverImage;
        this.releaseDate = releaseDate;
        this.isActive = isActive;
    }

    public int getAlbumID() { return albumID; }
    public void setAlbumID(int albumID) { this.albumID = albumID; }

    public String getAlbumName() { return albumName; }
    public void setAlbumName(String albumName) { this.albumName = albumName; }

    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }

    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }

    public boolean isIsActive() { return isActive; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }
}
