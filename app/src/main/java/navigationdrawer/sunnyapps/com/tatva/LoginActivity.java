package navigationdrawer.sunnyapps.com.tatva;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    EditText etemail;
    EditText etpwd;
    Button btnlogin;
    private FirebaseAuth mAuth;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pd=new ProgressDialog(this);
        setContentView(R.layout.activity_login);
        etemail=(EditText)findViewById(R.id.etemail);
        etpwd=(EditText)findViewById(R.id.etpwd);
        btnlogin=(Button)findViewById(R.id.btnlogin);
        mAuth = FirebaseAuth.getInstance();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startregidter();
            }
        });
    }

    private void startregidter() {
        pd.setMessage("Signing in..");
        pd.show();
        String email,pwd;
        email=etemail.getText().toString().trim();
        pwd=etpwd.getText().toString().trim();
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pwd))
        {

            mAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                  if(task.isSuccessful())
                  {
                   pd.dismiss();
                      Intent i=new Intent(LoginActivity.this,books.class);
                      i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                      startActivity(i);
                  }
                }
            });
        }
        else {
            Toast.makeText(LoginActivity.this,"Enter all the details",Toast.LENGTH_SHORT).show();
        }
    }
}
