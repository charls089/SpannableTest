package com.example.spannabletest;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private String[] mItems = new String[0];
    private String mInput = "";

    private Context mContext;

    public MenuAdapter(Context context) {
        mContext = context;
    }

    public void setItems(String[] items) {
        mItems = items;
        Log.d("####", "setItems() --> mItems : " + Arrays.toString(mItems));
        notifyDataSetChanged();
    }

    public void setInputFilter(String[] items, String input) {
        mInput = input;
        setItems(items);
    }

    private void setHighlight(TextView textView, String input) {
        CharSequence cs = textView.getText();
        SpannableString spannableString = new SpannableString(cs);
        Matcher matcher = Pattern.compile(input).matcher(cs);
        while (matcher.find()) {
            spannableString.setSpan(new BackgroundColorSpan(ContextCompat.getColor(mContext, R.color.colorAccent)), matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(spannableString);
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        if (position < mItems.length) {
            String item = mItems[position];
            TextView tv = holder.tv_menu_title;
            tv.setText(item);
            setHighlight(tv, mInput);
        }
    }


    @Override
    public int getItemCount() {
        return mItems.length;
    }

    static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tv_menu_title;

        MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_menu_title = itemView.findViewById(R.id.tv_menu_title);
        }
    }
}
