package go_solution.memory;

/**
 * Created by ralphniederer on 24.10.17.
 */
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

public class CustomAdapter extends RecyclerView.Adapter {
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
    @Override
    public int getItemCount() {
        return 0;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;// init the item view's
        public MyViewHolder(View itemView) {
            super(itemView);

// get the reference of item view's
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
