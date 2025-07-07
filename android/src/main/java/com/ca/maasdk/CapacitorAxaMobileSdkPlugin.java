package com.ca.maasdk;

import android.os.Bundle;
import android.util.Log;

import com.ca.integration.CaMDOCallback;
import com.ca.mdo.CALog;
import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.ca.android.app.CaMDOIntegration;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

@CapacitorPlugin(name = "CapacitorAxaMobileSdk")
public class CapacitorAxaMobileSdkPlugin extends Plugin {

    private String TAG = "CapacitorAxaMobileSdkPlugin";

    @PluginMethod()
    public void enableSDK(PluginCall call) {
        Log.i(TAG, "@enableSDK ");
        CaMDOIntegration.enableSDK();
        Log.i(TAG, "@enableSDK done ");
        call.resolve();
    }

    @PluginMethod()
    public void disableSDK(PluginCall call) {
        Log.i(TAG, "@disableSDK ");
        CaMDOIntegration.disableSDK();
        Log.i(TAG, "@disableSDK done ");
        call.resolve();
    }

    @PluginMethod()
    public void isSDKEnabled(PluginCall call) {
        Log.i(TAG, "@isSDKEnabled11 ");
        try {
            Boolean val = new Boolean(CaMDOIntegration.isSDKEnabled());
            Log.i(TAG, "@isSDKEnabled : " + val);
            call.resolve(new JSObject().put("value", val));
        } catch (Exception e) {
            call.reject("isSDKEnabled call failed ", e);
        }

    }

    @PluginMethod()
    public void getDeviceId(PluginCall call) {
        Log.i(TAG, "@getDeviceId ");
        String deviceId = CaMDOIntegration.getDeviceId();
        Log.i(TAG, "@getDeviceId : " + deviceId);
        call.resolve(new JSObject().put("value", deviceId));
    }

    @PluginMethod()
    public void getCustomerId(PluginCall call) {
        Log.i(TAG, "@getCustomerId ");
        String cid = CaMDOIntegration.getCustomerId();
        Log.i(TAG, "@getCustomerId : " + cid);
        call.resolve(new JSObject().put("value", cid));
    }

    @PluginMethod()
    public void setCustomerId(PluginCall call) {
        Log.i(TAG, "@setCustomerID :" + call);
        try {
            String customerId = call.getString("customerId");
            Log.i(TAG, "@setCustomerID  customerId: " + customerId);
            CaMDOIntegration.setCustomerId(customerId);
            Log.i(TAG, "@setCustomerID done ");
            call.resolve();
        } catch (Exception e) {
            Log.i(TAG, "@setCustomerID : Exception" + call);
            call.reject(e.getLocalizedMessage(), e);
        }

    }

    @PluginMethod()
    public void setSessionAttribute(PluginCall call) {
        Log.i(TAG, "@setSessionAttribute ");
        try {
            JSObject data = call.getData();
            String name = data.getString("name");
            String value = data.getString("value");
            Log.i(TAG, "@setSessionAttribute with value : (" + name + "," + value + ")");
            CaMDOIntegration.setSessionAttribute(name, value);
            Log.i(TAG, "@setSessionAttribute done setting location ");
            call.resolve();
        } catch (Exception e) {
            call.reject("invalid arguments ", e);
        }
    }

    @PluginMethod()
    public void enterPrivateZone(PluginCall call) {
        Log.i(TAG, "@enterPrivateZone ");
        CaMDOIntegration.enterPrivateZone();
        Log.i(TAG, "@enterPrivateZone : done");
        call.resolve();
    }

    @PluginMethod()
    public void exitPrivateZone(PluginCall call) {
        Log.i(TAG, "@exitPrivateZone ");
        CaMDOIntegration.exitPrivateZone();
        Log.i(TAG, "@exitPrivateZone : done");
        call.resolve();
    }

    @PluginMethod()
    public void isInPrivateZone(PluginCall call) {
        Log.i(TAG, "@isInPrivateZone ");
        Boolean val = new Boolean(CaMDOIntegration.isInPrivateZone());
        Log.i(TAG, "@isInPrivateZone : " + val);
        call.resolve(new JSObject().put("return", val));
    }

    @PluginMethod()
    public void getAPMHeader(PluginCall call) {
        Log.i(TAG, "@getAPMHeaders ");
        Map headers = CaMDOIntegration.getAPMHeaders();
        Log.i(TAG, "@getAPMHeaders done ");
        call.resolve(getJSObject(headers));
    }

