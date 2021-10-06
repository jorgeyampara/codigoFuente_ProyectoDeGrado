package co.chenao.stroopers;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import co.chenao.stroopers.actividades.AcercaDeActivity;
import co.chenao.stroopers.actividades.AjustesActivity;
import co.chenao.stroopers.fragments.InicioFragment;
import co.chenao.stroopers.interfaces.IComunicaFragments;
import co.chenao.stroopers.clases.PreferenciasJuego;

public class MainActivity extends AppCompatActivity implements IComunicaFragments, InicioFragment.OnFragmentInteractionListener{

    Fragment fragmentInicio,registroJugadorFragment,gestionJugadorFragment,rankingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // PreferenceManager.setDefaultValues(this,R.xml.preferencias,false);

        SharedPreferences preferences= android.preference.PreferenceManager.getDefaultSharedPreferences(this);
        PreferenciasJuego.obtenerPreferencias(preferences,getApplicationContext());

        System.out.println("COLOR MAIN");
        System.out.println("COLOR: "+PreferenciasJuego.colorTema);


        fragmentInicio =new InicioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();

    }

    public void onClick(View view) {

    }


    /*se controla la pulsacion del boton atras*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea salir de Stroopers?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }

        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void iniciarJuego() {
            Toast.makeText(getApplicationContext(),"PRUEBA",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void llamarAjustes() {
     //   Toast.makeText(getApplicationContext(),"Ajustes desde la actividad",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, AjustesActivity.class);
        startActivity(intent);
    }

    @Override
    public void consultarRanking() {
     //   Toast.makeText(getApplicationContext(),"Ranking desde la actividad",Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,rankingFragment).commit();
    }

    /*@Override
    public void consultarInstrucciones() {
     //   Toast.makeText(getApplicationContext(),"Ayuda desde la actividad",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, ContenedorInstruccionesActivity.class);
        startActivity(intent);
    }*/



    @Override
    public void consultarInformacion() {
     //   Toast.makeText(getApplicationContext(),"Informacion desde la actividad",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, AcercaDeActivity.class);
        startActivity(intent);
    }
}
