package nl.hro.marc_zijderveld.groceriebuddy;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import nl.hro.marc_zijderveld.groceriebuddy.app.AppConfig;

public class SettingsActivity extends AppCompatActivity {

    @Override
    //Build up the class and check the boolean variable in the prefs.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        boolean nonfood = getSharedPreferences("groceries", MODE_PRIVATE).getBoolean("nonfood", false);

        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);

        checkBox.setChecked(nonfood);
    }

    //Check the nonfood boolean on resume.
    public void onResume()
    {
        super.onResume();

        boolean nonfood = getSharedPreferences("groceries", MODE_PRIVATE).getBoolean("nonfood", false);

        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);

        checkBox.setChecked(nonfood);
    }

    //When clicking on the checkbox check if its checked or unchecked and set the values in the preferences.
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        Log.d("clicked", "clicked");

        SharedPreferences.Editor editor = getSharedPreferences("groceries", MODE_PRIVATE).edit();

        if (checked)
        {
            editor.putBoolean("nonfood", true);
            Log.d("checked", "true");
        }
        else
        {
            editor.putBoolean("nonfood", false);
            Log.d("checked", "false");
        }
        editor.commit();
    }
}
