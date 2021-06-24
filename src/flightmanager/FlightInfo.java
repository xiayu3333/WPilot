package flightmanager;

import data.cartography.Point;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Nhut Minh Ngo
 */
public class FlightInfo {
    // flight info variable
    private String flightID;
    private String callSign;
    private String acType;
    private int fl;
    private int rate;
    private int gspeed;
    private int vx;
    private int vy;
    private String dep;
    private String arr;
    private double heading;
    private String waypoint;
    private String mode;
    private String tcas;
    private boolean adsb;
    private int ssr;
    private boolean rvsm;
    private LocalTime startTime;
    private LocalTime currentTime;

    private Point pos;
    private ArrayList<String> route;

//------------------------- Aircraft position ------------------------------
    public void setPosition(String x, String y){
        pos.set(Double.parseDouble(x), Double.parseDouble(y));
    }

    public void setPosition(Point p){
        pos = p;
    }

    public Point getPosition(){
        return pos;
    }

//------------------------------- Route ------------------------------------
    public void setRoute(String s){
        route.clear();
        for(String w:s.split("(\\s)+[A-Z](\\s)+[0-9:]+(\\s)*[0-9]*(\\s)*", 0)){
            route.add(w);
        }
    }

    public ArrayList<String> getRoute(){
        return route;
    }
    
//----------------------------- set ARR -----------------------------------
    public void setArr(String s){
         arr = s;
    }

    public String getArr(){
        return arr;
    }
 
//----------------------------- set DEP -----------------------------------
    public void setDep(String s){
         dep = s;
    }

    public String getDep(){
        return dep;
    }

//----------------------------- Flight ID -----------------------------------
    public void setFlightID(String s){
        flightID = s;
    }

    public String getFlightID(){
        return flightID;
    }

//----------------------------- CallSign -----------------------------------
    public void setCallSign(String s){
         callSign = s;
    }

    public String getCallSign(){
         return callSign;
    }

//----------------------------- A/C Type -----------------------------------
    public void setACType(String s){
        acType = s;
    }

    public String getACType(){
        return acType;
    }

//----------------------------- Speed -----------------------------------
    public void setVx(String s){
        vx = Integer.parseInt(s);
    }

    public int getVx(){
        return vx;
    }

    public void setVy(String s){
        vy = Integer.parseInt(s);
    }

    public int getVy(){
        return vy;
    }

    public void setGroundSpeed(String s){
        gspeed = Integer.parseInt(s);
    }

    public int getGroundSpeed(){
        return gspeed;
    }

//----------------------------- FL -----------------------------------
    public void setFL(String s){
        fl = Integer.parseInt(s);
    }

    public int getFL(){
        return fl;
    }

//----------------------------- Heading -----------------------------------
    public void setHeading(String s){
        heading = Double.parseDouble(s);
    }

    public double getHeading(){
        return heading;
    }

//----------------------------- SSR -----------------------------------
    public void setSsr(String s){
        ssr = Integer.parseInt(s);
    }

    public int getSsr(){
        return ssr;
    }
//----------------------------- RATE -----------------------------------
    public void setRate(String s){
        rate = Integer.parseInt(s);
    }

    public int getRate(){
        return rate;
    }
//----------------------------- TCAS -----------------------------------
    public void setTCAS(String s){
        tcas = s;
    }

    public String getTCAS(){
        return tcas;
    }

//----------------------------- ADS-B -----------------------------------
    public void setADSB(String s){
        adsb = s.equals("YES")?true:false;
    }

    public boolean getADSB(){
        return adsb;
    }

//----------------------------- RVSM -----------------------------------
    public void setRVSM(String s){
        rvsm = s.equals("FALSE")?false:true;
    }

    public boolean getRVSM(){
        return rvsm;
    }

//----------------------------- Start time -----------------------------------
    public void setStartTime(String s){
        startTime = LocalTime.parse(s);
    }

    public LocalTime getStartTime(){
        return startTime;
    }
    
//----------------------------- Start time -----------------------------------
    public void setCurrentTime(String s){
        currentTime = LocalTime.parse(s);
    }

    public LocalTime getCurrentTime(){
        return currentTime;
    }
    
//-------------------------------- Beacon ---------------------------------
    public void setWaypoint(String s) {
        waypoint = s;
    }

    public String getWaypoint() {
        return waypoint;
    }
    
//-------------------------------- From-to ---------------------------------

    public String getFrom(){
        if(waypoint.equals("")) return dep;
        else return waypoint;
    }
    
