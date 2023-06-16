package org.example.observer;

public class PressureObserver implements WeatherObserver{
    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("pressure = " + pressure);
    }
}
