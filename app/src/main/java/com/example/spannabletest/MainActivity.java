package com.example.spannabletest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private String[] mMenuArr;
    private MenuAdapter mMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvMenu = findViewById(R.id.rv_menu);
        mMenuAdapter = new MenuAdapter(getApplicationContext());
        mMenuArr = getResources().getStringArray(R.array.menu);
        mMenuAdapter.setItems(mMenuArr);
        rvMenu.setAdapter(mMenuAdapter);

        EditText etSearchInput = findViewById(R.id.et_search_input);
        etSearchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                String[] filterArr = setSearchTextHighlight(mMenuArr, input);
                mMenuAdapter.setInputFilter(filterArr, input);
            }
        });
    }

    private String[] setSearchTextHighlight(String[] textArr, String input) {
        if (TextUtils.isEmpty(input))
            return textArr;

        ArrayList<String> filterList = new ArrayList<>();
        for (String text : textArr) {
            Matcher matcher = Pattern.compile(input).matcher(text);
            if (matcher.find()) {
                filterList.add(text);
            }
        }
        Log.d("####", "filterList : " + filterList);
        return filterList.toArray(new String[0]);
    }
}
