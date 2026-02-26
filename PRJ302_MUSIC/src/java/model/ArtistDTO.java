package model;

import java.sql.Date;

public class ArtistDTO {
    private int artistID;
    private String artistName, avatarURL, description;
    private Date debutDate;
    private boolean isActive;

    public ArtistDTO() {}

    public ArtistDTO(int artistID, String artistName, String avatarURL, String description, Date debutDate, boolean isActive) {
        this.artistID = artistID;
        this.artistName = artistName;
        this.avatarURL = avatarURL;
        this.description = description;
        this.debutDate = debutDate;
        this.isActive = isActive;
    }

    public int getArtistID() { return artistID; }
    public void setArtistID(int artistID) { this.artistID = artistID; }

    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }

    public String getAvatarURL() { return avatarURL; }
    public void setAvatarURL(String avatarURL) { this.avatarURL = avatarURL; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getDebutDate() { return debutDate; }
    public void setDebutDate(Date debutDate) { this.debutDate = debutDate; }

    public boolean isIsActive() { return isActive; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }
}
