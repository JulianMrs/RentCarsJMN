/**
 * 
 */
package es.rent.cars.utils;

/**
 * @author Julian
 *
 */
public enum PercentagesUtil {

	EIGHTY(0.80),
    TWENTY(0.20),
    THIRTY(0.30),
	FIFTY(0.50),
	SIXTY(0.60);
	
    private final double percentage;

    PercentagesUtil(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }
}
