package com.example.hidai.retrofittest2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.value;


/**
 * A simple {@link Fragment} subclass.
 */
public class TempExamFragment extends Fragment {

    private ExamList ex;


    public TempExamFragment() {
        // Required empty public constructor
    }

    //ArrayList<ExamTest> listEx = new ArrayList<ExamTest>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_temp_exam, container, false);

        iRetrofitInterface3 githubUser = iRetrofitInterface3.retrofit.create(iRetrofitInterface3.class);
        //final Call call = githubUser.getExam(new ExamTest("Nicholas","Database Test"));
        final Call call = githubUser.getExams();
        Log.i("OK: ", "chamada de rota ok");
        call.enqueue(new Callback<List<ExamTest>>() {
            @Override
            public void onResponse(Call<List<ExamTest>> call, Response<List<ExamTest>> response) {
                int code = response.code();
                System.out.println(code);
                final List<ExamTest> list = response.body();
                ListView listView = (ListView) view.findViewById(R.id.listExam);
                ArrayAdapter adapter = new ExamAdapter(getActivity(), R.layout.linha_exam,list);
                listView.setAdapter(adapter);
                //patientsListController.patientAdapter = new PatientAdapter(getActivity(), R.layout.patient_row, patientsListController.mPatientList);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        //Passar dados para outro fragmento
                        Bundle args = new Bundle();
                        args.putSerializable("exAuthor", list.get(position).getAuthor());
                        args.putSerializable("exTitle", list.get(position).getTitle());
                        Fragment toFragmentQuestion = new QuestionFragment();
                        toFragmentQuestion.setArguments(args);
                        //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new QuestionFragment()).commit();
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_container, toFragmentQuestion)
                                .commit();


                    }
                });

            }

            @Override
            public void onFailure(Call<List<ExamTest>> call, Throwable t) {
                Log.i("ERRO NA LISTA: ",t.toString());

            }
        });





        //Creating a rest adapter




        System.out.println("Antes do return");
        return view;
    }

}
