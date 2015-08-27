package com.kevinmost.typedpreferences;

public abstract class OnPreferenceChangedListener<T> {
  /**
   * Executed when a change is made to a TypedPreference, before it is committed.
   * @param newValue The newly-changed value of this preference
   */
  public void onPreferenceChanged(T newValue) {}

  /**
   * Executed when a change is made to a TypedPreference, after it is committed.
   * @param newValue The newly-committed value of this preference
   */
  public void onPreferenceCommitted(T newValue) {}
}
