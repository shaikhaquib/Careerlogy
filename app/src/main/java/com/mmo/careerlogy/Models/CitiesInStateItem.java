package com.mmo.careerlogy.Models;

import com.google.gson.annotations.SerializedName;

public class CitiesInStateItem{

	@SerializedName("cityname")
	private String cityname;

	@SerializedName("statename")
	private String statename;

	public void setCityname(String cityname){
		this.cityname = cityname;
	}

	public String getCityname(){
		return cityname;
	}

	public void setStatename(String statename){
		this.statename = statename;
	}

	public String getStatename(){
		return statename;
	}

	@Override
 	public String toString(){
		return 
			"CitiesInStateItem{" + 
			"cityname = '" + cityname + '\'' + 
			",statename = '" + statename + '\'' + 
			"}";
		}
}