package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;

@Controller
@RequestMapping("/car")
public class CarController extends AbstractItemController<Car> {

    @Autowired
    public CarController(CarService carService) {
        super(
            carService,
            "redirect:list",
            "CreateCar",
            "CarList",
            "EditCar",
            "car",
            "cars"
        );
    }

    @GetMapping("/create")
    public String createCarPage(Model model) {
        return createPage(model, new Car());
    }

    @PostMapping("/create")
    public String createCarPost(@ModelAttribute Car car, Model model) {
        return createPost(car);
    }

    @GetMapping("/list")
    public String carListPage(Model model) {
        return listPage(model);
    }

    @GetMapping("/edit/{id}")
    public String editCarPage(@PathVariable("id") String id, Model model) {
        return editPage(id, model);
    }

    @PostMapping("/edit")
    public String editCarPost(@ModelAttribute Car car, Model model) {
        return editPost(car);
    }

    @PostMapping("/delete")
    public String deleteCar(@RequestParam("id") String carId) {
        return delete(carId);
    }
}