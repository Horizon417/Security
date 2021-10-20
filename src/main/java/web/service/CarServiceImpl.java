package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.Arrays;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    public List<Car> getCarsList() {

        return Arrays.asList(new Car("Volvo", "XC90", "SUV"),
                new Car("Audi", "A3", "Sedan"),
                new Car("Volvo", "S60", "Sedan"),
                new Car("Nissan", "Quashquai", "SUV"),
                new Car("Tesla", "Model S", "Sedan"));
    }
}
