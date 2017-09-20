package edu.orangecoastcollege.cs273.ttran1272.shippingcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    // Create variables
    private EditText weightEditText;
    public NumberFormat money = NumberFormat.getCurrencyInstance();
    private ShipItem shippingCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the ShipItem instance
        shippingCost = new ShipItem();

        setContentView(R.layout.activity_main);

        weightEditText = (EditText) findViewById(R.id.weightEditText);

        // Process the requestFocus from the EditText
        weightEditText.requestFocus();

        weightEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                weightEditText.clearFocus();
                weightEditText.requestFocus();
            }
        });

        // Add the textChangedListener to the weightEditText widget
        TextChangeHandler tch = new TextChangeHandler();
        weightEditText.addTextChangedListener(tch);

    }

    /**
     * This function gets the text changed in the weightEditText
     * and use the ShipItem class to calculate the shipping cost
     * Then update the TextViews for base cost, added cost, and total cost with the updated values
     */
    public void calculate() {
        String weightString = weightEditText.getText().toString();
        TextView baseCostTextView = (TextView) findViewById(R.id.baseCostTextView);
        TextView addedCostTextView = (TextView) findViewById(R.id.addedCostTextView);
        TextView totalCostTextView = (TextView) findViewById(R.id.totalCostTextView);

        try {
            double weightAmount = Double.parseDouble(weightString);
            shippingCost.setTotalCost(0.0);
            shippingCost.setAddedCostPerWeight(0.0);
            shippingCost.setWeight(weightAmount);
            shippingCost.calTotalCost();

            // ask Model to calculate the added cost and total cost
            double baseCost = shippingCost.getBaseCostPerWeight();
            double addedCost = shippingCost.getAddedCostPerWeight();
            double totalCost = shippingCost.getTotalCost();

            // update the View with the new value in currency
            baseCostTextView.setText(money.format(baseCost));
            addedCostTextView.setText(money.format(addedCost));
            totalCostTextView.setText(money.format(totalCost));
        }
        catch (NumberFormatException e){
            Log.w("MainActivity.java" , "Number format exception");
        }

    }


    /**
     * This function handles the text changed in the EditText
     */
    private class TextChangeHandler implements TextWatcher {

        public void onTextChanged (CharSequence s, int start, int before, int after){
            calculate();
        }

        public void afterTextChanged(Editable e){

        }

        public void beforeTextChanged( CharSequence s, int start, int count, int after) {

        }


    }
}
