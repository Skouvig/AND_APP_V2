package com.example.andappv2.ui.profile;

public class Profiles {
    private String name, mail, username;
    private int age, noOfClicks;

    public Profiles(){

    }
    public Profiles(String name, String mail, String username, int age, int noOfClicks){
        this.name=name;
        this.mail=mail;
        this.username=username;
        this.age=age;
        this.noOfClicks=noOfClicks;
    }
    public String getName(){
        return name;
    }
    public String getMail(){
        return mail;
    }
    public String getUsername(){
        return username;
    }
    public int getAge(){
        return age;
    }
    public int getNoOfClicks(){
        return noOfClicks;
    }
}
