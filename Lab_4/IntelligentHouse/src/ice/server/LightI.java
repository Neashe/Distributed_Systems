package ice.server;

import House.Light;
import com.zeroc.Ice.Current;

import java.util.Timer;
import java.util.TimerTask;

public class LightI implements Light {
    boolean isTurnedOn = false;
    int brightness = 0;
    Timer timer;

    @Override
    public void turnOn(Current current) {
        this.isTurnedOn = true;
        System.out.println("[LIGHT]: turn on");
    }

    @Override
    public void turnOff(Current current) {
        this.isTurnedOn = false;
        System.out.println("[LIGHT]: turn off");
    }

    @Override
    public void setBrightness(int brightness, Current current) {
        this.brightness = brightness;
        System.out.println("[RGB LIGHT]: brightness set to " + brightness);
    }

    @Override
    public String automaticTurnOff(int time, Current current) {
        this.turnOn(current);
        System.out.println("[LIGHT]: scheduled lights");
        try{
            Thread.sleep(1000*time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[LIGHT]: automatic turn off");
        return("Lights have automatically turned off");
    }

    public String getInfo(Current current) {
        String info = "LIGHT INFO:\n";
        if (isTurnedOn) {
            info += "State: Turned On\n";
            info += "Brightness: " + brightness + "\n";
        } else {
            info += "State: Turned Off\n";
        }
        return info;
    }
}
