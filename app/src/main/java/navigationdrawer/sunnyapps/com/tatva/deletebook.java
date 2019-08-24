package navigationdrawer.sunnyapps.com.tatva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class deletebook extends AppCompatActivity {
    Button btndel;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletebook);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Sellbooks");




        Intent intent=getIntent();
        final String uid=intent.getStringExtra("userid");
        final String post_key=intent.getStringExtra("post_key");
        final FirebaseUser currentuser=FirebaseAuth.getInstance().getCurrentUser();





        btndel=(Button)findViewById(R.id.btndel);
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentuser.getUid().equals(uid))
                {
                    databaseReference.child(post_key).removeValue();
                    Intent i=new Intent(deletebook.this,books.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                }
                else {
                    Toast.makeText(deletebook.this,"You can't delete others post",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(deletebook.this,books.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            }
        });
    }
}
