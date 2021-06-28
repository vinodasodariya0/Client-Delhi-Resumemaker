package nithra.resume.maker.cv.builder.app;

import android.util.Log;
import com.bumptech.glide.load.Key;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import org.json.JSONObject;

public class HttpHandler {
    private static final String TAG = "HttpHandler";

    public String makeServiceCall(String str, JSONObject jSONObject) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            bufferedWriter.write(getPostDataString(jSONObject));
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuffer stringBuffer = new StringBuffer();
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuffer.append(readLine);
                }
                bufferedReader.close();
                stringBuffer.toString();
                return stringBuffer.toString();
            }
            return "false : " + responseCode;
        } catch (MalformedURLException e) {
            String str2 = TAG;
            Log.e(str2, "MalformedURLException: " + e.getMessage());
            return null;
        } catch (ProtocolException e2) {
            String str3 = TAG;
            Log.e(str3, "ProtocolException: " + e2.getMessage());
            return null;
        } catch (IOException e3) {
            String str4 = TAG;
            Log.e(str4, "IOException: " + e3.getMessage());
            return null;
        } catch (Exception e4) {
            String str5 = TAG;
            Log.e(str5, "Exception: " + e4.getMessage());
            return null;
        }
    }

    public String makeServiceCall(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            return convertStreamToString(new BufferedInputStream(httpURLConnection.getInputStream()));
        } catch (MalformedURLException e) {
            String str2 = TAG;
            Log.e(str2, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e2) {
            String str3 = TAG;
            Log.e(str3, "ProtocolException: " + e2.getMessage());
        } catch (IOException e3) {
            String str4 = TAG;
            Log.e(str4, "IOException: " + e3.getMessage());
        } catch (Exception e4) {
            String str5 = TAG;
            Log.e(str5, "Exception: " + e4.getMessage());
        }
        return null;
    }

    public String getPostDataString(JSONObject jSONObject) throws Exception {
        StringBuilder sb = new StringBuilder();
        Iterator<String> keys = jSONObject.keys();
        boolean z = true;
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (z) {
                z = false;
            } else {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(next, Key.STRING_CHARSET_NAME));
            sb.append("=");
            sb.append(URLEncoder.encode(obj.toString(), Key.STRING_CHARSET_NAME));
        }
        return sb.toString();
    }

    private String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    sb.append('\n');
                } else {
                    break;
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                throw th;
            }
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
