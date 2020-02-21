package com.dan.navigationapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuModel {

    @SerializedName("menu")
    @Expose
    private List<MenuItem> menu;


    public List<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuItem> menu) {
        this.menu = menu;
    }



    public class MenuItem {

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("function")
        @Expose
        private String function;

        @SerializedName("param")
        @Expose
        private String param;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFunction() {
            return function;
        }

        public void setFunction(String function) {
            this.function = function;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }
    }

}
