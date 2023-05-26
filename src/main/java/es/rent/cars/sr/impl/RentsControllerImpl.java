/**
 * 
 */
package es.rent.cars.sr.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import es.rent.cars.bl.api.ICarRentalService;
import es.rent.cars.bl.api.ICarService;
import es.rent.cars.bl.api.ICustomerService;
import es.rent.cars.bl.api.IRentService;
import es.rent.cars.bo.CarToRent;
import es.rent.cars.bo.MSERentCars;
import es.rent.cars.bo.MSEReturnCar;
import es.rent.cars.bo.MSSRentCars;
import es.rent.cars.bo.MSSReturnCar;
import es.rent.cars.entity.Car;
import es.rent.cars.entity.Customer;
import es.rent.cars.entity.Rent;
import es.rent.cars.sr.api.IRentsController;

/**
 * @author Julian
 *
 */
@Controller
public class RentsControllerImpl implements IRentsController {

	@Autowired
	ICarRentalService rentCarsService;

	@Autowired
	ICustomerService customerService;

	@Autowired
	ICarService carService;
	
	@Autowired
	IRentService rentService;

	public ResponseEntity<MSSRentCars> rentCars(@Valid @RequestBody MSERentCars entradaRentCars) {

		MSSRentCars salidaRentCars = null;

		Optional<Customer> customer = customerService.getCustomerById(entradaRentCars.getIdCustomer());
		if (!customer.isPresent()) {
			return new ResponseEntity<>(salidaRentCars, HttpStatus.BAD_REQUEST);
		}

		List<Car> cars = new ArrayList<>();
		for (CarToRent rentCar : entradaRentCars.getCartsToRent()) {
			Optional<Car> car = carService.getCarById(rentCar.getIdCar());
			if (!car.isPresent()) {
				return new ResponseEntity<>(salidaRentCars, HttpStatus.BAD_REQUEST);
			}
			cars.add(car.get());
		}

		salidaRentCars = rentCarsService.rentCars(entradaRentCars, cars, customer.get());

		return new ResponseEntity<>(salidaRentCars, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<MSSReturnCar> returnCar(@Valid @RequestBody MSEReturnCar entradaReturnCar) {

		MSSReturnCar salidaReturnCar = null;

		Optional<Customer> customer = customerService.getCustomerById(entradaReturnCar.getIdCustomer());
		if (!customer.isPresent()) {
			return new ResponseEntity<>(salidaReturnCar, HttpStatus.BAD_REQUEST);
		}

		Optional<Car> car = carService.getCarById(entradaReturnCar.getIdCarToReturn());
		if (!car.isPresent()) {
			return new ResponseEntity<>(salidaReturnCar, HttpStatus.BAD_REQUEST);
		}
		
		Rent rent = rentService.findRentByCarAndCustomer(car.get().getId(),customer.get().getId());
		if(rent == null) {
			return new ResponseEntity<>(salidaReturnCar, HttpStatus.BAD_REQUEST);
		}
				
		salidaReturnCar = rentCarsService.returnCar(car.get(), rent, entradaReturnCar.getReturnDate());
		
		return new ResponseEntity<>(salidaReturnCar, HttpStatus.OK);
	}
}
