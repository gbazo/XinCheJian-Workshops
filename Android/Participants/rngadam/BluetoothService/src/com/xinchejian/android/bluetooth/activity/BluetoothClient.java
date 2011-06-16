package com.xinchejian.android.bluetooth.activity;

import com.xinchejian.android.bluetooth.service.BluetoothService;

// we create an abstraction so that we can 
// swap for a Messenger interface instead
// (IPC communication)
// Right now, since it's a locally created service,
// the implementation is very simple.
public class BluetoothClient {

	private final BluetoothService service;

	public BluetoothClient(BluetoothService service) {
		this.service = service;
	}

	public synchronized boolean checkBluetoothAvailable() {
		return service.checkBluetoothAvailable();
	}

	public synchronized boolean isConnected() {
		return service.isConnected();
	}

	public synchronized void sendBuffer(byte[] msgBuffer) {
		service.sendBuffer(msgBuffer);
	}

}
