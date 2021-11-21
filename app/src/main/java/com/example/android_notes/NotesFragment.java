package com.example.android_notes;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class NotesFragment extends Fragment {

    public static final String CURRENT_POS = "CURRENT_POS"; // введене переменной для сохранения картинки при смене ориентации
    private int currentPosition = -1;

    public NotesFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(CURRENT_POS,-1);
        }
        
        initView(view);
        updateBackground();
    }



    private void initView(View view) {

        LinearLayout linearLayout = view.findViewById(R.id.notes_container);
//        TextView textView2 = new TextView(getContext());
//        textView2.setText("Список заметок");
//        textView2.setTextSize(25);           // выставляем размер текста
//        textView2.setTextColor(Color.BLACK); // выставляем цвет текста
//        linearLayout.addView(textView2);

        String[] notes = getResources().getStringArray(R.array.notes);  //создаем массив строк для списка, что формировали в файле array
        for (int i = 0; i < notes.length; i++) {  // проходимся по массиву заметок
            String note = notes[i];
            TextView textView = new TextView(getContext()); // создаем текстовый элемент

            final int position = i;

            textView.setText(note);

            textView.setTextSize(20);           // выставляем размер текста
            textView.setTextColor(Color.BLACK); // выставляем цвет текста

            textView.setOnClickListener(v -> {  // задаем команду при клике на текст
                currentPosition = position; // сохранение картинки в данной позиции
                showBody(position);
                updateBackground();
            });

            linearLayout.addView(textView);
        }
        if (currentPosition != -1) {
            showBody(currentPosition);
        }
    }
    private void updateBackground() {  // метод для подкрашивания строк при выборке и обновления закрашивания строк при выборке

        LinearLayout linearLayout = getView().findViewById(R.id.notes_container);

        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            linearLayout.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
            if(currentPosition == i) {  //если картинка = клик, то текст закрашиваем синим
                linearLayout.getChildAt(i).setBackgroundColor(Color.BLUE);
            }
        }
    }

    void showBody(int position) {
        BodyNotesFragment bodyNotesFragment = BodyNotesFragment.newInstance(position);

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide, R.anim.slide)
                .add(R.id.fragment_container1,bodyNotesFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) { // метод для сохранения рисунка при смене ориентации
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_POS, currentPosition);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}