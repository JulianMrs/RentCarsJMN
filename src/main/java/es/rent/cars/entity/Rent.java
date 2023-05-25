/**
 * 
 */
package es.rent.cars.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author JulianM
 *
 */
@Entity
@Data
@Table(name="rent")
public class Rent {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_car")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    private LocalDate startDate;
    
    private LocalDate endDate;
        
    private Double price;
}
