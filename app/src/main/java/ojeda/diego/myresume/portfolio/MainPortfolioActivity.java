package ojeda.diego.myresume.portfolio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ojeda.diego.myresume.MyResumeApplication;
import ojeda.diego.myresume.R;
import ojeda.diego.myresume.network.FirebaseApi;
import ojeda.diego.myresume.network.model.ProjectsListModel;
import ojeda.diego.myresume.portfolio.adapter.PortfolioRecyclerViewAdapter;

public class MainPortfolioActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_portfolio_projects_rv)
    RecyclerView mProjectsRV;

    @BindView(R.id.activity_main_portfolio_projects_progress_bar)
    ProgressBar mProgressBar;

    private PortfolioRecyclerViewAdapter mProjectsAdapter;
    private ProjectsListModel mProjectsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_portfolio);
        ButterKnife.bind(this);
        fetchProjectsList();
    }

    private void fetchProjectsList() {
        MyResumeApplication.sFirebaseApi.getProjects(new FirebaseApi.FirebaseListener<ProjectsListModel>() {
            @Override
            public void onSuccess(ProjectsListModel response) {
                mProjectsList = response;
                configureProjectsRV();
                mProgressBar.setVisibility(View.GONE);
                mProjectsRV.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError() {
                //TODO Handle error.
            }
        });
    }

    private void configureProjectsRV() {
        mProjectsAdapter = new PortfolioRecyclerViewAdapter();
        mProjectsRV.setAdapter(mProjectsAdapter);
        mProjectsAdapter.setProjectsList(mProjectsList.getProjects());
        mProjectsAdapter.notifyDataSetChanged();
        configureStaggeredLayoutManager();
    }

    private void configureStaggeredLayoutManager() {
        StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) mProjectsRV.getLayoutManager();
        layoutManager.setSpanCount(2);
        layoutManager.setOrientation(StaggeredGridLayoutManager.VERTICAL);
    }
}
