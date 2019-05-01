package com.intensivedatatech.totally.adopter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.LongDef;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.intensivedatatech.totally.AdduserActivity;
import com.intensivedatatech.totally.R;
import com.intensivedatatech.totally.helper.DatabaseHelper;
import com.intensivedatatech.totally.model.PartyModel;

import java.util.ArrayList;

public class UserAdopter extends RecyclerView.Adapter<UserAdopter.ViewHolder> {
    private ArrayList<PartyModel> data;
    DatabaseHelper db;

    public UserAdopter(ArrayList data) {
        this.data = data;
    }

    @NonNull
    @Override
    public UserAdopter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_party, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserAdopter.ViewHolder viewHolder, int i) {
        db = new DatabaseHelper(viewHolder.partyname.getContext());
        viewHolder.partyname.setText(data.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView partyname;
        Button btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            partyname = itemView.findViewById(R.id.nool_pid);
            btn = itemView.findViewById(R.id.party_del);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), AdduserActivity.class);
                    intent.putExtra("type", "update");
                    intent.putExtra("id", data.get(getLayoutPosition()).getId());
                    intent.putExtra("name", data.get(getLayoutPosition()).getName());
                    intent.putExtra("address", data.get(getLayoutPosition()).getAddress());
                    intent.putExtra("number", data.get(getLayoutPosition()).getNumber());
                    v.getContext().startActivity(intent);

                }
            });

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
        }
    }

    public void removeAt(int position) {

        try {
            boolean tt=db.deleteparty(data.get(position).getId());
            if(tt){
                Log.d("delparty","working");
            }else{
                Log.d("delparty","not working");
            }
        } catch (Exception t) {
            t.printStackTrace();
        }
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size());
    }
}
