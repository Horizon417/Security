package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping(value = "/cars")
    public String listCars(@RequestParam(value = "count", required = false, defaultValue = "5")
                                   int count, Model model) {
        List<Car> result = carService.getCarsList().stream().limit(count).collect(Collectors.toList());
        model.addAttribute("display", result);
        return "cars";
    }
}
