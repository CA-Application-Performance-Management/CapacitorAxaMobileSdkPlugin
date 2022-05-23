import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(CapacitorAxaMobileSdkPlugin)
public class CapacitorAxaMobileSdkPlugin: CAPPlugin {
    private let implementation = CapacitorAxaMobileSdk()

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }


    /**
     * Use this API to set the customer ID for this session.
     *
     * @param customerId is a string containing the customer ID
     * @param callback is a function which expects an (SDKError value)
     *
     * If an empty string is passed, the customer iD is reset.
     *
     */
    @objc func setCustomerId(_ call: CAPPluginCall) -> SDKError {
        guard let customerId = call.getString("customerId") else {
          call.reject("Must provide an Customer Id")
            return SDKError.ErrorInvalidValuesPassed
        }
        let error: SDKError = CAMDOReporter.setCustomerId(customerId)
        //call.resolve(@(error))
        return error
    }

    /**
     * Use this API to get the customer ID for this session.
     * @param callback is a function which expects an string value
     *
     * If the customer ID is not set, this API returns a null value.
     *
     */
    @objc func getCustomerId(_ call: CAPPluginCall) -> String? {
        let response = CAMDOReporter.customerId()
        return response
    }
    
    /**
     * Use this API to determine if the SDK is enabled or not.
     *
     * @param callback is a function which expects a boolean value
     *
    */
    @objc func isSDKEnabled(_ call: CAPPluginCall) -> Bool {
        let response = CAMDOReporter.isSDKEnabled()
        return response
    }
    
}

