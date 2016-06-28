package nl.hro.marc_zijderveld.groceriebuddy;

import java.io.Serializable;

//Location class which is serializable for the API data.
public class Location implements Serializable {
    public int   latitude,
                    longitude;
}
