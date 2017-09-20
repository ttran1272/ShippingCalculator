package edu.orangecoastcollege.cs273.ttran1272.shippingcalculator;

/**
 * Created by ttran1272 on 9/19/2017.
 */

public class ShipItem {

    private final double BASECOSTPERWEIGHT;
    private final int BASEWEIGHT;
    private final int STEPWEIGHT;

    private double weight;
    private double totalCost;
    private double addedCost;
    private double totalAddedCost;
    private double remainingWeight;

    /**
     * Constructor
     * @param weight
     */
    public ShipItem(double weight) {
        BASECOSTPERWEIGHT = 3.00;
        BASEWEIGHT = 16;
        STEPWEIGHT = 4;

        this.weight = weight;
        addedCost = 0.0;
        remainingWeight = 0.0;
        totalCost = 0.0;
        totalAddedCost = 0.0;
    }

    public double getBASECOSTPERWEIGHT() {
        return BASECOSTPERWEIGHT;
    }

    public double getWeight() {
        return weight;
    }

    public int getBASEWEIGHT() {
        return BASEWEIGHT;
    }

    public int getSTEPWEIGHT() {
        return STEPWEIGHT;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getAddedCost() {
        return addedCost;
    }

    public double getRemainingWeight() {
        return remainingWeight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double calAddedCostAmt(double rWeight) {

        if (rWeight <= STEPWEIGHT){
            addedCost += 0.5;
            totalAddedCost += rWeight * addedCost;
        }
        else{
            totalAddedCost += STEPWEIGHT * addedCost;
            rWeight -= STEPWEIGHT;
            calAddedCostAmt(rWeight);
        }

        return totalAddedCost;
    }

    public double getTotalAddedCost() {
        return totalAddedCost;
    }

    public double calTotalCost() {
        double addedCostAmt ;
        if (weight <= BASEWEIGHT){
            totalCost = BASECOSTPERWEIGHT * weight;
        }
        else {
            remainingWeight = weight - BASEWEIGHT;
            addedCostAmt = calAddedCostAmt(remainingWeight);
            totalCost = BASECOSTPERWEIGHT * BASEWEIGHT + addedCostAmt;
        }

        return totalCost;
    }
}
