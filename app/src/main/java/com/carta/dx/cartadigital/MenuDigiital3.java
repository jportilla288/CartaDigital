package com.carta.dx.cartadigital;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
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

public class MenuDigiital3 extends AppCompatActivity {
    private ImageButton papass, yucas, patacones,arepas,regresar;
    private TextView cantidad1,cantidad2, cantidad3,cantidad4 ;
    private TextView precio1,precio2,precio3,precio4;
    private RequestQueue requestQueue;
    private String insertUrl = "http://172.17.0.17/MenuDigitalNueva/insertar_inventario.php";
    private Context ctx;
    private AlertDialog.Builder myAlert;
    int cantidadpapas=0;
    int cantidadcroquetas=0;
    int cantidadpatacones=0;
    int cantidadarepas=0;
    ArrayList<Plato> p1 = new ArrayList<Plato>();
    MenuPlatos pedidocli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_digiital3);
        papass= (ImageButton)findViewById(R.id.papasFrancesa);
        yucas= (ImageButton)findViewById(R.id.croquetasboton);
        patacones= (ImageButton)findViewById(R.id.patacones);
        arepas= (ImageButton)findViewById(R.id.imagenProducto);
        cantidad1= (TextView)findViewById(R.id.papas);
        cantidad2= (TextView)findViewById(R.id.croquetas);
        regresar= (ImageButton)findViewById(R.id.regresar);
        cantidad3= (TextView)findViewById(R.id.textView3);
        cantidad4= (TextView)findViewById(R.id.textView4);
        precio1= (TextView)findViewById(R.id.precio);
        precio2= (TextView)findViewById(R.id.textView6);
        precio3= (TextView)findViewById(R.id.textView7);
        precio4= (TextView)findViewById(R.id.textView8);
        regresar= (ImageButton)findViewById(R.id.back);
        ctx = getApplicationContext();
        myAlert = new AlertDialog.Builder(this);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();

        ImageButton btpapas = (ImageButton)findViewById(R.id.papasFrancesa);
        btpapas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                cantidadpapas=cantidadpapas+1;

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
                                            papass.setBottom(Integer.parseInt(" "));
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
                        Log.i("crash",error.toString());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parametros = new HashMap<String, String>();
                        parametros.put("producto", String.valueOf("Papas a la Francesa"));
                        parametros.put("cantidad", cantidadpapas+"");
                        parametros.put("precio", "6000");
                        Log.i("crash","parametros");
                        return parametros;
                    }
                };
                requestQueue.add(request);

