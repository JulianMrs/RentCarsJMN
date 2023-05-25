/**
 * 
 */
package es.rent.cars.utils;

/**
 * @author Julian
 *
 */
public enum TypeOfCarUtil {

	PREMIUM("PREMIUM"),
	SUV("SUV"),
    SMALL("SMUALL");
	
    private final String typeOfCar;

    TypeOfCarUtil(String typeOfCar) {
        this.typeOfCar = typeOfCar;
    }

    public String getTypeOfCar() {
        return typeOfCar;
    }
}
