package es.rent.cars.bo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author Julian
 *
 */
@Data
public class MSERentCars {
	
	@JsonProperty("cartsToRent")
	private List<CarToRent> cartsToRent;
	
	@JsonProperty("idCustomer")
	private Integer idCustomer;
	
}