    @PluginMethod()
    public void addToAPMHeader(PluginCall call) {
        Log.i(TAG, "@addToApmHeader ");
        String headerString = call.getString("data");
        CaMDOIntegration.addToApmHeader(headerString);
        Log.i(TAG, "@addToApmHeader done ");
        call.resolve();
    }

    @PluginMethod()
    public void setSSLPinningMode(PluginCall call) {
        Log.i(TAG, "@setSSLPinningMode ");
        try {
            String pinningModeType = call.getString("pinningMode");
            JSArray pinnedValues = call.getArray("pinnedValues");
            Log.i(TAG, "@setSSLPinningMode pinningModeType: "+pinningModeType+", pinnedValues: "+pinnedValues);
            String pinningMode = "none";
            switch(pinningModeType){
                
                case "CAMDOSSLPinningModePublicKey":
                    pinningMode = "pk";
                break;

                case "CAMDOSSLPinningModeCertificate":
                    pinningMode = "certificate";
                break;

                case "CAMDOSSLPinningModeFingerPrintSHA1Signature":
                    pinningMode = "sha1signature";
                break;

                case "CAMDOSSLPinningModePublicKeyHash":
                    pinningMode = "hash";
                break;

                default:
                case "CAMDOSSLPinningModeNone":
                    pinningMode = "none";
                break;

            }
            ArrayList<byte[]> pinnedValuesL = new ArrayList<byte[]>();
            if(pinnedValues!=null){
                Log.i(TAG, "@setSSLPinningMode pinnedValues count: "+pinnedValues.length());
                for (int i = 0; i < pinnedValues.length(); ++i) {
                    pinnedValuesL.add( (""+pinnedValues.get(i)).getBytes());
                   }
            }

            if(pinnedValuesL.size()>0){
                CaMDOIntegration.setSSLPinningMode(null, pinningMode,pinnedValuesL);
                call.resolve();
            }else{
                call.reject("Invalid SSL pinning data ");
            }

            
        } catch (Exception e) {
            call.reject("Error in SSL pinning  ", e);
        }
        
    }

    @PluginMethod()
    public void stopCurrentSession(PluginCall call) {
        Log.i(TAG, "@stopCurrentSession ");
        CaMDOIntegration.stopCurrentSession();
        Log.i(TAG, "@stopCurrentSession ");
        call.resolve();
    }

    @PluginMethod()
    public void startNewSession(PluginCall call) {
        Log.i(TAG, "@startNewSession ");
        CaMDOIntegration.startNewSession();
        Log.i(TAG, "@startNewSession ");
        call.resolve();
    }

    @PluginMethod()
    public void stopCurrentAndStartNewSession(PluginCall call) {
        Log.i(TAG, "@stopCurrentAndStartNewSession ");
        CaMDOIntegration.stopCurrentAndStartNewSession();
        Log.i(TAG, "@stopCurrentAndStartNewSession ");
        call.resolve();
    }

    @PluginMethod()
    public void startApplicationTransaction(PluginCall call) {
        Log.i(TAG, "@startApplicationTransaction ");
        try {
            JSObject data = call.getData();
            String transactionName = data.getString("transactionName");
            String serviceName = data.getString("serviceName");
            Log.i(TAG, "@startApplicationTransaction with value : (" + transactionName + "," + serviceName + ")");
            CaMDOCallback cb = new CaMDOCallback(null) {

                @Override
                public void onError(int errorCode, Exception exception) {
                    call.reject("Error starting transaction. Reason: " + errorCode, exception);
                }

                @Override
                public void onSuccess(Bundle data) {
                    Log.i(TAG, "@startApplicationTransaction done ");
                    call.resolve();

                }
            };
            CaMDOIntegration.startApplicationTransaction(transactionName, serviceName, cb);

        } catch (Exception e) {
            call.reject("@startApplicationTransaction invalid arguments " + e.getMessage());
        }
    }

    @PluginMethod()
    public void stopApplicationTransaction(PluginCall call) {
        Log.i(TAG, "@stopApplicationTransaction ");
        try {
            JSObject data = call.getData();
            String transactionName = call.getString("transactionName");
            String failure = call.getString("failure");
            Log.i(TAG, "@stopApplicationTransaction with value : (" + transactionName + "," + failure + ")");
            CaMDOCallback cb = new CaMDOCallback(null) {

                @Override
                public void onError(int errorCode, Exception exception) {
                    call.reject("Error Stoping transaction. Reason: " + errorCode, exception);
                }

                @Override
                public void onSuccess(Bundle data) {
                    Log.i(TAG, "@stopApplicationTransaction done ");
                    call.resolve();

                }
            };
            CaMDOIntegration.stopApplicationTransaction(transactionName, failure, cb);
        } catch (Exception e) {
            call.reject("@stopApplicationTransaction invalid arguments " + e.getMessage());
        }
    }

