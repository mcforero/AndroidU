package itc.pdm.memoitc;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.view.View;
import android.widget.Toast;

public class Memorama extends AppCompatActivity implements View.OnClickListener{

    ImageButton imbCarta1, imbCarta2, imbCarta3, imbCarta4, imbCarta5, imbCarta6, imbCarta7, imbCarta8;
    int[] imagenes = {R.mipmap.firefox,R.mipmap.gnobots2,R.mipmap.gnome,R.mipmap.wireshark};
    int[] juego = new int[8];
    int[] cartas_selecionadas = new int[2];
    int[] imagenes_selecionadas = new int[2];
    int[] juego_terminado = new int[8];
    int turno=0, ganador=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorama);

        imbCarta1 = (ImageButton) findViewById(R.id.imbCarta1);
        imbCarta1.setOnClickListener(this);
        imbCarta2 = (ImageButton) findViewById(R.id.imbCarta2);
        imbCarta2.setOnClickListener(this);
        imbCarta3 = (ImageButton) findViewById(R.id.imbCarta3);
        imbCarta3.setOnClickListener(this);
        imbCarta4 = (ImageButton) findViewById(R.id.imbCarta4);
        imbCarta4.setOnClickListener(this);
        imbCarta5 = (ImageButton) findViewById(R.id.imbCarta5);
        imbCarta5.setOnClickListener(this);
        imbCarta6 = (ImageButton) findViewById(R.id.imbCarta6);
        imbCarta6.setOnClickListener(this);
        imbCarta7 = (ImageButton) findViewById(R.id.imbCarta7);
        imbCarta7.setOnClickListener(this);
        imbCarta8 = (ImageButton) findViewById(R.id.imbCarta8);
        imbCarta8.setOnClickListener(this);

        if (savedInstanceState == null)
        {
            asignarImagenes();
            for (int i=0;i<8;i++)
                juego_terminado[i]=0;

            cartas_selecionadas[0]=8;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_memorama, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.acercade) {
            Intent acercade = new Intent(this, Acercade.class);
            startActivity(acercade);
            return true;
        }

        if (id == R.id.juegonuevo) {
            Intent memorama = new Intent(this, Memorama.class);
            startActivity(memorama);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void asignarImagenes()
    {
        int posicion, contador = 0;

        for (int i=0;i<4;)
        {
            posicion = (int)(Math.random()*8);

            if(juego[posicion]==0) {
                juego[posicion] = imagenes[i];
                contador++;

                if(contador == 2)
                {
                    i++;
                    contador=0;
                }

            }
        }
    }

    @Override
    public void onClick(View v){

        switch (v.getId())
        {
            case R.id.imbCarta1:
                imbCarta1.setImageResource(juego[0]);
                imagenes_selecionadas[turno]=juego[0];
                cartas_selecionadas[turno]=0;
                imbCarta1.setEnabled(false);
                break;
            case R.id.imbCarta2:
                imbCarta2.setImageResource(juego[1]);
                imagenes_selecionadas[turno]=juego[1];
                cartas_selecionadas[turno]=1;
                imbCarta2.setEnabled(false);
                break;
            case R.id.imbCarta3:
                imbCarta3.setImageResource(juego[2]);
                imagenes_selecionadas[turno]=juego[2];
                cartas_selecionadas[turno]=2;
                imbCarta3.setEnabled(false);
                break;
            case R.id.imbCarta4:
                imbCarta4.setImageResource(juego[3]);
                imagenes_selecionadas[turno]=juego[3];
                cartas_selecionadas[turno]=3;
                imbCarta4.setEnabled(false);
                break;
            case R.id.imbCarta5:
                imbCarta5.setImageResource(juego[4]);
                imagenes_selecionadas[turno]=juego[4];
                cartas_selecionadas[turno]=4;
                imbCarta5.setEnabled(false);
                break;
            case R.id.imbCarta6:
                imbCarta6.setImageResource(juego[5]);
                imagenes_selecionadas[turno]=juego[5];
                cartas_selecionadas[turno]=5;
                imbCarta6.setEnabled(false);
                break;
            case R.id.imbCarta7:
                imbCarta7.setImageResource(juego[6]);
                imagenes_selecionadas[turno]=juego[6];
                cartas_selecionadas[turno]=6;
                imbCarta7.setEnabled(false);
                break;
            case R.id.imbCarta8:
                imbCarta8.setImageResource(juego[7]);
                imagenes_selecionadas[turno]=juego[7];
                cartas_selecionadas[turno]=7;
                imbCarta8.setEnabled(false);
        }

        if(turno==0) {
            turno = 1;
        }else{
            new Hilo().execute();
            turno=0;
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle estado) {
        super.onSaveInstanceState(estado);
        estado.putInt("TURNO", turno);
        estado.putInt("GANADOR", ganador);
        estado.putIntArray("ARREGLO_JUEGO", juego);
        estado.putIntArray("ARREGLO_JUEGO_TERMINADO", juego_terminado);
        estado.putIntArray("ARREGLO_CARTAS_SELECCIONADAS", cartas_selecionadas);
        estado.putIntArray("ARREGLO_IMAGENES_SELECCIONADAS", imagenes_selecionadas);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        turno = savedInstanceState.getInt("TURNO");
        ganador = savedInstanceState.getInt("GANADOR");
        juego = savedInstanceState.getIntArray("ARREGLO_JUEGO");
        juego_terminado = savedInstanceState.getIntArray("ARREGLO_JUEGO_TERMINADO");
        cartas_selecionadas = savedInstanceState.getIntArray("ARREGLO_CARTAS_SELECCIONADAS");
        imagenes_selecionadas = savedInstanceState.getIntArray("ARREGLO_IMAGENES_SELECCIONADAS");

        if (juego_terminado[0] == 1 || cartas_selecionadas[0]==0)
        {
            imbCarta1.setImageResource(juego[0]);
            imbCarta1.setEnabled(false);
        }

        if (juego_terminado[1] == 1 || cartas_selecionadas[0]==1)
        {
            imbCarta2.setImageResource(juego[1]);
            imbCarta2.setEnabled(false);
        }

        if (juego_terminado[2] == 1 || cartas_selecionadas[0]==2)
        {
            imbCarta3.setImageResource(juego[2]);
            imbCarta3.setEnabled(false);
        }

        if (juego_terminado[3] == 1 || cartas_selecionadas[0]==3)
        {
            imbCarta4.setImageResource(juego[3]);
            imbCarta4.setEnabled(false);
        }

        if (juego_terminado[4] == 1 || cartas_selecionadas[0]==4)
        {
            imbCarta5.setImageResource(juego[4]);
            imbCarta5.setEnabled(false);
        }

        if (juego_terminado[5] == 1 || cartas_selecionadas[0]==5)
        {
            imbCarta6.setImageResource(juego[5]);
            imbCarta6.setEnabled(false);
        }

        if (juego_terminado[6] == 1 || cartas_selecionadas[0]==6)
        {
            imbCarta7.setImageResource(juego[6]);
            imbCarta7.setEnabled(false);
        }

        if (juego_terminado[7] == 1 || cartas_selecionadas[0]==7)
        {
            imbCarta8.setImageResource(juego[7]);
            imbCarta8.setEnabled(false);
        }
    }

    class Hilo extends AsyncTask<Void,Integer,Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            imbCarta1.setEnabled(false);
            imbCarta2.setEnabled(false);
            imbCarta3.setEnabled(false);
            imbCarta4.setEnabled(false);
            imbCarta5.setEnabled(false);
            imbCarta6.setEnabled(false);
            imbCarta7.setEnabled(false);
            imbCarta8.setEnabled(false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                    Thread.sleep(300);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(imagenes_selecionadas[0]==imagenes_selecionadas[1])
            {
                juego_terminado[cartas_selecionadas[0]]=1;
                juego_terminado[cartas_selecionadas[1]]=1;

                ganador++;

                if(ganador==4){
                    Toast.makeText(getApplicationContext(),"!Felicidades has ganado¡",Toast.LENGTH_SHORT).show();
                }

            }

            if (juego_terminado[0] == 0)
            {
                imbCarta1.setImageResource(R.mipmap.games);
                imbCarta1.setEnabled(true);
            }

            if (juego_terminado[1] == 0)
            {
                imbCarta2.setImageResource(R.mipmap.games);
                imbCarta2.setEnabled(true);
            }

            if (juego_terminado[2] == 0)
            {
                imbCarta3.setImageResource(R.mipmap.games);
                imbCarta3.setEnabled(true);
            }

            if (juego_terminado[3] == 0)
            {
                imbCarta4.setImageResource(R.mipmap.games);
                imbCarta4.setEnabled(true);
            }

            if (juego_terminado[4] == 0)
            {
                imbCarta5.setImageResource(R.mipmap.games);
                imbCarta5.setEnabled(true);
            }

            if (juego_terminado[5] == 0)
            {
                imbCarta6.setImageResource(R.mipmap.games);
                imbCarta6.setEnabled(true);
            }

            if (juego_terminado[6] == 0)
            {
                imbCarta7.setImageResource(R.mipmap.games);
                imbCarta7.setEnabled(true);
            }

            if (juego_terminado[7] == 0)
            {
                imbCarta8.setImageResource(R.mipmap.games);
                imbCarta8.setEnabled(true);
            }

            cartas_selecionadas[0]=8;

        }
    }

}