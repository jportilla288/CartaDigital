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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuDigital4 extends AppCompatActivity {
    private ImageButton lasaña,pechuga,cazuela,salmon,carne, regresar;
    private TextView nombre1, nombre2, nombre3, nombre4, nombre5;
    private TextView precio1,precio2,precio3,precio4,precio5;
    private RequestQueue requestQueue;
    private String insertUrl =  "http://172.17.0.17/MenuDigitalNueva/CrearPedido.php";
    private Context ctx;
    private AlertDialog.Builder myAlert;
    int cantidadcasuela =0;
    int cantidadpechuga=0;
    int cantidadlasaña=0;
    int cantidadsalmon=0;
    int cantidadcarne=0;
    ArrayList<Plato> p1 = new ArrayList<Plato>();
    MenuPlatos pedidocli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_digital4);

        lasaña= (ImageButton)findViewById(R.id.lasaña);
        pechuga= (ImageButton)findViewById(R.id.pechuga);
        cazuela= (ImageButton)findViewById(R.id.cazuela);
        salmon= (ImageButton)findViewById(R.id.salmon);
        carne= (ImageButton)findViewById(R.id.carne);
        regresar= (ImageButton)findViewById(R.id.regresar);
        nombre1= (TextView)findViewById(R.id.textView9);
        nombre2= (TextView)findViewById(R.id.textView12);
        nombre3= (TextView)findViewById(R.id.textView13);
        nombre4= (TextView)findViewById(R.id.textView14);
        nombre5= (TextView)findViewById(R.id.textView15);
        precio1= (TextView)findViewById(R.id.textView10);
        precio2= (TextView)findViewById(R.id.textView16);
        precio3= (TextView)findViewById(R.id.textView18);
        precio4= (TextView)findViewById(R.id.textView20);
        precio5= (TextView)findViewById(R.id.textView21);
        ctx = getApplicationContext();
        myAlert = new AlertDialog.Builder(this);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();

        ImageButton btlasaña = (ImageButton)findViewById(R.id.lasaña);
        btlasaña.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ;
                cantidadpechuga=cantidadpechuga+1;
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
                                        lasaña.setBottom(Integer.parseInt(" "));
                                        nombre1.setText("");
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
                    parametros.put("producto",String.valueOf("Lasana") );
                    parametros.put("cantidad","20");
                    parametros.put("precio", "25000");
                    Log.i("crash", "parametros");
                    return parametros;
                }
            };
            requestQueue.add(request);


                MenuPlatos pedidocli = new MenuPlatos();
                Bundle extras = getIntent().getExtras();
                if(getIntent().getSerializableExtra("pedidocliente")!=null) {


                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getPlatoFuerte()==null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getPlatoFuerte();
                    /* p1 = (ArrayList<Platos>) extras.getSerializable("pedidocliente");
                    if (p1.size() > 0
                            ) {

                        Log.i("llego", "menu2" + p1.get(0).getCantidad());
                        Log.i("llego", "menu2" + p1.get(0).getProducto());
                        Log.i("llego", "menu2" + p1.get(0).getTipo());
                        Log.i("llego", "menu2" + p1.get(0).getPrecio());

                    }*/
                }

                Intent paso = new Intent(MenuDigital4.this, MenuDigitalPedir.class);
                paso.putExtra("pedidocliente", pedidocli);
                paso.putExtra("Precio", 5000 );
                paso.putExtra("Introduccion","Jugosa carne asada+Papa a la Francesa+ensalada.");
                paso.putExtra("Observaciones","Observaciones:");
                paso.putExtra("Comentarios","Comentarios");
                paso.putExtra("Total","TOTAL=");
                paso.putExtra("foto", R.drawable.lasana);
                startActivity(paso);


                if(getIntent().getSerializableExtra("pedidocliente")!=null) {


                    pedidocli = (MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getEntradas() == null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getEntradas();
                }


                int conta=0;
                for(int i=0;i<p1.size();i++){
                    Log.i("Lasaña",p1.get(i).getNombre());
                    if(p1.get(i).getNombre().equals("Lasaña")){
                        Log.i("Lasaña","entro");
                        p1.get(i).setCantidad(cantidadlasaña);
                        p1.get(i).setPrecio(cantidadlasaña*25000);
                        pedidocli.setPlatoFuerte(p1);
                        pedidocli.setValor(pedidocli.getValor() + 25000);
                        Log.i("Lasaña",p1.get(i).getCantidad()+" "+p1.get(i).getPrecio());
                        conta++;
                    }
                }
                if(conta==0) {
                    Plato unplato = new Plato(05,"Plato fuerte", cantidadlasaña, 25000, "Lasaña");
                    p1.add(unplato);
                    pedidocli.setPlatoFuerte(p1);
                    pedidocli.setValor(pedidocli.getValor() + 25000);
                }

                Log.i("llego", "menu4 Lasaña");

                paso.putExtra("pedidocliente", pedidocli);
                //startActivity(paso);

            }
        });
        ImageButton btpechuga = (ImageButton)findViewById(R.id.pechuga);
        btpechuga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                cantidadpechuga=cantidadpechuga+1;
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
                                            pechuga.setBottom(Integer.parseInt(" "));
                                            nombre2.setText("");
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
                        parametros.put("producto",String.valueOf("Pechuga") );
                        parametros.put("cantidad","20");
                        parametros.put("precio", "25000");
                        Log.i("crash", "parametros");
                        return parametros;
                    }
                };
                requestQueue.add(request);



                MenuPlatos pedidocli = new MenuPlatos();
                Bundle extras = getIntent().getExtras();
                if(getIntent().getSerializableExtra("pedidocliente")!=null) {


                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getPlatoFuerte()==null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getPlatoFuerte();
                    /* p1 = (ArrayList<Platos>) extras.getSerializable("pedidocliente");
                    if (p1.size() > 0
                            ) {

                        Log.i("llego", "menu2" + p1.get(0).getCantidad());
                        Log.i("llego", "menu2" + p1.get(0).getProducto());
                        Log.i("llego", "menu2" + p1.get(0).getTipo());
                        Log.i("llego", "menu2" + p1.get(0).getPrecio());

                    }*/
                }
                Intent paso = new Intent(MenuDigital4.this, MenuDigitalPedir.class);
                paso.putExtra("pedidocliente", pedidocli);
                paso.putExtra("Precio", 5000 );
                paso.putExtra("Introduccion","Jugosa pechuga asada + papa a la francesa + ensalada.");
                paso.putExtra("Observaciones","Observaciones:");
                paso.putExtra("Comentarios","Comentarios");
                paso.putExtra("Total","TOTAL=");
                paso.putExtra("foto", R.drawable.pechuga);
                startActivity(paso);


                if(getIntent().getSerializableExtra("pedidocliente")!=null) {


                    pedidocli = (MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getEntradas() == null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getEntradas();
                }

                int conta=0;
                for(int i=0;i<p1.size();i++){
                    Log.i("Pechuga",p1.get(i).getNombre());
                    if(p1.get(i).getNombre().equals("Pechuga")){
                        Log.i("Pechuga","entro");
                        p1.get(i).setCantidad(cantidadpechuga);
                        p1.get(i).setPrecio(cantidadpechuga*25000);
                        pedidocli.setPlatoFuerte(p1);
                        pedidocli.setValor(pedidocli.getValor() + 25000);
                        Log.i("Pechuga",p1.get(i).getCantidad()+" "+p1.get(i).getPrecio());
                        conta++;
                    }
                }
                if(conta==0) {
                    Plato unplato = new Plato(06,"Plato fuerte", cantidadpechuga, 25000, "Pechuga");
                    p1.add(unplato);
                    pedidocli.setPlatoFuerte(p1);
                    pedidocli.setValor(pedidocli.getValor() + 25000);
                }

                Log.i("llego", "menu4 Pechuga");

                paso.putExtra("pedidocliente", pedidocli);
                //startActivity(paso);



            }
        });

        ImageButton btcazuela = (ImageButton)findViewById(R.id.cazuela);
        btcazuela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                cantidadcasuela= cantidadcasuela+1;
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
                                            cazuela.setBottom(Integer.parseInt(" "));
                                            nombre3.setText("");
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
                        parametros.put("producto",String.valueOf("cazuela") );
                        parametros.put("cantidad",cantidadcasuela+"");
                        parametros.put("precio", "30000");
                        Log.i("crash", "parametros");
                        return parametros;
                    }
                };
                requestQueue.add(request);
                MenuPlatos pedidocli = new MenuPlatos();
                Bundle extras = getIntent().getExtras();
                if(getIntent().getSerializableExtra("pedidocliente")!=null) {



                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getPlatoFuerte()==null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getPlatoFuerte();
                   /* p1 = (ArrayList<Platos>) extras.getSerializable("pedidocliente");
                    if (p1.size() > 0
                            ) {

                    }*/
                }

