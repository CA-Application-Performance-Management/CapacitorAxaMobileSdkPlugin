package com.ca.maasdk;

import android.os.Bundle;
import android.util.Log;

import com.ca.integration.CaMDOCallback;
import com.ca.mdo.CALog;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.ca.android.app.CaMDOIntegration;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
    public void getAPMHeaders(PluginCall call) {
        Log.i(TAG, "@getAPMHeaders ");
        Map headers = CaMDOIntegration.getAPMHeaders();
        Log.i(TAG, "@getAPMHeaders done ");
        call.resolve(getJSObject(headers));
    }

    @PluginMethod()
    public void addToApmHeader(PluginCall call) {
        Log.i(TAG, "@addToApmHeader ");
        String headerString = call.getString("value");
        CaMDOIntegration.addToApmHeader(headerString);
        Log.i(TAG, "@addToApmHeader done ");
        call.resolve();
    }

    /**
     * Use this API to set the ssl pinning mode and array of pinned values.
     * 
     * @param pinningMode
     * @param pinnedValues
     */

    @PluginMethod()
    public void setSSLPinningMode(PluginCall call) {
        Log.i(TAG, "@setSSLPinningMode ");
        call.unimplemented();
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
                    call.error("Error starting transaction. Reason: " + errorCode, exception);
                }

                @Override
                public void onSuccess(Bundle data) {
                    Log.i(TAG, "@startApplicationTransaction done ");
                    call.resolve();

                }
            };
            CaMDOIntegration.startApplicationTransaction(transactionName, serviceName, cb);

        } catch (Exception e) {
            call.error("@startApplicationTransaction invalid arguments " + e.getMessage());
        }
    }

    @PluginMethod()
    public void stopApplicationTransaction(PluginCall call) {
        Log.i(TAG, "@stopApplicationTransaction ");
        try {
            JSObject data = call.getData();
            String transactionName = data.getString("transactionName");
            String failure = data.getString("failure");
            Log.i(TAG, "@stopApplicationTransaction with value : (" + transactionName + "," + failure + ")");
            CaMDOCallback cb = new CaMDOCallback(null) {

                @Override
                public void onError(int errorCode, Exception exception) {
                    call.error("Error Stoping transaction. Reason: " + errorCode, exception);
                }

                @Override
                public void onSuccess(Bundle data) {
                    Log.i(TAG, "@stopApplicationTransaction done ");
                    call.resolve();

                }
            };
            CaMDOIntegration.stopApplicationTransaction(transactionName, failure, cb);
        } catch (Exception e) {
            call.error("@stopApplicationTransaction invalid arguments " + e.getMessage());
        }
    }

    @PluginMethod()
    public void setCustomerFeedback(PluginCall call) {
        Log.i(TAG, "@setUserFeedback ");
        String feedback = call.getString("value");
        Log.i(TAG, "@setUserFeedback with value : " + feedback);
        CaMDOIntegration.setUserFeedback(feedback);
        Log.i(TAG, "@setUserFeedback done ");
        call.resolve();
    }

    @PluginMethod()
    public void setCrashFeedback(PluginCall call) {
        Log.i(TAG, "@setCrashFeedback ");
        String feedback = call.getString("value");
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
            call.error("@setCustomerLocation invalid arguments " + e.getMessage());
        }
    }

    @PluginMethod()
    public void sendScreenShot(PluginCall call) {
        Log.i(TAG, "@sendScreenShot ");
        try {
            JSObject data = call.getData();
            String screenName = data.getString("screenName");
            int imageQuality = data.getInt("imageQuality");
            Log.i(TAG, "@sendScreenShot with value : (" + screenName + "," + imageQuality + ")");
            CaMDOCallback cb = new CaMDOCallback(null) {

                @Override
                public void onError(int errorCode, Exception exception) {
                    call.error("Error sending ScreenShot. Reason: " + errorCode, exception);
                }

                @Override
                public void onSuccess(Bundle data) {
                    Log.i(TAG, "@sendScreenShot done ");
                    call.resolve();

                }
            };
            CaMDOIntegration.sendScreenShot(screenName, imageQuality, cb);

        } catch (Exception e) {
            call.error("@sendScreenShot invalid arguments " + e.getMessage());
        }
    }

    @PluginMethod()
    public void viewLoaded(PluginCall call) {
        Log.i(TAG, "@viewLoaded ");
        try {
            JSObject data = call.getData();
            String viewName = data.getString("viewName");
            int loadTime = data.getInt("loadTime");
            Log.i(TAG, "@viewLoaded with value : (" + viewName + "," + loadTime + ")");
            CaMDOCallback cb = new CaMDOCallback(null) {

                @Override
                public void onError(int errorCode, Exception exception) {
                    call.error("Error in viewLoaded. Reason: " + errorCode, exception);
                }

                @Override
                public void onSuccess(Bundle data) {
                    Log.i(TAG, "@viewLoaded done ");
                    call.resolve();

                }
            };
            CaMDOIntegration.viewLoaded(viewName, loadTime, cb);

        } catch (JSONException e) {
            call.error("@viewLoaded  Invalid arguments " + e.getMessage());
        }
    }

    @PluginMethod()
    public void ignoreView(PluginCall call) {
        Log.i(TAG, "@ignoreView ");
        String viewName = call.getString("value");
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
            call.error("@logNetworkEvent Invalid arguments " + e.getMessage());
        }
    }

    @PluginMethod()
    public void logTextMetric(PluginCall call) {
        Log.i(TAG, "@logTextMetric ");
        JSObject data = call.getData();
        String metricName = data.getString("metricName");
        String metricValue = data.getString("metricValue");
        JSObject attributes = data.getJSObject("attributes");
        Map attribMap = getMap(attributes);
        Log.i(TAG, "@logTextMetric with value : (" + metricName + "," + metricValue + ")");
        CaMDOCallback cb = new CaMDOCallback(null) {

            @Override
            public void onError(int errorCode, Exception exception) {
                call.error("Error in logTextMetric. Reason: " + errorCode, exception);
            }

            @Override
            public void onSuccess(Bundle data) {
                Log.i(TAG, "@logTextMetric done ");
                call.resolve();

            }
        };
        CaMDOIntegration.logTextMetric(metricName, metricValue, attribMap, cb);

    }

    @PluginMethod()
    public void logNumericMetric(PluginCall call) {
        Log.i(TAG, "@logNumericMetric ");
        JSObject data = call.getData();
        String metricName = data.getString("metricName");
        String metricValue = data.getString("metricValue");
        JSObject attributes = data.getJSObject("attributes");
        Map attribMap = getMap(attributes);
        Log.i(TAG, "@logNumericMetric with value : (" + metricName + "," + metricValue + ")");
        CaMDOCallback cb = new CaMDOCallback(null) {

            @Override
            public void onError(int errorCode, Exception exception) {
                call.error("Error in logNumericMetric. Reason: " + errorCode, exception);
            }

            @Override
            public void onSuccess(Bundle data) {
                Log.i(TAG, "@logNumericMetric done ");
                call.resolve();

            }
        };
        CaMDOIntegration.logNumericMetric(metricName, Double.parseDouble(metricValue), attribMap, cb);

    }

    @PluginMethod()
    public void uploadEvents(PluginCall call) {
        Log.i(TAG, "@uploadEvents ");
        CaMDOCallback cb = new CaMDOCallback(null) {

            @Override
            public void onError(int errorCode, Exception exception) {
                call.error("Error in uploadEvents. Reason: " + errorCode, exception);
            }

            @Override
            public void onSuccess(Bundle data) {
                Log.i(TAG, "@uploadEvents ");
                call.resolve();

            }
        };
        CaMDOIntegration.uploadEvents(cb);
    }

    @PluginMethod()
    public void addListener(PluginCall call) {
        Log.i(TAG, "@addListener not implemented for Android ");
        call.unimplemented();
    }

    @PluginMethod()
    public void logUIEvent(PluginCall call) {
        Log.i(TAG, "@ logUIEvent not implemented for Android ");
        call.unimplemented();
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
