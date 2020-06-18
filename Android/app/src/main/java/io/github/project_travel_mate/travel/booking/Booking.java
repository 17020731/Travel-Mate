package io.github.project_travel_mate.travel.booking;

public class Booking {
    private String name;
    private String address;
    private String phone;
    private long rating;
    private long count;
    private double price;
    private String image;

    public Booking(String name, String address, String phone, long rating, long count, double price, String image) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
        this.count = count;
        this.price = price;
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
