package com.example.sparkle.ui.Login;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sparkle.MainActivity;
import com.example.sparkle.R;
import com.example.sparkle.SharedPreference;
import com.example.sparkle.ui.backload.BlankFragment;
import com.example.sparkle.ui.backload.FrontRegisterFragment;

public class LoginFragment extends Fragment {




    private LoginViewModel mViewModel;
    EditText edtEmail,edtPW;
    TextView txvWelcome;
    Button btnLogin,btnLogin_reg;
    SQLiteDatabase db;
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        edtEmail = view.findViewById(R.id.edtLogEmailCln);
        edtPW = view.findViewById(R.id.edtLogPasswordCln);
        txvWelcome = view.findViewById(R.id.txvWelcomeCln);
        btnLogin = view.findViewById(R.id.btnLoginCln);
        btnLogin_reg = view.findViewById(R.id.btnLogin_regCln);


        SharedPreference preference= new SharedPreference();
        String name = preference.GetString(view.getContext(),SharedPreference.KEY_USER_NAME);
        if (name!=null){
            txvWelcome.setText("Welcome "+name);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDB();
                User user = new User();
                user.setEmail(edtEmail.getText().toString());
                user.setPassword(edtPW.getText().toString());
                if(user.CheckLogin(db))
                {
                    preference.SaveString(view.getContext(),edtEmail.getText().toString(),SharedPreference.USER_EMAIL);
                    preference.SaveBool(view.getContext(),true, SharedPreference.KEY_STATUS);
                    preference.SaveBool(view.getContext(),true, SharedPreference.CUSTOMER);
                    FragmentTransaction trans =getActivity().getSupportFragmentManager().beginTransaction();
                    BlankFragment fragment = new BlankFragment();
                    trans.replace(R.id.nav_host_fragment_content_main, fragment);
                    trans.addToBackStack(null);
                    trans.commit();



                }else{

                    Toast.makeText(getActivity().getApplicationContext(),"Wrong email or password!", Toast.LENGTH_LONG).show();

                }


            }
        });

        btnLogin_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrontRegisterFragment fragment = new FrontRegisterFragment();
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commit();

            }
        });

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel
    }





    private void setDB(){
        try{
            db = getActivity().openOrCreateDatabase("JobDB", Context.MODE_PRIVATE,null);
            db.execSQL("Create Table if not exists User(id integer primary key Autoincrement,name text,email text unique,password text )");
        }
        catch (Exception ex){
            Toast.makeText(getActivity().getApplicationContext(),"Error in DB" +ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}