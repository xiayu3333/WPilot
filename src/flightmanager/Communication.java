package flightmanager;

import fr.dgac.ivy.Ivy;
import fr.dgac.ivy.IvyClient;
import fr.dgac.ivy.IvyException;
import fr.dgac.ivy.IvyMessageListener;

import java.util.Hashtable;
import javafx.application.Platform;
import view.controls.ControlController;
import view.controls.MapController;
import view.controls.InfoController;

public class Communication {

    private Ivy bus;
    private static Hashtable<String, FlightInfo> aircraft;
    private FlightInfo flight;
    private ControlController controlCtr;
    private MapController mapCtr;
    private InfoController infoCtr;

    public void stop() {
        System.out.println("IvyTest is disconnecting..." + "\n");
        bus.stop();
    }

    private void createNewFlPLN(String[] s) {
        flight = new FlightInfo();
        flight.Initiate(s);
        aircraft.put(s[0], flight);
        //System.out.println(s[0] + " new PLN with route= " + s[12]);
    }

    private void updateFlPLN(String[] s) {
        flight = aircraft.get(s[0]);
        flight.Initiate(s);
        aircraft.replace(s[0], flight);
        //System.out.println(s[0] + " update PLN with route= " + s[12]);
    }

    private void FlightEvent() throws IvyException {
        try {
            bus.bindMsg("PlnEvent Flight=(\\w+) Time=([0-9]{1,2}:[0-9]{2}:[0-9]{2}) "
                    + "CallSign=(\\w+) AircraftType=(\\w+) Ssr=(\\d*) Speed=(\\w+) Rfl=(\\w+) "
                    + "Dep=(\\w+) Arr=(\\w+) Rvsm=(\\w+) Tcas=(\\w+) Adsb=(\\w+) List=(.*)",
                    new IvyMessageListener() {
                // callback
                @Override
                public void receive(IvyClient client, String[] s) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (!aircraft.contains(s[0])) createNewFlPLN(s);
                            else updateFlPLN(s);
                            controlCtr.setFlightInfo(aircraft);
                            mapCtr.setFlightInfo(aircraft);
                            if (s[0].equals("4996")) {
                                mapCtr.drawRoute();
                                controlCtr.setListWaypoint();
                            }
                        }
                    });
                }
            });
        } catch (Exception ex) {
            controlCtr.recordOrder(flight.getCurrentTime() + " Error!!! Can not receive FlightPlan!");
            System.out.println("Communication: " + ex);
        }
    }

    //--------------------------------------------------------------------
    private void MovedEvent() throws IvyException {
        try {
            bus.bindMsg("TrackMovedEvent Flight=(\\w+) CallSign=(\\w+) Ssr=(\\d*).*X=(-?\\d*.\\d*)"
                    + " Y=(-?\\d*.\\d*) Vx=(-?\\d*) Vy=(-?\\d*) Afl=(\\d*) Rate=(-?\\d*)"
                    + " Heading=(\\d*.\\d*) GroundSpeed=(\\d*).*Time=(.*)", new IvyMessageListener() {
                // callback
                @Override
                public void receive(IvyClient client, String[] s) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            flight = aircraft.get(s[0]);
                            flight.TrackMovement(s);
                            aircraft.replace(s[0], flight);
                            controlCtr.setFlightInfo(aircraft);
                            mapCtr.setFlightInfo(aircraft);
                            infoCtr.setFlightInfo(aircraft);
                            if (s[0].equals("4996")) {
                                //System.out.println("Flight 4996 route = " + aircraft.get("4996").getRoute());
                                infoCtr.displayInfo();
                                mapCtr.rotate();
                                mapCtr.move();
                            }
                            //aircraft.get(s[0]).showMovement(); //show both flight 4996 and 5042
                            //aircraft.get("4996").showMovement(); //show only flight 4996
                            //System.out.println(s[0]);
                        }
                    });
                }
            });
        } catch (Exception ex) {
            controlCtr.recordOrder(flight.getCurrentTime() + " Error!!! Can not track position!");
            System.out.println("Communication: " + ex);
        }
    }

    //--------------------------------------------------------------------
    private void BeaconEvent() throws IvyException {
        //track Beacon
        try {
            bus.bindMsg("BeaconEvent Flight=(\\w+) Beacon=(\\w+) Fl=(\\d*) Mode=(\\w+) Time=(.*)",
                    new IvyMessageListener() {
                // callback
                @Override
                public void receive(IvyClient client, String[] s) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            flight = aircraft.get(s[0]);
                            flight.TrackBeacon(s);
                            aircraft.replace(s[0], flight);
                            mapCtr.setFlightInfo(aircraft);
                            infoCtr.setFlightInfo(aircraft);
                            if (s[0].equals("4996")) {
                                controlCtr.recordOrder(flight.getCurrentTime() + " passed " + s[1]);
                                controlCtr.setListWaypoint(); //test
                                infoCtr.displayInfo();
                            }
                        }
                    });
                    //aircraft.get(s[0]).showBeacon(); //show both flight 4996 and 5042
                    //aircraft.get("5042").showBeacon(); //show flight 5042
                    //System.out.println(strings[0]);
                }
            });
        } catch (Exception ex) {
            controlCtr.recordOrder(flight.getCurrentTime() + " Error!!! Can not receive beacon signal!");
            System.out.println("Communication: " + ex);
        }
    }

    //--------------------------------------------------------------------
    private void ReportEvent() throws IvyException {
        try {
            bus.bindMsg("ReportEvent IvyRejeuWPilot (.*$)", new IvyMessageListener() {
                // callback
                @Override
                public void receive(IvyClient client, String[] s) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(flight.getCurrentTime() + " " + s[0]);
                            controlCtr.recordOrder(flight.getCurrentTime() + " " + s[0]);
                        }
                    });
                }
            });
        } catch (Exception ex) {
            controlCtr.recordOrder(flight.getCurrentTime() + " Error!!! Can not receive report signal!");
            System.out.println("Communication: " + ex);
        }
    }

    //ReportEvent IvyRejeuJavaFXApplication Result=OK Info=176 Order=AircraftDirect|4996|LMG
    //--------------------------------------------------------------------
    public void listener() throws IvyException {
        FlightEvent();  //create airplane object
        MovedEvent();   //track movement of every airplances
        BeaconEvent();  //track beacon of every airplances
        ReportEvent();  //track orders result
        /*bus.bindMsg("(.*$)", new IvyMessageListener() {
            // callback
            @Override
            public void receive(IvyClient client, String[] s) {
                System.out.println(s[0]);
            }
        });*/
    }

    public void sender(String s) throws IvyException {
        try {
            System.out.println("Send msg: " + s);
            bus.sendMsg(s);
        } catch (Exception ex) {
            controlCtr.recordOrder(flight.getCurrentTime() + " Error!!! Can not send order!");
            System.out.println("Communication: " + ex);
        }
    }

    public Communication(ControlController controlCtr, MapController mapCtr, InfoController infoCtr) throws IvyException {
        // initialize (set up the bus, name and ready message)
        bus = new Ivy("IvyRejeuWPilot", "IvyRejeuWPilot Ready", null);
        aircraft = new Hashtable<String, FlightInfo>();
        this.controlCtr = controlCtr;
        this.mapCtr = mapCtr;
        this.infoCtr = infoCtr;
        // start the bus on the default domain
        bus.start(null);
        //bus.start("10.0.0.255:1234");
    }
}

/*
PlnEvent Flight=4996 Time=11:02:46 CallSign=AAF312Q AircraftType=A319 Ssr=4315 Speed=436 
Rfl=380 Dep=LPPR Arr=LFPO Rvsm=TRUE Tcas=TA_RA Adsb=NO 
List=AMB V 11:02 0 LUREN V 11:02 0 EPL V 11:02 0 POGOL V 11:02 0 SOREM V 11:02 0 STR V 11:02 0
 */
