package com.pz.justjavapz;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.w3c.dom.Text;
import java.text.NumberFormat;


/**
 * This app displays and order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    final int CUP_PRICE = 5;
    String name = "Josu PZ";
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
        return quantity * CUP_PRICE;
    }

    /**
     * Creates a summary of the current order.
     */
    private String createOrderSummary(int price, String name, boolean whippedCream) {
        String summary = "";
        summary += "Name: " + name;
        summary += "\nWhipped Cream: " + Boolean.toString(whippedCream);
        summary += "\nQuantity: " + Integer.toString(quantity);
        summary += "\nTotal: " + Integer.toString(price) + " €";
        summary += "\nThank you!";
        return summary;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view){
        int price = calculatePrice();

        CheckBox checkBox = findViewById(R.id.checkbox);
        boolean whippedCream = checkBox.isChecked();

        displayMessage(createOrderSummary(price, name, whippedCream));
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
     * This method displays the given text on the screen.
     *
     * @param message message to be displayed.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method resets the quantity to zero and displays it.
     */
    public void reset(View view) {
        quantity = 0;
        displayQuantity(quantity);

        int price = calculatePrice();
        String priceMessage = createOrderSummary(0, "None", false);
        displayMessage(priceMessage);

        CheckBox checkBox = findViewById(R.id.checkbox);
        checkBox.setChecked(false);
    }
}
