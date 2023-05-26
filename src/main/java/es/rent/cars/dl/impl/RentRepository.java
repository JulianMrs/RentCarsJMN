package es.rent.cars.dl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import es.rent.cars.dl.api.IRentRepository;
import es.rent.cars.entity.Rent;

@Repository
public abstract class RentRepository implements IRentRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Rent findRentByCarAndCustomerIds(Integer idCar, Integer idCustomer) {

        TypedQuery<Rent> query = entityManager.createNamedQuery("Rent.findRentByCarAndCustomerIds", Rent.class);
        query.setParameter("idCar", idCar);
        query.setParameter("idCustomer", idCustomer);
        query.setMaxResults(1);

        List<Rent> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
	
}
