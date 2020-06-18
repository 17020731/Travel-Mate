package io.github.project_travel_mate.travel.booking;

public class BookDetail {
    private String name;
    private String id;
    private String image;
    private String type;
    private int count;
    private String check_in;
    private String check_out;
    private String hotel_name;
    private String address;
    private String method;
    private double total;

    public BookDetail() {
    }

    public BookDetail(String name, String id, String image, String type, int count, String check_in, String check_out, String hotel_name, String address, String method, double total) {
        this.name = name;
        this.id = id;
        this.image = image;
        this.type = type;
        this.count = count;
        this.check_in = check_in;
        this.check_out = check_out;
        this.hotel_name = hotel_name;
        this.address = address;
        this.method = method;
        this.total = total;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
