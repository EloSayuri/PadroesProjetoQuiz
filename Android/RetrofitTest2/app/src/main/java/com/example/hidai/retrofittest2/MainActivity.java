package com.example.hidai.retrofittest2;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button asincrono = (Button) findViewById(R.id.btnCarregar);
        asincrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iRetrofitInterface3 githubUser = iRetrofitInterface3.retrofit.create(iRetrofitInterface3.class);
                final Call<List<Exam>> call = githubUser.getUsuario("Database Test");

                call.enqueue(new Callback<List<Exam>>() {
                    @Override
                    public void onResponse(Call<List<Exam>> call, Response<List<Exam>> response) {
                        int code = response.code();
                        System.out.println(code);

                        //Log.d("RESPOSTA",lista.get(0).exam);
                        if (code == 200) {
                            List<Exam> lista = response.body();
                            Log.i("TREs",lista.get(0).exam);
                            for (Exam ex : lista) {
                                //Log.i("EXAM TITLE: ",ex.login);
                                Toast.makeText(getBaseContext(), "Nome do usuário: " +
                                        ex.exam, Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getBaseContext(), "Falha: " + String.valueOf(code),
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Exam>> call, Throwable t) {
                        Log.i("ERRO HUM: ",t.toString());
                    }
                });
            }
        });

        Button asincrono2 = (Button) findViewById(R.id.btnCarregar2);
        asincrono2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iRetrofitInterface3 githubUser = iRetrofitInterface3.retrofit.create(iRetrofitInterface3.class);
                final Call call = githubUser.getL(new Login("nicholas.checan@gmail.com","123"));
                Log.i("OK: ", "chamada de rota ok");
                call.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        int code = response.code();
                        System.out.println(code);
                        if (code == 200) {
                            Login usuario = response.body();

                            Toast.makeText(getBaseContext(), "Login encontrado: ", Toast.LENGTH_LONG).show();
                        } else {
                            Login usuario = response.body();
                            //Log.i("ERRO DOIS: ",usuario.toString());
                            Toast.makeText(getBaseContext(), "Falha ao tentar encontrar login: " + String.valueOf(code),
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Log.i("ERRO dois: ",t.toString());
                        Toast.makeText(getBaseContext(), "Falha desconhecida ao tentar encontrar login",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
