package com.example.bazaarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activitySignup extends AppCompatActivity {
EditText fName,lName,mail,passw,confirm;
    Button button;
    DatabaseReference reff;
    DatabaseReference ref;
    User user;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        fName=findViewById(R.id.editTextfname);
        lName=findViewById(R.id.editTextlname);
        mail=findViewById(R.id.editTextemail);
        passw=findViewById(R.id.editTextpassword);
        confirm=findViewById(R.id.editText_reenterpassword);

        firebaseAuth=FirebaseAuth.getInstance();

        //user=new User();
      //  reff= FirebaseDatabase.getInstance().getReference().child("User");

        //ref= FirebaseDatabase.getInstance().getReference().child("User");

        addListenerOnButton();

    }


    public void addListenerOnButton()
    {

        final Context context = this;

        button = (Button) findViewById(R.id.buttonSignup1);

        button.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View arg0) {
                String firstName=fName.getText().toString().trim();
                String lastName=lName.getText().toString().trim();
                String Email=mail.getText().toString().trim();
                String password=passw.getText().toString().trim();
                String confirmPassword=confirm.getText().toString().trim();

                if (TextUtils.isEmpty(firstName)){
                    Toast.makeText(context,"Please enter your First Name to continue.",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(lastName)){
                    Toast.makeText(context,"Please enter your Last Name to continue.",Toast.LENGTH_LONG).show();
                    return;
                }if (TextUtils.isEmpty(Email)){
                    Toast.makeText(context,"Please enter your Email Address to continue.",Toast.LENGTH_LONG).show();
                    return;
                }if (TextUtils.isEmpty(password)){
                    Toast.makeText(context,"Please enter your password to continue.",Toast.LENGTH_LONG).show();
                    return;
                }if (TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(context,"Please confirm your password to continue.",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    Toast.makeText(context,"Please enter a valid Email address!",Toast.LENGTH_LONG).show();
                    return;
                }
                if (password.equals(confirmPassword)){
                    firebaseAuth.createUserWithEmailAndPassword(Email, password)
                            .addOnCompleteListener(activitySignup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(context,"Sign Up Complete!",Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(context, MainActivity.class);
                                        startActivity(intent);

                                    } else {
                                        if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                            Toast.makeText(context,"You are already registered.",Toast.LENGTH_LONG).show();
                                            return;
                                    }
                                        Toast.makeText(context,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                        return;

                                    }

                                    // ...
                                }
                            });

                }
                else {
                    Toast.makeText(context,"The passwords you entered do no match!",Toast.LENGTH_LONG).show();
                    return;

                }
                //user.setfName(fName.getText().toString().trim());
               // user.setlName(lName.getText().toString().trim());
               // user.setEmail(mail.getText().toString().trim());
               // user.setPassword(passw.getText().toString().trim());

              //  reff.push().setValue(user);
              //  ref.push().setValue(user);
              //  Toast.makeText(context,"You have Signed up!",Toast.LENGTH_LONG).show();
              //  Intent intent = new Intent(context, MainActivity.class);
              //  startActivity(intent);

            }

        });

    }
}
