package es.rent.cars.bl.api;

import java.time.LocalDate;
import java.util.List;

import es.rent.cars.bo.MSERentCars;
import es.rent.cars.bo.MSSRentCars;
import es.rent.cars.bo.MSSReturnCar;
import es.rent.cars.entity.Car;
import es.rent.cars.entity.Customer;
import es.rent.cars.entity.Rent;

/**
 * @author Julian
 *
 */
public interface ICarRentalService {
	
	public MSSRentCars rentCars (MSERentCars rentCars, List<Car> car, Customer customer);

	public MSSReturnCar returnCar(Car car, Rent rent, LocalDate returnDate);

}
