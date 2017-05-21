package com.example.hidai.retrofittest2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_novo, container, false);

        // obter a view do fragmento
        View view = inflater.inflate(R.layout.fragment_novo, container, false);
        // linkar com o componente da view

        final EditText bLogin = (EditText) view.findViewById(R.id.txtLogin);
        final EditText bPassword = (EditText) view.findViewById(R.id.txtPassword);

        Button asincrono2 = (Button) view.findViewById(R.id.btnLogin);
        asincrono2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iRetrofitInterface3 githubUser = iRetrofitInterface3.retrofit.create(iRetrofitInterface3.class);
                final Call call = githubUser.getL(new Login(bLogin.getText().toString(),bPassword.getText().toString()));
                Log.i("OK: ", "chamada de rota ok");
                call.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        int code = response.code();
                        System.out.println(code);
                        if (code == 200) {
                            Login usuario = response.body();

                            Toast.makeText(getActivity().getApplicationContext(), "Login encontrado: ", Toast.LENGTH_LONG).show();

                            //  NESSE PONTO CHAMA OUTRO FRAGMENTO
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ExamFragment()).commit();
                        } else {
                            Login usuario = response.body();
                            //Log.i("ERRO DOIS: ",usuario.toString());
                            Toast.makeText(getActivity().getApplicationContext(), "Falha ao tentar encontrar login: " + String.valueOf(code),
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Log.i("ERRO dois: ",t.toString());
                        Toast.makeText(getActivity().getApplicationContext(), "Falha desconhecida ao tentar encontrar login",
                                Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        return view;
    }

}
