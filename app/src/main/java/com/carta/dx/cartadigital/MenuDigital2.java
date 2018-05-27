package com.carta.dx.cartadigital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MenuDigital2 extends AppCompatActivity {

    MenuPlatos pedidocli;
    ArrayList<Plato> p1 = new ArrayList<Plato>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_digital2);

//asi se recibe con el bundle y el get serializable
         pedidocli = new MenuPlatos();
        Bundle extras = getIntent().getExtras();
        if(getIntent().getSerializableExtra("pedidocliente")!=null){

            pedidocli= (MenuPlatos) extras.getSerializable("pedidocliente");

try {
    Log.i("prueba", "llego " + pedidocli.getEntradas().get(0).getNombre());
    Log.i("prueba", "llego " + pedidocli.getPlatoFuerte().get(0).getNombre());
    Log.i("prueba", "llego " + pedidocli.getPostres().get(0).getNombre());
    Log.i("prueba", "llego " + pedidocli.getBebidas().get(0).getNombre());
}catch (Exception e){

}


        }


        ImageButton btentradas = (ImageButton)findViewById(R.id.entradas);
        btentradas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                Intent paso = new Intent(MenuDigital2.this, MenuDigiital3.class);
                paso.putExtra("pedidocliente", pedidocli);
                startActivity(paso);


            }
        });


        ImageButton pfuerte = (ImageButton)findViewById(R.id.platoFuerte);
        pfuerte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {;
                Intent paso = new Intent(MenuDigital2.this,MenuDigital4 .class);
                paso.putExtra("pedidocliente", pedidocli);
                startActivity(paso);

            }
        });

        ImageButton btbebidas = (ImageButton)findViewById(R.id.bebidas);
        btbebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {;
                Intent paso = new Intent(MenuDigital2.this,MenuDigital5 .class);
                paso.putExtra("pedidocliente", pedidocli);
                startActivity(paso);

            }
        });

        ImageButton btpostre = (ImageButton)findViewById(R.id.postres);
        btpostre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                Intent paso = new Intent(MenuDigital2.this, MenuDigital6.class);
                paso.putExtra("pedidocliente", pedidocli);
                startActivity(paso);

            }
        });

        ImageButton btpedir = (ImageButton)findViewById(R.id.pedido);
        final MenuPlatos finalPedidocli = pedidocli;
        btpedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {;
                Intent paso = new Intent(MenuDigital2.this,MenuDigiital7 .class);
               // Pedido mipedio = new Pedido();
               // pedidocli.setEntradas("Papas a la francesa");
              //  paso.putExtra("pedidocliente", finalPedidocli);
                paso.putExtra("pedidocliente", pedidocli);
                startActivity(paso);

            }
        });

        ImageButton cuenta = (ImageButton)findViewById(R.id.Cuenta);
        cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                Intent paso = new Intent(MenuDigital2.this, Cuenta8.class);
                paso.putExtra("pedidocliente", pedidocli);
                startActivity(paso);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_digital2, menu);
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
