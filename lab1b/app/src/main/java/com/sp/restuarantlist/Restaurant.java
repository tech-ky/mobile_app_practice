package com.sp.restuarantlist;

public class Restaurant{
    private String restaurantName ="";
    private String restaurantAddress ="";
    private String restaurantTel ="";
    private String restaurantType ="";

    public String getName(){return restaurantName;}
    public void setName(String restaurantName){this.restaurantName = restaurantName;}
    public String getAddress(){return restaurantAddress;}
    public void setAddress(String restaurantAddress){this.restaurantAddress = restaurantAddress;}
    public String getTel(){return restaurantTel;}
    public void setTel(String restaurantTel){this.restaurantTel = restaurantTel;}
    public String getType(){return restaurantType;}
    public void setType(String restaurantType){this.restaurantType = restaurantType;}

    public String toString(){
        return (getName());
    }
}