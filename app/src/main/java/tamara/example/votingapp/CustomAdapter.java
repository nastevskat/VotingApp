package tamara.example.votingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList poll_id, question, option1, option2, option3, option4;

    CustomAdapter(Context context, ArrayList poll_id, ArrayList question, ArrayList option1, ArrayList option2, ArrayList option3,
                  ArrayList option4){
        this.context = context;
        this.question = question;
        this.poll_id = poll_id;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.poll_id_txt.setText(String.valueOf(poll_id.get(position)));
        holder.question_txt.setText(String.valueOf(question.get(position)));
        holder.option1_txt.setText(String.valueOf(option1.get(position)));
        holder.option2_txt.setText(String.valueOf(option2.get(position)));
        holder.option3_txt.setText(String.valueOf(option3.get(position)));
        holder.option4_txt.setText(String.valueOf(option4.get(position)));
    }

    @Override
    public int getItemCount() {
        return poll_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView poll_id_txt, question_txt, option1_txt, option2_txt, option3_txt, option4_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            poll_id_txt = itemView.findViewById(R.id.poll_id_txt);
            question_txt = itemView.findViewById(R.id.question_txt);
            option1_txt = itemView.findViewById(R.id.option1_txt);
            option2_txt = itemView.findViewById(R.id.option2_txt);
            option3_txt = itemView.findViewById(R.id.option3_txt);
            option4_txt = itemView.findViewById(R.id.option4_txt);
        }
    }
}
