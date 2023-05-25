package es.rent.cars.bo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author Julian
 *
 */
@Data
public class MSSReturnCar {

	@JsonProperty("startDate")
	private LocalDate startDate;
	
	@JsonProperty("finishDate")
	private LocalDate finishDate;
	
	@JsonProperty("finalPriceOfRent")
	private Double finalPriceOfRent = null;
	
	@JsonProperty("extraPrice")
	private Double extraPrice = null;
}
