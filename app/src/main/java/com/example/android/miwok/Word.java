package com.example.android.miwok;

import android.widget.ImageView;

public class Word {

  /** Default translation for the word */
  private String mDefaultTranslation;

  /** Miwok translation for the word */
  private String mMiwokTranslation;

  private int mImageResourceId = NO_IMAGE_PROVIDES;

  private static final int NO_IMAGE_PROVIDES = -1;

  private  int mMediaResourceId;

  /**
   * Create a new Word object.
   *
   * @param defaultTranslation is the word in a language that the user is already familiar with
   *                           (such as English)
   * @param miwokTranslation is the word in the Miwok language
   */
  public Word(String defaultTranslation, String miwokTranslation, int medeiaResource) {
    mDefaultTranslation = defaultTranslation;
    mMiwokTranslation = miwokTranslation;
    mMediaResourceId = medeiaResource;
  }

  /**
   * Create a new Word object.
   *
   * @param defaultTranslation is the word in a language that the user is already familiar with
   *                           (such as English)
   * @param miwokTranslation is the word in the Miwok language
   *
   * @param imageResourceId is the resourceId
   */
  public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int mediaResource) {
    mDefaultTranslation = defaultTranslation;
    mMiwokTranslation = miwokTranslation;
    mImageResourceId = imageResourceId;
    mMediaResourceId = mediaResource;
  }

  /**
   * Get the default translation of the word.
   */
  public String getDefaultTranslation() {
    return mDefaultTranslation;
  }

  /**
   * Get the Miwok translation of the word.
   */
  public String getMiwokTranslation() {
    return mMiwokTranslation;
  }

  public int getImageResourceId(){ return mImageResourceId ;}

  public boolean hasImage(){
    return mImageResourceId != NO_IMAGE_PROVIDES;
  }

  public int getAudioResourceId(){ return mMediaResourceId;}

  @Override public String toString() {
    return "Word{"
        + "mDefaultTranslation='"
        + mDefaultTranslation
        + '\''
        + ", mMiwokTranslation='"
        + mMiwokTranslation
        + '\''
        + ", mImageResourceId="
        + mImageResourceId
        + ", mMediaResourceId="
        + mMediaResourceId
        + '}';
  }
}
