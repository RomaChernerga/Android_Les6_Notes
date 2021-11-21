package com.example.android_notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initToolbar();  // метод для ActionBar
        initToolbar();  // метод для ActionBar

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container1, new NotesFragment())  // выбираем куда добавить и что добавить
                    .commit();
        } else {
            if (getSupportFragmentManager().getFragments().size() > 0) {
                getSupportFragmentManager().popBackStack();
            }
        }
        getSupportFragmentManager().popBackStack();

        findViewById(R.id.button_exit).setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setMessage("Вы действительно хотите закрыть приложение ?")
//                    .setTitle("Title")
                    .setIcon(R.drawable.icon_info)
                    .setPositiveButton("Да", (dialog, which) -> {
                        finish(); // обработчик кнопки

                        Toast.makeText(this, "Приложение закрыто", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Нет", (dialog, which) -> {
                        Toast.makeText(this, "Продолжаем работу", Toast.LENGTH_SHORT).show();
                    })
                    .show();
        });
    }

    private void initToolbar() {  // метод для ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(  //сэндвич
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView  = findViewById(R.id.navigation_view);




        navigationView.setNavigationItemSelectedListener(item -> {   // для работы кнопок в шторке

            int id = item.getItemId();
            if (id == R.id.action_about) {

                getSupportFragmentManager() // обработчик кнопки
                        .beginTransaction()
                        .addToBackStack("")
                        .add(R.id. fragment_container1, new AboutFragment())
                        .commit();
                drawerLayout.closeDrawers();  // для закрытия шторки
                return true;

            } else if (id == R.id.action_back) {
                onBackPressed();        // обработчик кнопки
                drawerLayout.closeDrawers();   // для закрытия шторки
                return true;
            }
            return false;
        });

    }

    @Override
    public void onBackPressed() {  // метод для возврата назад по кнопке

        if (getSupportFragmentManager().getFragments().size() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  //для отображения меню
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //для работы меню
        int id = item.getItemId();

        if (id == R.id.action_about) {
            getSupportFragmentManager() // обработчик кнопки
                    .beginTransaction()
                    .addToBackStack("")
                    .add(R.id. fragment_container1, new AboutFragment())
                    .commit();
            return true;
        } else if (id == R.id.action_back) {
            onBackPressed();        // обработчик кнопки
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}