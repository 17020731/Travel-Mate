package io.github.project_travel_mate.travel.booking;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.Calendar;

import io.github.project_travel_mate.R;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyHolder> {

    private Context mContext;
    private ArrayList<Booking> mListBooks;
    private int type;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public BookingAdapter(Context mContext, ArrayList<Booking> mListBooks, int type) {
        this.mContext = mContext;
        this.mListBooks = mListBooks;
        this.type = type;
    }

    @Override
    public int getItemViewType(int position) {
        return this.type;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyHolder myHolder = null;
        if (viewType == 1) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.item_booking_1, parent, false);
            myHolder = new MyHolder(v);
        } else if (viewType == 2) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.item_booking_2, parent, false);
            myHolder = new MyHolder(v);
        }
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Booking book = mListBooks.get(position);

        Glide.with(holder.image).load(book.getImage()).into(holder.image);

        holder.rating.setRating(book.getRating());
        holder.count.setText("(" + book.getCount() + ")");
        holder.name.setText(book.getName());
        holder.category.setText(book.getAddress());
        holder.price.setText(book.getPrice() + "$");


        holder.btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] METHOD = {""};
                BottomSheetDialog dialog = new BottomSheetDialog(mContext);
                dialog.setContentView(R.layout.dialig_booking);
                final EditText edName = dialog.findViewById(R.id.edName);
                EditText edIden = dialog.findViewById(R.id.edIden);
                Spinner spinType = dialog.findViewById(R.id.spinType);
                FloatingActionButton btnMinus = dialog.findViewById(R.id.btnMinus);
                FloatingActionButton btnPlus = dialog.findViewById(R.id.btnPlus);
                TextView tvNumGuest = dialog.findViewById(R.id.tvNumGuest);
                EditText edCheckIn = dialog.findViewById(R.id.edCheckIn);
                EditText edCheckOut = dialog.findViewById(R.id.edCheckOut);
                ImageView imgCheckIn = dialog.findViewById(R.id.imgCheckIn);
                ImageView imgCheckOut = dialog.findViewById(R.id.imgCheckOut);
                TextView tvHotel = dialog.findViewById(R.id.tvHotel);
                TextView tvAddress = dialog.findViewById(R.id.tvAddress);
                LinearLayout btnCash = dialog.findViewById(R.id.btnCash);
                LinearLayout btnMomo = dialog.findViewById(R.id.btnMomo);
                LinearLayout btnMaster = dialog.findViewById(R.id.btnMasterCard);
                LinearLayout btnVisa = dialog.findViewById(R.id.btnVisa);
                TextView tvTotal = dialog.findViewById(R.id.tvTotal);
                TextView tvTax = dialog.findViewById(R.id.tvTax);
                TextView tvAmount = dialog.findViewById(R.id.tvAmount);
                Button btnSubmit = dialog.findViewById(R.id.btnSubmit);


                //--Room type---//
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext, R.layout.item_search, new String[]{"Normal", "VIP"});
                spinType.setAdapter(arrayAdapter);

                tvNumGuest.setText("1");
                btnMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int count = Integer.parseInt(tvNumGuest.getText().toString());
                        if (count > 1) {
                            count--;
                            tvNumGuest.setText("" + count--);
                        }
                    }
                });

                btnPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int count = Integer.parseInt(tvNumGuest.getText().toString());
                        if (count <= 50) {
                            count++;
                            tvNumGuest.setText("" + count++);
                        }
                    }
                });
                final Calendar myCalendar = Calendar.getInstance();

                imgCheckIn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                String date = i2 + "/" + (i1 + 1) + "/" + i;
                                edCheckIn.setText(date);

                            }
                        }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });

                imgCheckOut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                String date = i2 + "/" + (i1 + 1) + "/" + i;
                                edCheckOut.setText(date);
                            }
                        }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });
                btnCash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        METHOD[0] = "Cash";
                        btnCash.setBackgroundResource(R.drawable.button_select);
                        btnMomo.setBackgroundResource(R.drawable.button_unselect);
                        btnVisa.setBackgroundResource(R.drawable.button_unselect);
                        btnMaster.setBackgroundResource(R.drawable.button_unselect);

                    }
                });

                btnMomo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        METHOD[0] = "Momo";
                        btnMomo.setBackgroundResource(R.drawable.button_select);
                        btnCash.setBackgroundResource(R.drawable.button_unselect);
                        btnVisa.setBackgroundResource(R.drawable.button_unselect);
                        btnMaster.setBackgroundResource(R.drawable.button_unselect);
                    }
                });

                btnVisa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        METHOD[0] = "Visa";
                        btnVisa.setBackgroundResource(R.drawable.button_select);
                        btnMomo.setBackgroundResource(R.drawable.button_unselect);
                        btnCash.setBackgroundResource(R.drawable.button_unselect);
                        btnMaster.setBackgroundResource(R.drawable.button_unselect);
                    }
                });

                btnMaster.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        METHOD[0] = "Master Card";
                        btnMaster.setBackgroundResource(R.drawable.button_select);
                        btnMomo.setBackgroundResource(R.drawable.button_unselect);
                        btnVisa.setBackgroundResource(R.drawable.button_unselect);
                        btnCash.setBackgroundResource(R.drawable.button_unselect);
                    }
                });

                tvHotel.setText(book.getName());
                tvAddress.setText(book.getAddress());
                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (edName.getText().toString().isEmpty()) {
                            Toast.makeText(mContext, "Please enter your name!", Toast.LENGTH_SHORT);
                            return;
                        }
                        if (edIden.getText().toString().isEmpty()) {
                            Toast.makeText(mContext, "Please enter your personal identification!", Toast.LENGTH_SHORT);
                            return;
                        }
                        if (edCheckIn.getText().toString().isEmpty()) {
                            Toast.makeText(mContext, "Please enter select date of check in!", Toast.LENGTH_SHORT);
                            return;
                        }
                        if (edCheckOut.getText().toString().isEmpty()) {
                            Toast.makeText(mContext, "Please enter select date of check out!", Toast.LENGTH_SHORT);
                            return;
                        }
                        String NAME = edName.getText().toString().trim();
                        String ID = edIden.getText().toString().trim();
                        String TYPE = spinType.getSelectedItem().toString();
                        String NUM = tvNumGuest.getText().toString();
                        String CHECK_IN = edCheckIn.getText().toString().trim();
                        String CHECK_OUT = edCheckOut.getText().toString().trim();
                        String HOTEL = tvHotel.getText().toString();
                        String ADDRESS = tvAddress.getText().toString();
                        double TOTAL = book.getPrice();


                        BookDetail bookDetail = new BookDetail(NAME, ID, book.getImage(), TYPE, Integer.parseInt(NUM), CHECK_IN, CHECK_OUT, HOTEL, ADDRESS, METHOD[0], TOTAL);
                        App.mListBooking.add(bookDetail);

                    }
                });
                tvTotal.setText(String.format("%.2f", (book.getPrice())));
                tvTax.setText("" + String.format("%.2f", (0.055 * book.getPrice())));
                tvAmount.setText(String.format("%.2f", book.getPrice() * 1.055));
                dialog.show();
            }
        });


        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + book.getPhone()));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListBooks.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView btnBook;
        private FloatingActionButton btnCall;
        private RoundedImageView image;
        private RatingBar rating;
        private TextView count;
        private TextView name;
        private TextView category;
        private TextView price;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            rating = itemView.findViewById(R.id.rating);
            count = itemView.findViewById(R.id.count);
            name = itemView.findViewById(R.id.name);
            category = itemView.findViewById(R.id.category);
            price = itemView.findViewById(R.id.price);

            btnBook = itemView.findViewById(R.id.btnBook);
            btnCall = itemView.findViewById(R.id.btnCall);
        }
    }
}