    @PluginMethod()
    public void setCustomerFeedback(PluginCall call) {
        Log.i(TAG, "@setUserFeedback ");
        String feedback = call.getString("feedback");
        Log.i(TAG, "@setUserFeedback with value : " + feedback);
        CaMDOIntegration.setUserFeedback(feedback);
        Log.i(TAG, "@setUserFeedback done ");
        call.resolve();
    }

    @PluginMethod()
    public void setCrashFeedback(PluginCall call) {
        Log.i(TAG, "@setCrashFeedback ");
        String feedback = call.getString("feedback");
        Log.i(TAG, "@setCrashFeedback with value : " + feedback);
        CaMDOIntegration.setCrashFeedback(feedback);
        Log.i(TAG, "@setCrashFeedback done ");
        call.resolve();
    }

    @PluginMethod()
    public void setCustomerLocation(PluginCall call) {
        Log.i(TAG, "@setCustomerLocation ");
        try {
            // JSObject data = call.getData();
            String postalCode = call.getString("postalCode");
            String countryCode = call.getString("countryCode");
            Log.i(TAG, "@setCustomerLocation with value : (" + postalCode + "," + countryCode + ")");
            CaMDOIntegration.setCustomerLocation(postalCode, countryCode);
            Log.i(TAG, "@setCustomerLocation done setting location ");
            call.resolve();
        } catch (Exception e) {
            call.reject("@setCustomerLocation invalid arguments " + e.getMessage());
        }
    }

    @PluginMethod()
    public void sendScreenShot(PluginCall call) {
        Log.i(TAG, "@sendScreenShot ");
        try {
            JSObject data = call.getData();
            String screenName = call.getString("name");
            String imageQuality = call.getString("quality");
            Log.i(TAG, "@sendScreenShot with value : (" + screenName + "," + imageQuality + ")");
            int screenshotQuality = CaMDOIntegration.CAMAA_SCREENSHOT_QUALITY_DEFAULT;
            switch (imageQuality) {

                case "CAMAA_SCREENSHOT_QUALITY_HIGH":
                    screenshotQuality = CaMDOIntegration.CAMAA_SCREENSHOT_QUALITY_HIGH;
                case "CAMAA_SCREENSHOT_QUALITY_MEDIUM":
                    screenshotQuality = CaMDOIntegration.CAMAA_SCREENSHOT_QUALITY_MEDIUM;
                case "CAMAA_SCREENSHOT_QUALITY_LOW":
                    screenshotQuality = CaMDOIntegration.CAMAA_SCREENSHOT_QUALITY_LOW;
                case "CAMAA_SCREENSHOT_QUALITY_DEFAULT":
                default:
                    screenshotQuality = CaMDOIntegration.CAMAA_SCREENSHOT_QUALITY_DEFAULT;
            }

            CaMDOCallback cb = new CaMDOCallback(null) {

                @Override
                public void onError(int errorCode, Exception exception) {
                    call.reject("Error sending ScreenShot. Reason: " + errorCode, exception);
                }

                @Override
                public void onSuccess(Bundle data) {
                    Log.i(TAG, "@sendScreenShot done ");
                    call.resolve();

                }
            };
            CaMDOIntegration.sendScreenShot(screenName, screenshotQuality, cb);

        } catch (Exception e) {
            call.reject("@sendScreenShot invalid arguments " + e.getMessage());
        }
    }

    @PluginMethod()
    public void viewLoaded(PluginCall call) {
        Log.i(TAG, "@viewLoaded ");
        try {
            JSObject data = call.getData();
            String viewName = call.getString("viewName");
            int loadTime = call.getInt("loadTime");
            Log.i(TAG, "@viewLoaded with value : (" + viewName + "," + loadTime + ")");
            CaMDOCallback cb = new CaMDOCallback(null) {

                @Override
                public void onError(int errorCode, Exception exception) {
                    call.reject("Error in viewLoaded. Reason: " + errorCode, exception);
                }

                @Override
                public void onSuccess(Bundle data) {
                    Log.i(TAG, "@viewLoaded done ");
                    call.resolve();

                }
            };
            CaMDOIntegration.viewLoaded(viewName, loadTime, cb);

        } catch (Exception e) {
            call.reject("@viewLoaded  Invalid arguments " + e.getMessage());

        }
    }

