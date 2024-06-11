package ice.server;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

public class IceServer {

    public void t1(String[] args) {
        int status = 0;
        Communicator communicator = null;

        try {
            communicator = Util.initialize(args);

            ObjectAdapter adapter = communicator.createObjectAdapter("Adapter1");
//            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("Adapter1", "tcp -h 127.0.0.2 -p 10000 : udp -h 127.0.0.2 -p 10000");

            OvenI ovenServant1 = new OvenI();
            LightI lightServant1 = new LightI();
            RGBLightI lightServant2 = new RGBLightI();

            adapter.add(ovenServant1, new Identity("oven1", "oven"));
            adapter.add(lightServant1, new Identity("normalLight", "light"));
            adapter.add(lightServant2, new Identity("RGBLight", "light"));

            adapter.activate();

            System.out.println("Entering event processing loop...");

            communicator.waitForShutdown();

        } catch (Exception e) {
            e.printStackTrace(System.err);
            status = 1;
        }
        if (communicator != null) {
            try {
                communicator.destroy();
            } catch (Exception e) {
                e.printStackTrace(System.err);
                status = 1;
            }

        }
        System.exit(status);
    }

    public static void main(String[] args) {
        IceServer app = new IceServer();
        app.t1(args);
    }
}
