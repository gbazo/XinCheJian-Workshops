package com.xinchejian.android.bluetooth.activity;

import com.xinchejian.android.bluetooth.service.BluetoothService;

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
