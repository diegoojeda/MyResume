package ojeda.diego.myresume.portfolio.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import ojeda.diego.myresume.MyResumeApplication;
import ojeda.diego.myresume.R;
import ojeda.diego.myresume.network.model.PortfolioProject;

/**
 * Created by diego on 16/7/16.
 */

public class PortfolioRecyclerViewAdapter extends RecyclerView.Adapter<PortfolioRecyclerViewAdapter.PortfolioCardViewHolder> {

    private List<PortfolioProject> mProjectsList;

    public PortfolioRecyclerViewAdapter(){};

    @Override
    public PortfolioCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PortfolioCardViewHolder holder, int position) {
        PortfolioProject singleProject = mProjectsList.get(position);
        holder.mProjectTitle.setText(singleProject.getName());
        MyResumeApplication.sFirebaseApi.getDownloadUriForMediaFile(singleProject.getImages().get(0), new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mProjectsList != null ? mProjectsList.size() : 0;
    }

    public List<PortfolioProject> getProjectsList() {
        return mProjectsList;
    }

    public void setProjectsList(List<PortfolioProject> projectsList) {
        this.mProjectsList = projectsList;
    }

    class PortfolioCardViewHolder extends RecyclerView.ViewHolder{

        ImageView mProjectMainImage;
        TextView mProjectTitle;

        public PortfolioCardViewHolder(View itemView) {
            super(itemView);
            mProjectMainImage = (ImageView) itemView.findViewById(R.id.portfolio_project_card_item_iv);
            mProjectTitle = (TextView) itemView.findViewById(R.id.portfolio_project_card_item_title_tv);
        }
    }
}
