package fr.labs.zwing.randomuserapp.database.entity;


public class Location {

    private String street;
    private String city;
    private String state;
    private int postcode;

    public Location(String street, String city, String state, int postcode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return street + "\n"
                + city.substring(0,1).toUpperCase() + city.substring(1) + "\n"
                + state.toUpperCase() + " "
                + postcode;
    }
}
