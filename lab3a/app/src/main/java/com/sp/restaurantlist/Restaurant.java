package com.sp.restaurantlist;

public class Restaurant {
    public String RestaurantName = "";
    public String RestaurantAddress = "";
    public String RestaurantTel = "";
    public String RestaurantType = "";

    public String getname(){return RestaurantName;}
    public void setName(String RestaurantName){this.RestaurantName = RestaurantName; }
    public String getAddress(){return RestaurantAddress;}
    public void setAddress(String RestaurantAddress){this.RestaurantAddress = RestaurantAddress; }
    public String getTel(){return RestaurantTel;}
    public void setTel(String RestaurantTel){this.RestaurantTel = RestaurantTel; }
    public String getType(){return RestaurantType;}
    public void setType(String RestaurantType){this.RestaurantType = RestaurantType; }

    @Override
    public String toString(){return (getname());}
}
