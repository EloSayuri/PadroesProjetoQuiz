package com.example.hidai.retrofittest2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {

    int contQ = 0;
    int contA =0;
    int contCertas =0;
    int contErrdas = 0;
    List<ExamTest> list;
    List<Answer> resposta = new ArrayList<>();//Inicializo com arraylist, por algum motivo não posso iniciar com null

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_question, container, false);

        View view = inflater.inflate(R.layout.fragment_question, container, false);

        final TextView tituloExam = (TextView) view.findViewById(R.id.txtTypeQuestion);
        final TextView tituloQuestion = (TextView) view.findViewById(R.id.txtTitleQuestion);
        final RadioButton rd1 = (RadioButton) view.findViewById(R.id.rd1);
        final RadioButton rd2 = (RadioButton) view.findViewById(R.id.rd2);
        final RadioButton rd3 = (RadioButton) view.findViewById(R.id.rd3);
        final RadioButton rd4 = (RadioButton) view.findViewById(R.id.rd4);
        final RadioGroup rdGroup = (RadioGroup) view.findViewById(R.id.rdGroup);

       //Argumentos vindo da classe ExamFragment via bundle
        Bundle args = getArguments();
        final String  exAuthor  = (String)args.getSerializable("exAuthor");
        final String  exTitle  = (String)args.getSerializable("exTitle");

        tituloExam.setText(exTitle);

        iRetrofitInterface3 githubUser = iRetrofitInterface3.retrofit.create(iRetrofitInterface3.class);
        final Call call = githubUser.getExam(new ExamTest(exAuthor,exTitle));
        //final Call call = githubUser.getExams();

        call.enqueue(new Callback<List<ExamTest>>() {
            @Override
            public void onResponse(Call<List<ExamTest>> call, Response<List<ExamTest>> response) {
                int code = response.code();
                //System.out.println(code);
                if (code == 200) {

                    list = response.body();

                    Log.i("teste",String.valueOf(list.size()));

                    //Executa apenas uma vez para iniciar Q e A
                    for (ExamTest examtext:list) {//pegar exame de um autor
                        System.out.println(examtext.author);
                        System.out.println(examtext.description);
                        for(Question quesion:examtext.getQuestions()){

                            tituloQuestion.setText(examtext.getQuestions().get(contQ).getQuestion());

                                if(contA == contQ) {
                                    rd1.setText(quesion.getAnswers().get(0).getAnswer());
                                    rd2.setText(quesion.getAnswers().get(1).getAnswer());
                                    rd3.setText(quesion.getAnswers().get(2).getAnswer());
                                    rd4.setText(quesion.getAnswers().get(3).getAnswer());
                                    contA++;

                                    }
                                for(Answer answer:quesion.getAnswers()){ //Criar lista de resposta certas
                                        if(answer.getCorrect().equals(true)){
                                            resposta.add(answer);
                                        }
                                }
                        }
                    }
                    Toast.makeText(getActivity().getApplicationContext(), "Quiz Iniciado: ", Toast.LENGTH_LONG).show();
                } else {

                    //Log.i("ERRO DOIS: ",usuario.toString());
                    Toast.makeText(getActivity().getApplicationContext(), "Falha ao tentar encontrar exame: " + String.valueOf(code),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<ExamTest>> call, Throwable t) {
                Log.i("ERRO dois: ",t.toString());
                Toast.makeText(getActivity().getApplicationContext(), "Falha desconhecida ao tentar encontrar exame",
                        Toast.LENGTH_LONG).show();
            }
        });


        //Carregar proxima questão do mesmo autor
        Button btnCarregar = (Button) view.findViewById(R.id.btnNext);

        btnCarregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                contQ++; //usado para trocar de questõa
                contA = 0; //usado para trocar as respostas

                //Terceiro verifica se o que foi selecionado esta certo
                switch (rdGroup.getCheckedRadioButtonId()) {
                    case R.id.rd1:
                        for (Answer answer:resposta) {
                            if(answer.getAnswer().equals(rd1.getText())){
                                System.out.println("RD1 CERTA");
                                contCertas++;
                            }else{
                                continue;
                            }
                        }
                        break;
                    case R.id.rd2:
                        for (Answer answer:resposta) {
                            if(answer.getAnswer().equals(rd2.getText())){
                                System.out.println("RD2 CERTA");
                                contCertas++;
                            }else{
                                continue;
                            }
                        }
                        break;
                    case R.id.rd3:
                        for (Answer answer:resposta) {
                            if(answer.getAnswer().equals(rd3.getText())){
                                System.out.println("RD3 CERTA");
                                contCertas++;
                            }else{
                                continue;
                            }
                        }
                        break;
                    case R.id.rd4:
                        for (Answer answer:resposta) {
                            if(answer.getAnswer().equals(rd4.getText())){
                                System.out.println("RD4 CERTA");
                                contCertas++;
                            }else{
                                continue;
                            }
                        }
                        break;
                    default:
                        Toast.makeText(getActivity().getApplicationContext(), "Não foi selecionado nenhuma resposta nessa pergunta",
                                Toast.LENGTH_LONG).show();
                }rdGroup.clearCheck(); //Limpa os radios

                //Primeiro corre o array preenchendo as classes
                for (ExamTest examtext:list) {//pegar exame de um autor
                    //System.out.println(examtext.author);
                    //System.out.println(examtext.description);
                    for(Question quesion:examtext.getQuestions()){
                        if(contQ >= examtext.getQuestions().size()){
                            contErrdas = examtext.getQuestions().size() - contCertas;
                            Toast.makeText(getActivity().getApplicationContext()
                                    ,"Quiz finalizado " + "Numero de acertos: "+contCertas+" Erradas "+ contErrdas,
                                    Toast.LENGTH_LONG).show();

                            //Passar dados para outro fragmento - ResultFragment
                            Bundle args = new Bundle();
                            args.putSerializable("certas", contCertas);
                            args.putSerializable("erradas", contErrdas);
                            Fragment toResultFragment = new ResultFragment();
                            toResultFragment.setArguments(args);
                            //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new QuestionFragment()).commit();
                            getFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.frame_container, toResultFragment)
                                    .commit();
                            break;
                        }

                        tituloQuestion.setText(examtext.getQuestions().get(contQ).getQuestion());//Set o titulo da questão

                        //Segundo preenche os radios
                        if(contA == contQ) { //Não  foi necessario usar a classe Answer aqui, pq na Question há um array de answers
                            rd1.setText(quesion.getAnswers().get(0).getAnswer());
                            rd2.setText(quesion.getAnswers().get(1).getAnswer());
                            rd3.setText(quesion.getAnswers().get(2).getAnswer());
                            rd4.setText(quesion.getAnswers().get(3).getAnswer());
                            contA++;
                            break;
                        }contA++;

                    }
                }
            }
        });

        return view;
    }

}
