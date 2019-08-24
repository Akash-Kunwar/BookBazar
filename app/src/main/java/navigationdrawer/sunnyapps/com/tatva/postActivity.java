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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class postActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Eventblog,EventviewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Eventblog, EventviewHolder>(
                Eventblog.class,R.layout.blogrow,EventviewHolder.class,databaseReference
        ) {
            @Override
            protected void populateViewHolder(EventviewHolder blogviewHolder, Eventblog blog, int i) {
                blogviewHolder.setName(blog.getName());
                blogviewHolder.setText(blog.getText());
                blogviewHolder.setImage(getApplicationContext(),blog.getImage());
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbarmenu);
        toolbar.setTitle("Tatva");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.add:
                        Intent intent=new Intent(postActivity.this,addpostActivity.class);
                        startActivity(intent);
                        Toast.makeText(postActivity.this,"Add Post",Toast.LENGTH_SHORT).show();


                }
                return true;
            }
        });
    }
    public static class EventviewHolder extends RecyclerView.ViewHolder{
        View mview;

        public EventviewHolder(@NonNull View itemView) {
            super(itemView);
            mview=itemView;
        }
        public void setName(String n)
        {
            TextView name=(TextView)mview.findViewById(R.id.etname);
            name.setText(n);
        }
        public void setText(String n)
        {
            TextView text=(TextView)mview.findViewById(R.id.ettext);
            text.setText(n);
        }
        public void setImage(Context c, String n)
        {
            ImageView iv=(ImageView) mview.findViewById(R.id.img);
            Glide.with(c).load(n!=null?n:R.drawable.team).into(iv);
        }
    }
}
