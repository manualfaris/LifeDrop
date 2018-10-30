package com.example.faris.lifedrops;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    ImageButton myImageButton;
    EditText EditName,EditAge,EditGroup,EditMail,EditPhone,EditPlace,EditState;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        myImageButton=findViewById(R.id.signin);
        EditName=findViewById(R.id.name);
        EditAge=findViewById(R.id.age);
        EditGroup=findViewById(R.id.group);
        EditMail=findViewById(R.id.email);
        EditPhone=findViewById(R.id.phone);
        EditPlace=findViewById(R.id.place);
        EditState=findViewById(R.id.state);
        mDatabase=FirebaseDatabase.getInstance().getReference();
        myImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = EditName.getText().toString();
                String group = EditGroup.getText().toString();
                String age=EditAge.getText().toString();
                Long Age=Long.parseLong(age);
                String email = EditMail.getText().toString();
                String phone = EditPhone.getText().toString();
                String place = EditPlace.getText().toString();
                String state = EditState.getText().toString();
                User user=new User(name,group,email,phone,place,state,Age);
                mDatabase.child("Users").setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent loadNewActivity = new Intent(HomeActivity.this, dashboard.class);
                            startActivity(loadNewActivity);
                        }
                        else
                        {
                            Toast.makeText(HomeActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    }

                });

            }
        });
    }
}
