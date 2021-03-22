package com.example.covidapp.module.OnBoarding.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.covidapp.R;
import com.example.covidapp.module.Home.Home;

import static com.example.covidapp.module.OnBoarding.OnBoarding.vpPager;

public class FragmentOnBoarding1 extends Fragment {
    // Store instance variables
    private String title, desc;
    private int img_banner, clrDot, clrBtn;
    private int page;
    private TextView lbl_skip, lbl_title, lbl_desc;
    private ImageView banner, dot1, dot2, dot3, btn_next;
    private LinearLayout lin_baground_vertical;

    // newInstance constructor for creating fragment with arguments
    public static FragmentOnBoarding1 newInstance(int page, String title, String desc, int clrdot, int banner, int imgbtn) {
        FragmentOnBoarding1 FragmentOnBoarding1 = new FragmentOnBoarding1();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        args.putString("someDesc", desc);

        args.putInt("someBanner", banner);
        args.putInt("someDot", clrdot);
        args.putInt("someBtn", imgbtn);

        FragmentOnBoarding1.setArguments(args);
        return FragmentOnBoarding1;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
        desc = getArguments().getString("someDesc");

        img_banner= getArguments().getInt("someBanner", R.drawable.ic_help1);
        clrDot= getArguments().getInt("someDot", R.color.light_green);
        clrBtn= getArguments().getInt("someBtn", R.drawable.ic_nexthelp1);

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_help, container, false);

        ///define id
        lbl_skip = (TextView) view.findViewById(R.id.lbl_skip);
        lbl_title = (TextView) view.findViewById(R.id.lbl_title);
        lbl_desc = (TextView) view.findViewById(R.id.lbl_desc);

        dot1 = (ImageView) view.findViewById(R.id.dot1);
        dot2 = (ImageView) view.findViewById(R.id.dot2);
        dot3 = (ImageView) view.findViewById(R.id.dot3);
        banner = (ImageView) view.findViewById(R.id.img_banner);
        btn_next = (ImageView) view.findViewById(R.id.btn_next);

        lin_baground_vertical = (LinearLayout) view.findViewById(R.id.lin_baground_vertical);

        ///setting the data
        lbl_title.setText(title);
        lbl_desc.setText(desc);

        banner.setBackgroundResource(img_banner);
        btn_next.setBackgroundResource(clrBtn);

        if(page==1){
            lbl_skip.setVisibility(View.VISIBLE);
            dot1.setBackgroundResource(R.drawable.ic_dot1);
            dot2.setBackgroundResource(R.drawable.ic_dot);
            dot3.setBackgroundResource(R.drawable.ic_dot);
            lin_baground_vertical.setBackgroundColor(Color.parseColor("#21D7AC"));
        }else if(page==2){
            lbl_skip.setVisibility(View.VISIBLE);
            dot1.setBackgroundResource(R.drawable.ic_dot);
            dot2.setBackgroundResource(R.drawable.ic_dot2);
            dot3.setBackgroundResource(R.drawable.ic_dot);
            lin_baground_vertical.setBackgroundColor(Color.parseColor("#DEBA3F"));
        }else{
            lbl_skip.setVisibility(View.GONE);
            dot1.setBackgroundResource(R.drawable.ic_dot);
            dot2.setBackgroundResource(R.drawable.ic_dot);
            dot3.setBackgroundResource(R.drawable.ic_dot3);
            lin_baground_vertical.setBackgroundColor(Color.parseColor("#EE3482"));
        }

        lbl_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewImageIntent = new Intent(getActivity(), Home.class);
                getActivity().startActivity(viewImageIntent);
                getActivity().finish();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(page==3){
                    Intent viewImageIntent = new Intent(getActivity(), Home.class);
                    getActivity().startActivity(viewImageIntent);
                    getActivity().finish();
                }else{
                    vpPager.setCurrentItem(page, true);
                }
            }
        });

        return view;
    }
}