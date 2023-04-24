package com.example.sparkle.ui.backload;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sparkle.R;
import com.example.sparkle.ui.Job.JobFragment;
import com.example.sparkle.ui.jobApplyCleaner.ApplyJobFragment;

public class ClnBlankFragment extends Fragment {

    private ClnBlankViewModel mViewModel;

    Button btnFindJob;

    public static ClnBlankFragment newInstance() {
        return new ClnBlankFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cln_blank, container, false);

        btnFindJob= view.findViewById(R.id.btnFindJob);
        btnFindJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplyJobFragment fragment = new ApplyJobFragment();
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commit();

            }
        });
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ClnBlankViewModel.class);
        // TODO: Use the ViewModel
    }

}