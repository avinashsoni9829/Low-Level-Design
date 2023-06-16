package org.example.observer;

public class TemperatureObserver implements WeatherObserver{
    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("Temperature  = " + temperature);
    }
}
