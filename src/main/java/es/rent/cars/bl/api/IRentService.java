package es.rent.cars.bl.api;

import es.rent.cars.entity.Rent;

/**
 * @author Julian
 *
 */
public interface IRentService {

	public Rent findRentByCarAndCustomer(Integer idCar, Integer idCustomer);
	
	public void saveRent(Rent rent);
}
