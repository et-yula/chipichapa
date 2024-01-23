package Beans;


import Models.Coordinates;
import jakarta.ejb.Stateless;


import java.math.BigDecimal;

@Stateless
public class CoordinatesChecker {

    public Boolean check(Coordinates coordinates) {
        BigDecimal x = new BigDecimal(String.valueOf(coordinates.getX()).replace(',', '.'));
        BigDecimal y = new BigDecimal(String.valueOf(coordinates.getY()).replace(',', '.'));
        BigDecimal r = new BigDecimal(String.valueOf(coordinates.getR()).replace(',', '.'));

        if (r.compareTo(BigDecimal.ZERO) < 0) { //radius negative
            boolean circle = x.compareTo(BigDecimal.ZERO) <= 0
                    && y.compareTo(BigDecimal.ZERO) >= 0
                    && x.pow(2).add(y.pow(2)).compareTo(r.pow(2)) <= 0;

            boolean triangle = x.compareTo(BigDecimal.ZERO) <= 0
                    && y.compareTo(BigDecimal.ZERO) <= 0
                    && ((BigDecimal.valueOf(2).multiply(x)).add(y)).compareTo(r) >= 0;

            boolean rectangle = x.compareTo(BigDecimal.ZERO) >= 0 // x<0
                    && y.compareTo(BigDecimal.ZERO) <= 0 // y>0
                    && x.compareTo(BigDecimal.ZERO.subtract(r)) <= 0 // x>-r
                    && y.compareTo(r) >= 0; // y<r
            return circle || triangle || rectangle;
        } else { // radius positive
            boolean circle = x.compareTo(BigDecimal.ZERO) >= 0
                    && y.compareTo(BigDecimal.ZERO) <= 0
                    && x.pow(2).add(y.pow(2)).compareTo(r.pow(2)) <= 0;

            boolean triangle = x.compareTo(BigDecimal.ZERO) >= 0
                    && y.compareTo(BigDecimal.ZERO) >= 0
                    && ((BigDecimal.valueOf(2).multiply(x)).add(y)).compareTo(r) <= 0;

            boolean rectangle = x.compareTo(BigDecimal.ZERO) <= 0 // x<0
                    && y.compareTo(BigDecimal.ZERO) >= 0 // y>0
                    && x.compareTo(BigDecimal.ZERO.subtract(r)) >= 0 // x>-r
                    && y.compareTo(r) <= 0; // y<r
            return circle || triangle || rectangle;
        }
    }
}