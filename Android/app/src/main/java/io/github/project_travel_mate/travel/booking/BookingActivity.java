package io.github.project_travel_mate.travel.booking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.project_travel_mate.R;

public class BookingActivity extends AppCompatActivity {

    @BindView(R.id.mRecycle)
    RecyclerView mRecycle;

    @BindView(R.id.btnGrid)
    ImageView btnGrid;

    @BindView(R.id.btnTable)
    ImageView btnTable;

    @BindView(R.id.btnBack)
    ImageView btnBack;

    private ArrayList<Booking> mListBookings = new ArrayList<>();
    private BookingAdapter mAdapter;
    private ItemOffsetDecoration itemDecoration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        getSupportActionBar().hide();

        ButterKnife.bind(this);

        mListBookings.add(new Booking("Coast Hotels", "857 Mandrake Point", "+86 932 918 3394", 1, 2, 616, "https://images.pexels.com/photos/258154/pexels-photo-258154.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Kings Inn", "88189 Nelson Parkway", "+86 162 897 2307", 1, 2, 1884, "https://images.pexels.com/photos/573552/pexels-photo-573552.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Lustrio Inn", "23 Cottonwood Park", "+62 638 104 1395", 5, 5, 524, "https://images.pexels.com/photos/338504/pexels-photo-338504.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Moss View Hotel", "3 Sachtjen Park", "+351 715 983 2997", 2, 17, 546, "https://images.pexels.com/photos/262047/pexels-photo-262047.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Omni Hotels", "63984 Ilene Court", "+353 348 645 7375", 5, 18, 886, "https://images.pexels.com/photos/941861/pexels-photo-941861.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Paramount Hotel", "89 Sheridan Road", "+62 272 858 2415", 1, 10, 1529, "https://images.pexels.com/photos/2034335/pexels-photo-2034335.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Primland Roadside", "5402 Sutteridge Alley", "+86 257 676 8359", 1, 19, 1274, "https://images.pexels.com/photos/1838554/pexels-photo-1838554.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Malibu", "4 Corben Place", "+86 551 142 0997", 4, 2, 554, "https://images.pexels.com/photos/2507010/pexels-photo-2507010.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Happy Mornings Motel", "57 Hanover Terrace", "+93 178 780 1201", 4, 15, 1097, "https://images.pexels.com/photos/460537/pexels-photo-460537.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Hillside Hotel", "179 La Follette Center", "+94 226 631 4716", 1, 14, 908, "https://images.pexels.com/photos/60217/pexels-photo-60217.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Small Town B&B", "7126 Glendale Lane", "+7 966 145 5734", 2, 14, 139, "https://images.pexels.com/photos/9376/groningen-the-netherlands.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("The Local B&B", "0741 Browning Drive", "+48 271 572 7133", 4, 14, 1072, "https://images.pexels.com/photos/1743231/pexels-photo-1743231.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Quaint Motel", "494 Bowman Crossing", "+33 710 804 2295", 2, 15, 893, "https://images.pexels.com/photos/261395/pexels-photo-261395.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Quality Hotels", "73 Buell Pass", "+86 585 770 3625", 3, 9, 1787, "https://images.pexels.com/photos/2507007/pexels-photo-2507007.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Breeze Blows", "02413 Florence Drive", "+1 210 803 2391", 1, 15, 991, "https://images.pexels.com/photos/2290753/pexels-photo-2290753.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("La Parisienne", "43051 Upham Pass", "+86 495 927 4852", 2, 20, 664, "https://images.pexels.com/photos/1560065/pexels-photo-1560065.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("The Worldly Traveler", "7360 Sheridan Court", "+63 840 953 3397", 5, 4, 638, "https://images.pexels.com/photos/2869215/pexels-photo-2869215.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Quick Stop Hotel", "61576 Arkansas Road", "+86 999 581 5719", 3, 20, 997, "https://images.pexels.com/photos/3201921/pexels-photo-3201921.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Clean Convenience", "0 Sommers Terrace", "+7 143 688 6718", 2, 12, 2062, "https://images.pexels.com/photos/3467153/pexels-photo-3467153.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Better And Better", "2 Caliangt Way", "+48 971 503 8192", 1, 18, 764, "https://images.pexels.com/photos/2883047/pexels-photo-2883047.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Ebony Tropic Motel", "46 Miller Place", "+39 369 871 0213", 4, 16, 597, "https://images.pexels.com/photos/1386168/pexels-photo-1386168.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Primal Vista Motel", "090 Mifflin Terrace", "+62 281 919 9869", 1, 6, 1624, "https://images.pexels.com/photos/2670273/pexels-photo-2670273.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Seacoast Hotel", "5 5th Junction", "+52 851 890 3309", 2, 17, 894, "https://images.pexels.com/photos/2837909/pexels-photo-2837909.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Voyage Resort", "16 Burning Wood Alley", "+63 744 735 8521", 2, 12, 732, "https://images.pexels.com/photos/453201/pexels-photo-453201.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Prophecy Resort & Spa", "99423 Fremont Crossing", "+1 562 128 6606", 5, 9, 1755, "https://images.pexels.com/photos/3124079/pexels-photo-3124079.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Opportunity Resort", "85 Melby Lane", "+30 106 251 2594", 4, 8, 869, "https://images.pexels.com/photos/26139/pexels-photo-26139.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Shoreline Hotel & Spa", "54 Graedel Crossing", "+691 312 443 5547", 1, 6, 501, "https://images.pexels.com/photos/3316925/pexels-photo-3316925.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Rose Thicket Resort", "2 Hooker Alley", "+84 919 563 9770", 5, 4, 1652, "https://images.pexels.com/photos/2467558/pexels-photo-2467558.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Jade Courtyard Hotel", "4989 Onsgard Street", "+48 949 835 1898", 5, 1, 139, "https://images.pexels.com/photos/3316922/pexels-photo-3316922.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Grand Heirloom Resort & Spa", "776 Miller Drive", "+7 314 735 4826", 4, 8, 1329, "https://images.pexels.com/photos/3329718/pexels-photo-3329718.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Majestic Hotel", "700 Randy Court", "+359 935 568 4010", 5, 6, 1004, "https://images.pexels.com/photos/2540653/pexels-photo-2540653.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("White Season Resort", "58967 Jenifer Alley", "+86 923 962 8754", 4, 8, 1192, "https://images.pexels.com/photos/971001/pexels-photo-971001.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Jade Bay Resort", "150 Sunnyside Drive", "+63 124 706 4162", 2, 11, 1375, "https://images.pexels.com/photos/2079453/pexels-photo-2079453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Antique Palms Resort", "9141 Cardinal Drive", "+62 857 736 6803", 5, 3, 2095, "https://images.pexels.com/photos/2964163/pexels-photo-2964163.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Parallel Shrine", "76369 Sutteridge Road", "+381 126 168 7544", 3, 11, 1426, "https://images.pexels.com/photos/2041556/pexels-photo-2041556.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Ebony Market", "5 Tennyson Alley", "+237 419 874 0854", 3, 12, 696, "https://images.pexels.com/photos/3687922/pexels-photo-3687922.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Soft Plains", "2539 Twin Pines Drive", "+86 271 261 8194", 2, 9, 794, "https://images.pexels.com/photos/3396656/pexels-photo-3396656.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        mListBookings.add(new Booking("Hardywood Hotel", "3317 Sherman Plaza", "+86 267 708 7083", 2, 14, 399, "https://images.pexels.com/photos/2086676/pexels-photo-2086676.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));

        randomHotel();
        itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);

        mRecycle.addItemDecoration(itemDecoration);

        setupGridView();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupGridView();
            }
        });
        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupTableView();
            }
        });

    }

    private void randomHotel() {
        for (int i = 0; i < 8; i++) {
            mListBookings.remove(new Random().nextInt(mListBookings.size()-1));
        }
    }

    private void setupGridView() {
        mAdapter = new BookingAdapter(this, mListBookings, 1);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecycle.setLayoutManager(layoutManager);
        mRecycle.setItemAnimator(new DefaultItemAnimator());
        mRecycle.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void setupTableView() {
        mAdapter = new BookingAdapter(this, mListBookings, 2);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecycle.setLayoutManager(layoutManager2);
        mRecycle.setItemAnimator(new DefaultItemAnimator());
        mRecycle.setNestedScrollingEnabled(false);
        mRecycle.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

}
