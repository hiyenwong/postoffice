package io.hiyenwong.postoffice.common;

import okhttp3.*;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.Objects;

/**
 * http connector
 *
 * @author Hi Yen Wong
 * @date 5/8/2021 5:50 PM
 */
public class HttpConnector {

    private static final HttpConnector HTTP_COMMON = new HttpConnector();

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    /**
     * POST METHOD
     *
     * @param url
     * @param json
     * @return
     */
    public String post(String url, String json) {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        if (isHttps(url)) {

            try (Response response = this.getUnsafeHttpClient().newCall(request).execute()) {
                return Objects.requireNonNull(response.body()).string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.setHttpClient(new OkHttpClient());
            try (Response response = this.getHttpClient().newCall(request).execute()) {
                return Objects.requireNonNull(response.body()).string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return url;
    }

    /**
     * instance func
     *
     * @return
     */
    public static HttpConnector getInstance() {
        return HTTP_COMMON;
    }

    /**
     * get Http client
     *
     * @return
     */
    public OkHttpClient getHttpClient() {
        return new OkHttpClient();
    }

    /**
     * set http client
     *
     * @param httpClient
     */
    private void setHttpClient(OkHttpClient httpClient) {
    }

    /**
     * unsafe http client
     *
     * @return
     */
    private OkHttpClient getUnsafeHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
                                throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
                                throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 检查是否是https
     *
     * @param url
     * @return
     */
    private boolean isHttps(String url) {
        return url.charAt(4) == 's';
    }
}
