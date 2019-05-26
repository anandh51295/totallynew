package com.intensivedatatech.totally.adopter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.intensivedatatech.totally.AdduserActivity;
import com.intensivedatatech.totally.NoolActivity;
import com.intensivedatatech.totally.R;
import com.intensivedatatech.totally.model.PartyModel;

import java.util.ArrayList;

public class GroupAdopter extends RecyclerView.Adapter<GroupAdopter.ViewHolder> {
    private ArrayList<PartyModel> data;

    public GroupAdopter(ArrayList data) {
        this.data = data;
    }
    @NonNull
    @Override
    public GroupAdopter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_party, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdopter.ViewHolder viewHolder, int i) {

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
            btn.setVisibility(View.INVISIBLE);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), NoolActivity.class);
                    intent.putExtra("type", "update");
                    intent.putExtra("id", data.get(getLayoutPosition()).getId());
                    v.getContext().startActivity(intent);

                }
            });
        }
    }
}
