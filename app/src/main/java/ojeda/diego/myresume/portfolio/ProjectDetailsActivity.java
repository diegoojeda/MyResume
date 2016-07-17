package ojeda.diego.myresume.portfolio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ojeda.diego.myresume.R;

public class ProjectDetailsActivity extends AppCompatActivity {

    public static final String PROJECT_MODEL_EXTRA = "projectDetailsActivity.projectModelExtra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
    }
}
