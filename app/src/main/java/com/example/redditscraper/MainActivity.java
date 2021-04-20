package com.example.redditscraper;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {
    private ImageView memeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        memeImageView = findViewById(R.id.memeView);
        Meme();
    }

    private void Meme(){
        // Instantiate the RequestQueue.

        RequestQueue queue = Volley.newRequestQueue(this);


        // Request a string response from the provided URL.
        String url="https://meme-api.herokuapp.com/gimme";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("myap","response ");
                        try {
                            String url=response.getString("url");
                            Glide.with(MainActivity.this).load(url).into(memeImageView);
                                Log.d("myap","response "+response.getString("title"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //   meme.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("myap","error "+error);
                    }
                });


        queue.add(jsonObjectRequest);

    }

}