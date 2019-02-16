package im.delight.android.example.location;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import im.delight.android.location.SimpleLocation;

public class MainActivity extends Activity {

	private SimpleLocation mLocation;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// construct a new instance
		mLocation = new SimpleLocation(this);

		// reduce the precision to 5,000m for privacy reasons
		mLocation.setBlurRadius(5000);

		// if we can't access the location yet
		if (!mLocation.hasLocationEnabled()) {
			// ask the user to enable location access
			SimpleLocation.openSettings(this);
		}

		findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final double latitude = mLocation.getLatitude();
				final double longitude = mLocation.getLongitude();

				Toast.makeText(MainActivity.this, "Latitude: "+latitude, Toast.LENGTH_SHORT).show();
				Toast.makeText(MainActivity.this, "Longitude: "+longitude, Toast.LENGTH_SHORT).show();
			}

		});
	}

	@Override
	protected void onResume() {
		super.onResume();

		// make the device update its location
		mLocation.beginUpdates();
	}

	@Override
	protected void onPause() {
		// stop location updates (saves battery)
		mLocation.endUpdates();

		super.onPause();
	}

}
