package model;

public class AlbumSongDTO {
    private int albumSongID;
    private int albumID;
    private int songID;
    private boolean isActive;

    public AlbumSongDTO() {}

    public AlbumSongDTO(int albumSongID, int albumID, int songID, boolean isActive) {
        this.albumSongID = albumSongID;
        this.albumID = albumID;
        this.songID = songID;
        this.isActive = isActive;
    }

    public int getAlbumSongID() {
        return albumSongID;
    }

    public void setAlbumSongID(int albumSongID) {
        this.albumSongID = albumSongID;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}