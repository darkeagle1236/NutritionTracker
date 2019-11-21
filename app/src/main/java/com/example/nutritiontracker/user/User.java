package com.example.nutritiontracker.user;

public class User {
    private String id,gender,weight,height,age,username;

    public User() {
    }

    public User(String id, String gender, String weight, String height, String age, String username) {
        this.id = id;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
