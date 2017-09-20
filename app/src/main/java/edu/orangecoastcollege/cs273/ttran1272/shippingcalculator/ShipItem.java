package edu.orangecoastcollege.cs273.ttran1272.shippingcalculator;


/**
 * Created by ttran1272 on 9/19/2017.
 * A class that calculate the cost to ship a package, given base cost, weight, and additional cost
 */

public class ShipItem {

    private final double BASE_COST_PER_WEIGHT = 3.00;
    private final int BASE_WEIGHT = 16;
    private final int STEP_WEIGHT = 4;

    // Create instance variables
    private double weight;
    private double remainingWeight;
    private double addedCostPerWeight;
    private double totalAddedCost;
    private double totalCost;

    public ShipItem(){
        weight = 0.0;
        remainingWeight = 0.0;
        addedCostPerWeight = 0.0;
        totalAddedCost = 0.0;
        totalCost = 0.0;
    }

    public double getBaseCostPerWeight() {
        return BASE_COST_PER_WEIGHT;
    }

    public double getWeight() {
        return weight;
    }

    public double getRemainingWeight() {
        return remainingWeight;
    }

    public double getAddedCostPerWeight() {
        return addedCostPerWeight;
    }

    public double getTotalAddedCost() {
        return totalAddedCost;
    }

    public double getTotalCost() {
        return totalCost;
    }


    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setAddedCostPerWeight(double addedCostPerWeight) {
        this.addedCostPerWeight = addedCostPerWeight;
    }

    public void setTotalAddedCost(double totalAddedCost) {
        this.totalAddedCost = totalAddedCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    /**
     *  All items weighing more than 16 ounces will have an added charge of $0.50
     * for each additional 4 ounces (about 16)
     * @param rWeight: the weight above the base weight (16 ounces)
     * @return the total cost calculated for the weight above the base weight.
     * If the remaining weight is greater than the base weight, the function is recalled
     */

    private double calTotalAddedCost(double rWeight) {
        addedCostPerWeight += 0.5;

        if (rWeight <= STEP_WEIGHT){
            totalAddedCost += rWeight * (BASE_COST_PER_WEIGHT + addedCostPerWeight);
        }
        else{
            totalAddedCost += STEP_WEIGHT * (BASE_COST_PER_WEIGHT + addedCostPerWeight);
            rWeight -= STEP_WEIGHT;
            calTotalAddedCost(rWeight);
        }


        return totalAddedCost;
    }



    /**
     * Calculate the total cost to ship a package
     * For the first 16 ounces: $3.00 / ounce
     * All items weighing more than 16 ounces: call the method calTotalAddedCost to calculate
     * the cost for each additional 4 ounces (above 16)
     * @return totalCost : including the base cost for the first 16 ounce plus the added cost for
     * the weight above the 16 ounces
     */

    public void calTotalCost() {
        double addedCostAmt ;
        setTotalAddedCost(0.0);

        if (weight <= BASE_WEIGHT){
            totalCost = BASE_COST_PER_WEIGHT * weight;
        }
        else {
            remainingWeight = weight - BASE_WEIGHT;
            addedCostAmt = calTotalAddedCost(remainingWeight);
            totalCost = BASE_COST_PER_WEIGHT * BASE_WEIGHT + addedCostAmt;
        }

    }
}