    public String getTo(){
        int i;
        if(waypoint.equals("")) return route.get(0);
        else if(waypoint.equals(route.get(route.size()-1))) return arr;
        for(i=0;i<route.size()-1;i++)
            if (waypoint.equals(route.get(i))) break;
        return route.get(++i);
    }
//-------------------------------- Mode ---------------------------------
    public void setMode(String s) {
        mode = s;
    }

    public String getMode(String s) {
        return mode;
    }
//-------------------------------- Display ---------------------------------
    public void showInit(){
        System.out.print("FlightID=" + flightID);
        System.out.print(", CallSign=" + callSign);
        System.out.print(", AcType=" + acType);
        System.out.print(", SSR=" + ssr);
        System.out.print(", GSpeed=" + gspeed);
        System.out.print(", FL=" + fl);
        System.out.print(", RVSM=" + rvsm);
        System.out.print(", TCAS=" + tcas);
        System.out.print(", ADS-B=" + adsb);
        System.out.print(", StartTime=" + startTime);
        System.out.print(", DEP=" + dep);
        System.out.println(", ARR=" + arr);

        System.out.print("Route: ");
        for(String w:route){
            System.out.print(w + " ");
        }

        System.out.println("");
    }

    public void showMovement(){
        System.out.print("FlightID=" + flightID);
        System.out.print(", SSR=" + ssr);
        System.out.print(", Position=" + pos.toString());
        System.out.print(", Speed: Vx=" + vx + ", Vy=" + vy);
        System.out.print(", FL=" + fl);
        System.out.print(", Rate=" + rate);
        System.out.print(", Heading=" + heading);
        System.out.println(", GroundSpeed=" + gspeed);
    }

    public void showBeacon(){
        System.out.print("FlightID=" + flightID);
        System.out.print(", Waypoint=" + waypoint);
        System.out.print(", FL=" + fl);
        System.out.println(", Mode=" + mode);
    }

//----------------------------------------------------------------------------
    public FlightInfo(){
       flightID = "";
       callSign = "";
       acType = "";
       fl = 0;
       vx = 0;
       vy = 0;
       gspeed = 0;
       tcas = "";
       adsb = false;
       ssr = 0;
       rvsm = false;
       arr = "";
       dep = "";
       rate = 0;
       startTime = LocalTime.of(0,0,0);
       currentTime = LocalTime.of(0,0,0);
       route = new ArrayList<String>();
       pos = new Point(0,0);
       waypoint = "";
    }
    
 //----------------------------------------------------------------------------
     public void Initiate(String[] s){
        int i=0;
        setFlightID(s[i++]);
        setCurrentTime(s[i]);
        setStartTime(s[i++]);
        setCallSign(s[i++]);
        setACType(s[i++]);
        setSsr(s[i++]);
        setGroundSpeed(s[i++]);
        setFL(s[i++]);
        setDep(s[i++]);
        setArr(s[i++]);
        setRVSM(s[i++]);
        setTCAS(s[i++]);
        setADSB(s[i++]);
        setRoute(s[i++]);
    }
    
    public void TrackMovement(String[] s){  
        int i=1;
        /*for(i=0;i<=8;i++)
            System.out.print(s[i] + ", ");
        System.out.println("");*/
        setCallSign(s[i++]);
        setSsr(s[i++]);
        setPosition(s[i++],s[i++]);
        setVx(s[i++]);
        setVy(s[i++]);
        setFL(s[i++]);
        setRate(s[i++]);
        setHeading(s[i++]);
        setGroundSpeed(s[i++]);
        setCurrentTime(s[i++]);
    }

    public void TrackBeacon(String[] s){
        int i=1;
        setWaypoint(s[i++]);
        setFL(s[i++]);
        setMode(s[i++]);
        setCurrentTime(s[i++]);
    }
}

/*PlnEvent Flight=4996 Time=11:02:18 CallSign=AAF312Q AircraftType=A319 Ssr=4315 Speed=436 Rfl=380
Dep=LPPR Arr=LFPO Rvsm=TRUE Tcas=TA_RA Adsb=NO List=AMB V 11:03 255 TIRET V 11:07 240 POLLY V 11:13 240
LUREN V 11:18 240 EPL V 11:29 240 POGOL V 11:32 240 SOREM V 11:34 240 STR V 11:36 240*/


/*TrackMovedEvent Flight=4996 CallSign=AAF312Q Ssr=4315 Sector=-- Layers=I
X=36.50 Y=13.53 Vx=235 Vy=447 Afl=280 Rate=0 Heading=28 GroundSpeed=505 Tendency=0 Time=11:02:18*/

/*BeaconEvent Flight=4996 Beacon=AMB Fl=255 Mode=Vertical Time=11:03:54*/