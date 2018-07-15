package com.example.rui.app;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class OverSpeedCarAdapter extends RecyclerView.Adapter<OverSpeedCarAdapter.ViewHolder> {

    private List<OverSpeedCar_info> mOverSpeedCar_infoList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.over_speed_car_info,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OverSpeedCar_info overSpeedCar_info = mOverSpeedCar_infoList.get(position);
        holder.carImage.setImageResource(overSpeedCar_info.getImageId());
        holder.plateNumber.setText(overSpeedCar_info.getCarId());
        holder.carSpeed.setText((overSpeedCar_info.getCarSpeed()));
        holder.date.setText(overSpeedCar_info.getDate());
    }

    @Override
    public int getItemCount() {
        return mOverSpeedCar_infoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView carImage;
        TextView plateNumber;
        TextView carSpeed;
        TextView date;
        public ViewHolder(View itemView) {
            super(itemView);
            carImage = itemView.findViewById(R.id.carImage);
            plateNumber =  itemView.findViewById(R.id.carId);
            carSpeed = itemView.findViewById(R.id.carSpeed);
            date = itemView.findViewById(R.id.date);
        }
    }
    public OverSpeedCarAdapter(List<OverSpeedCar_info> OverSpeedCar_infoList){
        mOverSpeedCar_infoList = OverSpeedCar_infoList;
    }
}
