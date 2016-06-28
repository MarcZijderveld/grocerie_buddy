package nl.hro.marc_zijderveld.groceriebuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import nl.hro.marc_zijderveld.groceriebuddy.app.AppConfig;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        boolean nonfood = getSharedPreferences("groceries", MODE_PRIVATE).getBoolean("nonfood", false);

        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);

        checkBox.setChecked(nonfood);
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox:

                Log.d("clicked", "");

                if (checked)
                {
                    getSharedPreferences("groceries", MODE_PRIVATE).edit().putBoolean("nonfood", true);
                }
                else
                {
                    getSharedPreferences("groceries", MODE_PRIVATE).edit().putBoolean("nonfood", false);
                }
                // Remove the meat
                break;
        }
    }
}
