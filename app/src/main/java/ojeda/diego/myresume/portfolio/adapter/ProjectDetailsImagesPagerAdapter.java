package ojeda.diego.myresume.portfolio.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ojeda.diego.myresume.MyResumeApplication;
import ojeda.diego.myresume.R;

/**
 * Created by diego on 24/9/16.
 */

public class ProjectDetailsImagesPagerAdapter extends PagerAdapter {

    private ArrayList<String> mImages;

    private LayoutInflater mLayoutInflater;

    @Override
    public int getCount() {
        return mImages != null ? mImages.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        if (mLayoutInflater == null) {
            mLayoutInflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        View itemView = mLayoutInflater.inflate(R.layout.project_details_pager_view, container, false);

        //TODO Apply ViewHolder pattern in order to improve efficiency
        final ImageView iv = (ImageView) itemView.findViewById(R.id.project_details_pager_view_iv);
        final ProgressBar progressBar = (ProgressBar) itemView.findViewById(R.id.project_details_pager_view_progress_bar);

        MyResumeApplication.sFirebaseApi.getDownloadUriForMediaFile(mImages.get(position), new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        Picasso.with(container.getContext()).load(uri).into(iv, new Callback() {
                            @Override
                            public void onSuccess() {
                                progressBar.setVisibility(View.GONE);
                                iv.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onError() {
                                //TODO Handle error
                            }
                        });

                    }
                },
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //TODO Handle error
                    }
                });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

    public ArrayList<String> getImages() {
        return mImages;
    }

    public void setImages(ArrayList<String> mImages) {
        this.mImages = mImages;
        notifyDataSetChanged();
    }
}
