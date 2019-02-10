package com.example.thony.pharma;

import android.os.Parcel;
import android.os.Parcelable;

public class GestCart implements Parcelable
{
    private int pharma_id;
    private String pharmaname;

    public GestCart(int pharma_id, String pharmaname) {
        this.pharma_id = pharma_id;
        this.pharmaname = pharmaname;
    }

    public GestCart()
    {

    }

    protected GestCart(Parcel in) {
        pharma_id = in.readInt();
        pharmaname = in.readString();
    }

    public static final Creator<GestCart> CREATOR = new Creator<GestCart>() {
        @Override
        public GestCart createFromParcel(Parcel in) {
            return new GestCart(in);
        }

        @Override
        public GestCart[] newArray(int size) {
            return new GestCart[size];
        }
    };

    public int getPharma_id() {
        return pharma_id;
    }

    public String getPharmaname() {
        return pharmaname;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
dest.writeInt(pharma_id);
dest.writeString(pharmaname);
    }

    @Override
    public String toString() {
        return "GestCart{" +
                "pharma_id=" + pharma_id +
                ", pharmaname='" + pharmaname + '\'' +
                '}';
    }
}
