package nl.hro.marc_zijderveld.groceriebuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private Grocerie g;

    @Override
    //Creates the detail view and fills all the appointed text fields with the data from the Grocery class retrieved from the main activity.
    //Also defines the onclick function for the map button at the bottom of the activity.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        g = (Grocerie) getIntent().getSerializableExtra("grocerie");

        final TextView titleText = (TextView) findViewById(R.id.title);
        titleText.setText(g.name);

        final TextView descriptionText = (TextView) findViewById(R.id.description);
        descriptionText.setText(g.description);

        final TextView priceText = (TextView) findViewById(R.id.price);
        priceText.setText("€ " + g.price);

        final TextView shopText = (TextView) findViewById(R.id.shop);
        shopText.setText(g.shop.name);

        Button mButton = (Button) findViewById(R.id.googlemaps);

        mButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent= new Intent(DetailActivity.this, MapActivity.class);

                intent.putExtra("shop", g.shop);
                startActivity(intent);
            }
        });
    }
}
