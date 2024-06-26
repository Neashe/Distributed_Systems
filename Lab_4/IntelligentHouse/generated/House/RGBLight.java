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

public interface RGBLight extends Light
{
    void setColor(int red, int green, int blue, com.zeroc.Ice.Current current)
        throws InputException;

    /** @hidden */
    static final String[] _iceIds =
    {
        "::House::Light",
        "::House::RGBLight",
        "::Ice::Object"
    };

    @Override
    default String[] ice_ids(com.zeroc.Ice.Current current)
    {
        return _iceIds;
    }

    @Override
    default String ice_id(com.zeroc.Ice.Current current)
    {
        return ice_staticId();
    }

    static String ice_staticId()
    {
        return "::House::RGBLight";
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
     * @throws com.zeroc.Ice.UserException -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_setColor(RGBLight obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        int iceP_red;
        int iceP_green;
        int iceP_blue;
        iceP_red = istr.readInt();
        iceP_green = istr.readInt();
        iceP_blue = istr.readInt();
        inS.endReadParams();
        obj.setColor(iceP_red, iceP_green, iceP_blue, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /** @hidden */
    final static String[] _iceOps =
    {
        "automaticTurnOff",
        "getInfo",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "setBrightness",
        "setColor",
        "turnOff",
        "turnOn"
    };

    /** @hidden */
    @Override
    default java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceDispatch(com.zeroc.IceInternal.Incoming in, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        int pos = java.util.Arrays.binarySearch(_iceOps, current.operation);
        if(pos < 0)
        {
            throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return Light._iceD_automaticTurnOff(this, in, current);
            }
            case 1:
            {
                return Light._iceD_getInfo(this, in, current);
            }
            case 2:
            {
                return com.zeroc.Ice.Object._iceD_ice_id(this, in, current);
            }
            case 3:
            {
                return com.zeroc.Ice.Object._iceD_ice_ids(this, in, current);
            }
            case 4:
            {
                return com.zeroc.Ice.Object._iceD_ice_isA(this, in, current);
            }
            case 5:
            {
                return com.zeroc.Ice.Object._iceD_ice_ping(this, in, current);
            }
            case 6:
            {
                return Light._iceD_setBrightness(this, in, current);
            }
            case 7:
            {
                return _iceD_setColor(this, in, current);
            }
            case 8:
            {
                return Light._iceD_turnOff(this, in, current);
            }
            case 9:
            {
                return Light._iceD_turnOn(this, in, current);
            }
        }

        assert(false);
        throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
    }
}
