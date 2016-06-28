package nl.hro.marc_zijderveld.groceriebuddy;

import java.io.Serializable;

//Shop class which is serializable for the API data.
public class Shop implements Serializable {
    public String name;
    public Location location;
}
