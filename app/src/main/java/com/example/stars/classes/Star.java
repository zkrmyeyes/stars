package com.example.stars.classes;

public class Star {
    private String nom;
    private float rating;
    private int imageRes;

    public Star(String nom, float rating, int imageRes) {
        this.nom = nom;
        this.rating = rating;
        this.imageRes = imageRes;
    }

    public String getNom() { return nom; }
    public float getRating() { return rating; }
    public int getImageRes() { return imageRes; }
    public void setRating(float rating) { this.rating = rating; }
}
