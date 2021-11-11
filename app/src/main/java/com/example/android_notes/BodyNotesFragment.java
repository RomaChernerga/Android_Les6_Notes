package com.example.android_notes;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BodyNotesFragment extends Fragment {


    public static final String PREFERENCES_NAME = "main";
    public static final String SAVE_NOTE = "save_note";
    
    private static final String ARG_POSITION = "ARG_POSITION";
    private int position = -1;  // позиция по умолчанию


    public BodyNotesFragment() {
        // Required empty public constructor
    }

    public static BodyNotesFragment newInstance(int position) {
        BodyNotesFragment fragment = new BodyNotesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_body_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText editText = view.findViewById(R.id.body_note);  // текстовое поле заметки
        editText.setBackgroundColor(Color.WHITE);       // текстовое поле заметки
        editText.setTypeface(Typeface.SERIF);       // текстовое поле заметки
        editText.setTextSize(20);       // текстовое поле заметки

        Button note_back = view.findViewById(R.id.note_back);
        note_back.setOnClickListener(v -> {
            requireActivity()
                    .getSupportFragmentManager()
                    .popBackStack(); // для возврата на предыдущий фрагмент

        });
        Button note_save = view.findViewById(R.id.note_save);

        note_save.setOnClickListener(v -> {


        });











    }


}