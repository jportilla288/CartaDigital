package com.carta.dx.cartadigital;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


/*

distribuciones red tube
* El que lea es estupido
*
*
* */



public class MenuDigitalPedir extends Activity {
    private ImageButton mas, menos, ok;
    private ImageView imagenProduct;
    private TextView cantidad, total, precio, observaciones, introduccion;
    Pedido pedidocli;
    int canti = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_digital_pedir);

        mas = (ImageButton) findViewById(R.id.mas);
        menos = (ImageButton) findViewById(R.id.menos);
        ok = (ImageButton) findViewById(R.id.ok);
        cantidad = (TextView) findViewById(R.id.cantidad);
        total = (TextView) findViewById(R.id.total);
        precio = (TextView) findViewById(R.id.precio);
        observaciones = (TextView) findViewById(R.id.observaciones);
        introduccion = (TextView) findViewById(R.id.introduccion);
        imagenProduct = (ImageView) findViewById(R.id.imagenProducto);

        String introduction= getIntent().getStringExtra("Introduccion");
        final int precios= getIntent().getIntExtra("Precio",0);
        String tot= getIntent().getStringExtra("Total");
        String observ= getIntent().getStringExtra("Observaciones");
        String cant= getIntent().getStringExtra("Cantidad");
        int imageButon=getIntent().getIntExtra("foto",0);

        Log.i("llegooo",imageButon+" "+precios+" "+introduction+" "+observ+""+tot);

        imagenProduct.setImageResource(imageButon);
        imagenProduct.setMinimumHeight(400);
        imagenProduct.setMaxHeight(400);
        introduccion.setText(introduction);
        precio.setText(precios+"");
        cantidad.setText(cant);
        observaciones.setText(observ);
        total.setText(tot);



        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canti--;
                cantidad.setText(canti+" ");
                int total =precios*canti;
                precio.setText(total+"");

                if (canti < 0) {
                    cantidad.setText(0+"");
                    precio.setText(0+"");;
                }
            }
        });

        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canti++;
                cantidad.setText(canti+"");
                int total =precios*canti;
                precio.setText(total+"");
            }
        });


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paso = new Intent(MenuDigitalPedir.this, MenuDigiital3.class);
                paso.putExtra("pedidocliente", pedidocli);
                startActivity(paso);
            }
        });

   /*     pedidocli = (Pedido) getIntent().getSerializableExtra("pedidocliente");
        ArrayList<Platos> entrada = pedidocli.getEntradas();

        boolean encontro = false;
        for (int k = 0; k < entrada.size(); k++) {
            if (entrada.get(k).getProducto().equals("Arepas")) {
                entrada.get(k).setCantidad(canti + entrada.get(k).getCantidad());
                entrada.get(k).setPrecio(canti * 5000 + entrada.get(k).getPrecio());
                encontro = true;
            }

        }
        if (!encontro) {
            entrada.add(new Platos("entradas", canti, 5000 * canti, "nombre"));
        }
        pedidocli.setEntradas(entrada);

        for (int i = 0; i < pedidocli.getEntradas().size(); i++)
            Log.i("cosas", pedidocli.getEntradas().get(i).getProducto() + " " + pedidocli.getEntradas().get(i).getPrecio());

*/

    }

}
