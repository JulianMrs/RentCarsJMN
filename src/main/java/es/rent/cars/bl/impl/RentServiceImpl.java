/**
 * 
 */
package es.rent.cars.bl.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.rent.cars.bl.api.IRentService;
import es.rent.cars.dl.api.IRentRepository;
import es.rent.cars.entity.Rent;

/**
 * @author Julian
 *
 */
@Service
public class RentServiceImpl implements IRentService {

	@Autowired
	IRentRepository rentRepository;

	public Rent findRentByCarAndCustomer(Integer idCar, Integer idCustomer) {

		Rent rent = rentRepository.findRentByCarAndCustomerIds(idCar, idCustomer);
		
		return rent;
    }

	public void saveRent(Rent rent) {
		
		rentRepository.save(rent);
	}
}
