export type callback = (message: String | null, err?: any) => void;

export interface CapacitorAxaMobileSdkPlugin {
  /**
    * Get the CustomerId.
    * 
    */
    getCustomerId(): String;


    /**
     *Set the CustomerID
     * @param customerId
     */
    setCustomerId(
        customerId: String
    ): String;


    /**
    * Checks if SDK is enabled or not
    * 
    */
    isSDKEnabled(): Boolean;
}
