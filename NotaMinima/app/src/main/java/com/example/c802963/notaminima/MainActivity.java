package com.example.c802963.notaminima;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Double notaPrimerCorte;
    private Double notaSegundoCorte;

    double notaMinima = 0;
    double acumulado = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void operar(View view){

        EditText txtNotaPrimerCorte = (EditText)findViewById(R.id.txtNotaPrimerCorte);
        EditText txtNotaSegundoCorte = (EditText)findViewById(R.id.txtNotaSegundoCorte);


        notaPrimerCorte = Double.parseDouble(txtNotaPrimerCorte.getText().toString() );

        notaSegundoCorte = Double.parseDouble(txtNotaSegundoCorte.getText().toString() );


        notaMinima = 3 - ( (notaPrimerCorte*0.3) + (notaSegundoCorte*0.3) );
        notaMinima = notaMinima/0.40;
        acumulado =  (notaPrimerCorte*0.3) + (notaSegundoCorte*0.3);

        ((TextView)findViewById(R.id.lblNotaMinima)).setText("Nota mínima: "+notaMinima);
        ((TextView)findViewById(R.id.lblNotaAcumulada)).setText("Nota acumulada: "+acumulado);


    }
}
