package android.bignerdranch.checkinactivity;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;
import java.util.UUID;

public class CheckInDaily {
    private UUID mId;
    private String mTitle;
    private String mPlace;
    private String mDetails;
    private LatLng mLatLng;
    private Date mDate;
    private boolean mIsContacted;
    private String mContact;

    public CheckInDaily() {
        this(UUID.randomUUID());
    }

    public CheckInDaily(UUID id){
        mId = id;
        mDate = new Date();
    }

    public LatLng getLatLng(){return mLatLng;}

    public void setLatLng(LatLng latLng) {
        mLatLng = latLng;
    }

    public UUID getId(){
        return mId;
    }

    public String getTitle(){
        return mTitle;
    }

    public void setTitle(String title){
        mTitle = title;
    }

    public String getPlace(){return mPlace;}

    public void setPlace(String place){mPlace = place;}

    public String getDetails() {
        return mDetails;
    }

    public void setDetails(String details){mDetails = details;}

    public Date getDate(){
        return mDate;
    }

    public void setDate(Date date){
        mDate = date;
    }

    public boolean isContacted(){
        return mIsContacted;
    }

    public void setIsContacted(boolean contacted){
        mIsContacted = contacted;
    }

    public String getContact(){
        return mContact;
    }

    public void setContact(String contact){
        mContact = contact;
    }

    public String getPhotoFilename(){
        return "IMG_" + getId().toString() + ".jpg";
    }
}
