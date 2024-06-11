//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.10
//
// <auto-generated>
//
// Generated from file `House.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package House;

public interface LightPrx extends com.zeroc.Ice.ObjectPrx
{
    default void turnOn()
    {
        turnOn(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void turnOn(java.util.Map<String, String> context)
    {
        _iceI_turnOnAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> turnOnAsync()
    {
        return _iceI_turnOnAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> turnOnAsync(java.util.Map<String, String> context)
    {
        return _iceI_turnOnAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_turnOnAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "turnOn", null, sync, null);
        f.invoke(false, context, null, null, null);
        return f;
    }

    default void turnOff()
    {
        turnOff(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void turnOff(java.util.Map<String, String> context)
    {
        _iceI_turnOffAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> turnOffAsync()
    {
        return _iceI_turnOffAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> turnOffAsync(java.util.Map<String, String> context)
    {
        return _iceI_turnOffAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_turnOffAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "turnOff", null, sync, null);
        f.invoke(false, context, null, null, null);
        return f;
    }

    default void setBrightness(int brightness)
    {
        setBrightness(brightness, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void setBrightness(int brightness, java.util.Map<String, String> context)
    {
        _iceI_setBrightnessAsync(brightness, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> setBrightnessAsync(int brightness)
    {
        return _iceI_setBrightnessAsync(brightness, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> setBrightnessAsync(int brightness, java.util.Map<String, String> context)
    {
        return _iceI_setBrightnessAsync(brightness, context, false);
    }

    /**
     * @hidden
     * @param iceP_brightness -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_setBrightnessAsync(int iceP_brightness, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "setBrightness", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeInt(iceP_brightness);
                 }, null);
        return f;
    }

    default String automaticTurnOff(int time)
    {
        return automaticTurnOff(time, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default String automaticTurnOff(int time, java.util.Map<String, String> context)
    {
        return _iceI_automaticTurnOffAsync(time, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.String> automaticTurnOffAsync(int time)
    {
        return _iceI_automaticTurnOffAsync(time, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.String> automaticTurnOffAsync(int time, java.util.Map<String, String> context)
    {
        return _iceI_automaticTurnOffAsync(time, context, false);
    }

    /**
     * @hidden
     * @param iceP_time -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.String> _iceI_automaticTurnOffAsync(int iceP_time, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.String> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "automaticTurnOff", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeInt(iceP_time);
                 }, istr -> {
                     String ret;
                     ret = istr.readString();
                     return ret;
                 });
        return f;
    }

    default String getInfo()
    {
        return getInfo(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default String getInfo(java.util.Map<String, String> context)
    {
        return _iceI_getInfoAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.String> getInfoAsync()
    {
        return _iceI_getInfoAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.String> getInfoAsync(java.util.Map<String, String> context)
    {
        return _iceI_getInfoAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.String> _iceI_getInfoAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.String> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "getInfo", null, sync, null);
        f.invoke(true, context, null, null, istr -> {
                     String ret;
                     ret = istr.readString();
                     return ret;
                 });
        return f;
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static LightPrx checkedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, ice_staticId(), LightPrx.class, _LightPrxI.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static LightPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, context, ice_staticId(), LightPrx.class, _LightPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static LightPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, ice_staticId(), LightPrx.class, _LightPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static LightPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, context, ice_staticId(), LightPrx.class, _LightPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @return A proxy for this type.
     **/
    static LightPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, LightPrx.class, _LightPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    static LightPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, facet, LightPrx.class, _LightPrxI.class);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the per-proxy context.
     * @param newContext The context for the new proxy.
     * @return A proxy with the specified per-proxy context.
     **/
    @Override
    default LightPrx ice_context(java.util.Map<String, String> newContext)
    {
        return (LightPrx)_ice_context(newContext);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the adapter ID.
     * @param newAdapterId The adapter ID for the new proxy.
     * @return A proxy with the specified adapter ID.
     **/
    @Override
    default LightPrx ice_adapterId(String newAdapterId)
    {
        return (LightPrx)_ice_adapterId(newAdapterId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoints.
     * @param newEndpoints The endpoints for the new proxy.
     * @return A proxy with the specified endpoints.
     **/
    @Override
    default LightPrx ice_endpoints(com.zeroc.Ice.Endpoint[] newEndpoints)
    {
        return (LightPrx)_ice_endpoints(newEndpoints);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator cache timeout.
     * @param newTimeout The new locator cache timeout (in seconds).
     * @return A proxy with the specified locator cache timeout.
     **/
    @Override
    default LightPrx ice_locatorCacheTimeout(int newTimeout)
    {
        return (LightPrx)_ice_locatorCacheTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the invocation timeout.
     * @param newTimeout The new invocation timeout (in seconds).
     * @return A proxy with the specified invocation timeout.
     **/
    @Override
    default LightPrx ice_invocationTimeout(int newTimeout)
    {
        return (LightPrx)_ice_invocationTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for connection caching.
     * @param newCache <code>true</code> if the new proxy should cache connections; <code>false</code> otherwise.
     * @return A proxy with the specified caching policy.
     **/
    @Override
    default LightPrx ice_connectionCached(boolean newCache)
    {
        return (LightPrx)_ice_connectionCached(newCache);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoint selection policy.
     * @param newType The new endpoint selection policy.
     * @return A proxy with the specified endpoint selection policy.
     **/
    @Override
    default LightPrx ice_endpointSelection(com.zeroc.Ice.EndpointSelectionType newType)
    {
        return (LightPrx)_ice_endpointSelection(newType);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for how it selects endpoints.
     * @param b If <code>b</code> is <code>true</code>, only endpoints that use a secure transport are
     * used by the new proxy. If <code>b</code> is false, the returned proxy uses both secure and
     * insecure endpoints.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default LightPrx ice_secure(boolean b)
    {
        return (LightPrx)_ice_secure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the encoding used to marshal parameters.
     * @param e The encoding version to use to marshal request parameters.
     * @return A proxy with the specified encoding version.
     **/
    @Override
    default LightPrx ice_encodingVersion(com.zeroc.Ice.EncodingVersion e)
    {
        return (LightPrx)_ice_encodingVersion(e);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its endpoint selection policy.
     * @param b If <code>b</code> is <code>true</code>, the new proxy will use secure endpoints for invocations
     * and only use insecure endpoints if an invocation cannot be made via secure endpoints. If <code>b</code> is
     * <code>false</code>, the proxy prefers insecure endpoints to secure ones.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default LightPrx ice_preferSecure(boolean b)
    {
        return (LightPrx)_ice_preferSecure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the router.
     * @param router The router for the new proxy.
     * @return A proxy with the specified router.
     **/
    @Override
    default LightPrx ice_router(com.zeroc.Ice.RouterPrx router)
    {
        return (LightPrx)_ice_router(router);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator.
     * @param locator The locator for the new proxy.
     * @return A proxy with the specified locator.
     **/
    @Override
    default LightPrx ice_locator(com.zeroc.Ice.LocatorPrx locator)
    {
        return (LightPrx)_ice_locator(locator);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for collocation optimization.
     * @param b <code>true</code> if the new proxy enables collocation optimization; <code>false</code> otherwise.
     * @return A proxy with the specified collocation optimization.
     **/
    @Override
    default LightPrx ice_collocationOptimized(boolean b)
    {
        return (LightPrx)_ice_collocationOptimized(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses twoway invocations.
     * @return A proxy that uses twoway invocations.
     **/
    @Override
    default LightPrx ice_twoway()
    {
        return (LightPrx)_ice_twoway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses oneway invocations.
     * @return A proxy that uses oneway invocations.
     **/
    @Override
    default LightPrx ice_oneway()
    {
        return (LightPrx)_ice_oneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch oneway invocations.
     * @return A proxy that uses batch oneway invocations.
     **/
    @Override
    default LightPrx ice_batchOneway()
    {
        return (LightPrx)_ice_batchOneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses datagram invocations.
     * @return A proxy that uses datagram invocations.
     **/
    @Override
    default LightPrx ice_datagram()
    {
        return (LightPrx)_ice_datagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch datagram invocations.
     * @return A proxy that uses batch datagram invocations.
     **/
    @Override
    default LightPrx ice_batchDatagram()
    {
        return (LightPrx)_ice_batchDatagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, except for compression.
     * @param co <code>true</code> enables compression for the new proxy; <code>false</code> disables compression.
     * @return A proxy with the specified compression setting.
     **/
    @Override
    default LightPrx ice_compress(boolean co)
    {
        return (LightPrx)_ice_compress(co);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection timeout setting.
     * @param t The connection timeout for the proxy in milliseconds.
     * @return A proxy with the specified timeout.
     **/
    @Override
    default LightPrx ice_timeout(int t)
    {
        return (LightPrx)_ice_timeout(t);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection ID.
     * @param connectionId The connection ID for the new proxy. An empty string removes the connection ID.
     * @return A proxy with the specified connection ID.
     **/
    @Override
    default LightPrx ice_connectionId(String connectionId)
    {
        return (LightPrx)_ice_connectionId(connectionId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except it's a fixed proxy bound
     * the given connection.@param connection The fixed proxy connection.
     * @return A fixed proxy bound to the given connection.
     **/
    @Override
    default LightPrx ice_fixed(com.zeroc.Ice.Connection connection)
    {
        return (LightPrx)_ice_fixed(connection);
    }

    static String ice_staticId()
    {
        return "::House::Light";
    }
}
