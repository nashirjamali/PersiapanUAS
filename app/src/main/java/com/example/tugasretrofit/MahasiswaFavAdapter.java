package com.example.tugasretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MahasiswaFavAdapter extends RecyclerView.Adapter<MahasiswaFavAdapter.ViewHolder> {

    private Context context;
    private List<MahasiswaModel> mahasiswaModels;

    public MahasiswaFavAdapter(Context context, List<MahasiswaModel> mahasiswaModels) {
        this.context = context;
        this.mahasiswaModels = mahasiswaModels;
    }

    @NonNull
    @Override
    public MahasiswaFavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mahasiswa, parent, false);
        MahasiswaFavAdapter.ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaFavAdapter.ViewHolder holder, int position) {
        final MahasiswaModel mahasiswaModel = mahasiswaModels.get(position);

        final DBHelper db = new DBHelper(context);
        final boolean fav = db.findData(mahasiswaModel.getNim());

        if (fav) {
            holder.star.setImageResource(R.drawable.ic_star_black_24dp);
            holder.txtNama.setText(mahasiswaModel.getNama());
            holder.txtUsia.setText(mahasiswaModel.getUmur());

            holder.star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean add = db.removeData(mahasiswaModel.getNim());
                    if (add) {
                        notifyDataSetChanged();
                    }

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mahasiswaModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNama, txtUsia;
        ImageView star;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNama = itemView.findViewById(R.id.txtNama);
            txtUsia = itemView.findViewById(R.id.txtUsia);
            star = itemView.findViewById(R.id.star);

        }
    }
}
