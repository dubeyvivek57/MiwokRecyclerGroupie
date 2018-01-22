package com.example.android.miwok.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.android.miwok.R;
import com.example.android.miwok.Word;
import com.example.android.miwok.databinding.ListItemBinding;
import com.xwray.groupie.databinding.BindableItem;

import java.util.ArrayList;

/**
 * Created by Vivek on 14-01-2018.
 */

public class MiwokItem extends BindableItem<ListItemBinding> {

    private final Word word;

    private int mColorResourceId;

    public MiwokItem(Word word, int mColorResourceId){
        this.word = word;
        this.mColorResourceId = mColorResourceId;
    }

    public Word getWord(){
        return word;
    }

    @Override
    public void bind(@NonNull ListItemBinding viewBinding, int position) {
        Context context = viewBinding.getRoot().getContext();
        viewBinding.miwokTextView.setText(word.getMiwokTranslation());
        viewBinding.defaultTextView.setText(word.getDefaultTranslation());

        if (word.hasImage()){
            viewBinding.wordImages.setImageResource(word.getImageResourceId());
            viewBinding.wordImages.setVisibility(View.VISIBLE);
        } else{
            viewBinding.wordImages.setVisibility(View.GONE);
        }
        int color = ContextCompat.getColor(viewBinding.wordImages.getContext(), mColorResourceId);
        viewBinding.textContainer.setBackgroundColor(color);
    }

    @Override
    public int getLayout() {
        return R.layout.list_item;
    }
}
