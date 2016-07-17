package ojeda.diego.myresume.network;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ojeda.diego.myresume.BuildConfig;
import ojeda.diego.myresume.network.model.BaseModel;
import ojeda.diego.myresume.network.model.ProjectsListModel;

/**
 * Created by diego on 16/7/16.
 */

public class FirebaseApi {

    public interface FirebaseListener<T extends BaseModel> {
        void onSuccess(T response);
        void onError();
    }

    private static StorageReference sFirebaseStorageReference;
    private static FirebaseDatabase sFirebaseDatabaseReference;

    public FirebaseApi() {
        sFirebaseStorageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BuildConfig.FIREBASE_URL);
        sFirebaseDatabaseReference = FirebaseDatabase.getInstance();
    }

    public void getProjects(@NonNull FirebaseListener<ProjectsListModel> listener){
        getGenericFromFirebase(listener, PORTFOLIO_URL, ProjectsListModel.class);
    }

    public void getDownloadUriForMediaFile(String databasePath, OnSuccessListener<Uri> successListener, OnFailureListener failureListener){
        sFirebaseStorageReference.child(databasePath).getDownloadUrl().addOnSuccessListener(successListener).addOnFailureListener(failureListener);
    }

    private void getGenericFromFirebase(@NonNull final FirebaseListener firebaseListener, String firebaseUrl, final Class<? extends BaseModel> modelClass){
        DatabaseReference dbReference = sFirebaseDatabaseReference.getReference(firebaseUrl);
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String jsonString = new Gson().toJson(dataSnapshot.getValue());
                Gson gson = new GsonBuilder().serializeNulls().create();
                BaseModel model = gson.fromJson(jsonString, modelClass);
                firebaseListener.onSuccess(model);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                firebaseListener.onError();
            }
        });
    }

    //URLs inside firebase database
    private static final String PORTFOLIO_URL = "portfolio";

}
