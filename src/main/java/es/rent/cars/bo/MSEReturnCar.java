package es.rent.cars.bo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author Julian
 *
 */
@Data
public class MSEReturnCar {

	@JsonProperty("idCustomer")
	private Integer idCustomer;
	
	@JsonProperty("idCarToReturn")
	private Integer idCarToReturn;
	
	@JsonProperty("returnDate")
	private LocalDate returnDate;
}
