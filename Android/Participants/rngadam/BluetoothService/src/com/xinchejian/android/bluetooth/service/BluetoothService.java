package com.xinchejian.android.bluetooth.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class BluetoothService extends Service {
	/**
	 * Class used for the client Binder. Because we know this service always
	 * runs in the same process as its clients, we don't need to deal with IPC.
	 */
	public class LocalBinder extends Binder {
		public BluetoothService getService() {
			// Return this instance of LocalService so clients can call public
			// methods
			return BluetoothService.this;
		}
	}
	
	private static final String TAG = BluetoothService.class.getName();

	// Binder given to clients
	private final IBinder binder = new LocalBinder();

	private Bluetooth bluetooth;

	private BlockingQueue<byte[]> sendQueue = new LinkedBlockingQueue<byte[]>();

	private Runnable backgroundProcessing = new Runnable() {
		private int pingAttempts = 0;

		@Override
		public void run() {
			while(running) {
				if(bluetooth != null && !bluetooth.ping(1000)) {
					Log.d(TAG, "Unsuccesful ping #" + pingAttempts);
					pingAttempts++;
					sleep(1000);
					if(pingAttempts>10) {
						Log.d(TAG, "Trying to reconnect");
						bluetooth.connect();
						pingAttempts = 0;
					}
				} else {
					pingAttempts = 0;
					byte[] take = sendQueue.poll();
					if(take != null) {
						Log.d(TAG, "Took from queue:" + take);
						bluetooth.sendBuffer(take);
					} else {
						sleep(100);
					}
				}
			}
		}

	};
	private boolean running = false;

	private Thread backgroundThread;
	public synchronized boolean checkBluetoothAvailable() {
		return bluetooth.checkBluetoothAvailable();
	}

	public synchronized boolean isConnected() {
		return bluetooth.isConnected();
	}

	@Override
	public IBinder onBind(Intent intent) {
		Toast.makeText(getApplicationContext(), "binding", Toast.LENGTH_SHORT)
				.show();
		return binder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "onCreate");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");		
	}

	// services exposed...

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		Log.d(TAG, "onLowMemory");		
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		if(backgroundThread == null) {
			running = true;
			bluetooth = new Bluetooth(intent.getStringExtra("UUID"));
			bluetooth.connect();
			backgroundThread = new Thread(backgroundProcessing);
			backgroundThread.start();
		}
	}

	@Override
	public boolean onUnbind(Intent intent) {
		running = false;
		return super.onUnbind(intent);
	}
	
	public synchronized void sendBuffer(byte[] msgBuffer) {
		try {
			Log.d(TAG, "Adding to queue:" + msgBuffer);
			sendQueue.put(msgBuffer);
		} catch (IllegalStateException e) {
			Log.e(TAG, "No space left in queue!");
			try {
				sendQueue.take();
				sendQueue.put(msgBuffer);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} // discard
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	private void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
