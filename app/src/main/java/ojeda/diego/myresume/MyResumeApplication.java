package ojeda.diego.myresume;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;

import ojeda.diego.myresume.network.FirebaseApi;

/**
 * Created by diego on 16/7/16.
 */

public class MyResumeApplication extends Application {

    public static FirebaseApi sFirebaseApi;
    public static FirebaseAuth sFirebaseAuth;

    @Override
    public void onCreate() {
        super.onCreate();
        sFirebaseApi = new FirebaseApi();
        sFirebaseAuth = FirebaseAuth.getInstance();
        sFirebaseAuth.signInAnonymously();
    }
}
