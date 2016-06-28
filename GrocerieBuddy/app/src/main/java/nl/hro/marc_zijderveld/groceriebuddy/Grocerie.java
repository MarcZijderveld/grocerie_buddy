package nl.hro.marc_zijderveld.groceriebuddy;

import java.io.Serializable;

/**
 * Created by mwz_2 on 6/28/2016.
 */
public class Grocerie implements Serializable{

    public String   name,
                    description,
                    image;

    public Shop     shop;

    public double   price;
}
