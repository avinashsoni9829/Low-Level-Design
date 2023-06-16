package org.example.observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements WeatherStation {
    private float temperature;
    private float humidity;
    private float pressure;
    List<WeatherObserver> observerList = new ArrayList<>();



    @Override
    public void registerObserver(WeatherObserver weatherObserver) {
        observerList.add(weatherObserver);
    }

    @Override
    public void unregisterObserver(WeatherObserver weatherObserver) {
         observerList.remove(weatherObserver);
    }

    @Override
    public void notifyObserver() {
           for(WeatherObserver observer : observerList)
           observer.update(temperature,humidity,pressure);
    }

    @Override
    public void updateData(float temp, float pressure, float humidity) {

            this.temperature = temp;
            this.humidity = humidity;
            this.pressure = pressure;
            notifyObserver();

    }


}
