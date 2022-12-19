package tamara.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    DBHelperPoll DB;
    ArrayList<String> poll_id, question, option1, option2, option3,option4;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( HomeActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        DB = new DBHelperPoll(HomeActivity.this);
        poll_id = new ArrayList<>();
        question = new ArrayList<>();
        option1 = new ArrayList<>();
        option2 = new ArrayList<>();
        option3 = new ArrayList<>();
        option4 = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(HomeActivity.this,poll_id, question, option1, option2, option3,option4);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
    }
    void storeDataInArrays(){
        Cursor cursor = DB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else {
            while(cursor.moveToNext()){
                poll_id.add(cursor.getString(0));
                question.add(cursor.getString(1));
                option1.add(cursor.getString(2));
                option2.add(cursor.getString(3));
                option3.add(cursor.getString(4));
                option4.add(cursor.getString(5));
            }
        }
    }
}