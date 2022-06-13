# Capacitor Axa Mobile Sdk

**Capacitor Axa Mobile Sdk** is a modern, well-supported, and cross-platform sdk for App Experience Analytics that provides deep insights into the performance, user experience, crash, and log analytics of apps.

## Platforms Supported

-  iOS
-  Android

## Getting started

[DX App Experience Analytics](https://www.broadcom.com/info/aiops/app-analytics)

Check out our [documentation](https://techdocs.broadcom.com/content/broadcom/techdocs/us/en/ca-enterprise-software/it-operations-management/app-experience-analytics-saas/SaaS/reference/data-collected-by-ca-app-experience-analytics-sdk.html) for more information about the features that the App Experience Analytics SDK collects from your app.

## Install

```bash
npm install capacitor-axa-mobile-sdk-plugin
npx cap sync
```

## Initialising the SDK in your Source code

<details>
<summary> Code Changes </summary>
    
<blockquote>
<details>
<summary> iOS </summary>

<blockquote>
<details>
<summary> Swift </summary>

1. Add a header file with the file name format as `<app_name>-Bridging-header.h`.
        
2. Add the import header `#import "CAMDOReporter.h"` to your `<app_name>-Bridging-header.h` file. 
        
3. Add the `<app_name>-Bridging-header.h` file to Swift Compiler - Code Generation section in the Build Settings.
        `<name of the project>/<app_name>-Bridging-header.h`
        
4. Initialize the CAMobileAppAnalytics sdk in `didFinishLaunchingWithOptions` method
    ```sh
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        //Initialize CA App Experience Analytics SDK
        CAMDOReporter.initializeSDK(options: SDKOptions.SDKLogLevelVerbose) { (completed, error) in

        }
        return true
    }
    ```
5. Drag & Drop the downloaded `xxx_camdo.plist` file into the Supporting files
</details>
</blockquote>

<blockquote>    
<details>
<summary> Objective C </summary>

1. Add the import header `#import "CAMDOReporter.h"` to your AppDelegate.m file
2. Initialize the CAMobileAppAnalytics sdk in `didFinishLaunchingWithOptions:` method 
    ```sh
    - (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
    {
        [CAMDOReporter initializeSDKWithOptions:SDKLogLevelVerbose  completionHandler:nil];
        return YES;
    }
    ```
3. Drag & Drop the downloaded `xxx_camdo.plist` file into the Supporting files
</details>
</blockquote>

</details>
</blockquote>
</details>


## Updation

```bash
npm upgrade capacitor-axa-mobile-sdk-plugin
npx cap sync
```

## Usage

```typescript
import { CapacitorAxaMobileSdk } from 'capacitor-axa-mobile-sdk-plugin';
```

To add listeners
```typescript
import { CAMDOSDKImageQualityType, CapacitorAxaMobileSdk } from 'capacitor-axa-mobile-sdk-plugin';
```


## APIs

Individual APIs interact with the SDK to perform specific tasks, reading, or setting information.  All APIs are asynchronous and returning information is achieved using a block.


```typescript
CapacitorAxaMobileSdk.individualAPI();
CapacitorAxaMobileSdk.individualAPI({ argument1: value, argument2: value, ... });
CapacitorAxaMobileSdk.individualAPI({ argument1: value, argument2: value, ... }).then(result) => {};
```


<docgen-index>

* [`addListener(CAMAA_NOTIFICATION_TYPE.CAMAA_CRASH_OCCURRED, ...)`](#addlistenercamaa_notification_typecamaa_crash_occurred)
* [`addListener(CAMAA_NOTIFICATION_TYPE.CAMAA_UPLOAD_INITIATED, ...)`](#addlistenercamaa_notification_typecamaa_upload_initiated)
* [`enableSDK()`](#enablesdk)
* [`disableSDK()`](#disablesdk)
* [`isSDKEnabled()`](#issdkenabled)
* [`getDeviceId()`](#getdeviceid)
* [`getCustomerId()`](#getcustomerid)
* [`setCustomerId(...)`](#setcustomerid)
* [`setSessionAttribute(...)`](#setsessionattribute)
* [`enterPrivateZone()`](#enterprivatezone)
* [`exitPrivateZone()`](#exitprivatezone)
* [`isInPrivateZone()`](#isinprivatezone)
* [`getAPMHeader()`](#getapmheader)
* [`addToAPMHeader(...)`](#addtoapmheader)
* [`setSSLPinningMode(...)`](#setsslpinningmode)
* [`stopCurrentSession()`](#stopcurrentsession)
* [`startNewSession()`](#startnewsession)
* [`stopCurrentAndStartNewSession()`](#stopcurrentandstartnewsession)
* [`startApplicationTransaction(...)`](#startapplicationtransaction)
* [`stopApplicationTransaction(...)`](#stopapplicationtransaction)
* [`setCustomerFeedback(...)`](#setcustomerfeedback)
* [`setCustomerLocation(...)`](#setcustomerlocation)
* [`sendScreenShot(...)`](#sendscreenshot)
* [`enableScreenShots(...)`](#enablescreenshots)
* [`viewLoaded(...)`](#viewloaded)
* [`ignoreView(...)`](#ignoreview)
* [`ignoreViews(...)`](#ignoreviews)
* [`isScreenshotPolicyEnabled()`](#isscreenshotpolicyenabled)
* [`logNetworkEvent(...)`](#lognetworkevent)
* [`logTextMetric(...)`](#logtextmetric)
* [`logNumericMetric(...)`](#lognumericmetric)
* [`uploadEvents()`](#uploadevents)
* [`setNSURLSessionDelegate(...)`](#setnsurlsessiondelegate)
* [`setLocation(...)`](#setlocation)
* [`logUIEvent(...)`](#loguievent)
* [Interfaces](#interfaces)
* [Enums](#enums)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### addListener(CAMAA_NOTIFICATION_TYPE.CAMAA_CRASH_OCCURRED, ...)

```typescript
addListener(eventName: CAMAA_NOTIFICATION_TYPE.CAMAA_CRASH_OCCURRED, listenerFunc: () => void) => Promise<PluginListenerHandle> & PluginListenerHandle
```

| Param              | Type                                                                                             |
| ------------------ | ------------------------------------------------------------------------------------------------ |
| **`eventName`**    | <code><a href="#camaa_notification_type">CAMAA_NOTIFICATION_TYPE.CAMAA_CRASH_OCCURRED</a></code> |
| **`listenerFunc`** | <code>() =&gt; void</code>                                                                       |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt; & <a href="#pluginlistenerhandle">PluginListenerHandle</a></code>

--------------------


### addListener(CAMAA_NOTIFICATION_TYPE.CAMAA_UPLOAD_INITIATED, ...)

```typescript
addListener(eventName: CAMAA_NOTIFICATION_TYPE.CAMAA_UPLOAD_INITIATED, listenerFunc: () => void) => Promise<PluginListenerHandle> & PluginListenerHandle
```

| Param              | Type                                                                                               |
| ------------------ | -------------------------------------------------------------------------------------------------- |
| **`eventName`**    | <code><a href="#camaa_notification_type">CAMAA_NOTIFICATION_TYPE.CAMAA_UPLOAD_INITIATED</a></code> |
| **`listenerFunc`** | <code>() =&gt; void</code>                                                                         |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt; & <a href="#pluginlistenerhandle">PluginListenerHandle</a></code>

--------------------


### enableSDK()

```typescript
enableSDK() => void
```

Use this API to enable SDK.
The SDK is enabled by default. You need to call this API
only if you called disableSDK earlier.

--------------------


### disableSDK()

```typescript
disableSDK() => void
```

Use this API to disable the SDK.
When disabled, the SDK no longer does any tracking of the application,
or user interaction.

--------------------


### isSDKEnabled()

```typescript
isSDKEnabled() => Promise<CAMDOIsSDKEnabledResult>
```

Use this API to determine if the SDK is enabled or not.

Returns a boolean value

**Returns:** <code>Promise&lt;<a href="#camdoissdkenabledresult">CAMDOIsSDKEnabledResult</a>&gt;</code>

--------------------


### getDeviceId()

```typescript
getDeviceId() => Promise<CAMDODeviceId>
```

Use this API to get the unique device ID generated by the SDK

Returns a device id value

**Returns:** <code>Promise&lt;<a href="#camdodeviceid">CAMDODeviceId</a>&gt;</code>

--------------------


### getCustomerId()

```typescript
getCustomerId() => Promise<CAMDOCustomerId>
```

Use this API to get the customer ID for this session.
Returns a customerId value. If the customer ID is not set, this API returns a null value.

**Returns:** <code>Promise&lt;<a href="#camdocustomerid">CAMDOCustomerId</a>&gt;</code>

--------------------


### setCustomerId(...)

```typescript
setCustomerId(options: { customerId: string; }) => Promise<CAMDOSDKErrorCallback>
```

Use this API to set the customer ID for this session.

| Param         | Type                                 |
| ------------- | ------------------------------------ |
| **`options`** | <code>{ customerId: string; }</code> |

**Returns:** <code>Promise&lt;<a href="#camdosdkerrorcallback">CAMDOSDKErrorCallback</a>&gt;</code>

--------------------


### setSessionAttribute(...)

```typescript
setSessionAttribute(options: { name: string; value: string; }) => Promise<CAMDOSDKErrorCallback>
```

Use this API to set a custom session attribute.

| Param         | Type                                          |
| ------------- | --------------------------------------------- |
| **`options`** | <code>{ name: string; value: string; }</code> |

**Returns:** <code>Promise&lt;<a href="#camdosdkerrorcallback">CAMDOSDKErrorCallback</a>&gt;</code>

--------------------


### enterPrivateZone()

```typescript
enterPrivateZone() => void
```

Use this API to stop collecting potentially sensitive data.

The following data is not collected when the app enters a private zone
   - Screenshots
   - Location information including GPS and IP addresses
   - Value in the text entry fields

--------------------


### exitPrivateZone()

```typescript
exitPrivateZone() => void
```

Use this API to start collecting all data again

--------------------


### isInPrivateZone()

```typescript
isInPrivateZone() => Promise<CAMDOIsInPrivateResult>
```

Use this API to determine if the SDK is in a private zone.

Returns a boolean value

**Returns:** <code>Promise&lt;<a href="#camdoisinprivateresult">CAMDOIsInPrivateResult</a>&gt;</code>

--------------------


### getAPMHeader()

```typescript
getAPMHeader() => Promise<CAMDOAPMHeaderResult>
```

Use this API to get the SDK computed APM header in key value format.
Returns dictionary or map of key, value pairs
Returns an empty string if apm header cannot be computed

**Returns:** <code>Promise&lt;<a href="#camdoapmheaderresult">CAMDOAPMHeaderResult</a>&gt;</code>

--------------------


### addToAPMHeader(...)

```typescript
addToAPMHeader(options: { data: string; }) => void
```

Use this API to add custom data to the SDK computed APM header.

| Param         | Type                           |
| ------------- | ------------------------------ |
| **`options`** | <code>{ data: string; }</code> |

--------------------


### setSSLPinningMode(...)

```typescript
setSSLPinningMode(options: { pinningMode: CAMDOSSLPinningMode; pinnedValues: string[]; }) => void
```

Use this API to set the ssl pinning mode and array of pinned values.
This method expects array of values depending on the pinningMode

| Param         | Type                                                                                                          |
| ------------- | ------------------------------------------------------------------------------------------------------------- |
| **`options`** | <code>{ pinningMode: <a href="#camdosslpinningmode">CAMDOSSLPinningMode</a>; pinnedValues: string[]; }</code> |

--------------------


### stopCurrentSession()

```typescript
stopCurrentSession() => void
```

Use this API to stop the current session.
No data will be logged until the startSession API is called

--------------------


### startNewSession()

```typescript
startNewSession() => void
```

Use this API to start a new session.
If a session is already in progress, it will be stopped and new session is started

--------------------


### stopCurrentAndStartNewSession()

```typescript
stopCurrentAndStartNewSession() => void
```

Convenience API to stop the current session in progress and start a new session
Equivalent to calling stopCurrentSession() and startNewSession()

--------------------


### startApplicationTransaction(...)

```typescript
startApplicationTransaction(options: { transactionName: string; serviceName?: string; }) => Promise<CAMDOSDKCallback>
```

Use this API to start a transaction with a specific name

| Param         | Type                                                            |
| ------------- | --------------------------------------------------------------- |
| **`options`** | <code>{ transactionName: string; serviceName?: string; }</code> |

**Returns:** <code>Promise&lt;<a href="#camdosdkcallback">CAMDOSDKCallback</a>&gt;</code>

--------------------


### stopApplicationTransaction(...)

```typescript
stopApplicationTransaction(options: { transactionName: string; failure?: string; }) => Promise<CAMDOSDKCallback>
```

Use this API to stop a transaction with a specific name and an optional failure string

| Param         | Type                                                        |
| ------------- | ----------------------------------------------------------- |
| **`options`** | <code>{ transactionName: string; failure?: string; }</code> |

**Returns:** <code>Promise&lt;<a href="#camdosdkcallback">CAMDOSDKCallback</a>&gt;</code>

--------------------


### setCustomerFeedback(...)

```typescript
setCustomerFeedback(options: { feedback: string; }) => void
```

Use this API to provide feedback from the user after a crash

| Param         | Type                               |
| ------------- | ---------------------------------- |
| **`options`** | <code>{ feedback: string; }</code> |

--------------------


### setCustomerLocation(...)

```typescript
setCustomerLocation(options: { postalCode: string; countryCode: string; }) => void
```

Use this API to set Location of the Customer/User
using postalCode and countryCode.

| Param         | Type                                                      |
| ------------- | --------------------------------------------------------- |
| **`options`** | <code>{ postalCode: string; countryCode: string; }</code> |

--------------------


### sendScreenShot(...)

```typescript
sendScreenShot(options: { name: string; quality: CAMDOSDKImageQualityType; }) => Promise<CAMDOSDKCallback>
```

Use this API to send a screen shot of the current screen

| Param         | Type                                                                                                      |
| ------------- | --------------------------------------------------------------------------------------------------------- |
| **`options`** | <code>{ name: string; quality: <a href="#camdosdkimagequalitytype">CAMDOSDKImageQualityType</a>; }</code> |

**Returns:** <code>Promise&lt;<a href="#camdosdkcallback">CAMDOSDKCallback</a>&gt;</code>

--------------------


### enableScreenShots(...)

```typescript
enableScreenShots(captureScreen: boolean) => void
```

Use this API to programmatically enable or disable automatic screen captures.

| Param               | Type                 | Description                                                                                                                                                                                                               |
| ------------------- | -------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **`captureScreen`** | <code>boolean</code> | is a boolean value to enable/disable automatic screen captures. Normally the policy determines whether automatic screen captures are performed. Use this API to override the policy, or the current setting of this flag. |

--------------------


### viewLoaded(...)

```typescript
viewLoaded(options: { viewName: string; loadTime: number; screenShot?: boolean; }) => Promise<CAMDOSDKCallback>
```

Use this API to create a custom app flow with dynamic views

| Param         | Type                                                                       |
| ------------- | -------------------------------------------------------------------------- |
| **`options`** | <code>{ viewName: string; loadTime: number; screenShot?: boolean; }</code> |

**Returns:** <code>Promise&lt;<a href="#camdosdkcallback">CAMDOSDKCallback</a>&gt;</code>

--------------------


### ignoreView(...)

```typescript
ignoreView(options: { viewName: string; }) => void
```

Use this API to set the name of a view to be ignored

| Param         | Type                               |
| ------------- | ---------------------------------- |
| **`options`** | <code>{ viewName: string; }</code> |

--------------------


### ignoreViews(...)

```typescript
ignoreViews(options: { viewNames: string[]; }) => void
```

Use this API to provide a list of view names to be ignored.

| Param         | Type                                  |
| ------------- | ------------------------------------- |
| **`options`** | <code>{ viewNames: string[]; }</code> |

--------------------


### isScreenshotPolicyEnabled()

```typescript
isScreenshotPolicyEnabled() => Promise<CAMDOIsPolicyEnabledResult>
```

Use this API to determine if automatic screenshots are enabled by policy.

Returns a boolean value
Returns YES if screenshots are enabled by policy.  Otherwise returns NO

**Returns:** <code>Promise&lt;<a href="#camdoispolicyenabledresult">CAMDOIsPolicyEnabledResult</a>&gt;</code>

--------------------


### logNetworkEvent(...)

```typescript
logNetworkEvent(options: { url: string; status: number; responseTime: number; inBytes: number; outBytes: number; }) => Promise<CAMDOSDKCallback>
```

Use this API to add a custom network event in the current session

| Param         | Type                                                                                                   |
| ------------- | ------------------------------------------------------------------------------------------------------ |
| **`options`** | <code>{ url: string; status: number; responseTime: number; inBytes: number; outBytes: number; }</code> |

**Returns:** <code>Promise&lt;<a href="#camdosdkcallback">CAMDOSDKCallback</a>&gt;</code>

--------------------


### logTextMetric(...)

```typescript
logTextMetric(options: { textMetricName: string; value: string; attributes?: object; }) => Promise<CAMDOSDKCallback>
```

Use this API to add a custom text metric in the current session

| Param         | Type                                                                         |
| ------------- | ---------------------------------------------------------------------------- |
| **`options`** | <code>{ textMetricName: string; value: string; attributes?: object; }</code> |

**Returns:** <code>Promise&lt;<a href="#camdosdkcallback">CAMDOSDKCallback</a>&gt;</code>

--------------------


### logNumericMetric(...)

```typescript
logNumericMetric(options: { numericMetricName: string; value: number; attributes?: object; }) => Promise<CAMDOSDKCallback>
```

Use this API to add a custom numeric metric value in the current session

| Param         | Type                                                                            |
| ------------- | ------------------------------------------------------------------------------- |
| **`options`** | <code>{ numericMetricName: string; value: number; attributes?: object; }</code> |

**Returns:** <code>Promise&lt;<a href="#camdosdkcallback">CAMDOSDKCallback</a>&gt;</code>

--------------------


### uploadEvents()

```typescript
uploadEvents() => Promise<CAMDOUploadEventsCallback>
```

Use this API to force an upload event.
This is bulk/resource consuming operation and should be used with caution

**Returns:** <code>Promise&lt;<a href="#camdouploadeventscallback">CAMDOUploadEventsCallback</a>&gt;</code>

--------------------


### setNSURLSessionDelegate(...)

```typescript
setNSURLSessionDelegate(options: { delegate: string; }) => void
```

Use this API to set your delegate instance to handle auth challenges.
Use it when using SDKUseNetworkProtocolSwizzling option

| Param         | Type                               |
| ------------- | ---------------------------------- |
| **`options`** | <code>{ delegate: string; }</code> |

--------------------


### setLocation(...)

```typescript
setLocation(options: { latitude: number; longitude: number; }) => void
```

Use this API to set Geographic or GPS Location of the Customer

| Param         | Type                                                  |
| ------------- | ----------------------------------------------------- |
| **`options`** | <code>{ latitude: number; longitude: number; }</code> |

--------------------


### logUIEvent(...)

```typescript
logUIEvent(options: { eventType: CAMDOUIEventType; value: string; }) => void
```

| Param         | Type                                                                                         |
| ------------- | -------------------------------------------------------------------------------------------- |
| **`options`** | <code>{ eventType: <a href="#camdouieventtype">CAMDOUIEventType</a>; value: string; }</code> |

--------------------


### Interfaces


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |


#### CAMDOIsSDKEnabledResult

| Prop        | Type                 |
| ----------- | -------------------- |
| **`value`** | <code>boolean</code> |


#### CAMDODeviceId

| Prop        | Type                |
| ----------- | ------------------- |
| **`value`** | <code>string</code> |


#### CAMDOCustomerId

| Prop        | Type                        |
| ----------- | --------------------------- |
| **`value`** | <code>string \| null</code> |


#### CAMDOSDKErrorCallback

| Prop        | Type                                          |
| ----------- | --------------------------------------------- |
| **`error`** | <code><a href="#sdkerror">SDKError</a></code> |


#### CAMDOIsInPrivateResult

| Prop        | Type                 |
| ----------- | -------------------- |
| **`value`** | <code>boolean</code> |


#### CAMDOAPMHeaderResult

| Prop        | Type                        |
| ----------- | --------------------------- |
| **`value`** | <code>object \| null</code> |


#### CAMDOSDKCallback

| Prop            | Type                        |
| --------------- | --------------------------- |
| **`completed`** | <code>boolean</code>        |
| **`error`**     | <code>string \| null</code> |


#### CAMDOIsPolicyEnabledResult

| Prop        | Type                 |
| ----------- | -------------------- |
| **`value`** | <code>boolean</code> |


#### CAMDOUploadEventsCallback

| Prop           | Type                        |
| -------------- | --------------------------- |
| **`response`** | <code>object</code>         |
| **`error`**    | <code>string \| null</code> |


### Enums


#### CAMAA_NOTIFICATION_TYPE

| Members                      | Value                                 |
| ---------------------------- | ------------------------------------- |
| **`CAMAA_UPLOAD_INITIATED`** | <code>'CAMAA_UPLOAD_INITIATED'</code> |
| **`CAMAA_CRASH_OCCURRED`**   | <code>'CAMAA_CRASH_OCCURRED'</code>   |


#### SDKError

| Members                           |
| --------------------------------- |
| **`ErrorNone`**                   |
| **`ErrorNoTransactionName`**      |
| **`ErrorTransactionInProgress`**  |
| **`ErrorFailedToTakeScreenshot`** |
| **`ErrorInvalidValuesPassed`**    |


#### CAMDOSSLPinningMode

| Members                                           |
| ------------------------------------------------- |
| **`CAMDOSSLPinningModeNone`**                     |
| **`CAMDOSSLPinningModePublicKey`**                |
| **`CAMDOSSLPinningModeCertificate`**              |
| **`CAMDOSSLPinningModeFingerPrintSHA1Signature`** |
| **`CAMDOSSLPinningModePublicKeyHash`**            |


#### CAMDOSDKImageQualityType

| Members                                | Value                                           |
| -------------------------------------- | ----------------------------------------------- |
| **`CAMAA_SCREENSHOT_QUALITY_HIGH`**    | <code>'CAMAA_SCREENSHOT_QUALITY_HIGH'</code>    |
| **`CAMAA_SCREENSHOT_QUALITY_MEDIUM`**  | <code>'CAMAA_SCREENSHOT_QUALITY_MEDIUM'</code>  |
| **`CAMAA_SCREENSHOT_QUALITY_LOW`**     | <code>'CAMAA_SCREENSHOT_QUALITY_LOW'</code>     |
| **`CAMAA_SCREENSHOT_QUALITY_DEFAULT`** | <code>'CAMAA_SCREENSHOT_QUALITY_DEFAULT'</code> |


#### CAMDOUIEventType

| Members                                     | Value                                  |
| ------------------------------------------- | -------------------------------------- |
| **`CAMAA_EVENT_BUTTON_PRESSED`**            | <code>'button_pressed'</code>          |
| **`CAMAA_EVENT_DATE_PICKER_VIEW_SELECTED`** | <code>'date_picker_selected'</code>    |
| **`CAMAA_EVENT_PAGE_CHANGED`**              | <code>'page_changed'</code>            |
| **`CAMAA_EVENT_SEGMENTED_CONTROL_PRESSED`** | <code>'segment_control_pressed'</code> |
| **`CAMAA_EVENT_SLIDER_MOVED`**              | <code>'slider_moved'</code>            |
| **`CAMAA_EVENT_STEPPER_PRESSED`**           | <code>'stepper_pressed'</code>         |
| **`CAMAA_EVENT_SWITCH_PRESSED`**            | <code>'switch_pressed'</code>          |

</docgen-api>
