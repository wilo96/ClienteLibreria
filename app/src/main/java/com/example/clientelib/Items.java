package com.example.clientelib;

public class Items {
    private int mImageResources;
    private String mText1;
    private String mText2;

    public Items(int imageResource, String text1, String text2)
    {
        mImageResources=imageResource;
        mText1=text1;
        mText2=text2;
    }
    public int getmImageResources() {
        return mImageResources;
    }

        public String getmText1 () {
            return mText1;
        }

        public String getmText2 ()
        {
            return mText2;
        }
}
