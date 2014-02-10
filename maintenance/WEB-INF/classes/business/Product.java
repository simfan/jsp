package business;

import java.text.NumberFormat;

public class Product{
    private String code;
    private String title;
    private String artist;
    private String category;
    private String description;
    private double price;

    public Product(){
        code = "";
        title = "";
        artist = "";
        category = "";
        description = "";
        price = 0.0;
    }

    public Product(String code, String title, String artist,
                   String category, String description, double price){
        this.code = code;
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public void setCode(String c){
        code = c;
    }
    public String getCode(){ return code; }

    public void setTitle(String t){
        title = t;
    }
    public String getTitle(){ return title; }

    public void setArtist(String a){
        artist = a;
    }
    public String getArtist(){ return artist; }

    public void setCategory(String c){
        category = c;
    }
    public String getCategory(){ return category; }

    public void setDescription(String d){
        description = d;
    }
    public String getDescription(){ return description; }

    public void setPrice(double p){
        price = p;
    }
    public double getPrice(){ return price; }

    public String getPriceCurrency(){ return NumberFormat.getCurrencyInstance().format(price); }

    public String getPriceNumber(){
	    NumberFormat priceNumber = NumberFormat.getNumberInstance();
	    priceNumber.setMinimumFractionDigits(2);
	    return priceNumber.format(price);
	}
}
