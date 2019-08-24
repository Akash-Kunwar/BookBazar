package navigationdrawer.sunnyapps.com.tatva;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class books extends AppCompatActivity {
SearchView sv;
    Toolbar toolbar;
    FirebaseAuth mauth;
    FirebaseAuth.AuthStateListener mauthlistner;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference databaseReference;
    ArrayList<bookblog> list;

    @Override
    protected void onStart() {
        super.onStart();
        mauth.addAuthStateListener(mauthlistner);
        if(databaseReference!=null)
        {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {
                        list=new ArrayList<>();
                        for(DataSnapshot ds:dataSnapshot.getChildren())
                        {
                            list.add(ds.getValue(bookblog.class));
                        }
                        Adapterclass adapterclass=new Adapterclass(list,books.this);
                        recyclerView.setAdapter(adapterclass);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(books.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
        if(sv!=null)
        {
            sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Sellbooks");

        mauth=FirebaseAuth.getInstance();
        mauthlistner=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
             if(firebaseAuth.getCurrentUser()==null)
             {
                 Intent i=new Intent(books.this,LoginActivity.class);
                 i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                 startActivity(i);
             }
            }
        };
        sv=(SearchView)findViewById(R.id.sv);
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
                        Intent i=new Intent(books.this,addbooksact.class);

                        startActivity(i);

                }
                return true;
            }
        });
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

    }
    public  void search(String str)
    {
      ArrayList<bookblog> mylist=new ArrayList<>();
        for(bookblog b:list)
        {
            if(b.getSub().toLowerCase().contains(str.toLowerCase()))
            {
                mylist.add(b);
            }
        }
        Adapterclass adapterclass=new Adapterclass(mylist,books.this);
        recyclerView.setAdapter(adapterclass);
    }
}
