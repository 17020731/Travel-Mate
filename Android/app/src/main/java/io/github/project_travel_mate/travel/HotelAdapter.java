package io.github.project_travel_mate.travel;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import io.github.project_travel_mate.R;
import io.github.project_travel_mate.travel.booking.BookDetail;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.MyHolder> {
    private Context mContext;
    private ArrayList<BookDetail> mListBooked;

    public HotelAdapter(Context mContext, ArrayList<BookDetail> mListBooked) {
        this.mContext = mContext;
        this.mListBooked = mListBooked;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_booked, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        BookDetail bd = mListBooked.get(position);

        Glide.with(holder.image).load(bd.getImage()).into(holder.image);
        holder.tvDate.setText("Booked: " + bd.getCheck_in());
        holder.tvName.setText(bd.getHotel_name());
        holder.tvAddress.setText(bd.getAddress());
        holder.tvGuest.setText("Guest:" + bd.getCount());
        holder.tvType.setText("Type: " + bd.getType());
        holder.tvAmount.setText(String.format("%.2f", bd.getTotal() * 1.055) + " $");

        if (bd.getMethod().equalsIgnoreCase("cash")) {
            holder.imgMethod.setImageResource(R.drawable.cash);
        } else if (bd.getMethod().equalsIgnoreCase("paypal")) {
            holder.imgMethod.setImageResource(R.drawable.paypal);
            holder.tvAmount.setPaintFlags(holder.tvAmount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        } else if (bd.getMethod().equalsIgnoreCase("visa")) {
            holder.imgMethod.setImageResource(R.drawable.visa);
            holder.tvAmount.setPaintFlags(holder.tvAmount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        } else if (bd.getMethod().equalsIgnoreCase("master card")) {
            holder.imgMethod.setImageResource(R.drawable.master_card);
            holder.tvAmount.setPaintFlags(holder.tvAmount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        } else if (bd.getMethod().equalsIgnoreCase("momo")) {
            holder.imgMethod.setImageResource(R.drawable.momo);
            holder.tvAmount.setPaintFlags(holder.tvAmount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }
    }

    @Override
    public int getItemCount() {
        return mListBooked.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private TextView tvDate, tvName, tvAddress, tvType, tvGuest, tvAmount;
        private RoundedImageView image;
        private ImageView imgMethod;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvDate);
            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvType = itemView.findViewById(R.id.tvType);
            tvGuest = itemView.findViewById(R.id.tvGuest);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            image = itemView.findViewById(R.id.image);
            imgMethod = itemView.findViewById(R.id.imgMethod);
        }
    }
}
