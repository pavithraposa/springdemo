package com.example.springdemo01;

// Uppgift: Lägg till mer information om filmen. Kanske en regissör, vilket år den kom ut, och i vilket land.

public class Movie {
    private int id;
    private int rating;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", rating=" + rating +
                ", title='" + title + '\'' +
                '}';
    }
}
