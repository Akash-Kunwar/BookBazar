package navigationdrawer.sunnyapps.com.tatva;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class addbooksact extends AppCompatActivity {

    Toolbar toolbar;
    EditText etname,etphone,etcondition,etsem,etsub,etprice;
    ImageButton imgbtn;
    Button btnsub;
    DatabaseReference databaserefrence;
    StorageReference storagerefernce;
    ProgressDialog progressDialog;
    Uri imageuri=null;
    FirebaseAuth mauth;
    FirebaseAuth.AuthStateListener mauthlistner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbooksact);
        databaserefrence= FirebaseDatabase.getInstance().getReference().child("Sellbooks");
        storagerefernce= FirebaseStorage.getInstance().getReference();
        mauth=FirebaseAuth.getInstance();
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etname=(EditText)findViewById(R.id.etname);
        etphone=(EditText)findViewById(R.id.etphone);
        etcondition=(EditText)findViewById(R.id.etcondition);
        etsem=(EditText)findViewById(R.id.etsem);
        etsub=(EditText)findViewById(R.id.etsub);
        etprice=(EditText)findViewById(R.id.etprice);
        imgbtn=(ImageButton)findViewById(R.id.imgbtn);
        btnsub=(Button)findViewById(R.id.btnsub);
        progressDialog=new ProgressDialog(this);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });
        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startposting();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 &&resultCode==RESULT_OK)
        {
            imageuri=data.getData();
            imgbtn.setImageURI(imageuri);
        }
    }

    private void startposting() {
        final String name,phone,condition,sem,sub,price;
        name=etname.getText().toString().trim();
        phone=etphone.getText().toString().trim();
        condition=etcondition.getText().toString().trim();
        sem=etsem.getText().toString().trim();
        sub=etsub.getText().toString().trim();
        price=etprice.getText().toString().trim();
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(condition) && !TextUtils.isEmpty(sem) && !TextUtils.isEmpty(price) && !TextUtils.isEmpty(sub)) {
            progressDialog.setMessage("Uploading ");
            progressDialog.show();
            final FirebaseUser cuurentuser = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference newPost=databaserefrence.push();
            final StorageReference mstorage=storagerefernce.child("Book_image").child(imageuri.getLastPathSegment());
            mstorage.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   Uri downloaduri=taskSnapshot.getDownloadUrl();
                   String finalstr=downloaduri.toString();
                   String uid=cuurentuser.getUid();
                   DatabaseReference newPost=databaserefrence.push();
                  /* newPost.child("name").setValue(name);
                   newPost.child("phone").setValue(phone);
                   newPost.child("condition").setValue(condition);
                   newPost.child("sem").setValue(sem);
                   newPost.child("sub").setValue(sub);
                   newPost.child("price").setValue(price);
                   newPost.child("uid").setValue(cuurentuser.getUid());
                   newPost.child("imageuri").setValue(finalstr);
                  */
                   bookblog b=new bookblog(name,price,sem,sub,condition,phone,finalstr,uid);
                   newPost.setValue(b);
                   newPost.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {
                           progressDialog.dismiss();
                           Intent intent=new Intent(addbooksact.this,books.class);
                           startActivity(intent);
                           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                           Toast.makeText(addbooksact.this,"Registerd  Succesfully",Toast.LENGTH_SHORT).show();
                   }

                       @Override
                       public void onCancelled(DatabaseError databaseError) {
                           progressDialog.dismiss();
                           Toast.makeText(addbooksact.this,"Please Connect to the Internet",Toast.LENGTH_SHORT).show();
                       }
                   });
               }
           }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   Toast.makeText(addbooksact.this,"Please Connect to the Internet",Toast.LENGTH_SHORT).show();
               }
           });

        }



        else {
            Toast.makeText(addbooksact.this,"Please Enter All the Details",Toast.LENGTH_LONG).show();
        }
    }
}
