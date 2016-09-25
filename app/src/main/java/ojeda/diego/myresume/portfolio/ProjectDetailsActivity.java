package ojeda.diego.myresume.portfolio;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.viewpagerindicator.TitlePageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import ojeda.diego.myresume.R;
import ojeda.diego.myresume.network.model.PortfolioProject;
import ojeda.diego.myresume.portfolio.adapter.ProjectDetailsImagesPagerAdapter;

public class ProjectDetailsActivity extends AppCompatActivity {

    public static final String PROJECT_MODEL_EXTRA = "projectDetailsActivity.projectModelExtra";

    @BindView(R.id.activity_project_details_viewpager)
    ViewPager mProjectImagesViewPager;

    @BindView(R.id.activity_project_details_indicator)
    TitlePageIndicator mPageIndicator;

    private PortfolioProject mSingleProject;

    private ProjectDetailsImagesPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        ButterKnife.bind(this);
        parseIntentExtras();
        initializeViewPager();
    }

    private void parseIntentExtras() {
        Bundle intentExtras = getIntent().getExtras();
        if (intentExtras != null) {
            if (intentExtras.containsKey(PROJECT_MODEL_EXTRA)){
                mSingleProject = intentExtras.getParcelable(PROJECT_MODEL_EXTRA);
            }
            else{
                //TODO Probably will end up on a finishWithError from BaseActivity
                finish();
            }
        }
    }

    private void initializeViewPager() {
        mViewPagerAdapter = new ProjectDetailsImagesPagerAdapter();
        mProjectImagesViewPager.setAdapter(mViewPagerAdapter);
        mViewPagerAdapter.setImages(mSingleProject.getImages());
        mPageIndicator.setViewPager(mProjectImagesViewPager, 0);
    }

}
