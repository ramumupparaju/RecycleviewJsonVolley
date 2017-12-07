package com.incon.recycleviewjsonvolley;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class  MainActivity extends AppCompatActivity {
    private static final String URL_DATA="http://www.androidbegin.com/tutorial/jsonparsetutorial.txt";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Data_list> data_lists;

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        data_lists=new ArrayList<>();
        lodedata();
    }

    private void lodedata() {


        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("data loading");

        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override            public void onResponse(String response) {

                progressDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("worldpopulation");
                    for(int i=0;i<jsonArray.length();i++)
                    {

                        JSONObject jsonObject1= jsonArray.getJSONObject(i);

                        Data_list data_list=new Data_list();

                        String  rank=jsonObject1.getString("rank");
                        String  country=jsonObject1.getString("country");
                        String  population=jsonObject1.getString("population");
                        String  flag=jsonObject1.getString("flag");
                        data_list.setRank(rank);
                        data_list.setCountry(country);
                        data_list.setPopulation(population);
                        data_list.setFlag(flag);

                        data_lists.add(data_list);

                    }

                    adapter=new MyAdapter(data_lists,getApplicationContext());

                    recyclerView.setAdapter(adapter);



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }
}