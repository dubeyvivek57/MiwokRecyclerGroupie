package com.example.android.miwok;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
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

import static com.example.android.miwok.MiwokFeatures.mAudioManager;
import static com.example.android.miwok.MiwokFeatures.mOnAudioFocusChangeListener;
import static com.example.android.miwok.MiwokFeatures.releaseMediaPlayer;

public class NumbersActivity extends AppCompatActivity {
  /**
   * This listener gets triggered when the {@link MediaPlayer} has completed
   * playing the audio file.
   */
  private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
      // Now that the sound file has finished playing, release the media player resources.
      releaseMediaPlayer();
    }
  };


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    WordListBinding wordListBinding = DataBindingUtil.setContentView(this, R.layout.word_list);

    // Create and setup the {@link AudioManager} to request audio focus
    mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

    GroupAdapter groupAdapter = new GroupAdapter();
    wordListBinding.list.setAdapter(groupAdapter);
    wordListBinding.list.setLayoutManager(new LinearLayoutManager(this));

    List<Word> words = MiwokWordList.getNumbers();
    for(Word word : words){
      groupAdapter.add(new MiwokItem(word, R.color.category_numbers));
    }

    groupAdapter.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(@NonNull Item item, @NonNull View view) {
        // Release the media player if it currently exists because we are about to
        // play a different sound file
        releaseMediaPlayer();
        Word word = ((MiwokItem) item).getWord();

        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
          // We have audio focus now.

          // Create and setup the {@link MediaPlayer} for the audio resource associated
          // with the current word
          MiwokFeatures.mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());

          // Start the audio file
          MiwokFeatures.mMediaPlayer.start();

          // Setup a listener on the media player, so that we can stop and release the
          // media player once the sound has finished playing.
          MiwokFeatures.mMediaPlayer.setOnCompletionListener(mCompletionListener);
        }
    }
    });
  }
  @Override
  protected void onStop() {
    super.onStop();
    // When the activity is stopped, release the media player resources because we won't
    // be playing any more sounds.
    releaseMediaPlayer();
  }


}
