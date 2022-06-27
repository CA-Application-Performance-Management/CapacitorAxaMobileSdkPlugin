import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(CapacitorAxaMobileSdkPlugin)
public class CapacitorAxaMobileSdkPlugin: CAPPlugin {

    override public func load() {
        NotificationCenter.default.addObserver(self, selector: #selector(self.handleCAMAAUploadEvent(notification:)), name: NSNotification.Name(rawValue: CAMAA_UPLOAD_INITIATED), object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(self.handleCAMAACrashOccurredEvent(notification:)), name: NSNotification.Name(rawValue: CAMAA_CRASH_OCCURRED), object: nil)
    }
    
    deinit {
        NotificationCenter.default.removeObserver(self)
    }

    @objc func handleCAMAAUploadEvent(notification: NSNotification) {
        notifyListeners("CAMAA_UPLOAD_INITIATED", data: [:], retainUntilConsumed: true)
    }
    @objc func handleCAMAACrashOccurredEvent(notification: NSNotification) {
        notifyListeners("CAMAA_CRASH_OCCURRED", data: [:], retainUntilConsumed: true)
    }

    /**
     * This function is internal and only for passing NSError * items to JS as a string
     */
    func CAMAAErrorString(error: NSError) -> String {
        return String(format: "%@: %ld %@", error.domain, error.code, error.userInfo["NSLocalizedDescription"] as! CVarArg)
    }
    
    @objc func enableSDK(_ call: CAPPluginCall) {
        CAMDOReporter.enableSDK()
        
        call.resolve()
    }
    
    @objc func disableSDK(_ call: CAPPluginCall) {
        CAMDOReporter.disableSDK()
        call.resolve()
    }
    
    @objc func isSDKEnabled(_ call: CAPPluginCall) {
        let isEnabled = CAMDOReporter.isSDKEnabled()
        call.resolve([
            "value": isEnabled
        ])
    }
    
    @objc func getDeviceId(_ call: CAPPluginCall) {
        let deviceId = CAMDOReporter.deviceId()
        call.resolve([
            "value": deviceId ?? ""
        ])
    }

    @objc func getCustomerId(_ call: CAPPluginCall) {
        let customerId = CAMDOReporter.customerId()
        call.resolve([
            "value": customerId as Any
        ])
    }
    
    @objc func setCustomerId(_ call: CAPPluginCall) {
        guard let customerId = call.getString("customerId"), !customerId.isEmpty else {
            call.resolve([
                "error": "No Customer Id"
            ])
            return
        }
        let error: SDKError = CAMDOReporter.setCustomerId(customerId)
        call.resolve([
            "error": error
        ])
    }
    
    @objc func setSessionAttribute(_ call: CAPPluginCall) {
        guard let name = call.getString("name"), !name.isEmpty else {
            call.resolve([
                "error": "No session attribute name"
            ])
            return
        }
        
        guard let value = call.getString("value"), !name.isEmpty else {
            call.resolve([
                "error": "No session attribute value"
            ])
            return
        }
        
        let error: SDKError = CAMDOReporter.setSessionAttribute(name, withValue: value)
        call.resolve([
            "error": error
        ])
    }
    
    @objc func enterPrivateZone(_ call: CAPPluginCall) {
        CAMDOReporter.enterPrivateZone()
        call.resolve()
    }
    
    @objc func exitPrivateZone(_ call: CAPPluginCall) {
        CAMDOReporter.exitPrivateZone()
        call.resolve()
    }
    
    @objc func isInPrivateZone(_ call: CAPPluginCall) {
        let isInPrivate = CAMDOReporter.isInPrivateZone()
        call.resolve([
            "value": isInPrivate
        ])
    }
    
    @objc func getAPMHeaders(_ call: CAPPluginCall) {
        let apmHeader = CAMDOReporter.apmHeader()
        call.resolve([
            "value": apmHeader as Any
        ])
    }
    
    @objc func addToAPMHeader(_ call: CAPPluginCall) {
        guard let apmHeader = call.getString("data"), !apmHeader.isEmpty else {
            call.reject("No APM header")
            return
        }
        CAMDOReporter.add(toApmHeader: apmHeader)
        call.resolve()
    }
    
    @objc func setSSLPinningMode(_ call: CAPPluginCall) {
        guard let pinningMode = call.getInt("pinningMode"), let pinnedValues = call.getArray("pinnedValues") else {
            return
        }
        CAMDOReporter.setSSLPinningMode(CAMDOSSLPinningMode(rawValue: UInt(pinningMode))!, withValues: pinnedValues)
        call.resolve()
    }
    
    @objc func stopCurrentSession(_ call: CAPPluginCall) {
        CAMDOReporter.stopCurrentSession()
        call.resolve()
    }
    
    @objc func startNewSession(_ call: CAPPluginCall) {
        CAMDOReporter.startNewSession()
        call.resolve()
    }
    
    @objc func stopCurrentAndStartNewSession(_ call: CAPPluginCall) {
        CAMDOReporter.stopCurrentAndStartNewSession()
        call.resolve()
    }
    
    @objc func startApplicationTransaction(_ call: CAPPluginCall) {
        guard let transactionName = call.getString("transactionName"), !transactionName.isEmpty else {
            call.resolve([
                "error": "No transaction name"
            ])
            return
        }
        
        let serviceName = call.getString("serviceName")
        if serviceName != nil {
            CAMDOReporter.startApplicationTransaction(withName: transactionName, service: serviceName) { completed, error in
                
                var errorStr: String? = nil
                if error != nil {
                    errorStr = self.CAMAAErrorString(error: error! as NSError)
                }
                call.resolve([
                    "completed": completed,
                    "error": errorStr as Any
                ])
            }
        } else {
            CAMDOReporter.startApplicationTransaction(withName: transactionName) { completed, error in
                var errorStr: String? = nil
                if error != nil {
                    errorStr = self.CAMAAErrorString(error: error! as NSError)
                }
                call.resolve([
                    "completed": completed,
                    "error": errorStr as Any
                ])
            }
        }
    }
    
    @objc func stopApplicationTransaction(_ call: CAPPluginCall) {
        guard let transactionName = call.getString("transactionName"), !transactionName.isEmpty else {
            call.resolve([
                "error": "No transaction name"
            ])
            return
        }
        
        let failure = call.getString("failure")
        if failure != nil {
            CAMDOReporter.stopApplicationTransaction(withName: transactionName, failure: failure) { completed, error in
                var errorStr: String? = nil
                if error != nil {
                    errorStr = self.CAMAAErrorString(error: error! as NSError)
                }
                call.resolve([
                    "completed": completed,
                    "error": errorStr as Any
                ])
            }
        } else {
            CAMDOReporter.stopApplicationTransaction(withName: transactionName) { completed, error in
                var errorStr: String? = nil
                if error != nil {
                    errorStr = self.CAMAAErrorString(error: error! as NSError)
                }
                call.resolve([
                    "completed": completed,
                    "error": errorStr as Any
                ])
            }
        }
    }
    
    @objc func setCustomerFeedback(_ call: CAPPluginCall) {
        guard let feedback = call.getString("feedback"), !feedback.isEmpty else {
            call.reject("No feedback")
            return
        }
        CAMDOReporter.setCustomerFeedback(feedback)
        call.resolve()
    }
    
    @objc func setCustomerLocation(_ call: CAPPluginCall) {
        guard let postalCode = call.getString("postalCode"), let countryCode = call.getString("countryCode") else {
            call.reject("No Postal code and country code")
            return
        }
        CAMDOReporter.setCustomerLocation(postalCode, andCountry: countryCode)
        call.resolve()
    }
    
    @objc func sendScreenShot(_ call: CAPPluginCall) {
        guard let name = call.getString("name"), !name.isEmpty else {
            call.resolve([
                "error": "No Screen name"
            ])
            return
        }
        
        let quality = call.getString("quality")
        var imageQuality: CGFloat = CAMAA_SCREENSHOT_QUALITY_DEFAULT
        switch quality {
        case "CAMAA_SCREENSHOT_QUALITY_DEFAULT":
            imageQuality = CAMAA_SCREENSHOT_QUALITY_DEFAULT
        case "CAMAA_SCREENSHOT_QUALITY_HIGH":
            imageQuality = CAMAA_SCREENSHOT_QUALITY_HIGH
        case "CAMAA_SCREENSHOT_QUALITY_MEDIUM":
            imageQuality = CAMAA_SCREENSHOT_QUALITY_MEDIUM
        case "CAMAA_SCREENSHOT_QUALITY_LOW":
            imageQuality = CAMAA_SCREENSHOT_QUALITY_LOW
        default:
            imageQuality = CAMAA_SCREENSHOT_QUALITY_DEFAULT
        }
        
        CAMDOReporter.sendScreenShot(name, withQuality: imageQuality) { completed, error in
            var errorStr: String? = nil
            if error != nil {
                errorStr = self.CAMAAErrorString(error: error! as NSError)
            }
            call.resolve([
                "completed": completed,
                "error": errorStr as Any
            ])
        }
    }
    
    @objc func enableScreenShots(_ call: CAPPluginCall) {
        let captureScreen = call.getBool("captureScreen")
        CAMDOReporter.enableScreenShots(captureScreen!)
        call.resolve()
    }
    
    @objc func viewLoaded(_ call: CAPPluginCall) {
        guard let viewName = call.getString("viewName"), let loadTime = call.getFloat("loadTime") else {
            call.reject("No viewName or loadTime")
            return
        }
        let failure = call.getBool("screenShot")
        if failure != nil {
            CAMDOReporter.viewLoaded(viewName, loadTime: CGFloat(loadTime), screenShot: failure!) { completed, error in
                var errorStr: String? = nil
                if error != nil {
                    errorStr = self.CAMAAErrorString(error: error! as NSError)
                }
                call.resolve([
                    "completed": completed,
                    "error": errorStr as Any
                ])
            }
        } else {
            CAMDOReporter.viewLoaded(viewName, loadTime: CGFloat(loadTime)) { completed, error in
                var errorStr: String? = nil
                if error != nil {
                    errorStr = self.CAMAAErrorString(error: error! as NSError)
                }
                call.resolve([
                    "completed": completed,
                    "error": errorStr as Any
                ])
            }
        }
    }
    
    @objc func ignoreView(_ call: CAPPluginCall) {
        guard let viewName = call.getString("viewName"), !viewName.isEmpty else {
            call.reject("No viewName")
            return
        }
        CAMDOReporter.ignoreView(viewName)
        call.resolve()
    }
    
    @objc func ignoreViews(_ call: CAPPluginCall) {
        guard let viewNames = call.getArray("viewNames", String.self), !viewNames.isEmpty else {
            call.reject("No viewNames")
            return
        }
        
        CAMDOReporter.ignoreViews(Set(viewNames))
        call.resolve()
    }
    
    @objc func isScreenshotPolicyEnabled(_ call: CAPPluginCall) {
        let isEnabled = CAMDOReporter.isScreenshotPolicyEnabled()
        call.resolve([
            "value": isEnabled
        ])
    }
    
    @objc func logNetworkEvent(_ call: CAPPluginCall) {
        guard let url = call.getString("url"), let status = call.getInt("status"), let responseTime = call.getInt("responseTime"), let inBytes = call.getInt("inBytes"), let outBytes = call.getInt("outBytes") else {
            call.reject("No url or status or responseTime or inBytes or outBytes")
            return
        }
        CAMDOReporter.logNetworkEvent(url, withStatus: status, withResponseTime: Int64(responseTime), withInBytes: Int64(inBytes), withOutBytes: Int64(outBytes)) { completed, error in
            var errorStr: String? = nil
            if error != nil {
                errorStr = self.CAMAAErrorString(error: error! as NSError)
            }
            call.resolve([
                "completed": completed,
                "error": errorStr as Any
            ])
        }
    }
    
    @objc func logTextMetric(_ call: CAPPluginCall) {
        guard let textMetricName = call.getString("textMetricName"), !textMetricName.isEmpty, let value = call.getString("value"), !value.isEmpty else {
            call.resolve([
                "error": "No Text Metric name or value"
            ])
            return
        }
        let attributes = call.getObject("attributes")
        CAMDOReporter.logTextMetric(textMetricName, withValue: value, withAttributes: attributes as? NSMutableDictionary) { completed, error in
            
            var errorStr: String? = nil
            if error != nil {
                errorStr = self.CAMAAErrorString(error: error! as NSError)
            }
            call.resolve([
                "completed": completed,
                "error": errorStr as Any
            ])
        }
    }
    
    @objc func logNumericMetric(_ call: CAPPluginCall) {
        guard let numericMetricName = call.getString("numericMetricName"), !numericMetricName.isEmpty, let value = call.getDouble("value") else {
            call.resolve([
                "error": "No Numeric Metric name or value"
            ])
            return
        }
        let attributes = call.getObject("attributes")
        CAMDOReporter.logNumericMetric(numericMetricName, withValue: value, withAttributes: attributes as? NSMutableDictionary) { completed, error in
            var errorStr: String? = nil
            if error != nil {
                errorStr = self.CAMAAErrorString(error: error! as NSError)
            }
            call.resolve([
                "completed": completed,
                "error": errorStr as Any
            ])
        }
    }
    
    @objc func uploadEvents(_ call: CAPPluginCall) {
        DispatchQueue.main.async {
            CAMDOReporter.uploadEvents { response, error in
                var errorStr: String? = nil
                if error != nil {
                    errorStr = self.CAMAAErrorString(error: error! as NSError)
                }
                call.resolve([
                    "response": response as Any,
                    "error": errorStr as Any
                ])
            }
        }
    }
    
    @objc func setLocation(_ call: CAPPluginCall) {
        guard let latitude = call.getDouble("latitude"), let longitude = call.getDouble("longitude") else {
            call.resolve([
                "error": "No latitude or longitude"
            ])
            return
        }
        CAMDOReporter.setCustomerLocation(CLLocation(latitude: latitude, longitude: longitude))
        call.resolve()
    }
    
    @objc func logUIEvent(_ call: CAPPluginCall) {
        guard let eventType = call.getString("eventType"), !eventType.isEmpty, let value = call.getString("value") else {
            call.resolve([
                "error": "No event type or value"
            ])
            return
        }
        CAMDOReporter.logUIEvent(eventType, withValue: value)
        call.resolve()
    }
}

