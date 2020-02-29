package com.mmo.careerlogy.Models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CitiesModel{

	@SerializedName("CitiesInState")
	private List<CitiesInStateItem> citiesInState;

	public void setCitiesInState(List<CitiesInStateItem> citiesInState){
		this.citiesInState = citiesInState;
	}

	public List<CitiesInStateItem> getCitiesInState(){
		return citiesInState;
	}

	@Override
 	public String toString(){
		return 
			"CitiesModel{" + 
			"citiesInState = '" + citiesInState + '\'' + 
			"}";
		}
}