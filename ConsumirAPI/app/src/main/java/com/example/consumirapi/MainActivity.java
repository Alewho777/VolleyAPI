package com.example.consumirapi;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout = findViewById(R.id.tableLayout);

        // Recibir datos desde la API.
        getDatos();
    }

    private void getDatos() {
        // Reemplaza la URL con la correcta para tu API
        String url = "http://192.168.40.68:8080/api/clientes";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Recibir el JSON y pasar a Cliente.
                pasarJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String errorMessage = "Error al obtener datos desde la API";
                if (error.networkResponse != null) {
                    errorMessage += ": " + error.networkResponse.statusCode;
                }
                // Manejar el error
            }
        });

        // Hacer la petición a la API
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }

    private void pasarJson(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            JSONObject json;
            Cliente cliente = new Cliente();

            try {
                json = array.getJSONObject(i);
                cliente.setId(json.getLong("id"));
                cliente.setNombre(json.getString("nombre"));
                cliente.setApellido(json.getString("apellido"));
                cliente.setEmail(json.getString("email"));

                // Agregar el cliente a la tabla
                agregarFila(cliente);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void agregarFila(Cliente cliente) {
        TableRow row = new TableRow(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(layoutParams);

        agregarCelda(cliente.getNombre(), row);
        agregarCelda(cliente.getApellido(), row);
        agregarCelda(cliente.getEmail(), row);

        tableLayout.addView(row);
    }

    private void agregarCelda(String texto, TableRow row) {
        TextView textView = new TextView(this);
        textView.setText(texto);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(8, 8, 8, 8);

        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 1;

        textView.setLayoutParams(layoutParams);
        row.addView(textView);
    }
}
