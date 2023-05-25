package es.rent.cars.dl.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.rent.cars.entity.Customer;

/**
 * @author Julian
 *
 */
@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

}
