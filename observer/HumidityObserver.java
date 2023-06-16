package org.example.observer;

public class HumidityObserver implements WeatherObserver{
    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("Humidity = "+humidity);
    }
}
