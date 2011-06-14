package com.xinchejian.android.tutorials;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class SoundBackgroundPlayActivity extends Activity {
	private String TAG = SoundBackgroundPlayActivity.class.getCanonicalName();
	private static final float MINIMAL_SOUND_VOLUME_PERCENTAGE = 70.0f;
	private static final int NUMBER_OF_SIMULTANEOUS_STREAMS = 1; // we just play one at a time, any play over will replace the previous stream
	private static final int NOT_LOADED_YET = -1;
	private static final int SOUND_POOL_SUCCESS = 0;
	private static final int PLAYBACK_RATE = 1;
	private static final int LOOP_FOREVER = -1;
	//Clips from: 
	//http://www.partnersinrhyme.com/soundfx/car_sounds/car_motorcycle-idling_wav.shtml
	protected static final int PRIORITY = 0;
	private Button playButton;
	private SoundPool soundPool = new SoundPool(NUMBER_OF_SIMULTANEOUS_STREAMS, AudioManager.STREAM_MUSIC, 0);
	private int idleSoundId = NOT_LOADED_YET;
	private int cruiseSoundId = NOT_LOADED_YET;
	private int up2SpeedSoundId = NOT_LOADED_YET;
	private int streamId = NOT_LOADED_YET;
	private SeekBar motorControl;
	private int lastSpeed;
	private boolean accelerating;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
				if(status != SOUND_POOL_SUCCESS) {
					notifyUser("Load of " + sampleId + " failed");
					return;
				}
				// wait until all clips are loaded to enable play button
				if(idleSoundId != NOT_LOADED_YET && cruiseSoundId != NOT_LOADED_YET && up2SpeedSoundId != NOT_LOADED_YET) {
					playButton.setEnabled(true);
					playButton.setText("Press to play");
				}
			}});
		// this happens asynchronously; for every clip, onLoadComplete is called
		idleSoundId = soundPool.load(this, R.raw.idle, 1); // folder raw manually created in res/
		cruiseSoundId = soundPool.load(this, R.raw.cruisen, 1);
		up2SpeedSoundId = soundPool.load(this, R.raw.up2speed, 1);

		playButton = (Button) findViewById(R.id.playButton);
		playButton.setEnabled(false);
		playButton.setText("Waiting for sound clips to load");
		playButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				synchronized(soundPool) { // it's possible to get multiple clicks... make sure we process one at a time
					if(streamId == NOT_LOADED_YET) {
						// there was no stream playing so start playing
						playButton.setText("Playing, click to stop");
						playIdleOrCruise();
						motorControl.setEnabled(true);					
					// stream was playing so we to stop it
					} else {
						// something was playing, stop
						motorControl.setEnabled(false);	
						soundPool.stop(streamId);
						streamId = NOT_LOADED_YET;
						playButton.setText("Start playing");
					}
				}
			}});

		motorControl = (SeekBar) findViewById(R.id.motoControl);
		motorControl.setEnabled(false);
		motorControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			// the user is starting to drag the scrollbar
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO: don't need this boolean
				if(!accelerating) {
					play(up2SpeedSoundId); // accelerating
					accelerating = true;
				}
			}

			// for every advance on the scrollbar
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				lastSpeed = progress;
				float volume = calcVolume();
				soundPool.setVolume(streamId, volume, volume);
				
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				accelerating = false;
				playIdleOrCruise(); // we've reached our desired speed
			}
		});
	}
	
	private void play(int soundId) {
		// max simultaneous sound is one
		Log.d(TAG , "Playing " + soundId);
		float volume;
		if(idleSoundId == soundId) {
			volume = MINIMAL_SOUND_VOLUME_PERCENTAGE/100.0f;
		} else {
			volume = calcVolume(); // volume at least 0.5f
			
		}
		synchronized(soundPool) {
			streamId = soundPool.play(soundId, volume, volume, PRIORITY, LOOP_FOREVER, PLAYBACK_RATE);
		}
	}

	private float calcVolume() {
		float volume;
		volume = ((float)lastSpeed)/MINIMAL_SOUND_VOLUME_PERCENTAGE + MINIMAL_SOUND_VOLUME_PERCENTAGE;
		if(volume > 1.0f) {
			volume = 1.0f;
		}
		return volume;
	}
	
	private void playIdleOrCruise() {
		if(lastSpeed == 0) {
			play(idleSoundId);			
		} else {
			play(cruiseSoundId);
		}
	}
	protected void notifyUser(String string) {
		Toast.makeText(this, string, Toast.LENGTH_LONG).show();
	}
}