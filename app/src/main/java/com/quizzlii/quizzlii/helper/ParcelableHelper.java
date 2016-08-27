package com.quizzlii.quizzlii.helper;

import android.os.Parcel;

public class ParcelableHelper {

    public static boolean readBoolean(Parcel in) {
        return 0 == in.readInt();
    }

    public static void writeBoolean(Parcel dest, boolean toWrite) {
        dest.writeInt(toWrite ? 0 : 1);
    }

}
