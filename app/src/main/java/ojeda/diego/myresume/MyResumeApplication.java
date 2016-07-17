package ojeda.diego.myresume;

import android.app.Application;

import ojeda.diego.myresume.network.FirebaseApi;

/**
 * Created by diego on 16/7/16.
 */

public class MyResumeApplication extends Application {

    public static FirebaseApi sFirebaseApi;

    @Override
    public void onCreate() {
        super.onCreate();
        sFirebaseApi = new FirebaseApi();
    }
}
