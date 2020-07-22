#include <jni.h>
#include <string>

extern "C" JNIEXPORT jbyteArray JNICALL
Java_com_cai_campus_common_utils_JNIUtils_getRsaPublicKey(
        JNIEnv *env,
        jclass instance) {
    char _rsaPublicKeyDictionary[] = {
            'M', 'F', 'w', 'w', 'D', 'Q', 'Y', 'J', 'K', 'o', 'Z', 'I',
            'h', 'v', 'c', 'N', 'A', 'Q', 'E', 'B', 'B', 'Q', 'A', 'D',
            'S', 'w', 'A', 'w', 'S', 'A', 'J', 'B', 'A', 'L', 'u', 'G',
            'u', '/', 'p', '7', 'I', 'O', 'X', 'H', 'X', 'v', 'b', 'w',
            '9', 'Y', 'c', 'U', 'U', '1', '9', 'Y', 'W', 'm', 'u', 'y',
            'i', 'B', 'q', 'c', 'Z', 'u', 'v', 'A', 'F', 'h', 'D', '7',
            'A', 'V', 'P', 'Z', 'V', 'p', '/', 'K', 'R', 'R', 'f', '5',
            '3', 't', 'H', 'O', 'A', 'r', 'r', '8', 'J', '8', 'd', 'Y',
            '/', 'd', 'x', 's', 'y', 'f', 'h', 'Y', 'b', 'U', 'B', '7',
            'F', 'i', 'E', 'l', 'H', '4', 'B', '7', 'Z', 'T', '8', 'C',
            'A', 'w', 'E', 'A', 'A', 'Q', '=', '='
    };

    int length = sizeof(_rsaPublicKeyDictionary);
    jbyteArray data = env->NewByteArray(length);
    env->SetByteArrayRegion(data, 0, length, (jbyte *) _rsaPublicKeyDictionary);
    return data;
}
