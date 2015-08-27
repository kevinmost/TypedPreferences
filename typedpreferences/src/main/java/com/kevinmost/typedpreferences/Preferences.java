package com.kevinmost.typedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class Preferences {

  final SharedPreferences sharedPreferences;

  private Map<BaseTypedPreference<?>, Object> preferenceQueue = new HashMap<>();

  public Preferences(Context context) {
    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
  }

  public Preferences(SharedPreferences preferences) {
    this.sharedPreferences = preferences;
  }

  /**
   * Commits all changes made to each {@link TypedPreference} given in the {@param toCommit} array.
   * @param toCommit The preferences to commit all changes made
   */
  public void commit(TypedPreference<?>... toCommit) {
    final SharedPreferences.Editor editor = sharedPreferences.edit();
    for (TypedPreference<?> preference : toCommit) {
      try {
        final BaseTypedPreference baseTypedPreference = (BaseTypedPreference) preference;
        final Object value = preferenceQueue.get(baseTypedPreference);
        //noinspection unchecked
        baseTypedPreference.set(editor, value);
        preferenceQueue.remove(baseTypedPreference);
      } catch (ClassCastException e) {
        Log.e("TypedPreferences", "Error while trying to cast TypedPreference to BaseTypedPreference", e);
      }
    }
    editor.apply();
  }

  public <C extends Collection<? extends TypedPreference<?>>> void commit(C toCommit) {
    commit((TypedPreference<?>[]) toCommit.toArray());
  }

  /**
   * Commits all changes to all {@link TypedPreference}s that have uncommitted changes.
   */
  public void commitAll() {
    final SharedPreferences.Editor editor = sharedPreferences.edit();
    final Set<BaseTypedPreference<?>> baseTypedPreferences = preferenceQueue.keySet();
    commit(baseTypedPreferences);
    for (Map.Entry<BaseTypedPreference<?>, Object> toBeCommitted : preferenceQueue.entrySet()) {
      final BaseTypedPreference preference = toBeCommitted.getKey();
      final Object value = toBeCommitted.getValue();
      //noinspection unchecked
      preference.set(editor, value);
    }
    editor.apply();
    discardAll();
  }

  /**
   * Discards all uncommitted changes made to all {@link TypedPreference}s
   */
  public void discardAll() {
    preferenceQueue.clear();
  }

  /**
   * Registers a {@link TypedPreference} to save its preferences into the {@link SharedPreferences}
   * object backing this {@link Preferences} object.
   */
  public void registerPreference(TypedPreference<?> preference) {
    preference.register(this);
  }

  /**
   * Queues a change to this preference that will be committed to the {@link this#sharedPreferences}
   * when {@link this#commitAll()} is called, or when
   */
  <T> void queueChange(BaseTypedPreference<T> preference, T value) {
    preferenceQueue.put(preference, value);
  }

}
