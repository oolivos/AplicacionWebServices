package co.oscarolivos.aplicacionwebservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView estado;
    TextView mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        estado = (TextView)findViewById(R.id.textViewEstado);
        mensaje = (TextView)findViewById(R.id.textViewSaludo);

        RequestQueue cola  = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest peticion = new JsonObjectRequest(
                Request.Method.POST,
                "http://192.168.31.123/unisangil/saludo.php",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            estado.setText(String.valueOf(response.getInt("estado")));
                            mensaje.setText(String.valueOf(response.getString("saludo")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        Toast.makeText(
                                getApplicationContext(),
                                "Rta: "+response.toString(),
                                Toast.LENGTH_LONG
                        ).show();
                        Log.d("Rta: ", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(
                                getApplicationContext(),
                                "Error : "+error,
                                Toast.LENGTH_LONG
                        ).show();
                        Log.d("Error Volley: ", " "+error);
                    }
                }

        );
        cola.add(peticion);
    }
}
