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
import com.example.sparkle.ui.Login.LoginFragment;
import com.example.sparkle.ui.Login.RegisterFragment;
import com.example.sparkle.ui.cleanerLogin.clnLoginFragment;
import com.example.sparkle.ui.cleanerLogin.clnRegisterFragment;

public class FrontRegisterFragment extends Fragment {

    private FrontRegisterViewModel mViewModel;
    Button btnRHirer, btnRFreelan;

    public static FrontRegisterFragment newInstance() {
        return new FrontRegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_front_register, container, false);




        btnRFreelan = view.findViewById(R.id.btnRFreelan);
        btnRHirer = view.findViewById(R.id.btnRHirer);





        btnRHirer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterFragment fragment = new RegisterFragment();
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commit();

            }
        });

        btnRFreelan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clnRegisterFragment fragment = new clnRegisterFragment();
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commit();

            }
        });
        return view;

    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FrontRegisterViewModel.class);
        // TODO: Use the ViewModel
    }

}