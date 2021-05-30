/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products;

import classes.Product;
import preferenceEnums.DietaryRestrictions;
import preferenceEnums.Storage;
import typeOfEnums.TypeOfLegume;

/**
 *
 * @author carlac
 */
  class Legume  extends Product{
    
    private TypeOfLegume typeOfLegume;

	public Legume(short caloricDensity, short carbs, short proteins, short fats, String productName, Short weight,
			Storage storage, boolean vegan, boolean halal, boolean highProtein, boolean lowCarb,
			TypeOfLegume typeOfLegume) {
		super(caloricDensity, carbs, proteins, fats, productName, weight, storage, vegan, halal, highProtein, lowCarb);
		this.typeOfLegume = typeOfLegume;
	}

  
}
