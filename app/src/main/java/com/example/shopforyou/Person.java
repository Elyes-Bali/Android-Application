package com.example.shopforyou;

public class Person {
    int image ;
    String Name;
    String des;


    public Person(int image, String name, String des) {
        this.image = image;
        Name = name;
        this.des = des;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }}

