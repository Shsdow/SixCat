package com.six.cat.sixcat;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by liguoying on 18-1-13.
 * retrofit 忽略证书
 */

public class SSLSocketClient {
    //获取这个SSLSocketFactory
    public static SSLSocketFactory getSSLSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, getTrustManager(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取TrustManager
    private static TrustManager[] getTrustManager() {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
        return trustAllCerts;
    }

    //获取HostnameVerifier
    public static HostnameVerifier getHostnameVerifier() {
        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        };
        return hostnameVerifier;
    }
}
//Error:FAILURE: Build failed with an exception.
//
//        * What went wrong:
//        Could not resolve all files for configuration ':app:debugAnnotationProcessorClasspath'.
//        > Could not resolve com.google.guava:guava:19.0.
//        Required by:
//        project :app > com.jakewharton:butterknife-compiler:8.8.1 > com.google.auto:auto-common:0.8
//        > Could not resolve com.google.guava:guava:19.0.
//        > Could not get resource 'https://dl.google.com/dl/android/maven2/com/google/guava/guava/19.0/guava-19.0.pom'.
//        > Could not GET 'https://dl.google.com/dl/android/maven2/com/google/guava/guava/19.0/guava-19.0.pom'.
//        > Read timed out
//
//        * Try:
//        Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.
//
//        * Get more help at https://help.gradle.org
//
//        BUILD FAILED in 32s
