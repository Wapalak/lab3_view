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

public class CustomAdapterNew extends RecyclerView.Adapter<CustomAdapterNew.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList<String> student_id, student_name, student_surname;

    CustomAdapterNew(Activity activity, Context context, ArrayList<String> student_id,
                     ArrayList<String> student_name, ArrayList<String> student_surname) {
        this.activity = activity;
        this.context = context;
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_surname = student_surname;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_new, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.student_id_txt.setText(String.valueOf(student_id.get(holder.getAdapterPosition())));
        holder.student_name_txt.setText(String.valueOf(student_name.get(holder.getAdapterPosition())));
        holder.student_surname_txt.setText(String.valueOf(student_surname.get(holder.getAdapterPosition())));

        // Recyclerview onClickListener (if needed)
        // You can add an onClickListener for items if required.
    }

    @Override
    public int getItemCount() {
        return student_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView student_id_txt, student_name_txt, student_surname_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            student_id_txt = itemView.findViewById(R.id.student_id_txt_new);
            student_name_txt = itemView.findViewById(R.id.student_name_txt_new);
            student_surname_txt = itemView.findViewById(R.id.student_surname_txt_new);
            mainLayout = itemView.findViewById(R.id.mainLayoutNew);
        }
    }
}