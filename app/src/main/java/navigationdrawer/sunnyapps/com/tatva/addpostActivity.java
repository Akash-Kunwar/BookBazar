package navigationdrawer.sunnyapps.com.tatva;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class addpostActivity extends AppCompatActivity {
    ImageButton imgbtn;
    EditText etname,ettext;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    Uri imguri=null;
    Button btnsub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);
        imgbtn=(ImageButton)findViewById(R.id.imgbtn);
        etname=(EditText)findViewById(R.id.etname);
        ettext=(EditText)findViewById(R.id.ettext);
        btnsub=(Button)findViewById(R.id.btnsub);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        storageReference= FirebaseStorage.getInstance().getReference();
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gi=new Intent(Intent.ACTION_GET_CONTENT);
                gi.setType("image/*");
                startActivityForResult(gi,1);

            }
        });
        progressDialog = new ProgressDialog(addpostActivity.this);
        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startpost();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 &&resultCode==RESULT_OK)
        {
            imguri=data.getData();
            imgbtn.setImageURI(imguri);
        }
    }
    private  void startpost()
    {    progressDialog.setMessage("Posting to Blog");
        progressDialog.show();
        final String name=etname.getText().toString().trim();
        final String text=ettext.getText().toString().trim();
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(text))
        {
            final StorageReference imgpath=storageReference.child("event_img").child(imguri.getLastPathSegment());
            imgpath.putFile(imguri).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(addpostActivity.this,"Check your connection",Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    DatabaseReference newpost=databaseReference.push();
                    Eventblog b=new Eventblog(name,text,downloadUrl.toString());
                    newpost.setValue(b);

                    progressDialog.dismiss();
                    Intent intent=new Intent(addpostActivity.this,postActivity.class);
                    startActivity(intent);
                    Toast.makeText(addpostActivity.this,"Post Added Succesfully",Toast.LENGTH_SHORT).show();

                }
            });

        }
        else {
            Toast.makeText(addpostActivity.this,"Fill ALL the details",Toast.LENGTH_LONG).show();
        }

    }

}
