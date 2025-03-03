package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl extends AbstractItemService<Car, ItemRepository<Car>> implements CarService {
  public CarServiceImpl(ItemRepository<Car> carRepositoryBean) {
    this.repository = carRepositoryBean;
  }
}