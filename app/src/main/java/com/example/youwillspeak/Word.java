package com.example.youwillspeak;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Word {
    String english;
    String spanish;
    int inquiriesThisSession;
    int totalInquiries;
    int accurateResponses;
    double accuracyPercentage;

    Word(JSONObject jsonWord){
        try{
            english = jsonWord.getString("english");
            spanish = jsonWord.getString("spanish");
            inquiriesThisSession = jsonWord.getInt("inquiriesThisSession");;
            totalInquiries = jsonWord.getInt("totalInquiries");
            accuracyPercentage = jsonWord.getDouble("accuracyPercentage");
        }catch (JSONException e){
            Log.d("OhSHit", "Houston we have a problem");
            e.printStackTrace();
        }

    }
}
