package com.example.android.miwok;

import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.miwok.data.MiwokItem;
import com.example.android.miwok.data.MiwokWordList;
import com.example.android.miwok.databinding.WordListBinding;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.miwok.MiwokFeatures.mCompletionListener;
import static com.example.android.miwok.MiwokFeatures.releaseMediaPlayer;

public class FamilyActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    WordListBinding wordListBinding = DataBindingUtil.setContentView(this,
            R.layout.word_list);

    GroupAdapter adapter = new GroupAdapter();

    wordListBinding.list.setAdapter(adapter);

    wordListBinding.list.setLayoutManager(new LinearLayoutManager(this));

    List<Word> words = MiwokWordList.getFamilyMembers();
    for (Word word : words){
       adapter.add(new MiwokItem(word, R.color.category_family));
     }

    adapter.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(@NonNull Item item, @NonNull View view) {
        if (item instanceof MiwokItem){
          Word word = ((MiwokItem) item).getWord();
          releaseMediaPlayer();

          MiwokFeatures.mMediaPlayer = MediaPlayer.create(FamilyActivity.this,
                  word.getAudioResourceId());

          // Start the audio file
          MiwokFeatures.mMediaPlayer.start();

          // Setup a listener on the media player, so that we can stop and release the
          // media player once the sound has finished playing.
          MiwokFeatures.mMediaPlayer.setOnCompletionListener(mCompletionListener);
        }
      }
    });
  }

  @Override protected void onStop() {
    super.onStop();
    releaseMediaPlayer();
  }
 }
