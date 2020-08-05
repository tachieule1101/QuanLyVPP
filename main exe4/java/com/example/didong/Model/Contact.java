package com.example.didong.Model;

public class Contact {
    private String Title;
    private int File;

    public Contact(String title, int file){
        Title = title;
        File = file;
    }
    public String getTitle(){return Title;}
    public int getFile(){return File;}
    public void setTitle(String title) {Title = title;}
    public void setFile(int file) {File = file;}

}
