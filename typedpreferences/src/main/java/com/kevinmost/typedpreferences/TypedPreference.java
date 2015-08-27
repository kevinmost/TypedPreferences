package com.kevinmost.typedpreferences;

public interface TypedPreference<T> {
  /**
   * @return The current committed value of this {@link TypedPreference}. Any uncommitted changes
   * will not be reflected. If no value was set for this preference, the default value is returned.
   */
  T get();

  /**
   * Sets the value of this {@link TypedPreference} to {@param value}. This value will not be
   * committed, and thus not reflected in {@link TypedPreference#get()}, until
   * {@link TypedPreference#commitChanges()} is called, or
   * {@link Preferences#commit(TypedPreference[])} is called upon the registered {@link Preferences}
   * object with this object as a parameter, or {@link Preferences#commitAll()} is called upon the
   * registered {@link Preferences} object.
   */
  void set(T value);

  /**
   * Commits these changes to the registered {@link Preferences} object. Alternatively, you can call
   * {@link Preferences#commit(TypedPreference[])} upon the registered {@link Preferences} object
   * with this object as a parameter, or {@link Preferences#commitAll()} upon the registered
   * {@link Preferences} object.
   */
  void commitChanges();

  /**
   * Discard all changes made to this preference since the last time the changes were committed.
   */
  void discardChanges();

  /**
   * Register a {@link Preferences} object that will be modified when {@link this#get()} and
   * {@link this#set(T)} are called.
   * @param preferences
   */
  void register(Preferences preferences);

  /**
   * @return The {@link Preferences} object last set via {@link this#register(Preferences)}.
   */
  Preferences getRegisteredPreferences();

  /**
   * @return true if {@param preferences} is the object that was passed in as a parameter the
   * last time {@link this#register(Preferences)} was called, false otherwise.
   */
  boolean isRegisteredTo(Preferences preferences);

  /**
   * @return true if {@link this#set(Object)} has been called and the changes have not yet been
   * either committed or discarded.
   */
  boolean hasUncommittedChanges();

  final class Builder<T> {
    private String key;
    private OnPreferenceChangedListener<T> onPreferenceChangedListener;
    private T defaultValue;
    private Preferences preferencesToRegisterTo;


    public Builder<T> setDefaultValue(T defaultValue) {
      this.defaultValue = defaultValue;
      return this;
    }

    public Builder<T> setOnPreferenceChangedListener(OnPreferenceChangedListener<T> onPreferenceChangedListener) {
      this.onPreferenceChangedListener = onPreferenceChangedListener;
      return this;
    }

    public Builder<T> setKey(String key) {
      this.key = key;
      return this;
    }

    public Builder<T> setRegisterTo(Preferences preferences) {
      this.preferencesToRegisterTo = preferences;
      return this;
    }

    public TypedPreference<T> build() {
      return PreferenceUtil.from(key, onPreferenceChangedListener, defaultValue, preferencesToRegisterTo);
    }
  }
}
