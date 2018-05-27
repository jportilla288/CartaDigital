package com.carta.dx.cartadigital;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuDigital5 extends AppCompatActivity {
    private int cantidad;

    private ImageButton cocacola,clubColombia,peroni,mandarinada,limonada, regresar;
    private TextView cantidad1,cantidad2,cantidad3,cantidad4,cantidad5;
    private TextView precio1, precio2, precio3, precio4, precio5;
    private RequestQueue requestQueue;
    private String insertUrl = "http://172.17.0.17/MenuDigitalNueva/CrearPedido.php";
    private Context ctx;
    private AlertDialog.Builder myAlert;
    int cantidadcoca=0;
    int cantidadclub=0;
    int cantidadperoni=0;
    int cantidadmandarinada=0;
    int cantidadlimonada=0;
    ArrayList<Plato> p1 = new ArrayList<Plato>();
    MenuPlatos pedidocli;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_digital5);

      cocacola= (ImageButton)findViewById(R.id.cocacola);
        clubColombia= (ImageButton)findViewById(R.id.clubColombia);
        peroni= (ImageButton)findViewById(R.id.peroni);
        mandarinada= (ImageButton)findViewById(R.id.mandarinada);
        regresar= (ImageButton)findViewById(R.id.regresar);
        limonada= (ImageButton)findViewById(R.id.limonda);
        cantidad1= (TextView)findViewById(R.id.textView24);
        cantidad2= (TextView)findViewById(R.id.textView25);
        cantidad3= (TextView)findViewById(R.id.textView26);
        cantidad4= (TextView)findViewById(R.id.textView27);
        cantidad5= (TextView)findViewById(R.id.textView28);
        precio1= (TextView)findViewById(R.id.textView29);
        precio2= (TextView)findViewById(R.id.textView30);
        precio3= (TextView)findViewById(R.id.textView31);
        precio4= (TextView)findViewById(R.id.textView32);
        precio5= (TextView)findViewById(R.id.textView33);
        ctx = getApplicationContext();
        myAlert = new AlertDialog.Builder(this);

        requestQueue = Volley.newRequestQueue(getApplicationContext());


        ImageButton btcocacola = (ImageButton)findViewById(R.id.cocacola);
        btcocacola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                cantidadcoca=cantidadcoca+1;

                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("crash",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response).getJSONObject("resultado");
                            String msg = jsonObject.getString("mensaje");

                            myAlert.setTitle("Pedidos!")
                                    .setMessage(msg)
                                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            cocacola.setBottom(Integer.parseInt(" "));
                                            cantidad1.setText("");
                                            precio1.setText("");
                                        }
                                    })
                                    .create();
                            myAlert.show();

                            Toast toast = Toast.makeText(ctx,msg, Toast.LENGTH_SHORT);
                            toast.show();
                            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 45, 45);

                        } catch (JSONException e) {
                            Log.i("crash",e.toString());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parametros = new HashMap<String, String>();
                        parametros.put("producto",String.valueOf("cocacola") );
                        parametros.put("cantidad","10");
                        parametros.put("precio", "3500");
                        Log.i("crash", "parametros");
                        return parametros;
                    }
                };
                requestQueue.add(request);

                MenuPlatos pedidocli = new MenuPlatos();
                Bundle extras = getIntent().getExtras();
                if(getIntent().getSerializableExtra("pedidocliente")!=null) {


                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getBebidas()==null){
                        p1 = new ArrayList<Plato>();
                        Log.i("pruebapapas",pedidocli.getEntradas().get(0).getNombre());
                    }
                    else {
                        p1 = pedidocli.getBebidas();
                        Log.i("pruebapapas",pedidocli.getEntradas().get(0).getNombre());
                    }
                   /* p1 = (ArrayList<Platos>) extras.getSerializable("pedidocliente");
                    if (p1.size() > 0
                            ) {



                    }*/
                }
                Intent paso = new Intent(MenuDigital5.this, MenuDigitalPedir.class);
                paso.putExtra("pedidocliente", pedidocli);
                paso.putExtra("Precio", 3500 );
                paso.putExtra("Introduccion","Cocacola Personal.");
                paso.putExtra("Observaciones","Observaciones:");
                paso.putExtra("Comentarios","Comentarios");
                paso.putExtra("Total","TOTAL=");
                paso.putExtra("foto", R.drawable.cocacola);

                startActivity(paso);

                int conta=0;
                for(int i=0;i<p1.size();i++){
                    Log.i("Cocacola",p1.get(i).getNombre());
                    if(p1.get(i).getNombre().equals("Cocacola")){
                        Log.i("Cocacola","entro");
                        p1.get(i).setCantidad(cantidadcoca);
                        p1.get(i).setPrecio(cantidadcoca*3500);
                        pedidocli.setBebidas(p1);
                        pedidocli.setValor(pedidocli.getValor() + 3500);
                        Log.i("Cocacola",p1.get(i).getCantidad()+" "+p1.get(i).getPrecio());
                        conta++;
                    }
                }
                if(conta==0) {
                    Plato unplato = new Plato(10,"Bebidas", cantidadcoca, 3500, "Cocacola");
                    p1.add(unplato);
                    pedidocli.setBebidas(p1);
                    pedidocli.setValor(pedidocli.getValor() + 3500);
                }

                Log.i("llego", "menu4 Cocacola");

                paso.putExtra("pedidocliente", pedidocli);
                //startActivity(paso);



            }
        });

        ImageButton btclub = (ImageButton)findViewById(R.id.clubColombia);
        btclub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                cantidadclub=cantidadclub+1;

                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("crash",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response).getJSONObject("resultado");
                            String msg = jsonObject.getString("mensaje");

                            myAlert.setTitle("Pedidos!")
                                    .setMessage(msg)
                                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            clubColombia.setBottom(Integer.parseInt(" "));
                                            cantidad2.setText("");
                                            precio2.setText("");
                                        }
                                    })
                                    .create();
                            myAlert.show();

                            Toast toast = Toast.makeText(ctx,msg, Toast.LENGTH_SHORT);
                            toast.show();
                            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 45, 45);

                        } catch (JSONException e) {
                            Log.i("crash",e.toString());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parametros = new HashMap<String, String>();
                        parametros.put("producto",String.valueOf("club colombia") );
                        parametros.put("cantidad","10");
                        parametros.put("precio", "4500");
                        Log.i("crash", "parametros");
                        return parametros;
                    }
                };
                requestQueue.add(request);

                MenuPlatos pedidocli = new MenuPlatos();
                Bundle extras = getIntent().getExtras();
                if(getIntent().getSerializableExtra("pedidocliente")!=null) {


                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getBebidas()==null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getBebidas();
                   /* p1 = (ArrayList<Platos>) extras.getSerializable("pedidocliente");
                    if (p1.size() > 0
                            ) {

                        Log.i("llego", "menu2" + p1.get(0).getCantidad());
                        Log.i("llego", "menu2" + p1.get(0).getProducto());
                        Log.i("llego", "menu2" + p1.get(0).getTipo());
                        Log.i("llego", "menu2" + p1.get(0).getPrecio());

                    }*/
                }
                Intent paso = new Intent(MenuDigital5.this, MenuDigitalPedir.class);
                paso.putExtra("pedidocliente", pedidocli);
                paso.putExtra("Precio", 4500 );
                paso.putExtra("Introduccion","Club Colombia Dorada.");
                paso.putExtra("Observaciones","Observaciones:");
                paso.putExtra("Comentarios","Comentarios");
                paso.putExtra("Total","TOTAL=");
                paso.putExtra("foto", R.drawable.cocacola);

                startActivity(paso);

                int conta=0;
                for(int i=0;i<p1.size();i++){
                    Log.i("Club Colombia",p1.get(i).getNombre());
                    if(p1.get(i).getNombre().equals("Club Colombia")){
                        Log.i("Club Colombia","entro");
                        p1.get(i).setCantidad(cantidadclub);
                        p1.get(i).setPrecio(cantidadclub*4500);
                        pedidocli.setBebidas(p1);
                        pedidocli.setValor(pedidocli.getValor() + 4500);
                        Log.i("Club Colombia",p1.get(i).getCantidad()+" "+p1.get(i).getPrecio());
                        conta++;
                    }
                }
                if(conta==0) {
                    Plato unplato = new Plato(11,"Bebidas", cantidadclub, 4500, "Club Colombia");
                    p1.add(unplato);
                    pedidocli.setBebidas(p1);
                    pedidocli.setValor(pedidocli.getValor() + 4500);
                }

                Log.i("llego", "menu4 Club Colombia");

                paso.putExtra("pedidocliente", pedidocli);
                //startActivity(paso);

            }
        });

        ImageButton btperoni = (ImageButton)findViewById(R.id.peroni);
        btperoni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                cantidadperoni=cantidadperoni+1;

                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("crash",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response).getJSONObject("resultado");
                            String msg = jsonObject.getString("mensaje");

                            myAlert.setTitle("Pedidos!")
                                    .setMessage(msg)
                                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            peroni.setBottom(Integer.parseInt(" "));
                                            cantidad3.setText("");
                                            precio3.setText("");
                                        }
                                    })
                                    .create();
                            myAlert.show();

                            Toast toast = Toast.makeText(ctx,msg, Toast.LENGTH_SHORT);
                            toast.show();
                            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 45, 45);

                        } catch (JSONException e) {
                            Log.i("crash",e.toString());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parametros = new HashMap<String, String>();
                        parametros.put("producto",String.valueOf("Peroni") );
                        parametros.put("cantidad","10");
                        parametros.put("precio", "3500");
                        Log.i("crash", "parametros");
                        return parametros;
                    }
                };
                requestQueue.add(request);

                MenuPlatos pedidocli = new MenuPlatos();
                Bundle extras = getIntent().getExtras();
                if(getIntent().getSerializableExtra("pedidocliente")!=null) {


                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getBebidas()==null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getBebidas();
                  /*  p1 = (ArrayList<Platos>) extras.getSerializable("pedidocliente");
                    if (p1.size() > 0
                            ) {

                        Log.i("llego", "menu2" + p1.get(0).getCantidad());
                        Log.i("llego", "menu2" + p1.get(0).getProducto());
                        Log.i("llego", "menu2" + p1.get(0).getTipo());
                        Log.i("llego", "menu2" + p1.get(0).getPrecio());
                    }*/
                }
                Intent paso = new Intent(MenuDigital5.this, MenuDigitalPedir.class);
                paso.putExtra("pedidocliente", pedidocli);
                paso.putExtra("Precio", 3500 );
                paso.putExtra("Introduccion","Cerveza Peronni.");
                paso.putExtra("Observaciones","Observaciones:");
                paso.putExtra("Comentarios","Comentarios");
                paso.putExtra("Total","TOTAL=");
                paso.putExtra("foto", R.drawable.peronni);

                startActivity(paso);

                int conta=0;
                for(int i=0;i<p1.size();i++){
                    Log.i("Peroni",p1.get(i).getNombre());
                    if(p1.get(i).getNombre().equals("Peroni")){
                        Log.i("Peroni","entro");
                        p1.get(i).setCantidad(cantidadperoni);
                        p1.get(i).setPrecio(cantidadperoni*4500);
                        pedidocli.setBebidas(p1);
                        pedidocli.setValor(pedidocli.getValor() + 4500);
                        Log.i("Peroni",p1.get(i).getCantidad()+" "+p1.get(i).getPrecio());
                        conta++;
                    }
                }
                if(conta==0) {
                    Plato unplato = new Plato(11,"Bebidas", cantidadperoni, 4500, "Peroni");
                    p1.add(unplato);
                    pedidocli.setBebidas(p1);
                    pedidocli.setValor(pedidocli.getValor() + 4500);
                }

                Log.i("llego", "menu4 Peroni");

                paso.putExtra("pedidocliente", pedidocli);
                //startActivity(paso);

            }
        });

        ImageButton btmandarinada = (ImageButton)findViewById(R.id.mandarinada);
        btmandarinada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                cantidadmandarinada=cantidadmandarinada+1;

                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("crash",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response).getJSONObject("resultado");
                            String msg = jsonObject.getString("mensaje");

                            myAlert.setTitle("Pedidos!")
                                    .setMessage(msg)
                                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            mandarinada.setBottom(Integer.parseInt(" "));
                                            cantidad4.setText("");
                                            precio4.setText("");
                                        }
                                    })
                                    .create();
                            myAlert.show();

                            Toast toast = Toast.makeText(ctx,msg, Toast.LENGTH_SHORT);
                            toast.show();
                            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 45, 45);

                        } catch (JSONException e) {
                            Log.i("crash",e.toString());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parametros = new HashMap<String, String>();
                        parametros.put("producto",String.valueOf("Mandarinada") );
                        parametros.put("cantidad","10");
                        parametros.put("precio", "5000");
                        Log.i("crash","parametros");
                        return parametros;
                }
                };
                requestQueue.add(request);

                MenuPlatos pedidocli = new MenuPlatos();
                Bundle extras = getIntent().getExtras();
                if(getIntent().getSerializableExtra("pedidocliente")!=null) {


                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getBebidas()==null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getBebidas();
                   /* p1 = (ArrayList<Platos>) extras.getSerializable("pedidocliente");
                    if (p1.size() > 0
                            ) {

                        Log.i("llego", "menu2" + p1.get(0).getCantidad());
                        Log.i("llego", "menu2" + p1.get(0).getProducto());
                        Log.i("llego", "menu2" + p1.get(0).getTipo());
                        Log.i("llego", "menu2" + p1.get(0).getPrecio());

                    }*/
                }

                Intent paso = new Intent(MenuDigital5.this, MenuDigitalPedir.class);
                paso.putExtra("pedidocliente", pedidocli);
                paso.putExtra("Precio", 3500 );
                paso.putExtra("Introduccion","Rica y refrescante Mandarinada.");
                paso.putExtra("Observaciones","Observaciones:");
                paso.putExtra("Comentarios","Comentarios");
                paso.putExtra("Total","TOTAL=");
                paso.putExtra("foto", R.drawable.naranja);

                startActivity(paso);
                int conta=0;
                for(int i=0;i<p1.size();i++){
                    Log.i("Mandarinada",p1.get(i).getNombre());
                    if(p1.get(i).getNombre().equals("Mandarinada")){
                        Log.i("Mandarinada","entro");
                        p1.get(i).setCantidad(cantidadmandarinada);
                        p1.get(i).setPrecio(cantidadmandarinada*5000);
                        pedidocli.setBebidas(p1);
                        pedidocli.setValor(pedidocli.getValor() + 5000);
                        Log.i("Mandarinada",p1.get(i).getCantidad()+" "+p1.get(i).getPrecio());
                        conta++;
                    }
                }
                if(conta==0) {
                    Plato unplato = new Plato(12,"Bebidas", cantidadmandarinada, 5000, "Mandarinada");
                    p1.add(unplato);
                    pedidocli.setBebidas(p1);
                    pedidocli.setValor(pedidocli.getValor() + 500);
                }

                Log.i("llego", "menu4 Mandarinada");

                paso.putExtra("pedidocliente", pedidocli);
                //startActivity(paso);

            }
        });

        ImageButton btlimonada = (ImageButton)findViewById(R.id.limonda);
        btlimonada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                cantidadlimonada=cantidadlimonada+1;

                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("crash",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response).getJSONObject("resultado");
                            String msg = jsonObject.getString("mensaje");

                            myAlert.setTitle("Pedidos!")
                                    .setMessage(msg)
                                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                           limonada.setBottom(Integer.parseInt(" "));
                                            cantidad5.setText("");
                                            precio5.setText("");
                                        }
                                    })
                                    .create();
                            myAlert.show();

                            Toast toast = Toast.makeText(ctx,msg, Toast.LENGTH_SHORT);
                            toast.show();
                            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 45, 45);

                        } catch (JSONException e) {
                            Log.i("crash",e.toString());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parametros = new HashMap<String, String>();
                        parametros.put("producto",String.valueOf("Limonda") );
                        parametros.put("cantidad","3");
                        parametros.put("precio", "5000");
                        Log.i("crash","parametros");
                        return parametros;
                    }
                };
                requestQueue.add(request);

                MenuPlatos pedidocli = new MenuPlatos();
                Bundle extras = getIntent().getExtras();
                if(getIntent().getSerializableExtra("pedidocliente")!=null) {


                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getBebidas()==null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getBebidas();
                   /* p1 = (ArrayList<Platos>) extras.getSerializable("pedidocliente");
                    if (p1.size() > 0
                            ) {

                        Log.i("llego", "menu2" + p1.get(0).getCantidad());
                        Log.i("llego", "menu2" + p1.get(0).getProducto());
                        Log.i("llego", "menu2" + p1.get(0).getTipo());
                        Log.i("llego", "menu2" + p1.get(0).getPrecio());

                    }*/
                }
                Intent paso = new Intent(MenuDigital5.this, MenuDigitalPedir.class);
                paso.putExtra("pedidocliente", pedidocli);
                paso.putExtra("Precio", 5000 );
                paso.putExtra("Introduccion","Rica y refrescante Limonada.");
                paso.putExtra("Observaciones","Observaciones:");
                paso.putExtra("Comentarios","Comentarios");
                paso.putExtra("Total","TOTAL=");
                paso.putExtra("foto", R.drawable.cocacola);

                startActivity(paso);

                int conta=0;
                for(int i=0;i<p1.size();i++){
                    Log.i("Limonada",p1.get(i).getNombre());
                    if(p1.get(i).getNombre().equals("Limonada")){
                        Log.i("Limonada","entro");
                        p1.get(i).setCantidad(cantidadlimonada);
                        p1.get(i).setPrecio(cantidadlimonada*5000);
                        pedidocli.setBebidas(p1);
                        pedidocli.setValor(pedidocli.getValor() + 5000);
                        Log.i("Limonada",p1.get(i).getCantidad()+" "+p1.get(i).getPrecio());
                        conta++;
                    }
                }
                if(conta==0) {
                    Plato unplato = new Plato(13,"Plato fuerte", cantidadlimonada, 5000, "Limonada");
                    p1.add(unplato);
                    pedidocli.setBebidas(p1);
                    pedidocli.setValor(pedidocli.getValor() + 500);
                }

                Log.i("llego", "menu4 Limonada");

                paso.putExtra("pedidocliente", pedidocli);
                //startActivity(paso);

            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paso = new Intent(MenuDigital5.this, MenuDigital2.class);
                paso.putExtra("pedidocliente", pedidocli);
                startActivity(paso);


            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_digital5, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
