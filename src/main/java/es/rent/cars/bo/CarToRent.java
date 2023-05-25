package es.rent.cars.bo;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author Julian
 *
 */
@Data
public class CarToRent {
	
	@NotNull
	private Integer idCar;
	
	@NotNull
	private LocalDate startDate;
	
	@NotNull
	private LocalDate finishDate;
	
	@NotNull
	private Double priceOfRent = null;
	
}
