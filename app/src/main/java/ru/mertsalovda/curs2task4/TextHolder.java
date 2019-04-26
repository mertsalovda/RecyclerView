package ru.mertsalovda.curs2task4;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TextHolder extends RecyclerView.ViewHolder {

    TextView nameText, numberText;

    public TextHolder(@NonNull View itemView) {
        super(itemView);
        nameText = itemView.findViewById(R.id.tv_name);
        numberText = itemView.findViewById(R.id.tv_number);
    }

    public TextView getNameText() {
        return nameText;
    }

    public void setNameText(TextView nameText) {
        this.nameText = nameText;
    }

    public TextView getNumberText() {
        return numberText;
    }

    public void setNumberText(TextView numberText) {
        this.numberText = numberText;
    }

    public void bind(TextCard textCard){
        if (textCard != null) {
            nameText.setText(textCard.getName());
            numberText.setText(textCard.getNumber());
        }
    }

    public void setListener(final ComplexRecyclerViewAdapter.OnItemClickListener listener){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick();
            }
        });
    }
}
