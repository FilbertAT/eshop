package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import id.ac.ui.cs.advprog.eshop.service.IdGeneratorService;

@Repository
public class CarRepository {
  private List<Car> carData = new ArrayList<>();
    
  @Autowired
  private IdGeneratorService idGeneratorService;

  public Car create(Car car) {
	if (car.getCarId() == null) {
	  car.setCarId(idGeneratorService.generateId());
	}
	carData.add(car);
	return car;
  }

  public Iterator<Car> findAll() {
	return carData.iterator();
  }
  public Car findById(String id) {
	for (Car car : carData) {
	  if (car.getCarId().equals(id)) {
		return car;
	  }
	}
	return null;
  }

  public Car update(String id, Car updateCar) {
	for (int i = 0; i < carData.size(); i++) {
	  Car car = carData.get(i);
	  if (car.getCarId().equals(id)) {
		car.setCarName(updateCar.getCarName());
		car.setCarColor(updateCar.getCarColor());
		car.setCarQuantity(updateCar.getCarQuantity());
		return car;
	  }
	}
	return null;
  }

  public void delete(String id) { carData.removeIf(car -> car.getCarId().equals(id)); }
}