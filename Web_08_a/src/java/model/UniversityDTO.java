/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NQ9
 */
public class UniversityDTO {
    private String id;
    private String name;
    private String shortName;
    private String description;
    private int foundedYear;
    private String address;
    private String city;
    private String region;
    private String type;
    private int totalStudent;
    private int totalFaculties;
    private boolean isDraft;

    public UniversityDTO() {
    }

    public UniversityDTO(String id, String name, String shortName, String description, int foundedYear, String address, String city, String region, String type, int totalStudent, int totalFaculties, boolean isDraft) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.foundedYear = foundedYear;
        this.address = address;
        this.city = city;
        this.region = region;
        this.type = type;
        this.totalStudent = totalStudent;
        this.totalFaculties = totalFaculties;
        this.isDraft = isDraft;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(int foundedYear) {
        this.foundedYear = foundedYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotalStudent() {
        return totalStudent;
    }

    public void setTotalStudent(int totalStudent) {
        this.totalStudent = totalStudent;
    }

    public int getTotalFaculties() {
        return totalFaculties;
    }

    public void setTotalFaculties(int totalFaculties) {
        this.totalFaculties = totalFaculties;
    }

    public boolean isIsDraft() {
        return isDraft;
    }

    public void setIsDraft(boolean isDraft) {
        this.isDraft = isDraft;
    }
    
}
