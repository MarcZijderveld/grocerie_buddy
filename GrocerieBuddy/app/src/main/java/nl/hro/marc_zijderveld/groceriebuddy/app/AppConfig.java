package nl.hro.marc_zijderveld.groceriebuddy.app;

import android.content.SharedPreferences;

/**
 * Created by Sleepyz on 6/1/2016.
 */
public class AppConfig {

    public static String ULR_GROCERIES = "http://docent.cmi.hro.nl/bootb/service/products";

    public static SharedPreferences preferenceSettings;
    public static SharedPreferences.Editor preferenceEditor;

    public static final int PREFERENCE_MODE_PRIVATE = 0;

    public static String PREFERENCE_NAME = "groceries";
}
