package es.rent.cars.bo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author Julian
 *
 */
@Data
public class MSSRentCars {
	
	@JsonProperty("rentedCars")
	private List<CarToRent> rentedCars;

}
