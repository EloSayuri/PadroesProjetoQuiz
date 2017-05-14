package com.example.hidai.retrofittest2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExamFragment extends Fragment {


    public ExamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_exam, container, false);
        // obter a view do fragmento
        View view = inflater.inflate(R.layout.fragment_novo, container, false);
        // linkar com o componente da view
        //ListView lista = (ListView) view.findViewById(R.id.lvExam);
        //ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),R.array.itens_exames, android.R.layout.simple_list_item_1);
        //lista.setAdapter(adapter);



        return view;
    }

}
