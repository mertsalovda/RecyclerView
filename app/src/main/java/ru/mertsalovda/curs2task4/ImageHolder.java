package ru.mertsalovda.curs2task4;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class ImageHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;

    public ImageHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.iv_image);
    }

    public void bind (ImageCard imageCard){
        if (imageCard != null) {
            imageView.setImageResource(imageCard.getImage());
        }
    }

    public void setListener(final ComplexRecyclerViewAdapter.OnItemClickListener listener){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(getAdapterPosition());
            }
        });
    }
}
