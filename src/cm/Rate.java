package cm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CM on 01/02/2018.
 */
public class Rate {
    private CarParkKind kind;
    private BigDecimal hourlyNormalRate;
    private BigDecimal hourlyReducedRate;
    private ArrayList<Period> reduced = new ArrayList<>();
    private ArrayList<Period> normal = new ArrayList<>();

    public Rate(CarParkKind kind, BigDecimal normalRate, BigDecimal reducedRate, ArrayList<Period> reducedPeriods
            , ArrayList<Period> normalPeriods) {
        if (reducedPeriods == null || normalPeriods == null) {
            throw new IllegalArgumentException("periods cannot be null");
        }
        if (normalRate == null || reducedRate == null) {
            throw new IllegalArgumentException("The rates cannot be null");
        }
        if (normalRate.compareTo(BigDecimal.ZERO) < 0 || reducedRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("A rate cannot be negative");
        }
        if (normalRate.compareTo(reducedRate) <= 0) {
            throw new IllegalArgumentException("The normal rate cannot be less or equal to the reduced rate");
        }
        if (!isValidPeriods(reducedPeriods) || !isValidPeriods(normalPeriods)) {
            throw new IllegalArgumentException("The periods are not valid individually");
        }
        if (!isValidPeriods(reducedPeriods, normalPeriods)) {
            throw new IllegalArgumentException("The periods overlaps");
        }
        this.kind = kind;
        this.hourlyNormalRate = normalRate;
        this.hourlyReducedRate = reducedRate;
        this.reduced = reducedPeriods;
        this.normal = normalPeriods;
    }

    /**
     * Checks if two collections of periods are valid together
     * @param periods1
     * @param periods2
     * @return true if the two collections of periods are valid together
     */
    private boolean isValidPeriods(ArrayList<Period> periods1, ArrayList<Period> periods2) {
        Boolean isValid = true;
        int i = 0;
        while (i < periods1.size() && isValid) {
            isValid = isValidPeriod(periods1.get(i), periods2);
            i++;
        }
        return isValid;
    }

    /**
     * checks if a collection of periods is valid
     * @param list the collection of periods to check
     * @return true if the periods do not overlap
     */
    private Boolean isValidPeriods(ArrayList<Period> list) {
        Boolean isValid = true;
        if (list.size() >= 2) {
            Period secondPeriod;
            int i = 0;
            int lastIndex = list.size()-1;
            while (i < lastIndex && isValid) {
                isValid = isValidPeriod(list.get(i), ((List<Period>)list).subList(i + 1, lastIndex+1));
                i++;
            }
        }
        return isValid;
    }

    /**
     * checks if a period is a valid addition to a collection of periods
     * @param period the Period addition
     * @param list the collection of periods to check
     * @return true if the period does not overlap in the collecton of periods
     */
    private Boolean isValidPeriod(Period period, List<Period> list) {
        Boolean isValid = true;
        int i = 0;
        while (i < list.size() && isValid) {
            isValid = !period.overlaps(list.get(i));
            i++;
        }
        return isValid;
    }

    public BigDecimal calculate(Period periodStay) {
        BigDecimal price;
        BigDecimal free = BigDecimal.valueOf(0.0);
        //Double d = new Double("0.5");
        BigDecimal g = BigDecimal.valueOf(0.5);
        //BigDecimal c = BigDecimal.valueOf(0.25);
        //BigDecimal freeBigDecimal = new BigDecimal(8);
        if (periodStay == null)      //THIS FIXES THE BUG.
            throw new IllegalArgumentException("The periodStay cannot null."); //THIS FIXES THE BUG
        int normalRateHours = periodStay.occurences(normal);
        int reducedRateHours = periodStay.occurences(reduced);
        // return (this.hourlyNormalRate.multiply(BigDecimal.valueOf(normalRateHours))).add(
        // this.hourlyReducedRate.multiply(BigDecimal.valueOf(reducedRateHours)));
        BigDecimal payment = this.hourlyNormalRate.multiply(BigDecimal.valueOf(normalRateHours)).add(
                this.hourlyReducedRate.multiply(BigDecimal.valueOf(reducedRateHours)));
        System.out.println(payment.doubleValue());


        //IF KIND IS A STAFF
        if (kind == CarParkKind.STAFF) {
            BigDecimal tempBigDecimal = new BigDecimal(16);
            if (payment.compareTo(new BigDecimal(16)) == 1)  // MEANS PAYMENT IS GREATER THAN 16
                return tempBigDecimal;
            else
                return payment;
        } //END OF IF STATEMENT FOR STAFF.


        //IF KIND IS A STUDENT
        if(kind == CarParkKind.STUDENT){
            BigDecimal tempBigDecimal;
            BigDecimal reduction;
            tempBigDecimal = new BigDecimal(5.5);
            reduction = new BigDecimal(0.25);
            if(payment.compareTo(tempBigDecimal) == 1)  // MEANS PAYMENT IS GREATER THAN  5.5
                  return payment.subtract(payment.multiply(reduction));
            else
                return payment;
        } //END OF IF STATEMENT FOR STUDENT

        //return null;

        //IF KIND IS A VISITOR
        if(kind == CarParkKind.VISITOR){
            BigDecimal tempBigDecimal;
            BigDecimal reduction;
            tempBigDecimal = new BigDecimal(8);
            if(payment.compareTo(tempBigDecimal) == 1)  // MEANS PAYMENT IS GREATER THAN 8
            {
                reduction = payment.subtract(new BigDecimal(8));
                return reduction.multiply(g);
            }
            else
                return new BigDecimal(0);
        } //END OF IF STATEMENT FOR VISITOR

        return null;



    } //END OF calculate METHOD.

} //END OF RATE CLASS
