package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.model.Car;

import java.util.*;

@Component
public class CarDAO {

    private static final int NUMBER_OF_CARS = 5;
    private List<Car> cars;
    public CarDAO() {
        cars = new ArrayList<>();

        cars.add(new Car(1, "Audi", 4));
        cars.add(new Car(2, "BMW", 3));
        cars.add(new Car(3, "Mercedes", 1));
        cars.add(new Car(4, "Tesla", 3));
        cars.add(new Car(5, "Mazda", 7));

    };
    public List<Car> getSomeCars(int count) {
        List<Car> car = new ArrayList<>();
        if( count > 0 & count <= NUMBER_OF_CARS) {
            for (int i = 0; i < count; i++) {
                car.add(cars.get(i));
            }
            return car;
        } else {
            return cars;
        }
    }

}
