package com.xinchejian.android.bluetooth.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.xinchejian.android.bluetooth.R;
import com.xinchejian.android.bluetooth.robot.RobotControl;
import com.xinchejian.android.bluetooth.service.BluetoothService;
import com.xinchejian.android.bluetooth.service.BluetoothService.LocalBinder;

public class ServoControlActivity extends Activity {
	private static final String arduinoAddress = "00:11:03:14:02:02";
	private BluetoothClient bluetoothClient = null;
	private ToggleButton bluetoothConnectionStatusToggle;
	private ToggleButton deviceConnectedStatusToggle;
	private final Handler handlerTimer = new Handler();
	/**
	 * Class for interacting with the main interface of the service.
	 */
	private ServiceConnection mConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			// This is called when the connection with the service has been
			// established, giving us the object we can use to
			// interact with the service. We are communicating with the
			// service using a Messenger, so here we get a client-side
			// representation of that from the raw IBinder object.
			LocalBinder binder = (LocalBinder) service;
			bluetoothClient = new BluetoothClient(binder.getService());
			robotControl = new RobotControl(bluetoothClient);
		}

		public void onServiceDisconnected(ComponentName className) {
			// This is called when the connection with the service has been
			// unexpectedly disconnected -- that is, its process crashed.
			bluetoothClient = null;
		}
	};
	private int prevSidewaysValue = 0;
	private int prevUpDownValue = 0;

	private RobotControl robotControl;
	private SeekBar sidewaysSeekBar;
	private int sidewaysValue = RobotControl.SIDEWAYS_DEFAULT_POS;
	private final Runnable taskTimerUpdate = new Runnable() {
		public void run() {
			if (bluetoothClient == null || robotControl == null)
				return;
			
			if (updownValue != prevUpDownValue) {
				robotControl.sendCommandString(RobotControl.UPDOWN_AXIS,
						updownValue);
				prevUpDownValue = updownValue;
			}
			if (prevSidewaysValue != sidewaysValue) {
				robotControl.sendCommandString(RobotControl.SIDEWAYS_AXIS,
						sidewaysValue);
				prevSidewaysValue = sidewaysValue;

			}
			bluetoothConnectionStatusToggle.setChecked(bluetoothClient
					.checkBluetoothAvailable());
			deviceConnectedStatusToggle.setChecked(bluetoothClient.isConnected());
			handlerTimer.postDelayed(this, 300);
		}
	};

	private SeekBar upDownSeekBar;

	private int updownValue = RobotControl.UPDOWN_DEFAULT_POS;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bluetoothConnectionStatusToggle = (ToggleButton) findViewById(R.id.bluetoothConnectionStatus);
		deviceConnectedStatusToggle = (ToggleButton) findViewById(R.id.bluetoothConnected);
		upDownSeekBar = (SeekBar) findViewById(R.id.UpDownSeekBar);
		sidewaysSeekBar = (SeekBar) findViewById(R.id.SidewaysSeekBar);
		upDownSeekBar.setProgress(updownValue);
		sidewaysSeekBar.setProgress(sidewaysValue);
		upDownSeekBar.setMax(RobotControl.UPDOWN_MAX_POS);
		sidewaysSeekBar.setMax(RobotControl.SIDEWAYS_MAX_POS);
		upDownSeekBar
				.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					public void onProgressChanged(final SeekBar seekBar,
							final int progress, final boolean fromUser) {
						if (fromUser) {
							if (progress < RobotControl.UPDOWN_MIN_POS) {
								updownValue = RobotControl.UPDOWN_MIN_POS;
							} else {
								updownValue = progress;
							}
						}
					}

					public void onStartTrackingTouch(SeekBar seekBar) {
					}

					public void onStopTrackingTouch(SeekBar seekBar) {
					}

				});
		sidewaysSeekBar
				.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					public void onProgressChanged(final SeekBar seekBar,
							final int progress, final boolean fromUser) {
						if (fromUser) {
							if (progress < RobotControl.SIDEWAYS_MIN_POS) {
								sidewaysValue = RobotControl.SIDEWAYS_MIN_POS;
							} else {
								sidewaysValue = progress;
							}
						}
					}

					public void onStartTrackingTouch(SeekBar seekBar) {
					}

					public void onStopTrackingTouch(SeekBar seekBar) {
					}
				});

		handlerTimer.postDelayed(taskTimerUpdate, 300);

	}

	@Override
	protected void onStart() {
		super.onStart();
		// startService
		Intent intent = new Intent(this, BluetoothService.class);
		intent.putExtra("UUID", arduinoAddress);
		ComponentName componentName = startService(intent);
		if (componentName == null) {
			Toast.makeText(this, "Could not connect to service",
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "Connecting to service",
					Toast.LENGTH_SHORT).show();
			// Bind to the service
			bindService(new Intent(this, BluetoothService.class),
					mConnection, Context.BIND_AUTO_CREATE);
		}

	}

	@Override
	protected void onStop() {
		super.onStop();
		// Unbind from the service
		if (bluetoothClient != null) {
			unbindService(mConnection);
			bluetoothClient = null;
		}
	}

}