package sg.edu.np.mad.mad_recyclerview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    final String TAG = "Adapter";
    ArrayList<String> data;
    TextView displayItem;
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener oicl) {
        this.onItemClickListener = oicl;
    }

    public interface OnItemClickListener{
        void ItemClick(int position);
    }

    public Adapter(ArrayList<String> input){
        data = input;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent, false);
        return new ViewHolder(item);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String s = data.get(position);
        holder.txt.setText(s);
        final String item = holder.txt.getText().toString();

        holder.deleteArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Alert and delete
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                //layout inflater
                LayoutInflater layoutInflater = LayoutInflater.from(v.getContext());
                View AlertView = layoutInflater.inflate(R.layout.alertdialog,null);
                builder.setView(AlertView)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                data.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                displayItem = AlertView.findViewById(R.id.item);
                displayItem.setText(item + "?");
                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("Delete");
                alertDialog.show();
            }
        });
    }
    @Override
        public int getItemCount() {
            return data.size();
        }
    }

