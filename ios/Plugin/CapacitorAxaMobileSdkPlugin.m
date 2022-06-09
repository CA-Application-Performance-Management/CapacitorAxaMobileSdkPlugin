#import <Foundation/Foundation.h>
#import <Capacitor/Capacitor.h>

// Define the plugin using the CAP_PLUGIN Macro, and
// each method the plugin supports using the CAP_PLUGIN_METHOD macro.
CAP_PLUGIN(CapacitorAxaMobileSdkPlugin, "CapacitorAxaMobileSdk",
           CAP_PLUGIN_METHOD(enableSDK, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(disableSDK, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(isSDKEnabled, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(getDeviceId, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(getCustomerId, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(setCustomerId, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(setSessionAttribute, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(enterPrivateZone, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(exitPrivateZone, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(isInPrivateZone, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(getAPMHeader, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(addToAPMHeader, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(setSSLPinningMode, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(stopCurrentSession, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(startNewSession, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(stopCurrentAndStartNewSession, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(startApplicationTransaction, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(stopApplicationTransaction, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(setCustomerFeedback, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(setCustomerLocation, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(sendScreenShot, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(enableScreenShots, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(viewLoaded, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(ignoreView, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(ignoreViews, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(isScreenshotPolicyEnabled, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(logNetworkEvent, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(logTextMetric, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(logNumericMetric, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(uploadEvents, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(setLocation, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(logUIEvent, CAPPluginReturnPromise);
)
