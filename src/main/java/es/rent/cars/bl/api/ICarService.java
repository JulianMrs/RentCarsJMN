/**
 * 
 */
package es.rent.cars.bl.api;

import java.util.Optional;

import es.rent.cars.entity.Car;

/**
 * @author Julian
 *
 */
public interface ICarService {

	public Optional<Car> getCarById(Integer idCar);
	
	public void saveCar(Car car);
}
