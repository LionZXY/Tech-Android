package com.techopark.fragment_example;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

public class ItemFragment extends BaseFragment {

    private static final String def_number = "0";
    private static final int def_color = Color.BLACK;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Context context = Objects.requireNonNull(getActivity()).getApplicationContext();
        FrameLayout frameLayout = new FrameLayout(context);
        Bundle bundle = getArguments();
        String number = def_number;
        int color = def_color;
        if (bundle != null) {
            color = bundle.getInt("SaveColor");
            number = bundle.getString("SaveInt");
        }

        TextView textView = new TextView(context);
        textView.setText(number);
        if (color == Color.RED)
            textView.setTextColor(Color.RED);
        else if (color == Color.BLUE)
            textView.setTextColor(Color.BLUE);
        else
            textView.setTextColor(Color.BLACK);
        textView.setTextSize(100);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        layoutParams.topMargin = 50;
        textView.setLayoutParams(layoutParams);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        frameLayout.addView(textView);

        Button back = new Button(context);
        back.setText("Back");
        back.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT));
        back.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        back.setOnClickListener(v -> {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentManager.popBackStack();
            fragmentTransaction.commit();
        });
        frameLayout.addView(back);
        return frameLayout;
    }
}
