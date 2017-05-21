package com.example.hidai.retrofittest2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {


    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        final TextView txtFinal = (TextView) view.findViewById(R.id.txtMensagemFinal);
        final TextView txtCerta = (TextView) view.findViewById(R.id.txtCertas);
        final TextView txtErrada = (TextView) view.findViewById(R.id.txtErradas);
        final TextView txtmsnCerta = (TextView) view.findViewById(R.id.txtMensagemCertas);
        final TextView txtmsnErrda = (TextView) view.findViewById(R.id.txtMensageErradas);


        //Argumentos vindo da classe QuestionFragment via bundle
        Bundle args = getArguments();
        final int  certas  = (int)args.getSerializable("certas");
        final int  erradas  = (int)args.getSerializable("erradas");

        txtCerta.setText(String.valueOf(certas));
        txtErrada.setText(String.valueOf(erradas));
        txtmsnCerta.setText("Acertou:");
        txtmsnErrda.setText("Errou:");

        if(certas > erradas && erradas == 0){
            txtFinal.setText("Parabéns, Acertou todas");
        }else if(certas > erradas){
            txtFinal.setText("Parabéns, Acertou a maioria");
        }else if(erradas > certas && certas == 0){
            txtFinal.setText("Que descepção errou todas");
        }else if(erradas > certas){
            txtFinal.setText("Precisa melhorar, Errou a maioria");
        }else{
            txtFinal.setText("Meio a Meio");
        }


        //Carregar proxima questão do mesmo autor
        Button btnCarregar = (Button) view.findViewById(R.id.btnFinalizar);

        btnCarregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ExamFragment()).commit();

            }
        });





        return view;
    }

}
