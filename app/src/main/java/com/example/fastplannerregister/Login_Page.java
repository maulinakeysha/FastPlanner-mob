package com.example.fastplannerregister;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login_Page extends AppCompatActivity {
    EditText username, password;
    Button login;
    private RequestQueue requestQueue;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        username = findViewById(R.id.username);
        password = findViewById(R.id.Password);
        login = findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pastikan objek EditText tidak null sebelum memanggil getText()
                if (username != null && password != null) {
                    String inusername = username.getText().toString();
                    String inpassword = password.getText().toString();

                    String url = "https://us-east-1.aws.data.mongodb-api.com/app/application-0-tarvq/endpoint/loginDietisien?username=" + inusername + "&password=" + inpassword;
                    StringRequest loginRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                // Parse the JSON response
                                JSONObject jsonResponse = new JSONObject(response);

                                // Check if the "username" key is present in the JSON response
                                if (jsonResponse.has("username")) {
                                    // Move to MainActivity
                                    Intent intent = new Intent(Login_Page.this, settings.class);
                                    startActivity(intent);
                                    finish();  // Don't forget to finish the current activity if login is successful
                                } else {
                                    // Username key not present in the JSON response
                                    Toast.makeText(getApplicationContext(), "Username tidak ditemukan", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Error parsing JSON response", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Handle errors here
                            Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
                    // Add the request to the RequestQueue.
                    rq.add(loginRequest);
                } else {
                    // Log atau tangani situasi di mana objek EditText null
                    Toast.makeText(getApplicationContext(), "EditText is null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
