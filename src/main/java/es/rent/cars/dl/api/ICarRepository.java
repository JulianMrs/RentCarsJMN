/**
 * 
 */
package es.rent.cars.dl.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.rent.cars.entity.Car;

/**
 * @author Julian
 *
 */
@Repository
public interface ICarRepository extends JpaRepository<Car, Integer>{

}
