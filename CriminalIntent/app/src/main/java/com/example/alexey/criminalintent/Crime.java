package com.example.alexey.criminalintent;

import java.util.UUID;

/**
 * Created by Alexey on 14.05.2016.
 */
public class Crime {
    private UUID mID;
    private String mTitle;

    public Crime(){
        //generates uniq identifier
        mID = UUID.randomUUID();
    }

    public UUID getmID() {
        return mID;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
