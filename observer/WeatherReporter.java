package org.example.observer;

public class WeatherReporter {
    public static void main(String[] args) {
        WeatherStation station = new WeatherData();

        WeatherObserver temperatureObs = new TemperatureObserver();
        WeatherObserver pressureObs = new PressureObserver();
        WeatherObserver humidityObs = new HumidityObserver();

        station.registerObserver(temperatureObs);
        station.registerObserver(pressureObs);
        station.registerObserver(humidityObs);


        station.updateData(23.5F,545F,35F);



    }
}
