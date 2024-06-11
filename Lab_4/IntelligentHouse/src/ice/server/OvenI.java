package ice.server;

import House.*;

import java.util.*;

import com.zeroc.Ice.Current;


public class OvenI implements Oven {
        int temp = 0;
        ovenMode mode = null;
        boolean isClosed = true;
        ovenInterior interior = ovenInterior.EMPTY;
        Timer timer;
        boolean isTurnedOn = false;

    @Override
    public void changeInterior(ovenInterior interior, Current current) {
        this.interior = interior;
    }

    @Override
    public void toggleDoor(Current current) throws WorkingOvenException {
        if (this.isTurnedOn){
            throw new WorkingOvenException();
        }
        this.isClosed = !this.isClosed;
        if (isClosed) {
            System.out.println("OVEN: DOORS CLOSED");
        }
        else{
            System.out.println("OVEN: DOORS OPEN");
        }
    }

    @Override
    public boolean changeSettings(OptionalInt temp, Optional<ovenMode> mode, OptionalInt time, Current current) {
        if (!this.isTurnedOn){
            System.out.println("You can change parameters only on working oven");
            return false;
        }
        else{
            if(temp.isPresent()){
                this.temp = temp.getAsInt();
                System.out.println("OVEN: Temperature was set to "+ this.temp + " Celsius degrees");
            }
            if (mode.isPresent()){
                this.mode = mode.get();
                System.out.println("OVEN: Current mode: "+ this.mode);
            }
            if (timer != null){
                timer.cancel();
            }
            if (time.isPresent()){
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("OVEN: finished");
                        turnOff(current);
                    }
                }, time.getAsInt()* 1000);
            }
        }
        return true;
    }

    @Override
    public void turnOff(Current current) {
        this.temp = 0;
        this.mode = null;
        if (timer != null){
            timer.cancel();
            System.out.println("Timer has been canceled");
        }
        this.isTurnedOn = false;
        System.out.println("OVEN: TURNED OFF");
    }

    @Override
    public void turnOn(int temp, ovenMode mode, OptionalInt time, Current current) throws DoorOpenException, UnsafeInteriorExeption {
        if(!isClosed){
            DoorOpenException exception = new DoorOpenException();
            System.out.println("ERROR: " +exception.reason);
            throw exception;
        }
        if (!isTurnedOn){
            if (interior == ovenInterior.UNKNOWN){
                UnsafeInteriorExeption exception = new UnsafeInteriorExeption();
                System.out.println("ERROR: " +exception.reason);
                throw exception;
            }
            System.out.println("OVEN: turn on");
            this.temp = temp;
            this.mode = mode;
            this.isTurnedOn = true;
            if (time.isPresent()){
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("OVEN: TIMER HAS FINISHED");
                        turnOff(current);
                    }
                }, time.getAsInt()* 1000);
            }
        }

    }

    @Override
    public boolean isTurnedOn(Current current) {
        return isTurnedOn;
    }

    @Override
    public String getInfo(Current current) {
        String info = "OVEN INFO:\n";
        if (isTurnedOn(current)) {
            info += "State: Turned On\n";
            info += "Temperature: " + temp + " Celsius degrees\n";
            info += "Mode: " + mode + "\n";
        } else {
            info += "State: Turned Off\n";
        }
        if (this.isClosed){
            info += "Doors: Closed\n";
        }
        else{
            info += "doors: Open\n";
        }
        return info;
    }
}
