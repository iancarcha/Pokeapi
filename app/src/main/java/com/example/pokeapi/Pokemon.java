package com.example.pokeapi;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Pokemon implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private String name;
    private int height;
    private int weight;
    private String image;
    private String detailsUrl;


    public Pokemon() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {

        return height;
    }

    public void setHeight(int height) {

        this.height = height;
    }

    public int getWeight() {

        return weight;
    }

    public void setWeight(int weight) {

        this.weight = weight;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }

    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", image='" + image + '\'' +
                ", detailsUrl='" + detailsUrl + '\'' +
                '}';
    }
}
