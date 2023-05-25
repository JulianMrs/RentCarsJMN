/**
 * 
 */
package es.rent.cars.dl.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.rent.cars.entity.Rent;

/**
 * @author Julian
 *
 */
@Repository
public interface IRentRepository extends JpaRepository<Rent, Integer> {

}
