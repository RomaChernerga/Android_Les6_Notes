package com.example.android_notes;

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

public class BodyNotesFragment extends Fragment {

    private static final String ARG_POSITION = "ARG_POSITION";

    private int position;

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

        TextView textView = view.findViewById(R.id. note_name);


        EditText editText = view.findViewById(R.id.body_note);
        editText.setBackgroundColor(Color.WHITE);
        editText.setTypeface(Typeface.SERIF);
        editText.setTextSize(20);





    }
}