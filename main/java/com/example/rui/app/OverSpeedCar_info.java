package com.example.rui.app;

public class OverSpeedCar_info {
    private int imageId;
    private String carId;
    private String carSpeed;
    private String date;

    public OverSpeedCar_info(int imageId,String carId,String carSpeed,String date){
        this.imageId = imageId;
        this.carId = carId;
        this.carSpeed = carSpeed;
        this.date = date;
    }

    public int getImageId() {
        return imageId;
    }
    public String getCarId() {
        return carId;
    }
    public String getCarSpeed() {
        return carSpeed;
    }
    public String getDate() {
        return date;
    }
}
