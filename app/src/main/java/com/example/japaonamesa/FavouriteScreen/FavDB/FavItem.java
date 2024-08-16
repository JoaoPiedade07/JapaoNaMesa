package com.example.japaonamesa.FavouriteScreen.FavDB;

public class FavItem {

    private String itemTitle;
    private String key_id;
    private int item_image;

    public FavItem(String itemTitle, String key_id, int item_image) {
        this.itemTitle = itemTitle;
        this.key_id = key_id;
        this.item_image = item_image;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getKey_id() {
        return key_id;
    }
    public int getItem_image() { return item_image;
    }
}
