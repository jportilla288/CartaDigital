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

public class MenuDigital6 extends AppCompatActivity {
    private int cantidad;

    private ImageButton mousseLimon,postreChoco, esponjadoMaracu,cheesecakeOreo,postreTresl;
    private TextView  cantidad1,cantidad2,cantidad3,cantidad4,cantidad5;
    private TextView precio1,precio2,precio3,precio4,precio5;
    private RequestQueue requestQueue;
    private String insertUrl =  "http://172.17.0.17/MenuDigitalNueva/CrearPedido.php";
    private Context ctx;
    private AlertDialog.Builder myAlert;
    int cantidadplimon=0;
    int cantidadpchoco=0;
    int cantidadespmaracuya=0;
    int cantidadcheesecakeoreo=0;
    int cantidadptresleches=0;
    ArrayList<Plato> p1 = new ArrayList<Plato>();
    MenuPlatos pedidocli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mousseLimon= (ImageButton)findViewById(R.id.mousseLimon);
        postreChoco = (ImageButton)findViewById(R.id.postreChoco);
        esponjadoMaracu= (ImageButton)findViewById(R.id.esponjadoMaracu);
        cheesecakeOreo= (ImageButton)findViewById(R.id.cheesecakeOreo);
        postreTresl= (ImageButton)findViewById(R.id.postreTresl);
        cantidad1= (TextView)findViewById(R.id.textView34);
        cantidad2= (TextView)findViewById(
                R.id.textView35);
        cantidad3= (TextView)findViewById(R.id.textView36);
        cantidad4= (TextView)findViewById(R.id.textView37);
        cantidad5= (TextView)findViewById(R.id.textView38);
        precio1= (TextView)findViewById(R.id.textView39);
        precio2= (TextView)findViewById(R.id.textView40);
        precio3= (TextView)findViewById(R.id.textView41);
        precio4= (TextView)findViewById(R.id.textView42);
        precio5= (TextView)findViewById(R.id.textView43);
        ctx = getApplicationContext();
        myAlert = new AlertDialog.Builder(this);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        setContentView(R.layout.activity_menu_digital6);
        ImageButton btmousslimon = (ImageButton)findViewById(R.id.mousseLimon);
        btmousslimon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                cantidadplimon=cantidadplimon+1;

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
                                            mousseLimon.setBottom(Integer.parseInt(" "));
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
                        parametros.put("producto",String.valueOf("mouse de limon") );
                        parametros.put("cantidad","10");
                        parametros.put("precio", "4000");
                        Log.i("crash", "parametros");
                        return parametros;
                    }
                };
                requestQueue.add(request);

                MenuPlatos pedidocli = new MenuPlatos();
                Bundle extras = getIntent().getExtras();
                if(getIntent().getSerializableExtra("pedidocliente")!=null) {


                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getPostres()==null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getPostres();
                    /*p1 = (ArrayList<Platos>) extras.getSerializable("pedidocliente");
                    if (p1.size() > 0
                            ) {

                                        }*/
                }
                Intent paso = new Intent(MenuDigital6.this, MenuDigitalPedir.class);
                paso.putExtra("pedidocliente", pedidocli);
                paso.putExtra("Precio", 4000 );
                paso.putExtra("Introduccion","Mousse de limon");
                paso.putExtra("Observaciones","Observaciones:");
                paso.putExtra("Comentarios","Comentarios");
                paso.putExtra("Total","TOTAL=");
                paso.putExtra("foto", R.drawable.mouselimon);

                startActivity(paso);
                //Pedido mipedio = new Pedido();
                int conta=0;
                for(int i=0;i<p1.size();i++){
                    Log.i("Postre tres leches",p1.get(i).getNombre());
                    if(p1.get(i).getNombre().equals("Postre tres leches")){
                        Log.i("Postre tres leches","entro");
                        p1.get(i).setCantidad(cantidadptresleches);
                        p1.get(i).setPrecio(cantidadptresleches*4000);
                        pedidocli.setPostres(p1);
                        pedidocli.setValor(pedidocli.getValor() + 4000);
                        Log.i("Postre tres leches",p1.get(i).getCantidad()+" "+p1.get(i).getPrecio());
                        conta++;
                    }
                }
                if(conta==0) {
                    Plato unplato = new Plato(12,"Postres", cantidadptresleches, 4000, "Postre tres leches");
                    p1.add(unplato);
                    pedidocli.setPostres(p1);
                    pedidocli.setValor(pedidocli.getValor() + 4000);
                }

                Log.i("llego", "menu4 Postre tres leches");

                paso.putExtra("pedidocliente", pedidocli);
                //startActivity(paso);

            }
        });

        ImageButton btchocolate = (ImageButton)findViewById(R.id.postreChoco);
        btchocolate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                cantidadpchoco=cantidadpchoco+1;

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
                                            postreChoco.setBottom(Integer.parseInt(" "));
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
                        parametros.put("producto",String.valueOf("postre de chocolate") );
                        parametros.put("cantidad","10");
                        parametros.put("precio", "4000");
                        Log.i("crash","parametros");
                        return parametros;
                    }
                };
                requestQueue.add(request);

                MenuPlatos pedidocli = new MenuPlatos();
                Bundle extras = getIntent().getExtras();

                if(getIntent().getSerializableExtra("pedidocliente")!=null){

                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getPostres()==null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getPostres();

                   /* p1= (ArrayList<Platos>) extras.getSerializable("pedidocliente");
                    Log.i("llego","menu2"+p1.get(0).getCantidad());
                    Log.i("llego","menu2"+p1.get(0).getProducto());
                    Log.i("llego","menu2"+p1.get(0).getTipo());
                    Log.i("llego","menu2"+p1.get(0).getPrecio());*/

                }

                Intent paso = new Intent(MenuDigital6.this, MenuDigitalPedir.class);
                paso.putExtra("pedidocliente", pedidocli);
                paso.putExtra("Precio", 4500 );
                paso.putExtra("Introduccion","Postre de Chocolate");
                paso.putExtra("Observaciones","Observaciones:");
                paso.putExtra("Comentarios","Comentarios");
                paso.putExtra("Total","TOTAL=");
                paso.putExtra("foto", R.drawable.postrechoco);

                int conta=0;
                for(int i=0;i<p1.size();i++){
                    Log.i("Cheesecake de oreo",p1.get(i).getNombre());
                    if(p1.get(i).getNombre().equals("Cheesecake de oreo")){
                        Log.i("Cheesecake de oreo","entro");
                        p1.get(i).setCantidad(cantidadcheesecakeoreo);
                        p1.get(i).setPrecio(cantidadcheesecakeoreo*4500);
                        pedidocli.setPostres(p1);
                        pedidocli.setValor(pedidocli.getValor() + 4500);
                        Log.i("Cheesecake de oreo",p1.get(i).getCantidad()+" "+p1.get(i).getPrecio());
                        conta++;
                    }
                }
                if(conta==0) {
                    Plato unplato = new Plato(13,"Postres", cantidadcheesecakeoreo, 4500, "Cheesecake de oreo");
                    p1.add(unplato);
                    pedidocli.setPostres(p1);
                    pedidocli.setValor(pedidocli.getValor() + 4500);
                }

                Log.i("llego", "menu4 Cheesecake de oreo");

                paso.putExtra("pedidocliente", pedidocli);
                //startActivity(paso);

            }
        });

        ImageButton btmaracuya = (ImageButton)findViewById(R.id.esponjadoMaracu);
        btmaracuya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                cantidadespmaracuya=cantidadespmaracuya+1;

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
                                            esponjadoMaracu.setBottom(Integer.parseInt(" "));
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
                        parametros.put("producto",String.valueOf("Esponjado de maracuya") );
                        parametros.put("cantidad","10");
                        parametros.put("precio", "3500");
                        Log.i("crash","parametros");
                        return parametros;
                    }
                };
                requestQueue.add(request);

                MenuPlatos pedidocli = new MenuPlatos();
                Bundle extras = getIntent().getExtras();
                if(getIntent().getSerializableExtra("pedidocliente")!=null) {


                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getPostres()==null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getPostres();
                    /*p1 = (ArrayList<Platos>) extras.getSerializable("pedidocliente");
                    if (p1.size() > 0
                            ) {

                        Log.i("llego", "menu2" + p1.get(0).getCantidad());
                        Log.i("llego", "menu2" + p1.get(0).getProducto());
                        Log.i("llego", "menu2" + p1.get(0).getTipo());
                        Log.i("llego", "menu2" + p1.get(0).getPrecio()); }*/

                }

                Intent paso = new Intent(MenuDigital6.this, MenuDigitalPedir.class);
                paso.putExtra("pedidocliente", pedidocli);
                paso.putExtra("Precio", 4000 );
                paso.putExtra("Introduccion","Esponjado de maracuyá");
                paso.putExtra("Observaciones","Observaciones:");
                paso.putExtra("Comentarios","Comentarios");
                paso.putExtra("Total","TOTAL=");
                paso.putExtra("foto", R.drawable.postremaracuya);

                int conta=0;
                for(int i=0;i<p1.size();i++){
                    Log.i("Esponjado de maracuyá",p1.get(i).getNombre());
                    if(p1.get(i).getNombre().equals("Esponjado de maracuyá")){
                        Log.i("Esponjado de maracuyá","entro");
                        p1.get(i).setCantidad(cantidadespmaracuya);
                        p1.get(i).setPrecio(cantidadespmaracuya*3500);
                        pedidocli.setPostres(p1);
                        pedidocli.setValor(pedidocli.getValor() + 3500);
                        Log.i("Esponjado de maracuyá",p1.get(i).getCantidad()+" "+p1.get(i).getPrecio());
                        conta++;
                    }
                }
                if(conta==0) {
                    Plato unplato = new Plato(14,"Postres", cantidadespmaracuya, 3500, "Esponjado de maracuyá");
                    p1.add(unplato);
                    pedidocli.setPostres(p1);
                    pedidocli.setValor(pedidocli.getValor() + 3500);
                }

                Log.i("llego", "menu4 Esponjado de maracuyá");

                paso.putExtra("pedidocliente", pedidocli);
                //startActivity(paso);

            }
        });
        ImageButton btcheesecake = (ImageButton)findViewById(R.id.cheesecakeOreo);
        btcheesecake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                cantidadcheesecakeoreo=cantidadcheesecakeoreo+1;

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
                                            cheesecakeOreo.setBottom(Integer.parseInt(" "));
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
                        parametros.put("producto",String.valueOf("cheese cake de oreo") );
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
                    if (pedidocli.getPostres()==null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getPostres();
                    /*p1 = (ArrayList<Platos>) extras.getSerializable("pedidocliente");
                    if (p1.size() > 0
                            ) {

                        Log.i("llego", "menu2" + p1.get(0).getCantidad());
                        Log.i("llego", "menu2" + p1.get(0).getProducto());
                        Log.i("llego", "menu2" + p1.get(0).getTipo());
                        Log.i("llego", "menu2" + p1.get(0).getPrecio());

                    }*/
                }

                Intent paso = new Intent(MenuDigital6.this, MenuDigital2.class);

                int conta=0;
                for(int i=0;i<p1.size();i++){
                    Log.i("Mouse de limon",p1.get(i).getNombre());
                    if(p1.get(i).getNombre().equals("Mouse de limon")){
                        Log.i("Mouse de limon","entro");
                        p1.get(i).setCantidad(cantidadplimon);
                        p1.get(i).setPrecio(cantidadplimon*4000);
                        pedidocli.setPostres(p1);
                        pedidocli.setValor(pedidocli.getValor() + 4000);
                        Log.i("Mouse de limon",p1.get(i).getCantidad()+" "+p1.get(i).getPrecio());
                        conta++;
                    }
                }
                if(conta==0) {
                    Plato unplato = new Plato(14,"Postres", cantidadplimon, 4000, "Mouse de limon");
                    p1.add(unplato);
                    pedidocli.setPostres(p1);
                    pedidocli.setValor(pedidocli.getValor() + 4000);
                }

                Log.i("llego", "menu4 Mouse de limon");

                paso.putExtra("pedidocliente", pedidocli);
                //startActivity(paso);

            }
        });
        ImageButton bttresleches = (ImageButton)findViewById(R.id.postreTresl);
        bttresleches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                cantidadptresleches=cantidadptresleches+1;

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
                                            postreTresl.setBottom(Integer.parseInt(" "));
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
                        parametros.put("producto",String.valueOf("Postre de chocolate") );
                        parametros.put("cantidad","3");
                        parametros.put("precio", "4000");
                        Log.i("crash", "parametros");
                        return parametros;
                    }
                };
                requestQueue.add(request);

                MenuPlatos pedidocli = new MenuPlatos();
                Bundle extras = getIntent().getExtras();
                if(getIntent().getSerializableExtra("pedidocliente")!=null) {


                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getPostres()==null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getPostres();
                    /* p1 = (ArrayList<Platos>) extras.getSerializable("pedidocliente");
                    if (p1.size() > 0
                            ) {



                    }*/
                }
                Intent paso = new Intent(MenuDigital6.this, MenuDigitalPedir.class);
                paso.putExtra("pedidocliente", pedidocli);
                paso.putExtra("Precio", 4000 );
                paso.putExtra("Introduccion","Postre de chocolate");
                paso.putExtra("Observaciones","Observaciones:");
                paso.putExtra("Comentarios","Comentarios");
                paso.putExtra("Total","TOTAL=");
                paso.putExtra("foto", R.drawable.postrechoco);;

                int conta=0;
                for(int i=0;i<p1.size();i++){
                    Log.i("Postre de chocolate",p1.get(i).getNombre());
                    if(p1.get(i).getNombre().equals("Postre de chocolate")){
                        Log.i("Postre de chocolate","entro");
                        p1.get(i).setCantidad(cantidadpchoco);
                        p1.get(i).setPrecio(cantidadpchoco*4000);
                        pedidocli.setPostres(p1);
                        pedidocli.setValor(pedidocli.getValor() + 4000);
                        Log.i("Postre de chocolate",p1.get(i).getCantidad()+" "+p1.get(i).getPrecio());
                        conta++;
                    }
                }
                if(conta==0) {
                    Plato unplato = new Plato(15,"Postres", cantidadpchoco, 4000, "Postre de chocolate");
                    p1.add(unplato);
                    pedidocli.setPostres(p1);
                    pedidocli.setValor(pedidocli.getValor() + 4000);
                }

                Log.i("llego", "menu4 Postre de chocolate");

                paso.putExtra("pedidocliente", pedidocli);
                //startActivity(paso);


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_digital6, menu);
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
