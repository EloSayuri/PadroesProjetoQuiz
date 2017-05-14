package com.example.hidai.retrofittest2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hidai on 11/05/2017.
 */

public class ExamAdapter extends ArrayAdapter<ExamTest> {

    List<ExamTest> examList;
    Context  context;
    public ExamAdapter(Context context, int resource, List<ExamTest> list) {
        super(context, resource,list);
        this.examList = list;
        this.context = context;

    }
    static class LayoutHandler {
        TextView title,description;
        ImageView imagem;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mView = convertView;
        LayoutHandler layoutHandler;
        if(mView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = layoutInflater.inflate(R.layout.linha_exam, parent, false);

            layoutHandler = new LayoutHandler();
            layoutHandler.title = (TextView)mView.findViewById(R.id.txtTitle);
            layoutHandler.description = (TextView)mView.findViewById(R.id.txtDescLinha);
            layoutHandler.imagem = (ImageView)mView.findViewById(R.id.imgExam);

            mView.setTag(layoutHandler);
        }else{
            layoutHandler = (LayoutHandler)mView.getTag();
        }

        ExamTest examTest = getItem(position);
        Log.i("Edu",getItem(position).getDescription());
        layoutHandler.title.setText(examTest.title);
        layoutHandler.description.setText(examTest.description);
        layoutHandler.imagem.setImageResource(R.drawable.db); //Personalizar depois 


        return mView;
    }





}
