package com.carta.dx.cartadigital;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;

public class MetodoPago9 extends Activity {


    Pedido pedidocli;
    private ImageButton efectivo, tarjeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodo_pago9);



            ImageButton efectivo = (ImageButton) findViewById(R.id.efectivobtn);

            efectivo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ;
                    Intent paso = new Intent(MetodoPago9.this, LlenarDatosyPedirCuenta10.class);
                    startActivity(paso);
                }
            });

            ImageButton tarjeta = (ImageButton)findViewById(R.id.tarjetabtn);
            tarjeta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {;
                    Intent paso = new Intent(MetodoPago9.this,LlenarDatosyPedirCuenta10.class);
                    startActivity(paso);

                }
            });


        }

    }