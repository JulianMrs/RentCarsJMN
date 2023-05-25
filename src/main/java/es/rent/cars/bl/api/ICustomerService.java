/**
 * 
 */
package es.rent.cars.bl.api;

import java.util.Optional;

import es.rent.cars.entity.Customer;

/**
 * @author Julia
 *
 */
public interface ICustomerService {

	public Optional<Customer> getCustomerById (Integer customerId);
	
	public void saveCustomer(Customer customer);
}
