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

    final int CUP_PRICE = 5;
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice() {
        int price = quantity * CUP_PRICE;
        return price;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view){
        int price = calculatePrice();
        String priceMessage = "Total: " + Integer.toString(price) + " €\nThank you!";
        displayMessage(priceMessage);
    }

    /**
     * This method increments the amount of coffees ordered.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method decrements the amount of coffees ordered.
     */
    public void decrement(View view) {
        if (quantity > 0) {
            quantity = quantity - 1;
        }
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     *
     * @param numberCoffees number of coffees that are being ordered.
     */
    private void displayQuantity(int numberCoffees){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(Integer.toString(numberCoffees));
    }

    /**
     * This method displays the given price on the screen.
     *
     * @param number it's the price to be displayed.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(Integer.toString(number));
    }

    /**
     * This method displays the given text on the screen.
     *
     * @param message message to be displayed.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    /**
     * This method resets the quantity to zero and displays it.
     */
    public void reset(View view) {
        quantity = 0;
        displayQuantity(quantity);

        int price = calculatePrice();
        String priceMessage = "Total: " + Integer.toString(price) + " €\nThank you!";
        displayMessage(priceMessage);
    }
}
