package ojeda.diego.myresume.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by diego on 16/7/16.
 */

public class ProjectsListModel extends BaseModel implements Parcelable{

    ArrayList<PortfolioProject> projects;

    public ProjectsListModel(){}

    protected ProjectsListModel(Parcel in) {
        projects = in.createTypedArrayList(PortfolioProject.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(projects);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProjectsListModel> CREATOR = new Creator<ProjectsListModel>() {
        @Override
        public ProjectsListModel createFromParcel(Parcel in) {
            return new ProjectsListModel(in);
        }

        @Override
        public ProjectsListModel[] newArray(int size) {
            return new ProjectsListModel[size];
        }
    };

    public ArrayList<PortfolioProject> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<PortfolioProject> projects) {
        this.projects = projects;
    }
}
