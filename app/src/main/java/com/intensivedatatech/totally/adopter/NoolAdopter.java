package com.intensivedatatech.totally.adopter;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.intensivedatatech.totally.R;
import com.intensivedatatech.totally.helper.DatabaseHelper;
import com.intensivedatatech.totally.model.NoolModel;

import java.util.ArrayList;

public class NoolAdopter extends RecyclerView.Adapter<NoolAdopter.ViewHolder> {
    DatabaseHelper db;
    private ArrayList<NoolModel> data;

    public NoolAdopter(ArrayList data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_nool, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        db = new DatabaseHelper(viewHolder.whitequantity.getContext());
        try {
            viewHolder.whitequantity.setText("White Quantity: " + String.valueOf(data.get(i).getWhitequantity()));
            viewHolder.whiteprice.setText("White Price: " + String.valueOf(data.get(i).getWhiteprice()));
            viewHolder.colorquantity.setText("Color Quantity: " + String.valueOf(data.get(i).getColorquantity()));
            viewHolder.colorprice.setText("Color Price: " + String.valueOf(data.get(i).getColorprice()));
            viewHolder.totalprice.setText("Total Price: " + String.valueOf(data.get(i).getTotalprice()));
            viewHolder.paid.setText("Paid: " + String.valueOf(data.get(i).getPaid()));
            viewHolder.date.setText("Date: " + data.get(i).getDate());
            try {
                float a, b, cr;
                a = data.get(i).getTotalprice();
                b = data.get(i).getPaid();
                if (b > 0) {
                    cr = a - b;
                    Log.d("balance", "working");
                    viewHolder.notpaid.setText("Not Paid:" + String.valueOf(cr));
                } else {
                    Log.d("balance", "not working");
                }
            } catch (Exception r) {
                r.printStackTrace();
            }
            try {
                Cursor c = db.getbyid(data.get(i).getPid());
                c.moveToFirst();
                viewHolder.pid.setText(String.valueOf(c.getString(0)));
            } catch (Exception t) {
                t.printStackTrace();
            }

        } catch (Exception r) {
            r.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView whitequantity, whiteprice, colorquantity, colorprice, totalprice, paid, pid, notpaid, date;
        Button btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            whitequantity = itemView.findViewById(R.id.nool_wq);
            whiteprice = itemView.findViewById(R.id.nool_wp);
            colorquantity = itemView.findViewById(R.id.nool_cq);
            colorprice = itemView.findViewById(R.id.nool_cp);
            totalprice = itemView.findViewById(R.id.nool_tp);
            paid = itemView.findViewById(R.id.nool_p);
            pid = itemView.findViewById(R.id.nool_pid);
            date = itemView.findViewById(R.id.nool_date);
            notpaid = itemView.findViewById(R.id.nool_notpaid);
            btn = itemView.findViewById(R.id.nool_del);
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
                public void onClick(final View v) {
//                    Intent intent = new Intent(v.getContext(), UpdatenoolActivity.class);
//                    v.getContext().startActivity(intent);

                    final int ids = data.get(getLayoutPosition()).getId();
                    final float pd = data.get(getLayoutPosition()).getPaid();
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Enter Amount");

// Set up the input
                    final EditText input = new EditText(v.getContext());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                    input.setInputType(InputType.TYPE_CLASS_NUMBER);
                    builder.setView(input);

// Set up the buttons
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                float m_Text = Integer.parseInt(input.getText().toString());
                                boolean b = db.uppaid(m_Text, ids);
                                if (b) {
                                    Log.d("nool", "paid");
                                    Toast.makeText(v.getContext(), "Updated", Toast.LENGTH_LONG).show();
                                } else {
                                    Log.d("nool", "not paid");
                                    Toast.makeText(v.getContext(), "Not Updated", Toast.LENGTH_LONG).show();
                                }
                            } catch (Exception r) {
                                r.printStackTrace();
                            }
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();

                }
            });
        }
    }

    public void removeAt(int position) {

        try {
            boolean tt=db.deletenool(data.get(position).getId());
            if(tt){
                Log.d("delnool","working");
            }else{
                Log.d("delnool","not working");
            }
        } catch (Exception t) {
            t.printStackTrace();
        }
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size());
    }
}
