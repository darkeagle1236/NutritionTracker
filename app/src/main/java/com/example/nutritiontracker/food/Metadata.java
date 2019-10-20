
package com.example.nutritiontracker.food;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata {

    @SerializedName("is_raw_food")
    @Expose
    private Boolean isRawFood;

    public Boolean getIsRawFood() {
        return isRawFood;
    }

    public void setIsRawFood(Boolean isRawFood) {
        this.isRawFood = isRawFood;
    }

}
