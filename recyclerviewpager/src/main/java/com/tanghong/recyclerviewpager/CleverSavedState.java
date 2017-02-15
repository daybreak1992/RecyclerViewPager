/*
 * Copyright 2015 Andy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tanghong.recyclerviewpager;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/**
 * 用于保存CleverRecyclerView的某些状态
 *
 * @author tanhong
 */
public class CleverSavedState extends View.BaseSavedState {
    public Parcelable mLayoutState;
    private int mLastScrollPosition;

    public CleverSavedState(Parcel in) {
        super(in);
        this.mLayoutState = in.readParcelable(CleverLinearLayoutManager.class.getClassLoader());
        this.mLastScrollPosition = in.readInt();
    }

    public CleverSavedState(Parcelable superState, int mLastScrollPosition) {
        super(superState);
        this.mLastScrollPosition = mLastScrollPosition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void copyFrom(CleverSavedState other) {
        mLayoutState = other.mLayoutState;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mLayoutState, 0);
        dest.writeInt(this.mLastScrollPosition);
    }

    public static final Creator<CleverSavedState> CREATOR = new Creator<CleverSavedState>() {
        @Override
        public CleverSavedState createFromParcel(Parcel source) {
            return new CleverSavedState(source);
        }

        @Override
        public CleverSavedState[] newArray(int size) {
            return new CleverSavedState[size];
        }
    };

    public int getLastScrollPosition() {
        return mLastScrollPosition;
    }
}
