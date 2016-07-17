package ojeda.diego.myresume.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by diego on 16/7/16.
 */

public class PortfolioProject extends BaseModel implements Parcelable{

    private String name;
    private String description;
    private ArrayList<String> images;

    public PortfolioProject(){}

    protected PortfolioProject(Parcel in) {
        name = in.readString();
        description = in.readString();
        images = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeStringList(images);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PortfolioProject> CREATOR = new Creator<PortfolioProject>() {
        @Override
        public PortfolioProject createFromParcel(Parcel in) {
            return new PortfolioProject(in);
        }

        @Override
        public PortfolioProject[] newArray(int size) {
            return new PortfolioProject[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}
