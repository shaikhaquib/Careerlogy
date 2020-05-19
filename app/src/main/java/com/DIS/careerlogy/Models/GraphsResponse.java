package com.DIS.careerlogy.Models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GraphsResponse{

	@SerializedName("MainGraph")
	private List<MainGraphItem> mainGraph;

	@SerializedName("CategoryGraph")
	private List<CategoryGraphItem> categoryGraph;

	public void setMainGraph(List<MainGraphItem> mainGraph){
		this.mainGraph = mainGraph;
	}

	public List<MainGraphItem> getMainGraph(){
		return mainGraph;
	}

	public void setCategoryGraph(List<CategoryGraphItem> categoryGraph){
		this.categoryGraph = categoryGraph;
	}

	public List<CategoryGraphItem> getCategoryGraph(){
		return categoryGraph;
	}

	@Override
 	public String toString(){
		return 
			"GraphsResponse{" + 
			"mainGraph = '" + mainGraph + '\'' + 
			",categoryGraph = '" + categoryGraph + '\'' + 
			"}";
		}
}