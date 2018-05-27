package com.carta.dx.cartadigital;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Selection;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Chronometer;
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


public class MenuDigiital7 extends AppCompatActivity {

    public ImageButton enviar;
   public Chronometer crono;
    public TextView mostrarped;
   private long Time=0;
    JSONArray tooodo;
    private RequestQueue requestQueue;

    private String consultarUrl = "http://172.17.0.17/MenuDigitalNueva/CrearPedido.php";


    private Context ctx;
    private AlertDialog.Builder myAlert;

    String pedudi;
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_digiital7);

        requestQueue = Volley.newRequestQueue(getApplicationContext());



        MenuPlatos pedidocli = new MenuPlatos();
        ArrayList<Plato> p1 = new ArrayList<Plato>();
        
        Bundle extras = getIntent().getExtras();
        if(getIntent().getSerializableExtra("pedidocliente")!=null){

            //p1= (ArrayList<Platos>) extras.getSerializable("pedidocliente");
            pedidocli = (MenuPlatos) extras.getSerializable("pedidocliente");

          /*  try {
                 tooodo = new JSONArray(pedidocli);
            } catch (JSONException e) {
                e.printStackTrace();
            }*/

            assert pedidocli != null;
            /*pedudi =pedidocli.getEntradas().get(0).getCantidad()+" "+pedidocli.getEntradas().get(0).getProducto()+" "+pedidocli.getEntradas().get(0).getPrecio()
                    + " \n "+ pedidocli.getPlatoFuerte().get(0).getCantidad()+" "+pedidocli.getPlatoFuerte().get(0).getProducto()+" "+pedidocli.getPlatoFuerte().get(0).getPrecio()
                    + " \n "+ pedidocli.getBebidas().get(0).getCantidad()+" "+pedidocli.getBebidas().get(0).getProducto()+" "+pedidocli.getBebidas().get(0).getPrecio()
                    + "\n " + pedidocli.getPostres().get(0).getCantidad()+" "+pedidocli.getPostres().get(0).getProducto()+" "+pedidocli.getPostres().get(0).getPrecio();*/



//for para que me muestre la cantidad y los precios totales por platos
            int totalEntradas=0;
            int totalplatoFuerte=0;
            int totalBebidas=0;
            int totalPostres=0;



            if(pedidocli.getEntradas()!=null) {
                Log.i("precio",pedidocli.getEntradas().get(0).getPrecio()+"");


                for (int i = 0; i < pedidocli.getEntradas().size(); i++) {

                    pedudi = pedudi + pedidocli.getEntradas().get(i).getCantidad()+1+"     "+pedidocli.getEntradas().get(i).getNombre()+"    "+pedidocli.getEntradas().get(i).getPrecio()+ " \n ";

                    totalEntradas = pedidocli.getEntradas().get(i).getPrecio() + totalEntradas ;
                }

                pedudi= pedudi + " "+ " total entradas "+totalEntradas+"\n ";
                Log.i("pedudi",pedidocli.getEntradas().get(0).getPrecio() + "  "+totalEntradas);
            }


            if(pedidocli.getPlatoFuerte()!=null) {

                for (int i = 0; i < pedidocli.getPlatoFuerte().size(); i++) {

                    pedudi = pedudi + pedidocli.getPlatoFuerte().get(i).getCantidad() + "   " + pedidocli.getPlatoFuerte().get(i).getNombre() + "   " + pedidocli.getPlatoFuerte().get(i).getPrecio() + "\n";

                    totalplatoFuerte = pedidocli.getPlatoFuerte().get(i).getPrecio() + totalplatoFuerte;
                }
                pedudi = pedudi + "total plato fuerte " + totalplatoFuerte + "\n ";
                Log.i("pedudi",pedidocli.getPlatoFuerte().get(0).getPrecio() + "  "+totalplatoFuerte);

            }



            if(pedidocli.getBebidas()!=null) {

                for (int i = 0; i < pedidocli.getBebidas().size(); i++) {
                    pedudi = pedidocli.getBebidas().get(i).getCantidad() + "    " + pedidocli.getBebidas().get(i).getNombre() + "    " + pedidocli.getBebidas().get(i).getPrecio() + "\n";

                    totalBebidas = pedidocli.getBebidas().get(i).getPrecio() + totalBebidas;
                }
                pedudi = pedudi + "total Bebidas " + totalBebidas + " \n ";
                Log.i("pedudi",pedidocli.getBebidas().get(0).getPrecio() + "  "+totalBebidas + " \n" );

            }

            if(pedidocli.getPostres()!=null) {


                for (int i = 0; i < pedidocli.getPostres().size(); i++) {
                    pedudi = pedidocli.getPostres().get(i).getCantidad() + "    " + pedidocli.getPostres().get(i).getNombre() + "    " + pedidocli.getPostres().get(i).getPrecio() + "\n";

                    totalPostres = pedidocli.getPostres().get(i).getPrecio() + totalPostres;
                }
                pedudi = pedudi + "total postres " + totalPostres + " \n ";
                Log.i("pedudi",pedidocli.getPostres().get(0).getPrecio() + "  "+totalPostres);

            }


            pedudi=pedudi.replace("null", " \n");
            //pedudi=pedudi.replace("0","");
           // pedudi=pedudi + '\n'+" Valor Total = "+pedidocli.getValor();


            /*
            Log.i("llego", "menu7 Centradas" + pedidocli.getEntradas());
            Log.i("llego", "menu7 Cplatofuerte" + pedidocli.getPlatoFuerte());
            Log.i("llego", "menu7 bebidas" + pedidocli. getBebidas());
            Log.i("llego", "menu7 postres" + pedidocli. getPostres());*/
        }

        mostrarped=(TextView) findViewById(R.id.mostrarped);
        mostrarped.setText(pedudi);

        crono= (Chronometer)findViewById(R.id.cronometro);


        crono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer cArg) {
                long time = SystemClock.elapsedRealtime() - cArg.getBase();
                int h = (int) (time / 3600000);
                int m = (int) (time - h * 3600000) / 60000;
                int s = (int) (time - h * 3600000 - m * 60000) / 1000;
                String hh = h < 10 ? "0" + h : h + "";
                String mm = m < 10 ? "0" + m : m + "";
                String ss = s < 10 ? "0" + s : s + "";
                cArg.setText(hh + ":" + mm + ":" + ss);

                Log.i("crono", hh + ":" + mm + ":" + ss);
            }
        });
        crono.setBase(SystemClock.elapsedRealtime());


        enviar=(ImageButton)findViewById(R.id.enviar);
       enviar.setEnabled(true);
        //terminado.setEnabled(false);
        enviar.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {

                                          enviar.setEnabled(false);
                                    //      terminado.setEnabled(true);
                                          crono.setBase(SystemClock.elapsedRealtime());
                                          crono.start();



                                              StringRequest request = new StringRequest(Request.Method.POST, consultarUrl, new Response.Listener<String>() {
                                                  @Override
                                                  public void onResponse(String response) {
                                                      Log.i("crash",response);
                                                      try {
                                                          JSONObject jsonObject = new JSONObject(response).getJSONObject("resultado");
                                                          String msg = jsonObject.getString("mensaje");



                                                          Toast toast = Toast.makeText(ctx,msg, Toast.LENGTH_SHORT);
                                                          toast.show();
                                                          toast.setGravity(Gravity.CENTER | Gravity.CENTER, 45, 45);

                                                      } catch (JSONException e) { //  JSON es una forma de mostrar datos
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
                                                      parametros.put("Producto",mostrarped.getText().toString() );
                                                      parametros.put("Mesa","1");
                                                      parametros.put("cantidad","1");
                                                      parametros.put("tipo", "");
                                                      parametros.put("duracion", "");
                                                      parametros.put("pedido", tooodo+"");
                                                      Log.i("crash", "parametros "+mostrarped.getText().toString());
                                                      return parametros;
                                                  }
                                              };
                                              requestQueue.add(request);

                                /*          JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                                  consultarUrl, new Response.Listener<JSONObject>() {
                                              @Override
                                              public void onResponse(JSONObject response) {

                                              }
                                          });*/

                                                  // JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                                  //       consultarUrl, new Response.Listener<JSONObject>() {

                        /*try {
                            JSONArray pedido = response.getJSONArray("pedido");
                            for (int i = 0; i < pedido.length(); i++) {
                                JSONObject ped = pedido.getJSONObject(i);

                                String producto = ped.getString("producto");
                                String cantidad = ped.getString("cantidad");
                                String precio = ped.getString("precio");

                                mostrarped.append(producto + " " + cantidad + "\n");
                            }
                            mostrarped.append("===\n");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/
                                      }
        });

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_digiital7, menu);
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