    @PluginMethod()
    public void ignoreView(PluginCall call) {
        Log.i(TAG, "@ignoreView ");
        String viewName = call.getString("viewName");
        CaMDOIntegration.ignoreView(viewName);
        Log.i(TAG, "@ignoreView done ");
        call.resolve();
    }

    @PluginMethod()
    public void ignoreViews(PluginCall call) {
        Log.i(TAG, "@ignoreViews ");
        call.unimplemented();
    }

    @PluginMethod()
    public void isScreenshotPolicyEnabled(PluginCall call) {
        Log.i(TAG, "@isScreenshotPolicyEnabled ");
        Boolean val = new Boolean(CaMDOIntegration.isScreenshotPolicyEnabled());
        Log.i(TAG, "@isScreenshotPolicyEnabled : " + val);
        call.resolve(new JSObject().put("value", val));
    }

    @PluginMethod()
    public void logNetworkEvent(PluginCall call) {
        Log.i(TAG, "@logNetworkEvent ");
        try {
            JSObject data = call.getData();
            String url = data.getString("url");
            int status = data.getInt("status");
            int responseTime = data.getInt("responseTime");
            int inBytes = data.getInt("inBytes");
            int outBytes = data.getInt("outBytes");
            Log.i(TAG, "@logNetworkEvent with value : (" + url + "," + status + "," + responseTime + "," + inBytes + ","
                    + outBytes + ")");
            CaMDOIntegration.logNetworkEvent(url, status, responseTime, inBytes, outBytes);
            Log.i(TAG, "@logNetworkEvent done ");
            call.resolve();
        } catch (JSONException e) {
            call.reject("@logNetworkEvent Invalid arguments " + e.getMessage());
        }
    }

    @PluginMethod()
    public void logTextMetric(PluginCall call) {
        Log.i(TAG, "@logTextMetric ");
        try {
            JSObject data = call.getData();
            String metricName = call.getString("textMetricName");
            String metricValue = call.getString("value");
            JSObject attributes = call.getObject("attributes");
            Map attribMap = getMap(attributes);
            Log.i(TAG, "@logTextMetric with value : (" + metricName + "," + metricValue + ")");
            CaMDOCallback cb = new CaMDOCallback(null) {

                @Override
                public void onError(int errorCode, Exception exception) {
                    call.reject("Error in logTextMetric. Reason: " + errorCode, exception);
                }

                @Override
                public void onSuccess(Bundle data) {
                    Log.i(TAG, "@logTextMetric done ");
                    call.resolve();

                }
            };
            CaMDOIntegration.logTextMetric(metricName, metricValue, attribMap, cb);
        } catch (Exception e) {
            call.reject("Generic Error in logTextMetric.", e);
        }


    }

    @PluginMethod()
    public void logNumericMetric(PluginCall call) {
        Log.i(TAG, "@logNumericMetric ");
        try {

            JSObject data = call.getData();
            String metricName = call.getString("numericMetricName");
            Double metricValue = call.getDouble("value");
            JSObject attributes = call.getObject("attributes");
            Map attribMap = getMap(attributes);
            Log.i(TAG, "@logNumericMetric with value : (" + metricName + "," + metricValue + ")");
            CaMDOCallback cb = new CaMDOCallback(null) {

                @Override
                public void onError(int errorCode, Exception exception) {
                    call.reject("Error in logNumericMetric. Reason: " + errorCode, exception);
                }

                @Override
                public void onSuccess(Bundle data) {
                    Log.i(TAG, "@logNumericMetric done ");
                    call.resolve();

                }
            };
            CaMDOIntegration.logNumericMetric(metricName, metricValue, attribMap, cb);
        } catch (Exception e) {
            call.reject("Generic Error in logNumericMetric.", e);
        }

    }

    @PluginMethod()
    public void uploadEvents(PluginCall call) {
        Log.i(TAG, "@uploadEvents ");
        try {
            CaMDOCallback cb = new CaMDOCallback(null) {

                @Override
                public void onError(int errorCode, Exception exception) {
                    call.reject("Error in uploadEvents. Reason: " + errorCode, exception);
                }

                @Override
                public void onSuccess(Bundle data) {
                    Log.i(TAG, "@uploadEvents ");
                    call.resolve();

                }
            };
            CaMDOIntegration.uploadEvents(cb);
        } catch (Exception e) {
            call.reject("Generic Error in uploadEvents.", e);
        }

    }

