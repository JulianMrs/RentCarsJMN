
package es.rent.cars.bl.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.rent.cars.bl.api.ICustomerService;
import es.rent.cars.dl.api.ICustomerRepository;
import es.rent.cars.entity.Customer;

/**
 * @author Julian
 *
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepository customerRepository;
	
	public Optional<Customer> getCustomerById (Integer customerId) {
		
		Optional<Customer> customer = customerRepository.findById(customerId);
		
		return customer;
	}
	
	public void saveCustomer(Customer customer) {
		
		customerRepository.save(customer);
	}
	
	
}
