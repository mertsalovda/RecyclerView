package ru.mertsalovda.curs2task4;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ComplexRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Object> itemList = new ArrayList<>();

    private static final int TEXT_CARD_TYPE = 10;
    private static final int IMAGE_CARD_TYPE = 20;
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public OnItemClickListener getListener() {
        return listener;
    }

    public interface OnItemClickListener {
        void onItemClick();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case TEXT_CARD_TYPE:
                View viewTextCard = inflater.inflate(R.layout.li_text, viewGroup, false);
                viewHolder = new TextHolder(viewTextCard);
                break;
            case IMAGE_CARD_TYPE:
                View viewImageCard = inflater.inflate(R.layout.li_image, viewGroup, false);
                viewHolder = new ImageHolder(viewImageCard);
                break;
            default:
                View defaltView = inflater.inflate(R.layout.li_text, viewGroup, false);
                viewHolder = new TextHolder(defaltView);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()){
            case TEXT_CARD_TYPE:
                TextHolder textHolder = (TextHolder)viewHolder;
                textHolder.bind((TextCard) itemList.get(position));
                //Listener -> Holder
                textHolder.setListener(listener);
                break;
            case IMAGE_CARD_TYPE:
                ImageHolder imageHolder = (ImageHolder)viewHolder;
                imageHolder.bind((ImageCard) itemList.get(position));
                //Listener -> Holder
                imageHolder.setListener(listener);
                break;
            default:
                break;
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (itemList.get(position) instanceof TextCard) {
            return TEXT_CARD_TYPE;
        } else if (itemList.get(position) instanceof ImageCard) {
            return IMAGE_CARD_TYPE;
        }
        return RecyclerView.INVALID_TYPE;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void addData(List<Object> items) {
        itemList.addAll(items);
        notifyDataSetChanged();
    }

    public void addData(Object items) {
        itemList.add(items);
        notifyItemInserted(itemList.size());
    }

    public void delete(){
        
    }
}
