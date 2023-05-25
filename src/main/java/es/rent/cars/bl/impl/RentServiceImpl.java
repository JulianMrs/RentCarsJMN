/**
 * 
 */
package es.rent.cars.bl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
	
	@Autowired
	EntityManager entityManager;

	public Rent findRentByCarAndCustomerIds(Integer idCar, Integer idCustomer) {
        String jpql = "SELECT r FROM Rent r WHERE r.car.id = :idCar AND r.customer.id = :idCustomer";

        TypedQuery<Rent> query = entityManager.createQuery(jpql, Rent.class);
        query.setParameter("idCar", idCar);
        query.setParameter("idCustomer", idCustomer);
        query.setMaxResults(1);

        List<Rent> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

	public void saveRent(Rent rent) {
		
		rentRepository.save(rent);
	}
}
