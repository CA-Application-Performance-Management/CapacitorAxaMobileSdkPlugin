# capacitor-axa-mobile-sdk-plugin

CA App Experience Analytic native SDK's capacitor supplement for using custom metrics

## Install

```bash
npm install capacitor-axa-mobile-sdk-plugin
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`getCustomerId()`](#getcustomerid)
* [`setCustomerId(...)`](#setcustomerid)
* [`isSDKEnabled()`](#issdkenabled)
* [Interfaces](#interfaces)
* [Enums](#enums)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### getCustomerId()

```typescript
getCustomerId() => Promise<SDKErrorCallback>
```

Get the CustomerId.

**Returns:** <code>Promise&lt;<a href="#sdkerrorcallback">SDKErrorCallback</a>&gt;</code>

--------------------


### setCustomerId(...)

```typescript
setCustomerId(options: { customerId: string; }) => Promise<SDKErrorCallback>
```

Set the CustomerID

| Param         | Type                                 |
| ------------- | ------------------------------------ |
| **`options`** | <code>{ customerId: string; }</code> |

**Returns:** <code>Promise&lt;<a href="#sdkerrorcallback">SDKErrorCallback</a>&gt;</code>

--------------------


### isSDKEnabled()

```typescript
isSDKEnabled() => Boolean
```

Checks if SDK is enabled or not

**Returns:** <code><a href="#boolean">Boolean</a></code>

--------------------


### Interfaces


#### SDKErrorCallback

| Prop        | Type                                          |
| ----------- | --------------------------------------------- |
| **`error`** | <code><a href="#sdkerror">SDKError</a></code> |


#### Boolean

| Method      | Signature        | Description                                          |
| ----------- | ---------------- | ---------------------------------------------------- |
| **valueOf** | () =&gt; boolean | Returns the primitive value of the specified object. |


### Enums


#### SDKError

| Members                           |
| --------------------------------- |
| **`ErrorNone`**                   |
| **`ErrorNoTransactionName`**      |
| **`ErrorTransactionInProgress`**  |
| **`ErrorFailedToTakeScreenshot`** |
| **`ErrorInvalidValuesPassed`**    |

</docgen-api>
