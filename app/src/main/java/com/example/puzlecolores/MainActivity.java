package com.example.puzlecolores;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    TextView tv3;
    String[] colores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);

        //cargamos los colores en el array de colores llamando a una clase que se llama getResouces
        colores= getResources().getStringArray(R.array.colores);
        Toast.makeText(this,"color rojo= "+ colores[0],Toast.LENGTH_SHORT).show();
        asignaColor(tv1);
        asignaColor(tv2);
        asignaColor(tv3);

    }



    public void asignaColor(TextView tv){
        Random r= new Random();
        int indexColor= r.nextInt(colores.length);
        tv.setBackgroundColor(Color.parseColor(colores[indexColor]));
        tv.setTag(indexColor);
    }
    public void onClickPanel(View v){
        v.startAnimation(AnimationUtils.loadAnimation(this,R.anim.girar));
        int indexColor=(int) v.getTag();

        if (indexColor==colores.length-1){
            indexColor=0;
        }else{
            indexColor++;
        }
        v.setBackgroundColor(Color.parseColor(colores[indexColor]));
        v.setTag(indexColor);

        if(todosIguales()){
            //Toast.makeText(this,"HAS GANADOOOO!!!!!",Toast.LENGTH_LONG).show();

            //esto siguiente no hace falta contarlo en el examen
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setTitle(R.string.fin);
            builder.setMessage(R.string.otraPartida);
            builder.setCancelable(false);
            builder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                        recreate();
                }
            });
            builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                  finish();
                }
            });

            AlertDialog alertDialog= builder.create();
            alertDialog.show();

        }
    }
    public boolean todosIguales(){
        ConstraintLayout cl= findViewById(R.id.clPaneles);
        int iPrimero=(int) tv1.getTag();
        TextView tv;
        for(int i=0;i<cl.getChildCount();i++){
            if(cl.getChildAt(i) instanceof TextView){
                tv=(TextView) cl.getChildAt(i);
                int index=(int) tv.getTag();
                if(index!=iPrimero){
                    return false;
                }
            }
        }
        return true;

    }
}