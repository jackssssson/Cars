package model;

import com.google.gson.annotations.SerializedName;

public class Car {
    @SerializedName("id")
    private int id;

    @SerializedName("model")
    private String model;

    @SerializedName("yearOfManufacture")
    private int yearOfManufacture;

    @SerializedName("color")
    private String color;

    public Car(int id, String model, int yearOfManufacture, String color) {
        this.id = id;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String getModel() {
        return model;
    }

    private int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString(){
        return getId() + ". " + getModel() + " with " + getColor()
                + " color - " + getYearOfManufacture() + " year!";
     }
}
