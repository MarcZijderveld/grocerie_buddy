package nl.hro.marc_zijderveld.groceriebuddy;

import java.io.Serializable;

//Grocerie class which is serializable for the API data.
public class Grocerie implements Serializable{

    public String   name,
                    description,
                    image;

    public Shop     shop;

    public double   price;
}
