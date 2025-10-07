package designPatterns.behavioural.observer.weather;

public class Main {
  public static void main(String[] args) {
    WeatherStation station = new WeatherStation();

    Observer obs1 = new MobileDisplay();
    Observer obs2 = new WebDashboard();
    Observer obs3 = new AlertSystem();

    station.addObserver(obs1);
    station.addObserver(obs2);
    station.addObserver(obs3);

    station.updateTemp(25);
    station.updateTemp(32);

    station.removeObserver(obs2);
    station.updateTemp(40);
  }
}
