package com.pz.justjavapz;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import org.w3c.dom.Text;
import java.text.NumberFormat;


/**
 * This app displays and order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    final int CUP_PRICE = 5;
    final int WHIPPED_CREAM_PRICE = 1;
    final int CHOCOLATE_PRICE = 2;
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(Bundle out){
        super.onSaveInstanceState(out);

        out.putInt("quantity", quantity);

        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        out.putString("quantity_textView", quantityTextView.getText().toString());

        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        out.putString("orderSummary_TextView", orderSummaryTextView.getText().toString());

        EditText editText = findViewById(R.id.edit_text);
        out.putString("editText", editText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle in){
        super.onRestoreInstanceState(in);

        quantity = in.getInt("quantity");

        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(in.getString("quantity_textView"));

        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(in.getString("orderSummary_TextView"));

        EditText editText = findViewById(R.id.edit_text);
        editText.setText(in.getString("editText"));

    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @param add_whipped_cream true if the order has whipped cream
     * @param add_chocolate true if the order has chocolate
     * @return the price
     */
    private int calculatePrice(boolean add_whipped_cream, boolean add_chocolate) {

        int unitPrice = CUP_PRICE;

        if (add_whipped_cream) {
            unitPrice += WHIPPED_CREAM_PRICE;
        }

        if (add_chocolate) {
            unitPrice += CHOCOLATE_PRICE;
        }

        return quantity * unitPrice;
    }

    /**
     * Creates a summary of the current order.
     *
     * @param price the amount to pay for the coffees.
     * @param name name of the person buying the coffees.
     * @param whippedCream boolean specifying if the coffee contains whipped cream or not.
     * @return text summary of the specified order.
     */
    private String createOrderSummary(int price, String name,
                                      boolean whippedCream, boolean chocolate)
    {
        String summary = "";
        summary += "Name: " + name;
        summary += "\nWhipped Cream: " + Boolean.toString(whippedCream);
        summary += "\nChocolate: " + Boolean.toString(chocolate);
        summary += "\nQuantity: " + Integer.toString(quantity);
        summary += "\nTotal: " + Integer.toString(price) + " €";
        summary += "\nThank you!";
        return summary;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view){

        CheckBox whipped_cream_checkBox = findViewById(R.id.whipped_cream_checkbox);
        boolean whippedCream = whipped_cream_checkBox.isChecked();

        CheckBox chocolate_checkBox= findViewById(R.id.chocolate_checkbox);
        boolean chocolate = chocolate_checkBox.isChecked();

        EditText editText = findViewById(R.id.edit_text);
        String name = editText.getText().toString();
        if (name.isEmpty()) {
            name = "None";
        }

        int price = calculatePrice(whippedCream, chocolate);

        displayMessage(createOrderSummary(price, name, whippedCream, chocolate));
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

        String priceMessage = createOrderSummary(0, "None", false, false);
        displayMessage(priceMessage);

        CheckBox whipped_cream_checkBox = findViewById(R.id.whipped_cream_checkbox);
        whipped_cream_checkBox.setChecked(false);

        CheckBox chocolate_checkBox = findViewById(R.id.chocolate_checkbox);
        chocolate_checkBox.setChecked(false);

        EditText editText = findViewById(R.id.edit_text);
        editText.setText("");
        editText.setHint("Name");
    }
}
