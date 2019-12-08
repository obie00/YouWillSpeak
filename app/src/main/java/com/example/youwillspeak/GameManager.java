package com.example.youwillspeak;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class GameManager {
    public String file_name = "jocab.json";

    public String newText = "{\\n  \\\"vocabularySize\\\": 4,\\n  \\\"vocabulary\\\": [\\n    {\\n      \\\"english\\\": \\\"afather\\\",\\n      \\\"spanish\\\": \\\"padre\\\",\\n      \\\"yoruba\\\": \\\"baba\\\",\\n      \\\"inquiriesThisSession\\\": 0,\\n      \\\"totalInquiries\\\": 0,\\n      \\\"accurateResponses\\\": 0,\\n      \\\"accuracyPercentage\\\": 0\\n    },\\n    {\\n      \\\"english\\\": \\\"amother\\\",\\n      \\\"spanish\\\": \\\"madre\\\",\\n      \\\"yoruba\\\": \\\"mama\\\",\\n      \\\"inquiriesThisSession\\\": 0,\\n      \\\"totalInquiries\\\": 0,\\n      \\\"accurateResponses\\\": 0,\\n      \\\"accuracyPercentage\\\": 0\\n    },\\n    {\\n      \\\"english\\\": \\\"abrother\\\",\\n      \\\"spanish\\\": \\\"hermano\\\",\\n      \\\"yoruba\\\": \\\"arakunrin\\\",\\n      \\\"inquiriesThisSession\\\": 0,\\n      \\\"totalInquiries\\\": 0,\\n      \\\"accurateResponses\\\": 0,\\n      \\\"accuracyPercentage\\\": 0\\n    },\\n    {\\n      \\\"english\\\": \\\"asister\\\",\\n      \\\"spanish\\\": \\\"hermana\\\",\\n      \\\"yoruba\\\": \\\"arabinrin\\\",\\n      \\\"inquiriesThisSession\\\": 0,\\n      \\\"totalInquiries\\\": 0,\\n      \\\"accurateResponses\\\": 0,\\n      \\\"accuracyPercentage\\\": 0\\n    }\\n  ]\\n}\";\n";

    public String loadText = "why me";
    Context activityContext;
    public int vocabularySize;
    public List<Word> vocabulary = new ArrayList<Word>();;

    public GameManager(Context gameActivityContext){
        activityContext = gameActivityContext;
        loadVocabularyWords();
    }

    public Word getNextWord(){
        int randomNumber = new Random().nextInt(vocabularySize);
        return vocabulary.get(randomNumber);
    }

    public void loadVocabularyWords(){
        String json;
        try{
            FileInputStream fileInputStream = activityContext.openFileInput("vocabulary.json");
            int size = fileInputStream.available();
            byte[] buffer = new byte[size];
            fileInputStream.read(buffer);
            fileInputStream.close();

            json = new String(buffer, "UTF-8");

            JSONObject vocabularyJsonFile = new JSONObject(json);
            vocabularySize = vocabularyJsonFile.getInt("vocabularySize");
            JSONArray vocabJsonArray = vocabularyJsonFile.getJSONArray("vocabulary");
            vocabulary.clear();
            for (int i = 0; i < vocabJsonArray.length(); i++){
                Word vocabWord = new Word(vocabJsonArray.getJSONObject(i));
                vocabulary.add(vocabWord);
            }

            Log.d("myTag", "This is my message");

        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void saveVocabularyWords(){
        String str = "{\n  \"vocabularySize\": 4,\n  \"vocabulary\": [\n    {\n      \"english\": \"father\",\n      \"spanish\": \"padre\",\n      \"yoruba\": \"baba\",\n      \"inquiriesThisSession\": 0,\n      \"totalInquiries\": 0,\n      \"accurateResponses\": 0,\n      \"accuracyPercentage\": 0\n    },\n    {\n      \"english\": \"mother\",\n      \"spanish\": \"madre\",\n      \"yoruba\": \"mama\",\n      \"inquiriesThisSession\": 0,\n      \"totalInquiries\": 0,\n      \"accurateResponses\": 0,\n      \"accuracyPercentage\": 0\n    },\n    {\n      \"english\": \"brother\",\n      \"spanish\": \"hermano\",\n      \"yoruba\": \"arakunrin\",\n      \"inquiriesThisSession\": 0,\n      \"totalInquiries\": 0,\n      \"accurateResponses\": 0,\n      \"accuracyPercentage\": 0\n    },\n    {\n      \"english\": \"sister\",\n      \"spanish\": \"hermana\",\n      \"yoruba\": \"arabinrin\",\n      \"inquiriesThisSession\": 0,\n      \"totalInquiries\": 0,\n      \"accurateResponses\": 0,\n      \"accuracyPercentage\": 0\n    }\n  ]\n}";

        try{
            FileOutputStream fileOutputStream = activityContext.openFileOutput("vocabulary.json",Context.MODE_PRIVATE);
            fileOutputStream.write(str.getBytes(),0,str.length());
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
