package com.example.shopforyou;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Person> userList;
    private RecyclerViewClickListener listener;


    public ItemAdapter(List<Person>userList,RecyclerViewClickListener listener){
        this.userList=userList;
        this.listener=listener;

    }

    public void setFilteredList (List<Person>filteredList){
        this.userList=filteredList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
    int resource=userList.get(position).getImage();
    String name=userList.get(position).getName();
    String des=userList.get(position).getDes();


    holder.setData(resource,name,des);

    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            int x=position;
            CharSequence[] delete={
                    "Delete"
            };
            AlertDialog.Builder alert=new AlertDialog.Builder(v.getContext());
            alert.setItems(delete, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which== 0){
                        userList.remove(x);
                        notifyItemRemoved(x);
                    }
                }
            });
            alert.create().show();
            return false;
        }
    });



    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
    interface RecyclerViewClickListener{
        void onClick(View v,int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView images;
        private TextView textView1;
        private TextView textView2;




        public ViewHolder(@NonNull View itemView ) {
            super(itemView);
            images=itemView.findViewById(R.id.image);
            textView1=itemView.findViewById(R.id.txtName);
            textView2=itemView.findViewById(R.id.txtDes);


            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });


        }

        public void setData(int resource, String name, String des) {
            images.setImageResource(resource);
           textView1.setText(name);
           textView2.setText(des);

        }


        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition());
        }
    }

}
