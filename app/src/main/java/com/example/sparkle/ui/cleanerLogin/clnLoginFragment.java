package com.example.sparkle.ui.cleanerLogin;

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
import com.example.sparkle.ui.Login.LoginFragment;
import com.example.sparkle.ui.Login.LoginViewModel;
import com.example.sparkle.ui.Login.RegisterFragment;
import com.example.sparkle.ui.Login.User;
import com.example.sparkle.ui.backload.BlankFragment;
import com.example.sparkle.ui.backload.ClnBlankFragment;
import com.example.sparkle.ui.backload.FrontRegisterFragment;
import com.example.sparkle.ui.jobApplyCleaner.ApplyJobFragment;

public class clnLoginFragment extends Fragment {


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
        View view = inflater.inflate(R.layout.fragment_cln_login, container, false);

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
                clnUser cleaner = new clnUser();
                cleaner.setEmail(edtEmail.getText().toString());
                cleaner.setPassword(edtPW.getText().toString());
                if(cleaner.CheckLogin(db))
                {

                    preference.SaveBool(view.getContext(),true, SharedPreference.KEY_STATUS);
                    preference.SaveBool(view.getContext(),true, SharedPreference.CLEANER);
                    FragmentTransaction trans =getActivity().getSupportFragmentManager().beginTransaction();
                    ClnBlankFragment fragment = new  ClnBlankFragment();
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




    //setting db
    private void setDB(){
        try{
            db = getActivity().openOrCreateDatabase("JobDB", Context.MODE_PRIVATE,null);
            db.execSQL("Create Table if not exists Cleaner(id integer primary key Autoincrement,name text,contact text ,email textunique,password text )");
        }
        catch (Exception ex){
            Toast.makeText(getActivity().getApplicationContext(),"Error in DB" +ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}