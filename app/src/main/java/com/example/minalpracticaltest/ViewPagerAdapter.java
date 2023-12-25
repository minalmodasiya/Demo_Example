package com.example.minalpracticaltest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {
 int totalcorrect=0;
    // Context object
    Context context;

    // Array of images
    ArrayList<UserModal> queanslist;

    // Layout Inflater
    LayoutInflater mLayoutInflater;


    // Viewpager Constructor
    public ViewPagerAdapter(Context context, ArrayList<UserModal> queanslist) {
        this.context = context;
        this.queanslist = queanslist;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // return the number of images
        return queanslist.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml
        View itemView = mLayoutInflater.inflate(R.layout.row_queans, container, false);
        UserModal userModal= queanslist.get(position);
        TextView txt_que = (TextView) itemView.findViewById(R.id.txt_que);
        TextView txt_ans1 = (TextView) itemView.findViewById(R.id.txt_ans1);
        TextView txt_ans2 = (TextView) itemView.findViewById(R.id.txt_ans2);
        TextView txt_ans3 = (TextView) itemView.findViewById(R.id.txt_ans3);
        TextView txt_ans4 = (TextView) itemView.findViewById(R.id.txt_ans4);
        TextView txt_ans5 = (TextView) itemView.findViewById(R.id.txt_ans5);
        TextView txt_a = (TextView) itemView.findViewById(R.id.txt_a);
        TextView txt_b = (TextView) itemView.findViewById(R.id.txt_b);
        TextView txt_c = (TextView) itemView.findViewById(R.id.txt_c);
        TextView txt_d = (TextView) itemView.findViewById(R.id.txt_d);
        TextView txt_e = (TextView) itemView.findViewById(R.id.txt_e);

        RadioGroup radioGroup =(RadioGroup) itemView.findViewById(R.id.radioGroup);
        RadioButton radio_a =(RadioButton)itemView.findViewById(R.id.radio_a);
        RadioButton radio_b =(RadioButton)itemView.findViewById(R.id.radio_b);
        RadioButton radio_c =(RadioButton)itemView.findViewById(R.id.radio_c);
        RadioButton radio_d =(RadioButton)itemView.findViewById(R.id.radio_d);
        RadioButton radio_e =(RadioButton)itemView.findViewById(R.id.radio_e);

        radio_a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    if(userModal.getCorrect().equals("ans1"))
                    {
                        totalcorrect = totalcorrect+1;
                    }
                }
            }
        });
        radio_b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    if(userModal.getCorrect().equals("ans2"))
                    {
                        totalcorrect = totalcorrect+1;
                    }
                }
            }
        });
        radio_c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    if(userModal.getCorrect().equals("ans3"))
                    {
                        totalcorrect = totalcorrect+1;
                    }
                }
            }
        });
        radio_d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    if(userModal.getCorrect().equals("ans4"))
                    {
                        totalcorrect = totalcorrect+1;
                    }
                }
            }
        });
        radio_e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    if(userModal.getCorrect().equals("ans5"))
                    {
                        totalcorrect = totalcorrect+1;
                    }
                }
            }
        });







        txt_que.setText(userModal.getQuestion());
        if(!userModal.getAns1().isEmpty()) {
            txt_ans1.setText(userModal.getAns1());
            txt_a.setVisibility(View.VISIBLE);
            radio_a.setVisibility(View.VISIBLE);
        }
        if(!userModal.getAns2().isEmpty()) {
            txt_ans2.setText(userModal.getAns2());
            txt_b.setVisibility(View.VISIBLE);
            radio_b.setVisibility(View.VISIBLE);
        }
        if(!userModal.getAns3().isEmpty()) {
            txt_ans3.setText(userModal.getAns3());
            txt_c.setVisibility(View.VISIBLE);
            radio_c.setVisibility(View.VISIBLE);
        }
        if(!userModal.getAns4().isEmpty()) {
            txt_ans4.setText(userModal.getAns4());
            txt_d.setVisibility(View.VISIBLE);
            radio_d.setVisibility(View.VISIBLE);
        }
        if(!userModal.getAns5().isEmpty()) {
            txt_ans5.setText(userModal.getAns5());
            txt_e.setVisibility(View.VISIBLE);
            radio_e.setVisibility(View.VISIBLE);
        }


        // Adding the View
        Objects.requireNonNull(container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);
    }
}
