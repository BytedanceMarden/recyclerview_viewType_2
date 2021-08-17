package com.bytedance.day20210817_2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<String> list;

    public MyAdapter(List<String> list){
        this.list=list;
    }


    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view=null;
        switch (viewType){
            case 0:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item1,parent,false);
                break;
            case 1:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item2,parent,false);
                break;
            case 2:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item3,parent,false);
                break;
        }
        MyViewHolder myViewHolder=new MyViewHolder(view);
        Log.d("marden","onCreateViewHolder:"+myViewHolder);
        return myViewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull MyAdapter.MyViewHolder holder, int position) {
        Log.d("marden","onBindViewHolder:"+holder+"------position:"+position);
        String temp=list.get(position);
        int viewType=getItemViewType(position);
        holder.bindData(temp+"-----------viewType:"+viewType);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //不能在此处直接调用notifyDataSetChanged()方法，匿名内部类！！！
                myMethod2(position);
            }
        });
    }


    //更新数据，并刷新
    public void myMethod1(){
        list.remove(0);
        notifyDataSetChanged();
    }

    //更新数据，并刷新
    public void myMethod2(int position){
        list.add(position,"111");
        list.add(position,"222");
        notifyItemRangeInserted(position,2);
    }



    @Override
    public int getItemViewType(int position) {
        return position%3;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.my_textview);
        }

        public void bindData(String str){
            textView.setText(str);
        }




    }
}
