package com.ctrip.openapi;

import API.Hotel.hotelSearch;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button start = (Button)findViewById(R.id.connect);
        final TextView view = (TextView)findViewById(R.id.view1);
        start.setOnClickListener(new OnClickListener()
        {
            public void onClick(View source)
            {

                 hotelSearch data = new hotelSearch(false,2,112,"…œ∫£","HotelStarRate",3);    
                view.setText(data.getRequestXML());
          
            }
        });
    }
    
}