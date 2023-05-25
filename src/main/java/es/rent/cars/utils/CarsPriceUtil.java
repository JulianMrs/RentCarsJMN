/**
 * 
 */
package es.rent.cars.utils;

/**
 * @author Julian
 *
 */
public enum CarsPriceUtil {

	PREMIUM_PRICE(300),
	SUV_PRICE(150),
    SMALL_PRICE(50);
	
    private final double priceCar;

    CarsPriceUtil(double priceCar) {
        this.priceCar = priceCar;
    }

    public double getPriceCar() {
        return priceCar;
    }
}
