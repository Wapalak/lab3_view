package com.example.lab1test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList<String> student_id, student_name, student_surname, student_phone, student_blood_group, student_birth_date;

    CustomAdapter(Activity activity, Context context, ArrayList<String> student_id,
                  ArrayList<String> student_name, ArrayList<String> student_surname,
                  ArrayList<String> student_phone, ArrayList<String> student_blood_group,
                  ArrayList<String> student_birth_date){
        this.activity = activity;
        this.context = context;
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_surname = student_surname;
        this.student_phone = student_phone;
        this.student_blood_group = student_blood_group;
        this.student_birth_date = student_birth_date;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.student_id_txt.setText(String.valueOf(student_id.get(holder.getAdapterPosition())));
        holder.student_name_txt.setText(String.valueOf(student_name.get(holder.getAdapterPosition())));
        holder.student_surname_txt.setText(String.valueOf(student_surname.get(holder.getAdapterPosition())));
        holder.student_phone_txt.setText(String.valueOf(student_phone.get(position)));
        holder.student_blood_group_txt.setText(String.valueOf(student_blood_group.get(position)));
        holder.student_birth_date_txt.setText(String.valueOf(student_birth_date.get(position)));

        // Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", student_id.get(holder.getAdapterPosition()));
                intent.putExtra("name", student_name.get(holder.getAdapterPosition()));
                intent.putExtra("surname", student_surname.get(holder.getAdapterPosition()));
                intent.putExtra("phone", student_phone.get(holder.getAdapterPosition()));
                intent.putExtra("blood", student_blood_group.get(holder.getAdapterPosition()));
                intent.putExtra("bDate", student_birth_date.get(holder.getAdapterPosition()));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return student_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView student_id_txt, student_name_txt, student_surname_txt, student_phone_txt,
                student_blood_group_txt, student_birth_date_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            student_id_txt = itemView.findViewById(R.id.student_id_txt);
            student_name_txt = itemView.findViewById(R.id.student_name_txt);
            student_surname_txt = itemView.findViewById(R.id.student_surname_txt);
            student_phone_txt = itemView.findViewById(R.id.student_phone_txt);
            student_blood_group_txt = itemView.findViewById(R.id.student_blood_group_txt);
            student_birth_date_txt = itemView.findViewById(R.id.student_birth_date_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
        }
    }
}












//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
//
//    private Context context;
//    private ArrayList student_id, student_name, student_surname;
//    CustomAdapter(Context context, ArrayList student_id, ArrayList student_name, ArrayList student_surname){
//        this.context = context;
//        this.student_id = student_id;
//        this.student_name = student_name;
//        this.student_surname = student_surname;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.my_row, parent, false);
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
//        holder.student_id_txt.setText(String.valueOf(student_id.get(position)));
//        holder.student_name_txt.setText(String.valueOf(student_name.get(position)));
//        holder.student_surname_txt.setText(String.valueOf(student_surname.get(position)));
//        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int adapterPosition = holder.getAdapterPosition();
//                if (adapterPosition != RecyclerView.NO_POSITION) {
//                    Intent intent = new Intent(context, UpdateActivity.class);
//                    intent.putExtra("id", String.valueOf(student_id.get(adapterPosition)));
//                    intent.putExtra("name", String.valueOf(student_name.get(adapterPosition)));
//                    intent.putExtra("surname", String.valueOf(student_surname.get(adapterPosition)));
//                    context.startActivity(intent);
//                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return student_id.size();
//    }
//
//    class MyViewHolder extends RecyclerView.ViewHolder {
//
//        TextView student_id_txt, student_name_txt, student_surname_txt;
//        LinearLayout mainLayout;
//
//
//        MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            student_id_txt = itemView.findViewById(R.id.student_id_txt);
//            student_name_txt = itemView.findViewById(R.id.student_name_txt);
//            student_surname_txt = itemView.findViewById(R.id.student_surname_txt);
//            mainLayout = itemView.findViewById(R.id.mainLayout);
//        }
//    }
//}
