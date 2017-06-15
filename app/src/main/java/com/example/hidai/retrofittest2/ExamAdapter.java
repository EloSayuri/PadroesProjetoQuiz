package com.example.hidai.retrofittest2;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Hidai on 11/05/2017.
 */

public class ExamAdapter extends ArrayAdapter<ExamTest> {

    String nomeImg;
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

        layoutHandler.title.setText(examList.get(position).getTitle());
        layoutHandler.description.setText(examList.get(position).getDescription());


       //layoutHandler.imagem.setImageResource(R.drawable.imagem01); //Personalizar depois

        Resources res = context.getResources();
        String mDrawableName = examList.get(position).getImagem()+"";
        int resID = res.getIdentifier(mDrawableName , "drawable", context.getPackageName());
        Drawable drawable = res.getDrawable(resID );
        layoutHandler.imagem.setImageDrawable(drawable );

       /* AssetManager manager =  context.getAssets();

        // read a Bitmap from Assets
        InputStream open = null;
        try {

            InputStream inputStream = manager.open("res/drawable/"+examList.get(position).getImagem()+".jpg");
            //open = manager.open(examList.get(position).getImagem()+".jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            // Assign the bitmap to an ImageView in this layout

            layoutHandler.imagem.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (open != null) {
                try {
                    open.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/








        return mView;
    }





}