    @PluginMethod()
    public void induceNativeCrash(PluginCall call) {
        Log.i(TAG, "@induceNativeCrash ");
        String crashType = call.getString("crashType");
        Log.i(TAG, "@induceNativeCrash with crashType" + crashType);
        switch(crashType){
            
            case "AIOBE":{
                String[] temp = new String[10];
                temp[12].length();
            }
            break;

            case "Error":{
                throw new Error("Forced Error");
            }

            case "NPE":
            default:
            {
                String temp = null;
                temp.length();
            };

        }
        call.resolve();
    }

    @PluginMethod()
    public void addListener(PluginCall call) {
        Log.i(TAG, "@addListener not implemented for Android ");
        call.unimplemented();
    }

    @PluginMethod()
    public void logUIEvent(PluginCall call) {
        Log.i(TAG, "@ logUIEvent ");
        try {
            String eventType = call.getString("eventType");
            String eventName = call.getString("value");
            Log.i(TAG, "@ logUIEvent eventType: "+eventType+", eventName: "+eventName);
            CaMDOIntegration.logUIEvent(eventType,eventName);
            call.resolve();    
        } catch (Exception e) {
            call.reject("Unable to log UI Event ", e);
        }
        
    }

    @PluginMethod
    public void logHandledException(PluginCall call) {
        Log.i(TAG, "@ logHandledException");
        try {
            String name = call.getString("name");
            String message = call.getString("message");
            String stacktrace = call.getString("stacktrace");
            // Default values
            String methodName = "UnknownMethod";
            String className = "UnknownClass";
            int line = -1;
            // Return early if stacktrace is null or empty
            if (stacktrace == null || stacktrace.trim().isEmpty()) {
                call.reject("Missing or empty 'stacktrace'");
                return;
            }

            Log.i(TAG, "@ logHandledException stacktrace: "+stacktrace);

            if (stacktrace != null && !stacktrace.trim().isEmpty()) {
                String[] stackLines = stacktrace.split("\n");

                for (String lineEntry : stackLines) {
                    // Match format like: at someFunc (file.js:10:5)
                    Pattern pattern = Pattern.compile("at\\s+(.*?)\\s+\\((.*?):(\\d+):(\\d+)\\)");
                    Matcher matcher = pattern.matcher(lineEntry.trim());

                    if (matcher.find()) {
                        methodName = matcher.group(1);    // e.g., someFunc
                        className = matcher.group(2);          // e.g., file.js
                        line = Integer.parseInt(matcher.group(3));  // line number
                        break; // Only take the top stack frame
                    }
                }
            }

            String fullMessage = (name != null ? name : "Error") + ": " + (message != null ? message : "Unknown message") + "\n" + stacktrace;

            JSObject errorInfo = new JSObject();
            errorInfo.put("Origin", "javascript");  
            errorInfo.put("Type",name);
            errorInfo.put("Message",message);
            errorInfo.put("Stacktrace",stacktrace);
            errorInfo.put("Class",className);
            errorInfo.put("Method", methodName);
            errorInfo.put("Line", line);
            String jsonErrorInfo = errorInfo.toString(); // 2 = indentation level

            Throwable cause = new Throwable(jsonErrorInfo);
            Exception wrapped = new Exception(fullMessage, cause);
            CaMDOIntegration.logHandledException(wrapped);

            call.resolve();
        } catch (Exception ex) {
            call.reject("Failed to log handled exception", ex);
        }
    }

    @PluginMethod()
    public void setNSURLSessionDelegate(PluginCall call) {
        Log.i(TAG, "@  setNSURLSessionDelegate not implemented for Android ");
        call.unimplemented();
    }

    @PluginMethod()
    public void setLocation(PluginCall call) {
        Log.i(TAG, "@  setLocation not implemented for Android ");
        call.unimplemented();
    }

    @PluginMethod()
    public void enableScreenShots(PluginCall call) {
        Log.i(TAG, "@   enableScreenShots not implemented for Android ");
        call.unimplemented();
    }

    private Map getMap(JSObject jsonObj) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            Iterator iter = jsonObj.keys();
            while (iter.hasNext()) {
                String key = (String) iter.next();
                String value = jsonObj.getString(key);
                map.put(key, value);
            }
        } catch (Exception e) {
            CALog.w("Error getting map from JSObject " + e);
        }
        return map;

    }

    private JSObject getJSObject(Map<String, String> data) {
        JSObject returnObj = new JSObject();
        try {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                returnObj.put(entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
            CALog.w("Error getting map from JSObject " + e);
        }
        return returnObj;

    }

}
