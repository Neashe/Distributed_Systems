#ifndef HOUSE_ICE
#define HOUSE_ICE

module House
{
exception InputException{
    string reason = "Ivalid input";
};
    exception DoorOpenException {
    string reason = "Doors should be closed";
    };
    exception UnsafeInteriorExeption {
    string reason = "There is unexpected item in the oven";
    };
    exception WorkingOvenException {
    string reason = "The oven is currently working";
    };

    enum ovenMode{
        FANOVEN,
        GRILL,
        BOTTOMHEATING,
        CONVENTIONALHEATING,
    };

    enum ovenInterior{
        EMPTY,
        SAFE,
        UNKNOWN
    };

    interface Oven
    {
        void changeInterior(ovenInterior interior);
        void toggleDoor() throws WorkingOvenException;
        bool changeSettings(optional(1) int temp, optional(2) ovenMode mode, optional(3) int time);
        void turnOff();
        void turnOn(int temp, ovenMode mode, optional(4) int time) throws DoorOpenException, UnsafeInteriorExeption;
        bool isTurnedOn();
        string getInfo();
    };

    enum LightMode {
        NORMAL,
        NIGHTMODE,
        PARTYMODE,
        READINGMODE
    }

    interface Light
    {
        void turnOn();
        void turnOff();
        void setBrightness(int brightness);
        string automaticTurnOff(int time);
        string getInfo(); // returns overall information about current light state

    }
    interface RGBLight extends Light{
        void setColor(int red, int green, int blue) throws InputException;
    }

};

#endif