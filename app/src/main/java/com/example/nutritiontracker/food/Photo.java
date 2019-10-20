
package com.example.nutritiontracker.food;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {
    public Photo(String thumb, String highres) {
        this.thumb = thumb;
        this.highres = highres;
    }

    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("highres")
    @Expose
    private String highres;
    @SerializedName("is_user_uploaded")
    @Expose
    private Boolean isUserUploaded;

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getHighres() {
        return highres;
    }

    public void setHighres(String highres) {
        this.highres = highres;
    }

    public Boolean getIsUserUploaded() {
        return isUserUploaded;
    }

    public void setIsUserUploaded(Boolean isUserUploaded) {
        this.isUserUploaded = isUserUploaded;
    }

}
