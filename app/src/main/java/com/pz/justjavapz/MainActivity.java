package com.pz.justjavapz;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.w3c.dom.Text;
import java.text.NumberFormat;


/**
 * This app displays and order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view){
        int quantity = 2;
        display(quantity);
        displayPrice(quantity* 5);
    }

    /**
     * This method increments the amount of coffees ordered.
     */
    public void increment(View view) {
        int quantity = 2;
        quantity = 3;
        display(quantity);
        displayPrice(quantity * 5);
    }

    /**
     * This method decrements the amount of coffees ordered.
     */
    public void decrement(View view) {
        int quantity = 2;
        quantity = 1;
        display(quantity);
        displayPrice(quantity * 5);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(Integer.toString(number));
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(Integer.toString(number) + "€");
    }

}
