package tamara.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, signin;
    DBHelper DB;
    boolean passwordVisible;
    boolean repasswordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);
        signup=findViewById(R.id.signup);
        signin=findViewById(R.id.signin);
        DB = new DBHelper(this);

        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                  if(event.getRawX()>=password.getRight()-password.getCompoundDrawables()[Right].getBounds().width()){
                      int selection=password.getSelectionEnd();
                      if(passwordVisible){
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_visibility_off,0);

                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                      }else{
                          password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_visibility,0);

                          password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                          passwordVisible=true;
                      }
                      password.setSelection(selection);
                      return true;
                    }
                }

                return false;
            }
        });
        repassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=repassword.getRight()-repassword.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=repassword.getSelectionEnd();
                        if(repasswordVisible){
                            repassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_visibility_off,0);

                            repassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            repasswordVisible=false;
                        }else{
                            repassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_visibility,0);

                            repassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            repasswordVisible=true;
                        }
                        repassword.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                    Toast.makeText(MainActivity.this, "All fields Required", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(MainActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this,"Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                                Toast.makeText(MainActivity.this, "User already Exists", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                        Toast.makeText(MainActivity.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}