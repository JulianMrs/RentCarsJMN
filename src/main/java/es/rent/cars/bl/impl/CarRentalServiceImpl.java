/**
 * 
 */
package es.rent.cars.bl.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.rent.cars.bl.api.ICarRentalService;
import es.rent.cars.bl.api.ICarService;
import es.rent.cars.bl.api.ICustomerService;
import es.rent.cars.bl.api.IRentService;
import es.rent.cars.bo.CarToRent;
import es.rent.cars.bo.MSERentCars;
import es.rent.cars.bo.MSSRentCars;
import es.rent.cars.bo.MSSReturnCar;
import es.rent.cars.entity.Car;
import es.rent.cars.entity.Customer;
import es.rent.cars.entity.Rent;
import es.rent.cars.utils.CarsPriceUtil;
import es.rent.cars.utils.PercentagesUtil;
import es.rent.cars.utils.TypeOfCarUtil;

/**
 * @author Julian
 *
 */

@Service
public class CarRentalServiceImpl implements ICarRentalService {

	@Autowired
	IRentService rentService;

	@Autowired
	ICustomerService customerService;

	@Autowired
	ICarService carService;

	@Override
	@Transactional
	public MSSRentCars rentCars(MSERentCars rentCars, List<Car> cars, Customer customer) {

		MSSRentCars salidaRentCars = new MSSRentCars();
		Rent rent = null;

		for (Car carForRent : cars) {
			rent = new Rent();	
			rent.setCustomer(customer);
			rent.setCar(carForRent);
			rent.setStartDate(findCarById(rentCars.getCartsToRent(), carForRent.getId()).get().getStartDate());
			rent.setEndDate(findCarById(rentCars.getCartsToRent(), carForRent.getId()).get().getFinishDate());

			rent.setPrice(calculateRentalPrice(carForRent, rent.getStartDate(), rent.getEndDate()));

			findCarById(rentCars.getCartsToRent(), carForRent.getId()).get().setPriceOfRent(rent.getPrice());

			customer.setPoints(calculatePointsOfCustomer(customer.getPoints(), carForRent.getType()));

			customer.getRentals().add(rent);
			customerService.saveCustomer(customer);

			carForRent.setRented(true);
			carForRent.getRentals().add(rent);
			carService.saveCar(carForRent);

			rentService.saveRent(rent);
		}

		salidaRentCars.setRentedCars(rentCars.getCartsToRent());

		return salidaRentCars;

	}

	@Override
	public MSSReturnCar returnCar(Car car, Rent rent, LocalDate returnDate) {

		Double extraPrice = 0.0;

		long extraDays = ChronoUnit.DAYS.between(rent.getEndDate(), returnDate);

		MSSReturnCar salidaReturnCar = new MSSReturnCar();

		salidaReturnCar.setStartDate(rent.getStartDate());
		salidaReturnCar.setFinishDate(returnDate);

		Double percentage = 0.0;
		Double carPrice = 0.0;

		if (car.getType().equalsIgnoreCase(TypeOfCarUtil.SUV.getTypeOfCar())) {
			
			percentage = PercentagesUtil.SIXTY.getPercentage();
			carPrice = CarsPriceUtil.SUV_PRICE.getPriceCar();	
			
		} else if (car.getType().equalsIgnoreCase(TypeOfCarUtil.SMALL.getTypeOfCar())) {
			
			percentage = PercentagesUtil.THIRTY.getPercentage();
			carPrice = CarsPriceUtil.SMALL_PRICE.getPriceCar();
			
		} else if (car.getType().equalsIgnoreCase(TypeOfCarUtil.PREMIUM.getTypeOfCar())) {
			
			percentage = PercentagesUtil.TWENTY.getPercentage();
			carPrice = CarsPriceUtil.PREMIUM_PRICE.getPriceCar();		
		}

		extraPrice = (car.getPrice() + (percentage * carPrice)) * extraDays;

		rent.setPrice(extraPrice + rent.getPrice());
		rent.setEndDate(returnDate);

		rentService.saveRent(rent);

		car.setRented(false);
		carService.saveCar(car);

		salidaReturnCar.setFinalPriceOfRent(rent.getPrice());
		salidaReturnCar.setExtraPrice(extraPrice);

		return salidaReturnCar;
	}

	private Double calculateRentalPrice(Car carForRent, LocalDate startDate, LocalDate endDate) {

		long totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;

		Double rentalPrice = 0.0;
		String carType = carForRent.getType();

		if (carType.equalsIgnoreCase(TypeOfCarUtil.SUV.getTypeOfCar())) {
			
		    rentalPrice = calculatePriceSUVCar(carForRent.getPrice(), totalDays);
		    
		} else if (carType.equalsIgnoreCase(TypeOfCarUtil.SMALL.getTypeOfCar())) {
			
		    rentalPrice = calculatePriceSmallCar(carForRent.getPrice(), totalDays);
		    
		} else if (carType.equalsIgnoreCase(TypeOfCarUtil.PREMIUM.getTypeOfCar())) {
			
		    rentalPrice = carForRent.getPrice() * totalDays;
		}

		return rentalPrice;
	}

	private Double calculatePriceSmallCar(Double carUnitPrice, long totalDays) {

		Double calculatedPriceSmallCar = 0.0;

		for (int dayCounter = 1; dayCounter <= totalDays; dayCounter++) {

			if (dayCounter <= 7) {

				calculatedPriceSmallCar += carUnitPrice;
			} else if (dayCounter > 7) {

				calculatedPriceSmallCar += (PercentagesUtil.SIXTY.getPercentage() * carUnitPrice);

			}
		}

		return calculatedPriceSmallCar;
	}

	private Double calculatePriceSUVCar(Double carUnitPrice, long totalDays) {

		Double calculatedPriceSUVCar = 0.0;

		for (int dayCounter = 1; dayCounter <= totalDays; dayCounter++) {

			if (dayCounter <= 7) {

				calculatedPriceSUVCar += carUnitPrice;
			} else if (dayCounter > 7 && dayCounter <= 30) {

				calculatedPriceSUVCar += (PercentagesUtil.EIGHTY.getPercentage() * carUnitPrice);
			} else if (dayCounter > 30) {

				calculatedPriceSUVCar += (PercentagesUtil.FIFTY.getPercentage() * carUnitPrice);
			}
		}

		return calculatedPriceSUVCar;
	}

	private Integer calculatePointsOfCustomer(Integer actualPoints, String typeOfCar) {

		Integer newPoints = null;

		if (typeOfCar.equalsIgnoreCase(TypeOfCarUtil.PREMIUM.getTypeOfCar())) {
			newPoints = 5;
		} else if (typeOfCar.equalsIgnoreCase(TypeOfCarUtil.SUV.getTypeOfCar())) {
			newPoints = 3;
		} else {
			newPoints = 1;
		}

		return actualPoints != 0 ? actualPoints + newPoints : newPoints;
	}

	private Optional<CarToRent> findCarById(List<CarToRent> cars, int id) {
		return cars.stream().filter(car -> car.getIdCar() == id).findFirst();
	}

}
