package com.xinchejian.android.tutorials;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class Gauges extends Activity {
	private static final int TOP_SAFE_SPEED = 70;
	private static final int TOP_SPEED = 85;
	private int increment = 5;
	private int direction = 1;
	private AnimationControlView animationControlView;
	private int simulatedSpeed = 0;
	private Handler simulation = new Handler();
	private Runnable simulator = new Runnable() {
		public void run() {
			if(simulatedSpeed >= TOP_SPEED){
				direction = -1;
				simulatedSpeed = TOP_SPEED;
			}
			if(simulatedSpeed <= 0){
				direction = 1;
				simulatedSpeed = 0;
			}			
			simulatedSpeed+=increment*direction;
			
			//those two lines go in real bike
			animationControlView.setSpeed(simulatedSpeed);
			simulation.postDelayed(this, 100);
		}
		
	};
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		LinearLayout main = new LinearLayout(this);
		main.setOrientation(LinearLayout.VERTICAL);
		setContentView(main);
		
		animationControlView = new AnimationControlView(this, "file:/android_asset/gauges2.html");
		animationControlView.setTopSpeed(TOP_SPEED);
		animationControlView.setTopSafeSpeed(TOP_SAFE_SPEED);
		animationControlView.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		main.addView(animationControlView);
		
		simulation.postDelayed(simulator, 2000);
    }
}