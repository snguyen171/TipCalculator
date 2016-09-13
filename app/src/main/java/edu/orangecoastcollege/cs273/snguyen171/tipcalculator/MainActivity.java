package edu.orangecoastcollege.cs273.snguyen171.tipcalculator;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static NumberFormat percent = NumberFormat.getPercentInstance();

    // Associate the controller with the needed views
    private EditText amountEditText;
    private TextView amountTextView;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalAmountTextView;
    private SeekBar percentSeekBar;

    // Associate the controller with the needed model
    RestaurantBill currentBill = new RestaurantBill();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect the controller with the widgets in our app
        amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountTextView = (TextView) findViewById(R.id.amountTextView);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        totalAmountTextView = (TextView) findViewById(R.id.totalTextView);
        percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);

        // Define a listener for the amountEditText (onTextChanged)
        amountEditText.addTextChangedListener(amountTextChangeListener);

        // Define a listener for the percentSeekBar (onProgressChanged)
        percentSeekBar.setOnSeekBarChangeListener(percentChangedListener);

        // Let's define a default tip percent
        currentBill.setTipPercent(0.15);
    }

    private TextWatcher amountTextChangeListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Do nothing
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Try to get the amount from amountEditText;
            try {
                if(charSequence.toString().isEmpty())
                {
                    currentBill.setAmount(0.0);
                }
                else
                {
                    double amount = Double.parseDouble(charSequence.toString()) / 100.0;
                    currentBill.setAmount(amount);
                }
            }
            catch (NumberFormatException e)
            {
                amountEditText.setText("");
            }



            // No exception, input is valid:
            // 1) Set the bill amount (amountTextView)
            amountTextView.setText(currency.format(currentBill.getAmount()));
            updateViews();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // Do nothing
        }
    };

    private SeekBar.OnSeekBarChangeListener percentChangedListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            // Update the model with the new tip amount
            currentBill.setTipPercent(i / 100.0);

            // Update the percentTextView
            percentTextView.setText(percent.format(currentBill.getTipPercent()));

            // Update the views
            updateViews();


        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // Do nothing
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // Do nothing
        }
    };

    private void updateViews()
    {
        // 2) Set the tip amount (tipAmountTextView)
        tipTextView.setText(currency.format(currentBill.getTipAmount()));
        // 3) Set the total amount (totalAmountTextView)
        totalAmountTextView.setText(currency.format(currentBill.getTotalAmount()));
    }
}
