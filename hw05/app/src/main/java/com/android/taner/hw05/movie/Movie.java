package com.android.taner.hw05.movie;

public class Movie {
    private String Name;
    private int Year;
    private int Image;

    public Movie(int image, String name, int year) {
        Name = name;
        Year = year;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
