package navigationdrawer.sunnyapps.com.tatva;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Todelete extends AppCompatActivity {

    Toolbar toolbar;
    FirebaseAuth mauth;
    FirebaseAuth.AuthStateListener mauthlistner;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference databaseReference;

    @Override
    protected void onStart() {
        super.onStart();
        mauth.addAuthStateListener(mauthlistner);
        FirebaseRecyclerAdapter<bookblog,Todelete.EventViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<
                bookblog,EventViewHolder>(
                bookblog.class,R.layout.bookrow,Todelete.EventViewHolder.class,databaseReference
        ) {
            @Override
            protected void populateViewHolder(Todelete.EventViewHolder blogviewHolder, final bookblog blog, int i) {
                blogviewHolder.setName(blog.getName());
                blogviewHolder.setCondition(blog.getCondition());
                blogviewHolder.setSem(blog.getSem());
                blogviewHolder.setSub(blog.getSub());
                blogviewHolder.setPrice(blog.getPrice());
                blogviewHolder.setPhone(blog.getPhone());
                blogviewHolder.setImage(getApplicationContext(),blog.getImageurl());
                final String post_key=getRef(i).getKey();
                blogviewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String uid;
                        uid=blog.getUid();
                        if(uid.equals(""))
                        {
                            Toast.makeText(Todelete.this,"Error",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Intent intent=new Intent(Todelete.this,deletebook.class);
                            intent.putExtra("userid",uid);
                            intent.putExtra("post_key",post_key);
                            startActivityForResult(intent,1);

                        }

                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        mauth=FirebaseAuth.getInstance();
        mauthlistner=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null)
                {
                    Intent i=new Intent(Todelete.this,LoginActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            }
        };

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Bookschor");
        toolbar.setSubtitle("Buy ,Sell, Rent-Inside your college ");
        toolbar.inflateMenu(R.menu.toolbarmenu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.add:
                        Intent i=new Intent(Todelete.this,addbooksact.class);

                        startActivity(i);

                }
                return true;
            }
        });
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Sellbooks");
    }

    public static class  EventViewHolder extends RecyclerView.ViewHolder
    {
        View mview;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            mview =itemView;
        }
        public void setName(String n)
        {
            TextView name=(TextView)mview.findViewById(R.id.tvname);
            name.setText(n);
        }
        public void setPhone(String n)
        {
            TextView phone=(TextView)mview.findViewById(R.id.tvphone);
            phone.setText(n);
        }
        public void setSem(String n)
        {
            TextView sem=(TextView)mview.findViewById(R.id.tvsem);
            sem.setText(n);
        }
        public void setSub(String n)
        {
            TextView sub=(TextView)mview.findViewById(R.id.tvsub);
            sub.setText(n);
        }
        public void setPrice(String n)
        {
            TextView price=(TextView)mview.findViewById(R.id.tvprice);
            price.setText(n);
        }
        public void setCondition(String n)
        {
            TextView c=(TextView)mview.findViewById(R.id.tvcondition);
            c.setText(n);
        }
        public void setImage(Context c, String n)
        {
            ImageView iv=(ImageView) mview.findViewById(R.id.ivimg);
            Glide.with(c).load(n!=null?n:R.drawable.team).into(iv);
        }

    }
}
