package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepository extends AbstractItemRepository<Car> {
  @Override
  public Car update(Car car) {
	for (Car existingCar : itemData) {
		if (existingCar.getId().equals(car.getId())) {
			// Update the existing car with the new information
			existingCar.setName(car.getName());
			existingCar.setQuantity(car.getQuantity());
			existingCar.setColor(car.getColor());

			// For backward compatibility
			existingCar.setCarName(car.getCarName());
			existingCar.setCarColor(car.getCarColor());
			existingCar.setCarQuantity(car.getCarQuantity());
			return existingCar;
	  }
	}
	return null;
  }
    
  /**
   * Legacy method for backward compatibility
   */
  public Car update(String id, Car updatedCar) {
    updatedCar.setId(id);
	return update(updatedCar);
  }
}