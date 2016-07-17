package ojeda.diego.myresume.portfolio.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import ojeda.diego.myresume.MyResumeApplication;
import ojeda.diego.myresume.R;
import ojeda.diego.myresume.network.model.PortfolioProject;
import ojeda.diego.myresume.portfolio.ProjectDetailsActivity;

/**
 * Created by diego on 16/7/16.
 */

public class PortfolioRecyclerViewAdapter extends RecyclerView.Adapter<PortfolioRecyclerViewAdapter.PortfolioCardViewHolder> {

    private List<PortfolioProject> mProjectsList;

    public PortfolioRecyclerViewAdapter(){};

    @Override
    public PortfolioCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.portfolio_project_card_item, parent, false);
        return new PortfolioCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PortfolioCardViewHolder holder, int position) {
        final Context context = holder.itemView.getContext();
        final PortfolioProject singleProject = mProjectsList.get(position);
        holder.mProjectTitle.setText(singleProject.getName());
        MyResumeApplication.sFirebaseApi.getDownloadUriForMediaFile(singleProject.getThumbnailImage(), new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(context).load(uri).into(holder.mProjectMainImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.mProgressBar.setVisibility(View.GONE);
                        holder.mProjectMainImage.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError() {
                        //TODO Handle error
                    }
                });
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //TODO Handle failure
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ProjectDetailsActivity.class);
                i.putExtra(ProjectDetailsActivity.PROJECT_MODEL_EXTRA, singleProject);
                context.startActivity(i);
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
        ProgressBar mProgressBar;
        TextView mProjectTitle;

        public PortfolioCardViewHolder(View itemView) {
            super(itemView);
            mProjectMainImage = (ImageView) itemView.findViewById(R.id.portfolio_project_card_item_iv);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.portfolio_project_card_item_progress_bar);
            mProjectTitle = (TextView) itemView.findViewById(R.id.portfolio_project_card_item_title_tv);
        }
    }
}
