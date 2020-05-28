package sg.edu.np.mad.mad_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final String TAG="To-Do List";
    ArrayList<String>data = new ArrayList<>();
    Button button;
    EditText editText;
    Adapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.addButton);
        editText = findViewById(R.id.addDescription);
        recyclerView = findViewById(R.id.taskListLayout);
        data.add("Buy Milk");
        data.add("Send Postage");
        data.add("Buy Android Development Book");

        adapter = new Adapter(data);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        }

        /**
         * Upon calling this method, the keyboard will retract
         * and the recyclerview will scroll to the last item
         *
         * @param rv RecyclerView for scrolling to
         * @param data ArrayList that was passed into RecyclerView
         */
    private void showNewEntry(RecyclerView rv, ArrayList data){
        //scroll to the last item of the recyclerview
        rv.scrollToPosition(data.size() - 1);

        //auto hide keyboard after entry
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(recyclerView.getWindowToken(), 0);
    }

    public void AddtoList (View v){
        data.add(editText.getText().toString());
        adapter.notifyDataSetChanged();
        showNewEntry(recyclerView,data);
        Log.v(TAG,"Added new to-do option.");
        editText.getText().clear();
    }

}
