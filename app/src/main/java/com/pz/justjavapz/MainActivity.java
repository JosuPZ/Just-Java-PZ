package com.pz.justjavapz;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


/**
 * This app displays and order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    final int CUP_PRICE = 5;
    final int WHIPPED_CREAM_PRICE = 1;
    final int CHOCOLATE_PRICE = 2;
    int quantity = 1;

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
     * Sends email with current order
     *
     * @param adresses adresses that will receive the email
     * @param subject subject of the email
     * @param summary is the body of the email
     */
    private void sendEmail(String [] adresses, String subject, String summary) {
        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:coffee.pz@gmail.com"));
        email.putExtra(Intent.EXTRA_EMAIL, adresses);
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, summary);

        if (email.resolveActivity(getPackageManager()) != null) {
            startActivity(email);
        }
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

        summary += getString(R.string.name) + ": " + name;

        if (whippedCream) {
            summary += "\n" + getString(R.string.whipped_cream) + ": " + getString(R.string.yes);
        }
        else {
            summary += "\n" + getString(R.string.whipped_cream) + ": " + getString(R.string.no);
        }

        if (chocolate) {
            summary += "\n" + getString(R.string.chocolate) + ": " + getString(R.string.yes);
        }
        else {
            summary += "\n" + getString(R.string.chocolate) + ": " + getString(R.string.no);
        }

        summary += "\n" + getString(R.string.quantity) + ": " + Integer.toString(quantity);
        summary += "\n" + getString(R.string.total) + ": " + Integer.toString(price) + " €";
        summary += "\n" + getString(R.string.ty);
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
            name = getString(R.string.default_name);
        }

        int price = calculatePrice(whippedCream, chocolate);

        String summary = createOrderSummary(price, name, whippedCream, chocolate);

        displayMessage(summary);

        sendEmail(new String[]{getString(R.string.e_adress)}, getString(R.string.e_subject, name),  summary);
    }

    /**
     * This method is called when the preview button is clicked.
     */
    public void preview(View view){

        CheckBox whipped_cream_checkBox = findViewById(R.id.whipped_cream_checkbox);
        boolean whippedCream = whipped_cream_checkBox.isChecked();

        CheckBox chocolate_checkBox= findViewById(R.id.chocolate_checkbox);
        boolean chocolate = chocolate_checkBox.isChecked();

        EditText editText = findViewById(R.id.edit_text);
        String name = editText.getText().toString();
        if (name.isEmpty()) {
            name = getString(R.string.default_name);
        }

        int price = calculatePrice(whippedCream, chocolate);

        String summary = createOrderSummary(price, name, whippedCream, chocolate);

        displayMessage(summary);
    }

    /**
     * This method increments the amount of coffees ordered.
     */
    public void increment(View view) {
        if (quantity < 99) {
            quantity = quantity + 1;
        }
        else {
            Toast.makeText(getApplicationContext(), getString(R.string.too_much_coffee), Toast.LENGTH_LONG).show();
        }
        displayQuantity(quantity);
    }

    /**
     * This method decrements the amount of coffees ordered.
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
        }
        else {
            Toast.makeText(getApplicationContext(), getString(R.string.very_little_coffee), Toast.LENGTH_SHORT).show();
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

        String priceMessage = createOrderSummary(0, getString(R.string.default_name), false, false);
        displayMessage(priceMessage);

        CheckBox whipped_cream_checkBox = findViewById(R.id.whipped_cream_checkbox);
        whipped_cream_checkBox.setChecked(false);

        CheckBox chocolate_checkBox = findViewById(R.id.chocolate_checkbox);
        chocolate_checkBox.setChecked(false);

        EditText editText = findViewById(R.id.edit_text);
        editText.setText("");
        editText.setHint(getString(R.string.name));
    }
}
