package com.example.sparkle.ui.backload;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.sparkle.MainActivity;
import com.example.sparkle.R;
import com.example.sparkle.SharedPreference;
import com.example.sparkle.ui.Login.LoginFragment;
import com.example.sparkle.ui.Login.RegisterFragment;
import com.example.sparkle.ui.Login.User;
import com.example.sparkle.ui.cleanerLogin.clnLoginFragment;

public class FrontLoginFragment extends Fragment {

    private FrontLoginViewModel mViewModel;

    Button btnLHirer, btnLFreelan,btnLReg;





    public static FrontLoginFragment newInstance() {
        return new FrontLoginFragment();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_front_login, container, false);



       btnLReg= view.findViewById(R.id.btnLReg);
        btnLFreelan = view.findViewById(R.id.btnLFreelan);
        btnLHirer = view.findViewById(R.id.btnLHirer);



        btnLReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrontRegisterFragment fragment = new FrontRegisterFragment();
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commit();

            }
        });

       btnLHirer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginFragment fragment = new LoginFragment();
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commit();

            }
        });

       btnLFreelan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clnLoginFragment fragment = new clnLoginFragment();
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commit();

            }
        });
        return view;

    }








    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FrontLoginViewModel.class);
        // TODO: Use the ViewModel
    }

}