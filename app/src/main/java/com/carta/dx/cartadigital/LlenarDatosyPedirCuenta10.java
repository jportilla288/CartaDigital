package com.carta.dx.cartadigital;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LlenarDatosyPedirCuenta10 extends Activity {

    Pedido pedidocli;
    ArrayList<Platos> p1 = new ArrayList<Platos>();
    private GoogleApiClient client;
    private String insertUrl = "http://172.17.0.17/MenuDigitalNueva/Cliente.php";
    private ImageButton factura;
    private TextView email, telefono, direccion, nombre;
    private EditText emailedit, telefonoedit, direccionedict, nombreedict;
    private RequestQueue requestQueue;
    private Context ctx;
    private AlertDialog.Builder myAlert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llenar_datosy_pedir_cuenta10);
        factura= (ImageButton)findViewById(R.id.enviar);
        email= (TextView)findViewById(R.id.email);
        telefono= (TextView)findViewById(R.id.telefono);
        direccion= (TextView)findViewById(R.id.direccion);
        nombre= (TextView)findViewById(R.id.nombre);
        emailedit=(EditText)findViewById(R.id.editEmail);
        telefonoedit=(EditText)findViewById(R.id.editTelefono);
        direccionedict=(EditText)findViewById(R.id.editDireccion);
        nombreedict=(EditText)findViewById(R.id.editNombre);
        ctx = getApplicationContext();
        myAlert = new AlertDialog.Builder(this);


        ImageButton fact = (ImageButton) findViewById(R.id.enviar);
        fact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i("crash",response);
                        try {
                            //JSONObject jsonObject = new JSONObject(response).getJSONObject("resultado");
                            //String msg = jsonObject.getString("mensaje");

                            myAlert.setTitle("Cliente!")
                                    .setMessage("mensaje")
                                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            factura.setBottom(Integer.parseInt(" "));
                                            emailedit.setText(" ");
                                            telefonoedit.setText(" ");
                                            direccionedict.setText(" ");
                                            nombreedict.setText(" ");

                                        }
                                    })
                                    .create();
                            myAlert.show();

                            Toast toast = Toast.makeText(ctx,"", Toast.LENGTH_SHORT);
                            toast.show();
                            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 45, 45);

                        } catch (Exception e) {
                            Log.i("crash",e.toString());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("crash",error.toString());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parametros = new HashMap<String, String>();
                        parametros.put("enail", emailedit.getText().toString());
                        parametros.put("telefono",  telefonoedit.getText().toString());
                        parametros.put("direccion",  direccionedict.getText().toString());
                        parametros.put("nombre",  nombreedict.getText().toString());
                        Log.i("crash","parametros");
                        return parametros;
                    }
                };
                requestQueue.add(request);

                }
        });

        Intent paso = new Intent(LlenarDatosyPedirCuenta10.this, generarFactura.class);
        startActivity(paso);
    }

    /*Intent intent = new Intent(Intent.ACTION_SENDTO);
    intent.setData(Uri.parse("mailto:")); // only email apps should handle this
    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
    intent.putExtra(Intent.EXTRA_SUBJECT, "Tu factura");
    intent.putExtra(Intent.EXTRA_TEXT, "Tu factura es de:");
    if (intent.resolveActivity(getPackageManager()) != null) {
        startActivity(intent);
    }*/
}





