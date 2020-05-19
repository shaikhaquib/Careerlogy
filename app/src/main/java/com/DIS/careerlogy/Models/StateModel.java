package com.DIS.careerlogy.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StateModel {


    @SerializedName("StateList")
    private List<StateListItem> stateList;

    public void setStateList(List<StateListItem> stateList){
        this.stateList = stateList;
    }

    public List<StateListItem> getStateList(){
        return stateList;
    }

    @Override
    public String toString(){
        return
                "StateModel{" +
                        "stateList = '" + stateList + '\'' +
                        "}";
    }

    public class StateListItem{

        @SerializedName("statename")
        private String statename;

        public void setStatename(String statename){
            this.statename = statename;
        }

        public String getStatename(){
            return statename;
        }

        @Override
        public String toString(){
            return
                    "StateListItem{" +
                            "statename = '" + statename + '\'' +
                            "}";
        }
    }
}


