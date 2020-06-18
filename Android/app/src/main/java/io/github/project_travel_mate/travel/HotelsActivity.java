package io.github.project_travel_mate.travel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.project_travel_mate.R;
import io.github.project_travel_mate.searchcitydialog.CitySearchModel;
import io.github.project_travel_mate.travel.booking.BookDetail;
import io.github.project_travel_mate.travel.booking.BookingActivity;

import static utils.Constants.USER_TOKEN;

/**
 * Display list of hotels in destination city
 */
public class HotelsActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.booking_list)
    RecyclerView recyclerView;
    @BindView(R.id.animation_view)
    LottieAnimationView animationView;
    @BindView(R.id.text_view)
    TextView textView;
    @BindView(R.id.layout)
    LinearLayout layout;

    @BindView(R.id.autoSearch)
    AutoCompleteTextView autoSearch;
    @BindView(R.id.empty)
    LinearLayout empty;
    private ArrayList<String> mListCities = new ArrayList<>();
    private ArrayList<CitySearchModel> mSearchCities = new ArrayList<>();

    private DatabaseReference mDatabase;
    private HotelAdapter mAdapter;
    private String mToken;
    private SharedPreferences mSharedPreferences;
    private ArrayList<BookDetail> mListBooks = new ArrayList<>();

    public static Intent getStartIntent(Context context) {
        return new Intent(context, HotelsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mToken = mSharedPreferences.getString(USER_TOKEN, null);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        ButterKnife.bind(this);


        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getDataSearchCities();
        for (CitySearchModel c : mSearchCities) {
            mListCities.add(c.getName());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.item_search, mListCities);
        autoSearch.setAdapter(arrayAdapter);
        autoSearch.setThreshold(1);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        mDatabase.child("books").child(mToken).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    BookDetail bd = snapshot.getValue(BookDetail.class);
                    mListBooks.add(bd);
                }

                if (mListBooks.size()==0){
                    empty.setVisibility(View.VISIBLE);
                    return;
                }
                empty.setVisibility(View.GONE);
                mAdapter = new HotelAdapter(HotelsActivity.this, mListBooks);
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void getDataSearchCities() {
        mSearchCities.add(new CitySearchModel("Delhi", "https://upload.wikimedia.org/wikipedia/commons/9/94/%E0%B4%96%E0%B5%81%E0%B4%A4%E0%B5%8D%E0%B4%AC%E0%B5%8D_%E0%B4%AE%E0%B4%BF%E0%B4%A8%E0%B4%BE%E0%B5%BC1.jpg", "1"));
        mSearchCities.add(new CitySearchModel("Mumbai", "https://static1.squarespace.com/static/55917ae2e4b0984fc8c264c4/t/5ad7a037758d464041bfba27/1524102624280/Victoria_Mumbai.jpg", "2"));
        mSearchCities.add(new CitySearchModel("Bangalore", "https://scontent.fhan1-1.fna.fbcdn.net/v/t34.0-12/p843x403/38988483_398924174048648_1668427624_n.jpg?_nc_cat=1&_nc_sid=e3f864&_nc_ohc=zsXsLCw8yewAX-dVeFX&_nc_ht=scontent.fhan1-1.fna&_nc_tp=6&oh=86a8e754966668f9cff8c6fd37b78b0f&oe=5EE7B29C", "3"));
        mSearchCities.add(new CitySearchModel("Pune", "https://www.zricks.com/ImagesPostProject/47929//6557d975-20a9-4c6c-a776-8072a1936a76.jpg", "4"));
        mSearchCities.add(new CitySearchModel("Chennai", "http://lh3.ggpht.com/_YLb7tJ8RoAk/SfazBLdgf3I/AAAAAAAANEg/p52_Zz_URxg/s640/Picture%20009.jpg", "5"));
        mSearchCities.add(new CitySearchModel("Kolkata", "https://i.pinimg.com/originals/05/ec/42/05ec42c545b494d12cc612923483b4f0.jpg", "6"));
        mSearchCities.add(new CitySearchModel("Jaipur", "https://www.trodly.com/pictures/attraction/1943.jpg", "7"));
        mSearchCities.add(new CitySearchModel("Hyderabad", "https://i1.trekearth.com/photos/59194/charminar_020_sm.jpg", "8"));
        mSearchCities.add(new CitySearchModel("Panjim", "http://www.nomad4ever.com/wp-content/uploads/2009/04/palm-trees-at-baga-creek-north-goa1.jpg", "9"));
        mSearchCities.add(new CitySearchModel("Ahmedabad", "https://i.pinimg.com/originals/a5/ed/29/a5ed293271669a61f47059f7b0d9cf2c.jpg", "10"));

        autoSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                autoSearch.setText("");
                Intent intent = new Intent(HotelsActivity.this, BookingActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_city:
                break;
        }
    }


    // TODO :: Move adapter to a new class
    public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.HotelsViewHolder> {

        private final Context mContext;
        private List<HotelsModel> mHotelsModelList;

        HotelsAdapter(Context context, List<HotelsModel> mHotelsModelList) {
            this.mContext = context;
            this.mHotelsModelList = mHotelsModelList;
        }

        @NonNull
        @Override
        public HotelsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_listitem, parent, false);
            return new HotelsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull HotelsViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mHotelsModelList.size();
        }

        //        View holder Class to hold the Views
        class HotelsViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.hotel_name)
            TextView title;
            @BindView(R.id.hotel_address)
            TextView description;
            @BindView(R.id.call)
            RelativeLayout call;
            @BindView(R.id.map)
            RelativeLayout map;
            @BindView(R.id.book)
            RelativeLayout book;
            @BindView(R.id.more_details)
            LinearLayout detailsLayout;
            @BindView(R.id.expand_collapse)
            ImageView expandCollapse;
            @BindView(R.id.distance)
            TextView distance;
            @BindView(R.id.expand_more_details)
            RelativeLayout expand_more_details;

            private HotelsViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

    // TODO :: Move model to a new class
//    Model class to hold the data instead of passing the whole JSONObject to recyclerView
    class HotelsModel {
        private String mTitle;
        private String mAddress;
        private String mPhone;
        private String mHref;
        private int mDistance;
        private double mLatitude;
        private double mLongitude;

        HotelsModel(String mTitle, String mAddress, String mPhone, String mHref, int mDistance,
                    double mLatitude, double mLongitude) {
            this.mTitle = mTitle;
            this.mAddress = mAddress;
            this.mPhone = mPhone;
            this.mHref = mHref;
            this.mDistance = mDistance;
            this.mLatitude = mLatitude;
            this.mLongitude = mLongitude;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getAddress() {
            return mAddress;
        }

        public String getPhone() {
            return mPhone;
        }

        public String getHref() {
            return mHref;
        }

        public int getDistance() {
            return mDistance;
        }

        public double getLatitude() {
            return mLatitude;
        }

        public double getLongitude() {
            return mLongitude;
        }
    }
}
