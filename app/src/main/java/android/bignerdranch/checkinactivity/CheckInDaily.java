package android.bignerdranch.checkinactivity;

import java.util.Date;
import java.util.UUID;

public class CheckInDaily {
    private UUID mId;
    private String mTitle;
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

    public UUID getId(){
        return mId;
    }

    public String getTitle(){
        return mTitle;
    }

    public void setTitle(String title){
        mTitle = title;
    }

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