//es la vista que saldra en menuDigitalPedir
                Intent paso = new Intent(MenuDigital4.this, MenuDigitalPedir.class);
                paso.putExtra("pedidocliente", pedidocli);
                paso.putExtra("Precio", 5000 );
                paso.putExtra("Introduccion","Cazuela de mariscos + arroz de coco + patacones.");
                paso.putExtra("Observaciones","Observaciones:");
                paso.putExtra("Comentarios","Comentarios");
                paso.putExtra("Total","TOTAL=");
                paso.putExtra("foto", R.drawable.cazuela);
                startActivity(paso);


                if(getIntent().getSerializableExtra("pedidocliente")!=null) {


                    pedidocli = (MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getEntradas() == null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getEntradas();
                }

                int conta=0;
                for(int i=0;i<p1.size();i++){
                    Log.i("Cazuela",p1.get(i).getNombre());
                    if(p1.get(i).getNombre().equals("Cazuela")){
                        Log.i("Cazuela","entro");
                        p1.get(i).setCantidad(cantidadcasuela);
                        p1.get(i).setPrecio(cantidadcasuela*30000);
                        pedidocli.setPlatoFuerte(p1);
                        pedidocli.setValor(pedidocli.getValor() + 30000);
                        Log.i("Cazuela",p1.get(i).getCantidad()+" "+p1.get(i).getPrecio());
                        conta++;
                    }
                }
                if(conta==0) {
                    Plato unplato = new Plato(07,"Plato fuerte", cantidadcasuela, 30000, "Cazuela");
                    p1.add(unplato);
                    pedidocli.setPlatoFuerte(p1);
                    pedidocli.setValor(pedidocli.getValor() + 30000);
                }

                Log.i("llego", "menu4 Cazuela");

                paso.putExtra("pedidocliente", pedidocli);
                //startActivity(paso);



            }
        });

        ImageButton btsalmon = (ImageButton)findViewById(R.id.salmon);
        btsalmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                cantidadsalmon=cantidadsalmon+1;
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
                                           salmon.setBottom(Integer.parseInt(" "));
                                            nombre4.setText("");
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
                        parametros.put("producto",String.valueOf("salmon") );
                        parametros.put("cantidad","20");
                        parametros.put("precio", "30000");
                        Log.i("crash", "parametros");
                        return parametros;
                    }
                };
                requestQueue.add(request);

                MenuPlatos pedidocli = new MenuPlatos();
                Bundle extras = getIntent().getExtras();
                if(getIntent().getSerializableExtra("pedidocliente")!=null) {

                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getPlatoFuerte()==null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getPlatoFuerte();
                    /*  p1 = (ArrayList<Platos>) extras.getSerializable("pedidocliente");
                    if (p1.size() > 0
                            ) {

                        Log.i("llego", "menu2" + p1.get(0).getCantidad());
                        Log.i("llego", "menu2" + p1.get(0).getProducto());
                        Log.i("llego", "menu2" + p1.get(0).getTipo());
                        Log.i("llego", "menu2" + p1.get(0).getPrecio());

                    }*/
                }
                Intent paso = new Intent(MenuDigital4.this, MenuDigitalPedir.class);
                paso.putExtra("pedidocliente", pedidocli);
                paso.putExtra("Precio", 5000 );
                paso.putExtra("Introduccion","Jugosa pechuga asada + papa a la francesa + ensalada.");
                paso.putExtra("Observaciones","Observaciones:");
                paso.putExtra("Comentarios","Comentarios");
                paso.putExtra("Total","TOTAL=");
                paso.putExtra("foto", R.drawable.salmon);
                startActivity(paso);


                if(getIntent().getSerializableExtra("pedidocliente")!=null) {


                    pedidocli = (MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getEntradas() == null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getEntradas();
                }

                int conta=0;
                for(int i=0;i<p1.size();i++){
                    Log.i("Salmon",p1.get(i).getNombre());
                    if(p1.get(i).getNombre().equals("Salmon")){
                        Log.i("Salmon","entro");
                        p1.get(i).setCantidad(cantidadsalmon);
                        p1.get(i).setPrecio(cantidadsalmon*30000);
                        pedidocli.setPlatoFuerte(p1);
                        pedidocli.setValor(pedidocli.getValor() + 30000);
                        Log.i("Salmon",p1.get(i).getCantidad()+" "+p1.get(i).getPrecio());
                        conta++;
                    }
                }
                if(conta==0) {
                    Plato unplato = new Plato(9,"Plato fuerte", cantidadsalmon, 30000, "Salmon");
                    p1.add(unplato);
                    pedidocli.setPlatoFuerte(p1);
                    pedidocli.setValor(pedidocli.getValor() + 30000);
                }

                Log.i("llego", "menu4 Salmon");

                paso.putExtra("pedidocliente", pedidocli);
                //startActivity(paso);


            }
        });

        ImageButton btcarne = (ImageButton)findViewById(R.id.carne);
        btcarne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                cantidadcarne=cantidadcarne+1;
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
                                           carne.setBottom(Integer.parseInt(" "));
                                            nombre5.setText("");
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
                        parametros.put("producto",String.valueOf("Babby Reef") );
                        parametros.put("cantidad","20");
                        parametros.put("precio", "30000");
                        Log.i("crash", "parametros");
                        return parametros;
                    }
                };
                requestQueue.add(request);

                MenuPlatos pedidocli = new MenuPlatos();
                Bundle extras = getIntent().getExtras();
                if(getIntent().getSerializableExtra("pedidocliente")!=null) {


                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getPlatoFuerte()==null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getPlatoFuerte();
                   /* p1 = (ArrayList<Platos>) extras.getSerializable("pedidocliente");
                    if (p1.size() > 0
                            ) {

                        Log.i("llego", "menu2" + p1.get(0).getCantidad());
                        Log.i("llego", "menu2" + p1.get(0).getProducto());
                        Log.i("llego", "menu2" + p1.get(0).getTipo());
                        Log.i("llego", "menu2" + p1.get(0).getPrecio());

                    }*/
                }
                Intent paso = new Intent(MenuDigital4.this, MenuDigitalPedir.class);
                paso.putExtra("pedidocliente", pedidocli);
                paso.putExtra("Precio", 5000 );
                paso.putExtra("Introduccion","Jugosa carne asada+Papa a la Francesa+ensalada.");
                paso.putExtra("Observaciones","Observaciones:");
                paso.putExtra("Comentarios","Comentarios");
                paso.putExtra("Total","TOTAL=");
                paso.putExtra("foto", R.drawable.carne);
                startActivity(paso);


                if(getIntent().getSerializableExtra("pedidocliente")!=null) {


                    pedidocli = (MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getEntradas() == null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getEntradas();
                }

                int conta=0;
                for(int i=0;i<p1.size();i++){
                    Log.i("Carne",p1.get(i).getNombre());
                    if(p1.get(i).getNombre().equals("Carne")){
                        Log.i("Carne","entro");
                        p1.get(i).setCantidad(cantidadcarne);
                        p1.get(i).setPrecio(cantidadcarne*30000);
                        pedidocli.setPlatoFuerte(p1);
                        pedidocli.setValor(pedidocli.getValor() + 30000);
                        Log.i("Carne",p1.get(i).getCantidad()+" "+p1.get(i).getPrecio());
                        conta++;
                    }
                }
                if(conta==0) {
                    Plato unplato = new Plato(8, "Plato fuerte", cantidadlasaña, 30000, "Carne");
                    p1.add(unplato);
                    pedidocli.setPlatoFuerte(p1);
                    pedidocli.setValor(pedidocli.getValor() + 30000);
                }

                Log.i("llego", "menu4 Carne");

                paso.putExtra("pedidocliente", pedidocli);
                //startActivity(paso);



            }
        });
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paso = new Intent(MenuDigital4.this, MenuDigital2.class);
                paso.putExtra("pedidocliente", pedidocli);
                startActivity(paso);


            }

        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_digital4, menu);
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
        ;