package com.example.capsulas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;


public class    Principal1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal1);
        //Referencia al Toolbar
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        TabLayout tl = (TabLayout) findViewById(R.id.tablayout);
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                    //Codificar cosas a ejecutar cuando le das tap a un tab
                    int position = tab.getPosition();

                    switch (position) {
                    case 0:
                        //llamar al fragmento perfil
                        Perfil p = new Perfil();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,p).commit();
                        break;
                    case 1:
                            //llamar al fragmento empresas
                        Empresas e = new Empresas();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,e).commit();

                        break;
                    case 2:
                            //llamar al fragmento informacion
                        Informacion i = new Informacion();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,i).commit();
                        break;
                            

                }







            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                    //Codificar cosas a ejecutar cuando un tap deja de estar seleccionado
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                    //codificar cosas a ejecutar cuando un tab se vuelve a seleccionar
            }
        });


        //incorporamos el menu lateral
        NavigationView nav = (NavigationView) findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //recuperar la opcion del menu
                int id = item.getItemId(); //Recuperar el id de la opcion seleccionada
                if(id==R.id.op4){
                    Toast.makeText(getApplicationContext(), "Vas a reservas", Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.op5){
                    Toast.makeText(getApplicationContext(), "Vas a pedidos", Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.op6){
                    Toast.makeText(getApplicationContext(), "Vas a configuracion", Toast.LENGTH_SHORT).show();
                    Informacion i = new Informacion();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,i).commit();
                }
                else if(id==R.id.op7){
                    SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = datos.edit();
                    editor.remove("correo");
                    editor.apply();
                    finish();
                }

                return false;
            }
        });
        DrawerLayout dl = (DrawerLayout) findViewById(R.id.principal);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                dl,
                R.string.abrir_drawer,
                R.string.cerrar_drawer

        );
        dl.addDrawerListener(toggle);
        toggle.syncState();


        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dl.isDrawerOpen(GravityCompat.START)){
                    dl.closeDrawer(GravityCompat.START);
                }
                else{
                    dl.openDrawer((int) Gravity.START);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Incorporar el menu dentro de la activity
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId(); //Recuperar el id de la opcion seleccionada
        if(id==R.id.op1){
            Toast.makeText(this, "Vas al perfil", Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.op5){
            Toast.makeText(this, "Vas a los pedidos", Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.op6){
            Toast.makeText(this, "Vas a las configuraciones", Toast.LENGTH_SHORT).show();
        }



        return super.onOptionsItemSelected(item);
    }
}