//lo nuevooo

                pedidocli = new MenuPlatos();
                Bundle extras = getIntent().getExtras();
                if(getIntent().getSerializableExtra("pedidocliente")!=null){


                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                }

                Intent paso = new Intent(MenuDigiital3.this, MenuDigitalPedir.class);
                paso.putExtra("pedidocliente", (Parcelable) pedidocli);
                paso.putExtra("Precio", 5000 );
                paso.putExtra("Introduccion","Deliciosa Papa a la Francesa con un toque BBQ");
                paso.putExtra("Observaciones","Observaciones:");
                paso.putExtra("Comentarios","Comentarios");
                paso.putExtra("Total","TOTAL=");
                paso.putExtra("foto", R.drawable.papasf);

                startActivity(paso);



               /*  pedidocli = new Pedido();
                Bundle extras = getIntent().getExtras();*/
                if(getIntent().getSerializableExtra("pedidocliente")!=null){


                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getEntradas()==null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getEntradas();
                    /* p1= (ArrayList<Platos>) extras.getSerializable("pedidocliente");
                    if (p1.size() > 0
                            ) {

                        Log.i("llego", "menu2" + p1.get(0).getCantidad());
                        Log.i("llego", "menu2" + p1.get(0).getProducto());
                        Log.i("llego", "menu2" + p1.get(0).getTipo());
                        Log.i("llego", "menu2" + p1.get(0).getPrecio());


                    }*/
                }

                //asi se manda colocandole los extras al intent paso
                //Intent paso = new Intent(MenuDigiital3.this, MenuDigital2.class);
                int conta=0;
                for(int i=0;i<p1.size();i++){
                    Log.i("papas",p1.get(i).getNombre());
                    if(p1.get(i).getNombre().equals("Papas a la francesa")){
                        Log.i("papas","entro");
                        p1.get(i).setCantidad(cantidadpapas);
                        p1.get(i).setPrecio(cantidadpapas*6000);
                        pedidocli.setEntradas(p1);
                        pedidocli.setValor(pedidocli.getValor() + 6000);
                        Log.i("papas",p1.get(i).getCantidad()+" "+p1.get(i).getPrecio());
                        conta++;
                    }
                }
                if(conta==0) {
                    Plato unplato = new Plato(01,"Entrada", cantidadpapas, 6000, "Papas a la francesa");
                    p1.add(unplato);
                    pedidocli.setEntradas(p1);
                    pedidocli.setValor(pedidocli.getValor() + 6000);
                }

                Log.i("llego", "menu3 Papas a la Francesa");

                paso.putExtra("pedidocliente", pedidocli);
                //startActivity(paso);

            }

        });

        ImageButton btcroquetas = (ImageButton)findViewById(R.id.croquetasboton);
        btcroquetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PedidoDbHelper db = new PedidoDbHelper(getApplicationContext());
                db.InsertarPedido();
                ;
                cantidadcroquetas = cantidadcroquetas + 1;

                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("crash", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response).getJSONObject("resultado");
                            String msg = jsonObject.getString("mensaje");

                            myAlert.setTitle("Pedidos!")
                                    .setMessage(msg)
                                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            yucas.setBottom(Integer.parseInt(" "));
                                            cantidad2.setText("");
                                            precio2.setText("");
                                        }
                                    })
                                    .create();
                            myAlert.show();

                            Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
                            toast.show();
                            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 45, 45);

                        } catch (JSONException e) {
                            Log.i("crash", e.toString());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("crash", error.toString());

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parametros = new HashMap<String, String>();
                        parametros.put("producto", String.valueOf("Croquetas de Yuca"));
                        parametros.put("cantidad", "20");
                        parametros.put("precio", "5000");
                        Log.i("crash", "parametros");
                        return parametros;
                    }
                };
                requestQueue.add(request);

                pedidocli = new MenuPlatos();
                Bundle extras = getIntent().getExtras();
                if(getIntent().getSerializableExtra("pedidocliente")!=null){


                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                }

                Intent paso = new Intent(MenuDigiital3.this, MenuDigitalPedir.class);
                paso.putExtra("pedidocliente", pedidocli);
                paso.putExtra("precio", 5000 );
                paso.putExtra("introduccion","5 croquetas de yuca con el toque de la casa ");
                paso.putExtra("observaciones","Observaciones:");
                paso.putExtra("comentarios","Comentarios");
                paso.putExtra("total","TOTAL=");
                paso.putExtra("foto", R.drawable.croquetas);
                startActivity(paso);



               /*  pedidocli = new Pedido();
                Bundle extras = getIntent().getExtras();*/
                if(getIntent().getSerializableExtra("pedidocliente")!=null){


                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getEntradas()==null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getEntradas();
                  /*  if (p1.size() > 0
                            ) {

                        Log.i("llego","menu2"+p1.get(0).getCantidad());
                    Log.i("llego","menu2"+p1.get(0).getProducto());
                    Log.i("llego","menu2"+p1.get(0).getTipo());
                    Log.i("llego","menu2"+p1.get(0).getPrecio());
                    }*/
                }

               // Intent paso1 = new Intent(MenuDigiital3.this, MenuD2Croquetas.class);
                int conta = 0;
                for (int i = 0; i < p1.size(); i++) {
                    Log.i("yucas", p1.get(i).getNombre());
                    if (p1.get(i).getNombre().equals("croquetas de yuca")) {
                        Log.i("yucas", "entro");
                        p1.get(i).setCantidad(cantidadcroquetas);
                        p1.get(i).setPrecio(cantidadcroquetas * 5000);
                        pedidocli.setEntradas(p1);
                        pedidocli.setValor(pedidocli.getValor() + 5000);
                        Log.i("yucas", p1.get(i).getCantidad() + " " + p1.get(i).getPrecio());
                        conta++;
                    }
                }

                if (conta == 0) {
                    Plato unplato = new Plato(02,"Entrada", cantidadcroquetas, 5000, "Croquetas de yuca");
                    p1.add(unplato);
                    pedidocli.setEntradas(p1);
                    pedidocli.setValor(pedidocli.getValor() + 5000);
                }

                Log.i("llego", "menu3 croquetas de yuca");

                paso.putExtra("pedidocliente", pedidocli);
                //startActivity(paso);
            }




        });


        ImageButton btpatacon = (ImageButton)findViewById(R.id.patacones);
        btpatacon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                cantidadpatacones=cantidadpatacones+1;

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
                                            patacones.setBottom(Integer.parseInt(" "));
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
                        parametros.put("producto",String.valueOf("Patacones") );
                        parametros.put("cantidad","10");
                        parametros.put("precio", "5000");
                        Log.i("crash", "parametros");
                        return parametros;
                    }
                };
                requestQueue.add(request);



                pedidocli = new MenuPlatos();
                Bundle extras = getIntent().getExtras();
                if(getIntent().getSerializableExtra("pedidocliente")!=null){


                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                }

                Intent paso = new Intent(MenuDigiital3.this, MenuDigitalPedir.class);
                paso.putExtra("pedidocliente", pedidocli);
                paso.putExtra("precio", 5000 );
                paso.putExtra("introduccion","5 deliciosos patacones con el toque de la casa");
                paso.putExtra("observaciones","Observaciones:");
                paso.putExtra("comentarios","Comentarios");
                paso.putExtra("total","TOTAL=");
                paso.putExtra("foto", R.drawable.pattacon);




                startActivity(paso);


                if(getIntent().getSerializableExtra("pedidocliente")!=null){


                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getEntradas()==null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getEntradas();


                }
                int conta = 0;
                for (int i = 0; i < p1.size(); i++) {
                    Log.i("Patacones", p1.get(i).getNombre());
                    if (p1.get(i).getNombre().equals("Patacones")) {
                        Log.i("Patacones", "entro");
                        p1.get(i).setCantidad(cantidadpatacones);
                        p1.get(i).setPrecio(cantidadpatacones * 5000);
                        //pedidocli.setEntradas1(p1);
                        pedidocli.setValor(pedidocli.getValor() + 5000);
                        Log.i("Patacones", p1.get(i).getCantidad() + " " + p1.get(i).getPrecio());
                        conta++;
                    }
                }

                if (conta == 0) {
                    Plato unplato = new Plato(03,"Entrada", cantidadpatacones, 5000, "Patacones");
                    p1.add(unplato);
                    pedidocli.setEntradas(p1);
                    pedidocli.setValor(pedidocli.getValor() + 5000);
                }

                Log.i("llego", "menu3 Patacones");

                paso.putExtra("pedidocliente", pedidocli);
                //startActivity(paso);
            }



        });

              //  Intent paso = new Intent(MenuDigiital3.this, MenuDigital2.class);



        ImageButton btarepa = (ImageButton)findViewById(R.id.imagenProducto);
        btarepa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                cantidadarepas=cantidadarepas+1;
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
                                            arepas.setBottom(Integer.parseInt(" "));
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
                        parametros.put("producto",String.valueOf("Arepas") );
                        parametros.put("cantidad","20");
                        parametros.put("precio", "5000");
                        Log.i("crash","parametros");
                        return parametros;
                    }
                };
                requestQueue.add(request);


                /* lo nuevo*/


                 pedidocli = new MenuPlatos();
                Bundle extras = getIntent().getExtras();
                if(getIntent().getSerializableExtra("pedidocliente")!=null){


                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                 }

                Intent paso = new Intent(MenuDigiital3.this, MenuDigitalPedir.class);
                paso.putExtra("pedidocliente", pedidocli);
                paso.putExtra("Precio", 5000 );
                paso.putExtra("Introduccion"," 5 Deliciosas arepas rellenas de queso.");
                paso.putExtra("Observaciones","Observaciones:");
                paso.putExtra("Comentarios","Comentarios");
                paso.putExtra("Total","TOTAL=");
                paso.putExtra("foto", R.drawable.arepas);
                startActivity(paso);




               /*  pedidocli = new Pedido();
                Bundle extras = getIntent().getExtras();*/
                if(getIntent().getSerializableExtra("pedidocliente")!=null){


                    pedidocli =(MenuPlatos) extras.getSerializable("pedidocliente");
                    if (pedidocli.getEntradas()==null)
                        p1 = new ArrayList<Plato>();
                    else
                        p1 = pedidocli.getEntradas();
                    /* p1= (ArrayList<Platos>) extras.getSerializable("pedidocliente");
                    if (p1.size() > 0
                            ) {

                        Log.i("llego", "menu2" + p1.get(0).getCantidad());
                        Log.i("llego", "menu2" + p1.get(0).getProducto());
                        Log.i("llego", "menu2" + p1.get(0).getTipo());
                        Log.i("llego", "menu2" + p1.get(0).getPrecio());


                    }*/
                }


               // Intent paso = new Intent(MenuDigiital3.this, MenuDigital2.class);
               // Pedido mipedio = new Pedido();
                int conta = 0;
                for (int i = 0; i < p1.size(); i++) {
                    Log.i("Arepas", p1.get(i).getNombre());
                    if (p1.get(i).getNombre().equals("Arepas")) {
                        Log.i("erepas", "entro");
                        p1.get(i).setCantidad(cantidadarepas);
                        p1.get(i).setPrecio(cantidadarepas * 5000);
                        //pedidocli.setEntradas1(p1);
                        pedidocli.setValor(pedidocli.getValor() + 5000);
                        Log.i("arepas", p1.get(i).getCantidad() + " " + p1.get(i).getPrecio());
                        conta++;
                    }
                }

                if (conta == 0) {
                    Plato unplato = new Plato(04,"Entrada", cantidadarepas, 5000, "Arepas");
                    p1.add(unplato);
                    pedidocli.setEntradas(p1);
                    pedidocli.setValor(pedidocli.getValor() + 5000);
                }

                Log.i("llego", "menu3 arepas");

                paso.putExtra("pedidocliente", pedidocli);
                //startActivity(paso);
            }



        });


regresar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent pasoo = new Intent(MenuDigiital3.this, MenuDigital2.class);
        pasoo.putExtra("pedidocliente", pedidocli);
        startActivity(pasoo);


    }

});
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_digiital3, menu);
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
