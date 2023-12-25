package com.example.minalpracticaltest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView submit;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://run.mocky.io/v3/f427e168-9b10-4c2b-a1f9-c630f385f306";

    private ArrayList<UserModal> userModalArrayList = new ArrayList<>();
    private  ViewPagerAdapter viewPagerAdapter;

    private   ImageView imgview_next,imgview_prev;


    private ViewPager mViewPager;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && RequestPermissionsActivity.startPermissionActivity(this)) {
            return;
        }
        setContentView(R.layout.activity_main);
        // Minal
        mViewPager = (ViewPager) findViewById(R.id.viewPagerMain);
        textView = (TextView)findViewById(R.id.txt_q_total);
        submit= (TextView)findViewById(R.id.submit);
        imgview_next=(ImageView)findViewById(R.id.imgview_next);
        imgview_prev=(ImageView)findViewById(R.id.imgview_prev);

        sendAndRequestResponse();
    }



    private void sendAndRequestResponse() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("eeeeee" , "response taken");
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for(int i=0;i<10;i++){
                        JSONObject jresponse =
                                jsonArray.getJSONObject(new Random().nextInt(jsonArray.length()));
                        userModalArrayList.add(new UserModal(jresponse.getString("id"), jresponse.getString("question"), jresponse.getString("ans1"), jresponse.getString("ans2"),jresponse.getString("ans3"),jresponse.getString("ans4"),jresponse.getString("ans5"),jresponse.getString("correct")));
                        viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(),userModalArrayList);
                        mViewPager.setAdapter(viewPagerAdapter);
                        if(mViewPager.getCurrentItem() == 0)
                        {
                            imgview_prev.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            imgview_prev.setVisibility(View.VISIBLE);

                        }
                        if(mViewPager.getCurrentItem() == 9)
                        {
                            submit.setVisibility(View.VISIBLE);

                            imgview_next.setVisibility(View.INVISIBLE);
                        }
                        else {
                            imgview_next.setVisibility(View.VISIBLE);

                        }
                        imgview_next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(mViewPager.getCurrentItem() < 10) {
                                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                                }
                            }
                        });
                        imgview_prev.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(mViewPager.getCurrentItem() > 0) {
                                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
                                }
                            }
                        });
                        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                textView.setText("Question " +( position + 1) +" of 10");
                                if(mViewPager.getCurrentItem() == 0)
                                {
                                    imgview_prev.setVisibility(View.INVISIBLE);
                                }
                                else
                                {
                                    imgview_prev.setVisibility(View.VISIBLE);

                                }
                                if(mViewPager.getCurrentItem() == 9)
                                {
                                    submit.setVisibility(View.VISIBLE);
                                    imgview_next.setVisibility(View.INVISIBLE);
                                }
                                else {
                                    imgview_next.setVisibility(View.VISIBLE);

                                }
                            }

                            @Override
                            public void onPageSelected(int position) {

                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });

                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                          Toast.makeText(getApplicationContext(),"total Correct="+viewPagerAdapter.totalcorrect+"",Toast.LENGTH_LONG).show();
                            }
                        });

//                        String question =
//                                jresponse.getString("question");
//                        Log.i("question",question);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                  //  Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("eeeeee" , error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }

}