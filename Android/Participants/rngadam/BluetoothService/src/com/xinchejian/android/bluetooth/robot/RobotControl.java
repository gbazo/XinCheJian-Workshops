package com.xinchejian.android.bluetooth.robot;

import java.io.IOException;
import java.util.Formatter;
import java.util.Locale;

import com.xinchejian.android.bluetooth.activity.BluetoothClient;

import android.util.Log;

public class RobotControl {
	public static final char SIDEWAYS_AXIS = 'S'; // left/right
	public static final int SIDEWAYS_DEFAULT_POS = 90;
	public static final int SIDEWAYS_MAX_POS = 160;
	public static final int SIDEWAYS_MIN_POS = 20;
	private static final String TAG = RobotControl.class.getSimpleName();
	public static final char UPDOWN_AXIS = 'U'; // up/down
	public static final int UPDOWN_DEFAULT_POS = 135;
	public static final int UPDOWN_MAX_POS = 180;
	public static final int UPDOWN_MIN_POS = 55;
	private final BluetoothClient bluetooth;
	private final StringBuilder formatted_output = new StringBuilder();
	private final Formatter formatter = new Formatter(formatted_output,
			Locale.US);

	public RobotControl(BluetoothClient bluetooth) {
		this.bluetooth = bluetooth;
	}

	private String getCommandString(final char axis, final int value) {
		synchronized (formatter) {
			formatter.format("%c0%03d0%d", axis, value, value % 9);
			final String string = formatted_output.toString();
			formatted_output.delete(0, formatted_output.length());
			return string;
		}
	}

	public void sendCommandString(final char axis, final int value) {
		sendCommandString(getCommandString(axis, value));
	}

	private void sendCommandString(final String string) {
		try {
			byte[] msgBuffer;
			Log.d(TAG, "sending: " + string);
			msgBuffer = string.getBytes("ISO-8859-1");
			if (msgBuffer.length != 7) {
				throw new RuntimeException("Unexpected bytes output for: "
						+ string);
			}
			synchronized (bluetooth) {
				bluetooth.sendBuffer(msgBuffer);
			}
		} catch (final IOException e) {
			Log.e(TAG, "Exception during write.", e);
			return;
		}
	}
}
