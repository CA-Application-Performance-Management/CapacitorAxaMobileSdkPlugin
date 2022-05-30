// export type callback = (message: String | null, err?: any) => void;

export interface CapacitorAxaMobileSdkPlugin {

  echo(options: { value: string }): Promise<{ value: string }>;

  /**
    * Get the CustomerId.
    * 
    */
    getCustomerId(): Promise<SDKErrorCallback>;


    /**
     *Set the CustomerID
     * @param customerId
     */
    setCustomerId(options: { customerId: string }): Promise<SDKErrorCallback>;

    /**
    * Checks if SDK is enabled or not
    * 
    */
    isSDKEnabled(): Boolean;
}

export interface SDKErrorCallback {
  error: SDKError;
}

export enum SDKError {
    ErrorNone,
    ErrorNoTransactionName,
    ErrorTransactionInProgress,
    ErrorFailedToTakeScreenshot,
    ErrorInvalidValuesPassed
}