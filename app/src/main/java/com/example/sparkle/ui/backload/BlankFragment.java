package com.example.sparkle.ui.backload;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sparkle.R;
import com.example.sparkle.ui.Job.JobFragment;
import com.example.sparkle.ui.Login.RegisterFragment;

public class BlankFragment extends Fragment {

    private BlankViewModel mViewModel;
    Button btnHire;

    public static BlankFragment newInstance() {
        return new BlankFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        btnHire= view.findViewById(R.id.btnHire);
        btnHire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JobFragment fragment = new JobFragment();
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commit();

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BlankViewModel.class);
        // TODO: Use the ViewModel
    }

}