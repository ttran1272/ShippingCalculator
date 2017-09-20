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

    private EditText weightEditText;
    public NumberFormat money = NumberFormat.getCurrencyInstance();
    private ShipItem shippingCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shippingCost = new ShipItem(58.0);
        setContentView(R.layout.activity_main);

        weightEditText = (EditText) findViewById(R.id.weightEditText);
        weightEditText.requestFocus();

        weightEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                weightEditText.clearFocus();
                weightEditText.requestFocus();
            }
        });

        TextChangeHandler tch = new TextChangeHandler();
        weightEditText.addTextChangedListener(tch);

    }

    public void calculate() {
        String weightString = weightEditText.getText().toString();
        TextView baseCostTextView = (TextView) findViewById(R.id.baseCostTextView);
        TextView addedCostTextView = (TextView) findViewById(R.id.addedCostTextView);
        TextView totalCostTextView = (TextView) findViewById(R.id.totalCostTextView);

        try {
            double weightAmount = Double.parseDouble(weightString);
            shippingCost.setWeight(weightAmount);

            // ask Model to calculate the added cost and total cost
            double baseCost = shippingCost.getBASECOSTPERWEIGHT();
            double addedCost = shippingCost.getTotalAddedCost();
            double totalCost = shippingCost.calTotalCost();

            // update the View with the new value in currency
            baseCostTextView.setText(money.format(baseCost));
            addedCostTextView.setText(money.format(addedCost));
            totalCostTextView.setText(money.format(totalCost));
        }
        catch (NumberFormatException e){
            Log.w("MainActivity.java" , "Number format exception");
        }

    }


    private class TextChangeHandler implements TextWatcher {

        public void afterTextChanged(Editable e){

        }

        public void beforeTextChanged( CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged (CharSequence s, int start, int before, int after){
            calculate();
        }
    }
}
