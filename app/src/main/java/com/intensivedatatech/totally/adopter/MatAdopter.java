package com.intensivedatatech.totally.adopter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.intensivedatatech.totally.AddmatActivity;
import com.intensivedatatech.totally.R;
import com.intensivedatatech.totally.helper.DatabaseHelper;
import com.intensivedatatech.totally.model.MatModel;

import java.util.ArrayList;

public class MatAdopter extends RecyclerView.Adapter<MatAdopter.ViewHolder> {
    private ArrayList<MatModel> data;
    DatabaseHelper db;

    public MatAdopter(ArrayList data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MatAdopter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_mat, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatAdopter.ViewHolder viewHolder, int i) {
        try {
            db = new DatabaseHelper(viewHolder.name_address.getContext());
            viewHolder.name_address.setText(data.get(i).getName_address());
            viewHolder.quantity.setText(String.valueOf(data.get(i).getPrice()));
            viewHolder.price.setText(String.valueOf(data.get(i).getQuantity()));
            viewHolder.totalprice.setText(String.valueOf(data.get(i).getTotalprice()));
            viewHolder.date.setText("Date: " + data.get(i).getDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView price, quantity, totalprice, name_address, date;
        Button btn;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.nool_wp);
            quantity = itemView.findViewById(R.id.nool_wq);
            totalprice = itemView.findViewById(R.id.nool_tp);
            name_address = itemView.findViewById(R.id.nool_pid);
            date = itemView.findViewById(R.id.noolv_date);
            btn = itemView.findViewById(R.id.mat_del);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("Are you sure you want to Delete?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    removeAt(getLayoutPosition());
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), AddmatActivity.class);
                    intent.putExtra("type", "update");
                    intent.putExtra("id", data.get(getLayoutPosition()).getId());
                    intent.putExtra("name", name_address.getText().toString());
                    intent.putExtra("quantity", quantity.getText().toString());
                    intent.putExtra("price", price.getText().toString());
                    intent.putExtra("totalprice", totalprice.getText().toString());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public void removeAt(int position) {

        try {
            boolean tt=db.deletemat(data.get(position).getId());
            if(tt){
                Log.d("delmat","working");
            }else{
                Log.d("delmat","not working");
            }
        } catch (Exception r) {
            r.printStackTrace();
        }
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size());
    }
}
