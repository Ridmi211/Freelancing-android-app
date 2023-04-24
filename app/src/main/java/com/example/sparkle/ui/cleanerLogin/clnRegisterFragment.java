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
import android.widget.Toast;

import com.example.sparkle.R;
import com.example.sparkle.SharedPreference;
import com.example.sparkle.ui.Login.LoginFragment;
import com.example.sparkle.ui.Login.RegisterFragment;
import com.example.sparkle.ui.Login.RegisterViewModel;
import com.example.sparkle.ui.Login.User;

public class clnRegisterFragment extends Fragment {

    private ClnRegisterViewModel mViewModel;
    SQLiteDatabase db;
    EditText edtClnName,edtClnNumber,edtClnEmail,edtClnPassword;
    Button regClButton;


    public static clnRegisterFragment newInstance() {
        return new clnRegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cln_register, container, false);
        edtClnName = view.findViewById(R.id.edtClnName);
        edtClnNumber = view.findViewById(R.id.edtClnNumber);
        edtClnEmail = view.findViewById(R.id.edtClnEmail);
        edtClnPassword = view.findViewById(R.id.edtClnPassword);
        regClButton= view.findViewById(R.id.regClButton);

        regClButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDB();
                clnUser cleaner = new clnUser();
                cleaner.setName(edtClnName.getText().toString());
                cleaner.setContact(edtClnNumber.getText().toString());
                cleaner.setEmail(edtClnEmail.getText().toString());
                cleaner.setPassword(edtClnPassword.getText().toString());
                cleaner.Save(db);
                SharedPreference preference=new SharedPreference();
                preference.SaveString(view.getContext(),cleaner.getName(),SharedPreference.KEY_USER_NAME);


                clnLoginFragment fragment = new clnLoginFragment();
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
        mViewModel = new ViewModelProvider(this).get(ClnRegisterViewModel.class);
        // TODO: Use the ViewModel
    }


    private void setDB(){
        try{
            db = getActivity().openOrCreateDatabase("JobDB", Context.MODE_PRIVATE,null);
            db.execSQL("Create Table if not exists Cleaner(id integer primary key Autoincrement,name text,contact text,email text unique,password text )");
        }
        catch (Exception ex){
            Toast.makeText(getActivity().getApplicationContext(),"Error in DB" +ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}