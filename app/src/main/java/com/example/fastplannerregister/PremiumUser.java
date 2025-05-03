package com.example.fastplannerregister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

public class PremiumUser extends AppCompatActivity {
    Button tambah;
    ListView daftar_plan;

    private JSONArray jsonArrayRes;

    List<String> ids = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_user);

        daftar_plan = findViewById(R.id.lv_plan);

        fetchDataAndPopulateListView();
    }

    private void planListView(String jsonResponse) {
        try {
            jsonArrayRes = new JSONArray(jsonResponse);
            List<String> plan = new ArrayList<>();

            for (int i = 0; i < jsonArrayRes.length(); i++) {
                JSONObject planObject = jsonArrayRes.getJSONObject(i);
                String nama = planObject.getString("nama");
                String waktu = planObject.getString("waktu");
                String harga = planObject.getString("harga");

                // Concatenate nama, waktu, and harga to create a string for each item
                String planItem = nama + " ->> " + waktu;
                plan.add(planItem);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, plan);
            ListView listView = findViewById(R.id.lv_plan);
            listView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getOrderInfoAtPosition(int position) {
        String orderId = "";
        try {
            JSONObject jsonObject = jsonArrayRes.getJSONObject(position);
            orderId = jsonObject.getString("_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return orderId;
    }

    private void fetchDataAndPopulateListView() {
        getAPIdata();
    }

    public void getAPIdata() {
        String url = "https://ap-southeast-1.aws.data.mongodb-api.com/app/fastplanner-app-ekutp/endpoint/getAllPlan";

        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);
                // Panggil metode untuk mengisi ListView dengan data JSON
                planListView(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PremiumUser.this, "Error mas, coba diulang", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        rq.add(sr);
    }
}
