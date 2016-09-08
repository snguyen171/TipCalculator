package edu.orangecoastcollege.cs273.snguyen171.tipcalculator;

/**
 * Created by snguyen171 on 9/8/2016.
 */
public class RestaurantBill {

    private double mAmount;
    private double mTipPercent;
    private double mTipAmount;
    private double mTotalAmount;

    public RestaurantBill(){
        mAmount = 0.0;
        mTipPercent = 0.0;
        mTipAmount = 0.0;
        mTotalAmount = 0.0;
    }

    public RestaurantBill(double mAmount, double mTipPercent) {
        this.mAmount = mAmount;
        this.mTipPercent = mTipPercent;
        recalculateAmounts();
    }

    public double getAmount() {
        return mAmount;
    }

    public double getTipPercent() {
        return mTipPercent;
    }

    public double getTipAmount() {
        return mTipAmount;
    }

    public double getTotalAmount() {
        return mTotalAmount;
    }

    public void setAmount(double mAmount) {
        this.mAmount = mAmount;
        recalculateAmounts();
    }

    public void setTipPercent(double mTipPercent) {
        this.mTipPercent = mTipPercent;
        recalculateAmounts();
    }

    private void recalculateAmounts()
    {
        mTipAmount = mAmount * mTipAmount;
        mTotalAmount = mAmount + mTipAmount;
    }
}
