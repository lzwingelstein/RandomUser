package fr.labs.zwing.randomuserapp.database.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("value")
    private int id;

    private String gender;

    @Embedded(prefix = "name")
    private Name name;

    @Embedded(prefix = "location")
    private Location location;

    private String email;

    private String phone;

    private String cell;

    @Embedded(prefix = "picture")
    private Picture picture;

    private String nat;

    @Ignore
    public User() {
    }

    public User(@NonNull int id, String gender, Name name, Location location, String email, String phone, String cell, Picture picture, String nat) {
        this.id = id;
        this.gender = gender;
        this.name = name;
        this.location = location;
        this.email = email;
        this.phone = phone;
        this.cell = cell;
        this.picture = picture;
        this.nat = nat;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", gender='" + gender + '\'' +
                ", name=" + name +
                ", location=" + location +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", cell='" + cell + '\'' +
                ", picture=" + picture +
                ", nat='" + nat + '\'' +
                '}';
    }
}
