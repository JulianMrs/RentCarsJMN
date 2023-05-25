/**
 * 
 */
package es.rent.cars.sr.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import es.rent.cars.bo.MSERentCars;
import es.rent.cars.bo.MSEReturnCar;
import es.rent.cars.bo.MSSRentCars;
import es.rent.cars.bo.MSSReturnCar;

/**
 * @author Julian
 *
 */
public interface IRentsController {

	@PostMapping(path = "api/1.0/rentCars", produces = {"application/json; charset=utf-8"})
	public ResponseEntity<MSSRentCars> rentCars(@Valid @RequestBody MSERentCars entradaRentCars);
	
	@PostMapping(path = "api/1.0/returnCar", produces = {"application/json; charset=utf-8"})
	public ResponseEntity<MSSReturnCar> returnCar(@Valid @RequestBody MSEReturnCar entradReturnCar);

}
