package tamara.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText question_input,option1_input,option2_input,option3_input,option4_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        question_input = findViewById(R.id.question_input);
        option1_input = findViewById(R.id.option1_input);
        option2_input = findViewById(R.id.option2_input);
        option3_input = findViewById(R.id.option3_input);
        option4_input = findViewById(R.id.option4_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override //ako nesto ne mi raboti ovoj del go ima na pocetok
            public void onClick(View view) {
                DBHelperPoll DB = new DBHelperPoll(AddActivity.this);
                DB.addPoll(question_input.getText().toString().trim(), option1_input.getText().toString().trim(), option2_input.getText().toString().trim(),option3_input.getText().toString().trim(),option4_input.getText().toString().trim());
            }
        });
    }
}