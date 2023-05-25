/**
 * 
 */
package es.rent.cars.bl.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.rent.cars.bl.api.ICarService;
import es.rent.cars.dl.api.ICarRepository;
import es.rent.cars.entity.Car;

/**
 * @author Julian
 *
 */
@Service
public class CarServiceImpl implements ICarService {

	@Autowired
	ICarRepository carRepository;
	
	public Optional<Car> getCarById(Integer idCar) {
		
		Optional<Car> carForRent = carRepository.findById(idCar);
		
		return carForRent;
	}
	
	public void saveCar(Car car) {
		
		carRepository.save(car);
	}
}
