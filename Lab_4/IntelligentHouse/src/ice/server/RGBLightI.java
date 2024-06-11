package ice.server;

import House.InputException;
import House.RGBLight;
import com.zeroc.Ice.Current;

public class RGBLightI implements RGBLight{
    int[] RGBValues = {0,0,0};
    boolean isTurnedOn = false;
    int brightness = 10;

    @Override
    public void turnOn(Current current) {
        this.isTurnedOn = true;
        System.out.println("[RGB LIGHT]: turn on");
    }

    @Override
    public void turnOff(Current current) {
        this.isTurnedOn = false;
        System.out.println("[RGB LIGHT]: turn off");
    }

    @Override
    public void setBrightness(int brightness, Current current) {
        this.brightness = brightness;
        System.out.println("[RGB LIGHT]: brightness set to " + brightness);
    }

    @Override
    public String automaticTurnOff(int time, Current current) {
        this.turnOn(current);
        System.out.println("[RGB LIGHT]: scheduled lights");
        try{
            Thread.sleep(1000*time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[RGB LIGHT]: automatic turn off");
        return("Lights have automatically turned off");
    }

    public String getInfo(Current current) {
        String info = "RGB LIGHT INFO:\n";
        if (isTurnedOn) {
            info += "State: Turned On\n";
            info += "Brightness: " + brightness + "\n";
        } else {
            info += "State: Turned Off\n";
        }
        info += "RGB Values: " + RGBValues[0] + ", " + RGBValues[1] + ", " + RGBValues[2] + "\n";
        return info;
    }

    @Override
    public void setColor(int red, int green, int blue, Current current) throws InputException {
        if (red >= 0 && red <= 255 && green >= 0 && green <= 255 && blue >= 0 && blue <= 255) {
            RGBValues[0] = red;
            RGBValues[1] = green;
            RGBValues[2] = blue;
            System.out.println("RGB values set to: " + red + ", " + green + ", " + blue);
        } else {
            throw new InputException("Invalid RGB values. Values must be in the range of 0 to 255.");
        }
    }
}
