package go_solution.memory;

/**
 * Created by ralphniederer on 24.10.17.
 */
        import android.support.v7.widget.RecyclerView;
        import android.content.Context;
        import android.graphics.BitmapFactory;
        import android.support.v7.widget.CardView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private List<Saver> setData;
    private MainActivity mact;
    private Context inhalt;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView bild;
        public TextView text;
        public CardView cview;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    mact.takeQrCodePicture();
                    mact.clicks = getAdapterPosition();
                }
            });
            this.cview = (CardView)itemView.findViewById(R.id.cview);
            this.bild = (ImageView)itemView.findViewById(R.id.image);
            this.text = (TextView)itemView.findViewById(R.id.text);
        }
    }

    public CustomAdapter(List<Saver> SetData, Context Inhalt, MainActivity MainActivity){
        this.setData = SetData;
        this.inhalt = Inhalt;
        this.mact = MainActivity;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        if (!setData.get(position).getCode().equals("") && !setData.get(position).getPath().equals("")) {
            myViewHolder.bild.setImageBitmap(BitmapFactory.decodeFile(setData.get(position).getPath()));
            myViewHolder.text.setText(setData.get(position).getCode());
        } else {
            myViewHolder.bild.setImageDrawable(inhalt.getResources().getDrawable(R.drawable.takeit));
            myViewHolder.text.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return setData.size();
    }

}
