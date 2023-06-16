package org.example.observer;

public interface WeatherStation {
    void registerObserver(WeatherObserver weatherObserver);
    void unregisterObserver(WeatherObserver weatherObserver);
    void notifyObserver();

    void updateData(float temp , float pressure , float humidity);
}
