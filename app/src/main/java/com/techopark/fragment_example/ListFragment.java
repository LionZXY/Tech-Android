package com.techopark.fragment_example;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class ListFragment extends BaseFragment {

    private static final String SaveString = "SaveString";
    private static final String SaveColor = "SaveColor";
    private static final String SaveInt = "SaveInt";
    private ArrayList<Integer> list = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            list.addAll(Objects.requireNonNull(savedInstanceState.getIntegerArrayList(SaveString)));
        } else {
            for (int i = 1; i <= 100; i++)
                list.add(i);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        RelativeLayout relativeLayout = new RelativeLayout(Objects.requireNonNull(getActivity()).getApplicationContext());
        RecyclerView recyclerView = new RecyclerView(Objects.requireNonNull(getActivity()).getApplicationContext());
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        else
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        MyAdapter myAdapter = new MyAdapter(list, currentItem -> {
            ItemFragment fragment2 = new ItemFragment();
            Bundle bundle = new Bundle();
            bundle.putString(SaveInt, (String) currentItem.getText());
            bundle.putInt(SaveColor, currentItem.getCurrentTextColor());
            fragment2.setArguments(bundle);
            FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment, fragment2);
            transaction.addToBackStack(null);
            transaction.commit();
        });
        recyclerView.setAdapter(myAdapter);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = 48;
        recyclerView.setLayoutParams(layoutParams);

        Button button = new Button(Objects.requireNonNull(getActivity()).getApplicationContext());
        button.setText(R.string.add);
        button.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT)
        );
        button.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        button.setOnClickListener(v -> {
            list.add(list.size() + 1);
            myAdapter.notifyDataSetChanged();
        });
        relativeLayout.addView(button);
        relativeLayout.addView(recyclerView);
        return relativeLayout;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putIntegerArrayList(SaveString, list);
        super.onSaveInstanceState(outState);
    }
}


