package navigationdrawer.sunnyapps.com.tatva;

import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LinearLayout b1,b11,b10,b2,b3,b5,b6,b7,h10;
       // GridLayout grid;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       /*grid=(GridLayout)findViewById(R.id.grid);
        SetsingleEventListner(grid);*/
        b1=(LinearLayout)findViewById(R.id.b1);
        b2=(LinearLayout)findViewById(R.id.b2);
        b3=(LinearLayout)findViewById(R.id.b3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,books.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,books.class);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,books.class);
                startActivity(intent);
            }
        });
        b11=(LinearLayout)findViewById(R.id.h1);
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,About.class);
                startActivity(intent);
            }
        });
        b10=(LinearLayout)findViewById(R.id.h2);
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Credits.class);
                startActivity(intent);
            }
        });
        b5=(LinearLayout)findViewById(R.id.b5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Todelete.class);
                startActivity(intent);
            }
        });

        b6=(LinearLayout)findViewById(R.id.b6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"trioindia697@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "User Feedback");
                intent.setType("plain/text");
                startActivity(intent);
            }
        });
        b7=(LinearLayout)findViewById(R.id.h3);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent si = new Intent(Intent.ACTION_SEND);
                si.setType("text/plain");
                String ln ="Share and Support";
                si.putExtra(Intent.EXTRA_TEXT,ln + "    " );
                startActivity(Intent.createChooser(si,"Share via"));
            }
        });
        h10=(LinearLayout)findViewById(R.id.h10);
        h10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,webview.class);
                startActivity(intent);
            }
        });
    }

    /*private void SetsingleEventListner(GridLayout grid) {
        for(int i=0;i<grid.getChildCount();i++)
        {
            final CardView cardview=(CardView)grid.getChildAt(i);
            final int k=i;
            cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cardview.getCardBackgroundColor().getDefaultColor()==-1)
                    {
                        cardview.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                    }
                    else {
                        cardview.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                    if(k==0)
                    {
                        Intent intent=new Intent(MainActivity.this,books.class);
                        startActivity(intent);

                    }
                    else if(k==1)
                    {

                    }
                    else if(k==2)
                    {
                        Intent intent=new Intent(MainActivity.this,EventsActivity.class);
                        startActivity(intent);

                    }
                    else if(k==3)
                    {

                        Intent intent=new Intent(MainActivity.this,Registeractivity.class);
                        startActivity(intent);
                    }
                    else if(k==4)
                    {

                    }
                    else if(k==5)
                    {

                    }

                }
            });
        }
    }*/
}
