package nl.hro.marc_zijderveld.groceriebuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nl.hro.marc_zijderveld.groceriebuddy.app.AppConfig;
import nl.hro.marc_zijderveld.groceriebuddy.app.AppController;

///The main activity is the screen where all the groceries or products get downloaded from the API and displayed in the list view.
public class MainActivity extends AppCompatActivity
{
    public ArrayList<Grocerie> groceries;
    private ArrayList<String> grocerieStrings;

    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groceries = new ArrayList<Grocerie>();

        grocerieStrings = new ArrayList<String>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, grocerieStrings);

        ListView lv = (ListView)findViewById(R.id.grocerieList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent= new Intent(MainActivity.this,DetailActivity.class);

                Grocerie g = groceries.get(position);

                intent.putExtra("grocerie", g);
                startActivity(intent);
            }
        });

        LoadGroceries();
    }

    private void LoadGroceries() {
        // Tag used to cancel the request
        String tag_string_req = "req_events";

        groceries.clear();
        grocerieStrings.clear();

        boolean nonfood = getSharedPreferences("groceries", MODE_PRIVATE).getBoolean("nonfood", false);

        String URL = "";

        if(nonfood)
            URL = AppConfig.ULR_GROCERIES_NONFOOD;
        else
            URL = AppConfig.ULR_GROCERIES;

        StringRequest strReq = new StringRequest(Request.Method.GET,
                URL, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("", "Grocerie Response: " + response.toString());

                try {
                    JSONObject jObject = new JSONObject(response);

                    JSONArray jArray = jObject.getJSONArray("products");

                    for (int i = 0; i < jArray.length(); i++) {  // line 2
                        JSONObject childJSONObject = jArray.getJSONObject(i);

                        Grocerie g = new Grocerie();

                        g.name = childJSONObject.getString("name");
                        g.price = childJSONObject.getDouble("price");
                        g.description = childJSONObject.getString("description");
                        g.image = childJSONObject.getString("image");

                        Shop s = new Shop();
                        s.location = new Location();

                        JSONObject shopObj = childJSONObject.getJSONObject("shop");

                        s.name = shopObj.getString("name");

                        JSONObject location = shopObj.getJSONObject("location");

                        s.location.latitude = location.getInt("latitude");
                        s.location.longitude = location.getInt("longitude");

                        g.shop = s;

                        groceries.add(g);

                        grocerieStrings.add(g.name + " \n € " + g.price);

                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("" , "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();;
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();

                AppConfig.preferenceSettings = getSharedPreferences(AppConfig.PREFERENCE_NAME, AppConfig.PREFERENCE_MODE_PRIVATE);
                final String allowedCrownStone = AppConfig.preferenceSettings.getString("crownstone", "default");

                //params.put("alarm_id", allowedCrownStone);

                return params;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent= new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);

                return true;
            case R.id.refresh:
                LoadGroceries();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

