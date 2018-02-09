package com.example.ekene.crosscheck;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String URL_DATA = "http://babelli-gutenberg-copypasta.appspot.com/appsearch/prince";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<DevelopersList> developersLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        developersLists = new ArrayList<>();
        //calling the loadUrl method
        loadUrlData();
    }


    //defining the loadUrl method ( calls the api for the json data)
    private void loadUrlData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();/**
                try {
                    JSONArray arr = new JSONArray(response);
                    for (int i = 0; i < arr.length(); i++) { // Walk through the Array.
                        JSONObject obj = arr.getJSONObject(i);
                        DevelopersList developers = new DevelopersList(obj.getString("title"));
                        developersLists.add(developers);
                    }

                    adapter = new DevelopersAdapter(developersLists, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }**/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//This indicates that the reuest has either time out or there is no connection
                    Toast.makeText(MainActivity.this, "No Connection Error", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else if (error instanceof AuthFailureError) {
// Error indicating that there was an Authentication Failure while performing the request
                    Toast.makeText(MainActivity.this, "Auth Error", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else if (error instanceof ServerError) {
//Indicates that the server responded with a error response
                    Toast.makeText(MainActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else if (error instanceof NetworkError) {
//Indicates that there was network error while performing the request
                    Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else if (error instanceof ParseError) {
// Indicates that the server response could not be parsed
                    Toast.makeText(MainActivity.this, "Parse Error", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }
        })/**{ @Override
        public String getBodyContentType() {
            return "application/x-www-form-urlencoded; charset=UTF-8";
        }}**/;

        //defining the requestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
