package com.intensivedatatech.totally.adopter;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.intensivedatatech.totally.R;
import com.intensivedatatech.totally.UpdatenoolActivity;
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
            viewHolder.whitequantity.setText("நூல் அளவு: " + String.valueOf(data.get(i).getWhitequantity()));
            viewHolder.whiteprice.setText("நூல் விலை: " + String.valueOf(data.get(i).getWhiteprice()));


            viewHolder.totalprice.setText("TP: " + String.valueOf(data.get(i).getTotalprice()));
            viewHolder.totalw.setText("மொ.1: "+String.valueOf(data.get(i).getTotalwhiteprice()));

            viewHolder.matwp.setText("மேட் விலை: "+String.valueOf(data.get(i).getCottonmatprice()));
            viewHolder.matwq.setText("மேட் அளவு: "+String.valueOf(data.get(i).getCottonmatquantity()));


            viewHolder.mattotalw.setText("மொ.2: "+String.valueOf(data.get(i).getCottonmattotalprice()));

            viewHolder.paid.setText("Paid: " + String.valueOf(data.get(i).getPaid()));
            viewHolder.date.setText(data.get(i).getDate());
            float twp=data.get(i).getTotalwhiteprice()-data.get(i).getCottonmattotalprice();

            viewHolder.total1.setText("மொ.வெ: "+String.valueOf(twp));

//            float temps=twp-mtw;
//            viewHolder.m1.setText(String.valueOf(temps));
//            float tcp=data.get(i).getTotalwhiteprice();
//            float mtc=data.get(i).getColormattotalprice();
//            float tem1=tcp-mtc;
//            viewHolder.m2.setText(String.valueOf(tem1));
            float tep1,tep2,mtot1,mtot2;
            tep1=data.get(i).getEntryprice1();


            if(String.valueOf(tep1).startsWith("-")){
                viewHolder.ep1.setText("மு.பாக்கி(-): "+String.valueOf(tep1));
                viewHolder.ep1.setTextColor(Color.MAGENTA);
            }else{
                viewHolder.ep1.setText("மு.இருப்பு(+): "+String.valueOf(tep1));
            }

            mtot1=Math.abs(twp)+ tep1;

            viewHolder.m1.setText("P1: "+String.valueOf(mtot1));


            try {
                float a, b, cr;
                a = data.get(i).getTotalprice();
                b = data.get(i).getPaid();
                if (b > 0) {
                    cr = Math.abs(a) - Math.abs(b);
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
        TextView whitequantity, whiteprice, totalprice, paid, pid, notpaid, date, totalw, matwp, matwq, mattotalw,total1,ep1,m1;
        ImageView edt, del;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            total1=itemView.findViewById(R.id.nool_total1);

            ep1=itemView.findViewById(R.id.nool_ep1);

            m1=itemView.findViewById(R.id.nool_m1);


            whitequantity = itemView.findViewById(R.id.nool_wq);
            whiteprice = itemView.findViewById(R.id.nool_wp);
            totalprice = itemView.findViewById(R.id.nool_tp);
            paid = itemView.findViewById(R.id.nool_p);
            pid = itemView.findViewById(R.id.nool_pid);
            date = itemView.findViewById(R.id.nool_date);
            notpaid = itemView.findViewById(R.id.nool_notpaid);

            edt = itemView.findViewById(R.id.nool_edit);
            del = itemView.findViewById(R.id.nool_del);
            totalw=itemView.findViewById(R.id.nooltotalw);

            matwp=itemView.findViewById(R.id.noolmatwp);
            matwq=itemView.findViewById(R.id.noolmatwq);

            mattotalw=itemView.findViewById(R.id.noolmattotalw);


            edt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(), UpdatenoolActivity.class);
                    intent.putExtra("mid",data.get(getLayoutPosition()).getId());
                    v.getContext().startActivity(intent);
                }
            });
            del.setOnClickListener(new View.OnClickListener() {
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
                                    paid.setText(String.valueOf(m_Text));
//                                    try {
//                                        Float tmv,tmp;
//                                        tmv=Float.parseFloat(totalprice.getText().toString());
//                                        if (m_Text > 0) {
//                                            tmp=tmv - m_Text;
//                                            notpaid.setText("Not Paid:" + String.valueOf(tmp));
//                                        } else {
//                                            Log.d("balance", "not working");
//                                        }
//                                    } catch (Exception r) {
//                                        r.printStackTrace();
//                                    }


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
            boolean tt = db.deletenool(data.get(position).getId());
            if (tt) {
                Log.d("delnool", "working");
            } else {
                Log.d("delnool", "not working");
            }
        } catch (Exception t) {
            t.printStackTrace();
        }
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size());
    }
}
