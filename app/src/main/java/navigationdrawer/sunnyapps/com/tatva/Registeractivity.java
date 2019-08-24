package navigationdrawer.sunnyapps.com.tatva;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registeractivity extends AppCompatActivity {

    EditText etname,etevent,etclasss,etusn,etphone;
    Button btnsub;
    DatabaseReference databaseReference;
    ProgressDialog p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registeractivity);
        p = new ProgressDialog(Registeractivity.this);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("register");
        etname=(EditText)findViewById(R.id.etname);
        etevent=(EditText)findViewById(R.id.etevent);
        etclasss=(EditText)findViewById(R.id.etclasss);
        etusn=(EditText)findViewById(R.id.etusn);
        etphone=(EditText)findViewById(R.id.etphone);
        btnsub=(Button)findViewById(R.id.btnsub);
        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               p.setMessage("Registering");
                p.show();
                startregister();
            }
        });
    }

    private void startregister() {
        String n,e,c,u,phone;
        n=etname.getText().toString().trim();
        e=etevent.getText().toString().trim();
        c=etclasss.getText().toString().trim();
        u=etusn.getText().toString().trim();
        phone=etphone.getText().toString().trim();
        if(!TextUtils.isEmpty(n)&&!TextUtils.isEmpty(e)&&!TextUtils.isEmpty(c)&& !TextUtils.isEmpty(u)&& !TextUtils.isEmpty(phone))
        {
            User po=new User(n,u,c,e,phone);
            DatabaseReference nweuser=databaseReference.push();
            nweuser.setValue(po);
            nweuser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    p.dismiss();
                    Intent intent=new Intent(Registeractivity.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(Registeractivity.this,"Registerd  Succesfully",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else {
            Toast.makeText(Registeractivity.this,"Enter all the details Correctly",Toast.LENGTH_SHORT).show();
        }
    }
}
