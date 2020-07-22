package com.cai.campus.common.utils;

public class JNIUtils {
    static {
        System.loadLibrary("native-lib");
    }

    public static native byte[] getRsaPublicKey();
}
