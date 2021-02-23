package com.example.cmss.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmss.Attendance;
import com.example.cmss.R;
import com.example.cmss.model.Users;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private  static  final  String Tag = "RecyclerView";
    private Context context;
    private ArrayList<Users> usersArrayList;


    public RecyclerAdapter(Context context, ArrayList<Users> usersArrayList) {
        this.context = context;
        this.usersArrayList = usersArrayList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userlist,parent,false);
        return  new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        //Textview

        holder.textView.setText(usersArrayList.get(position).getName());

        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(context, Attendance.class);
                //intent.putExtra("Creaters",holder.textView.getText());
                //intent.putExtra("joiner",1);
               // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return usersArrayList.size() ;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        //Widgets

        TextView textView;
        TextView textView1;

        Button button,button1;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            textView1 = itemView.findViewById(R.id.textView1);

            button = itemView.findViewById(R.id.delete);
            button1 =itemView.findViewById(R.id.attendance);
        }


    }

}



