package com.example.viikko10ver2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        UserStorage.getInstance().loadUsers(this);
    }

    public void switchToAddUser(View view)  {
        Intent intent = new Intent(this, AddUserActivity.class);
        startActivity(intent);
    }

    public void switchToListUsers(View view)  {
        Intent intent = new Intent(this, ListUsersActivity.class);
        startActivity(intent);
    }
}