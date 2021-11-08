package com.example.android_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container1, new NotesFragment())  // выбираем куда добавить и что добавить
                    .commit();
        } else {
            if (getSupportFragmentManager().getFragments().size() > 0){
                getSupportFragmentManager().popBackStack();
            }
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onBackPressed() {  // метод для возврата назад по кнопке

        if (getSupportFragmentManager().getFragments().size() > 0){
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

}