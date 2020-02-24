package com.mubaracktahir.wishes.ui.main;

public class Friends {
    private String name;
    private String dob;
    private int daysRemaining;
    private String mobile_number;
    public int image = -1;
    private String email;
    public int color = -1;

    public Friends(){

    }

    public Friends(String name, String dob, int daysRemaining, String mobile_number, int image, String email,int color) {
        this.name = name;
        this.dob = dob;
        this.daysRemaining = daysRemaining;
        this.mobile_number = mobile_number;
        this.image = image;
        this.email = email;
        this.color = color;
    }

    public int getColor(){
        return color;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Friends(String name,String dob,int daysRemaining,int image,int color){
        this.name = name;
        this.dob = dob;
        this.daysRemaining = daysRemaining;
        this.image = image;
        this.color = color;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getDaysRemaining() {
        return daysRemaining;
    }

    public void setDaysRemaining(int daysRemaining) {
        this.daysRemaining = daysRemaining;
    }

}
