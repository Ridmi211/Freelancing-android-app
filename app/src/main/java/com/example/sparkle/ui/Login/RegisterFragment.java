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
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sparkle.R;
import com.example.sparkle.SharedPreference;

public class RegisterFragment extends Fragment {


    private RegisterViewModel mViewModel;
    SQLiteDatabase db;
    EditText edtUName,edtUEmail,edtPassword;
    Button regButton;


    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        edtUName = view.findViewById(R.id.edtUName);
        edtUEmail = view.findViewById(R.id.edtUEmail);
        edtPassword = view.findViewById(R.id.edtPassword);
        regButton= view.findViewById(R.id.regButton);

       regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDB();
                User user = new User();
                user.setName(edtUName.getText().toString());
                user.setEmail(edtUEmail.getText().toString());
                user.setPassword(edtPassword.getText().toString());
                user.Save(db);
                SharedPreference preference=new SharedPreference();
                preference.SaveString(view.getContext(),user.getName(),SharedPreference.KEY_USER_NAME);


                LoginFragment fragment = new LoginFragment();
                FragmentTransaction trans =getActivity().getSupportFragmentManager().beginTransaction();
                trans.replace(R.id.nav_host_fragment_content_main, fragment);
                trans.addToBackStack(null);
                trans.commit();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
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