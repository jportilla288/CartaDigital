package com.carta.dx.cartadigital;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cuenta8 extends AppCompatActivity {

    public TextView mostrarped;
    private RequestQueue requestQueue;
    String pedudi;
    private Context ctx;
    private AlertDialog.Builder myAlert;
    Pedido pedidocli;
    private String consultarUrl = "http://172.17.0.17/MenuDigitalNueva/CrearPedido.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_cuenta8);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        ImageButton cuenta=(ImageButton)findViewById(R.id.Cuenta);
        
        cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent paso =new Intent(getApplicationContext(),MetodoPago9.class);
                paso.putExtra("pedidocliente", pedidocli);
                startActivity(paso);
            }
        });




        Pedido pedidocli = new Pedido();
        ArrayList<Platos> p1 = new ArrayList<Platos>();

        Bundle extras = getIntent().getExtras();
        if(getIntent().getSerializableExtra("pedidocliente")!=null){

            //p1= (ArrayList<Platos>) extras.getSerializable("pedidocliente");
            pedidocli = (Pedido) extras.getSerializable("pedidocliente");

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
                Log.i("precioo",pedidocli.getEntradas().get(0).getPrecio()+"");


                for (int i = 0; i < pedidocli.getEntradas().size(); i++) {

                    pedudi =pedudi+pedidocli.getEntradas().get(i).getCantidad()+" "+pedidocli.getEntradas().get(i).getProducto()+" "+pedidocli.getEntradas().get(i).getPrecio()+"\n";

                    totalEntradas = pedidocli.getEntradas().get(i).getPrecio() + totalEntradas;
                }

                pedudi=pedudi+"\n "+"total entradas "+totalEntradas+"\n ";
                Log.i("pedudi",pedidocli.getEntradas().get(0).getPrecio() + "  "+totalEntradas);
            }


            if(pedidocli.getPlatoFuerte()!=null) {

                for (int i = 0; i < pedidocli.getPlatoFuerte().size(); i++) {

                    pedudi = pedudi + pedidocli.getPlatoFuerte().get(i).getCantidad() + " " + pedidocli.getPlatoFuerte().get(i).getProducto() + " " + pedidocli.getPlatoFuerte().get(i).getPrecio() + "\n";

                    totalplatoFuerte = pedidocli.getPlatoFuerte().get(i).getPrecio() + totalplatoFuerte;
                }
                pedudi = pedudi + "total plato fuerte " + totalplatoFuerte + "\n ";
            }



            if(pedidocli.getBebidas()!=null) {

                for (int i = 0; i < pedidocli.getBebidas().size(); i++) {
                    pedudi = pedidocli.getBebidas().get(i).getCantidad() + " " + pedidocli.getBebidas().get(i).getProducto() + " " + pedidocli.getBebidas().get(i).getPrecio() + "\n";

                    totalBebidas = pedidocli.getBebidas().get(i).getPrecio() + totalBebidas;
                }
                pedudi = pedudi + "total Bebidas " + totalBebidas + "\n ";
            }

            if(pedidocli.getPostres()!=null) {


                for (int i = 0; i < pedidocli.getPostres().size(); i++) {
                    pedudi = pedidocli.getPostres().get(i).getCantidad() + " " + pedidocli.getPostres().get(i).getProducto() + " " + pedidocli.getPostres().get(i).getPrecio() + "\n";

                    totalPostres = pedidocli.getPostres().get(i).getPrecio() + totalPostres;
                }
                pedudi = pedudi + "total postres " + totalPostres + "\n ";
            }


            pedudi=pedudi.replace("null","");
            //pedudi=pedudi.replace("0","");
            pedudi=pedudi + '\n'+" Valor Total = "+pedidocli.getValor();


            /*
            Log.i("llego", "menu7 Centradas" + pedidocli.getEntradas());
            Log.i("llego", "menu7 Cplatofuerte" + pedidocli.getPlatoFuerte());
            Log.i("llego", "menu7 bebidas" + pedidocli. getBebidas());
            Log.i("llego", "menu7 postres" + pedidocli. getPostres());*/
        }

        mostrarped=(TextView) findViewById(R.id.mostrarped);
        mostrarped.setText(pedudi);
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
                parametros.put("Producto",mostrarped.getText().toString() );
                parametros.put("Mesa","1");
                parametros.put("Pago","no");
                parametros.put("Valor", "");
                Log.i("crash", "parametros "+mostrarped.getText().toString());
                return parametros;
            }
        };
        requestQueue.add(request);





    }

}
