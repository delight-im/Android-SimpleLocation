# Android-SimpleLocation

Utility class for easy access to the device location on Android

## Installation

 * Include one of the [JARs](JARs) in your `libs` folder
 * or
 * Copy the Java package to your project's source folder
 * or
 * Create a new library project from this repository and reference it in your project

## Usage

### Decide for the required granularity

 * If you want to get the device's location with fine granularity (between 2m and 100m precision), GPS will be required. This consumes more battery but is most precise.
 * If you want to get the device's location with coarse granularity only (precise to several hundred meters), the location will be retrieved from the network (Wi-Fi and cell towers). This saves battery but is less precise.

### Add the required permissions

For fine location (GPS location), add the following permission in your `AndroidManifest.xml`:

```
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

For coarse location (network location), add the following permission in your `AndroidManifest.xml`:

```
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

### Retrieve the location from the device

```
public class MyActivity extends Activity {

	private SimpleLocation location;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// ...

		// construct a new instance of SimpleLocation
		location = new SimpleLocation(this);

		// if we can't access the location yet
		if (!location.hasLocationEnabled()) {
			// ask the user to enable location access
			SimpleLocation.openSettings(this);
		}

		findViewById(R.id.someView).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final double latitude = location.getLatitude();
				final double longitude = location.getLongitude();

				// TODO
			}

		});
	}

	@Override
	protected void onResume() {
		super.onResume();

		// make the device update its location
		location.beginUpdates();

		// ...
	}

	@Override
	protected void onPause() {
		// stop location updates (saves battery)
		location.endUpdates();

		// ...

		super.onPause();
	}

}
```


### Calculate the distance between two locations

```
// alternative A
location.calculateDistance(startLatitude, startLongitude, endLatitude, endLongitude);

// alternative B
location.calculateDistance(startPoint, endPoint);
```

### Blur the location for privacy reasons

```
// reduce the precision to 10,000m for privacy reasons
location.setBlurRadius(10000);
```

## License

```
Copyright 2014 www.delight.im <info@delight.im>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
