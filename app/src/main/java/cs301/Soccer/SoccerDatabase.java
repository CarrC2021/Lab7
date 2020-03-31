package cs301.Soccer;

import android.graphics.drawable.shapes.OvalShape;
import android.util.Log;
import cs301.Soccer.soccerPlayer.SoccerPlayer;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    private HashMap<String, SoccerPlayer> hashMap = new HashMap();

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
    public boolean addPlayer(String firstName, String lastName, int uniformNumber, String teamName) {

        SoccerPlayer player = new SoccerPlayer(firstName,lastName,uniformNumber, teamName);
        for(SoccerPlayer value: hashMap.values()){
            if (value.equals(player)){
                Log.e(makeNameString(firstName,lastName), "addPlayer: failed since player was already in the database");
                return false;
            }
        }

        //did not find the player in the database
        hashMap.put(makeNameString(firstName,lastName), player);
        Log.e(makeNameString(firstName,lastName), "addPlayer: added player to database");
        return true;
    }

    public String makeNameString(String first, String last){
        return first + " ## " + last;
    }

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        if(hashMap.containsKey(makeNameString(firstName,lastName))){
            hashMap.remove(makeNameString(firstName,lastName));
            return true;
        }
        return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {
        if(hashMap.containsKey(makeNameString(firstName,lastName))){
            return hashMap.get(makeNameString(firstName,lastName));
        }
        return null;
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        if(hashMap.containsKey(makeNameString(firstName,lastName))){
            hashMap.get(makeNameString(firstName,lastName)).bumpGoals();
            return true;
        }
        return false;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        if(hashMap.containsKey(makeNameString(firstName,lastName))){
            hashMap.get(makeNameString(firstName,lastName)).bumpAssists();
            return true;
        }
        return false;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        if(hashMap.containsKey(makeNameString(firstName,lastName))){
            hashMap.get(makeNameString(firstName,lastName)).bumpShots();
            return true;
        }
        return false;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        if(hashMap.containsKey(makeNameString(firstName,lastName))){
            hashMap.get(makeNameString(firstName,lastName)).bumpSaves();
            return true;
        }
        return false;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        if(hashMap.containsKey(makeNameString(firstName,lastName))){
            hashMap.get(makeNameString(firstName,lastName)).bumpFouls();
            return true;
        }
        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        if(hashMap.containsKey(makeNameString(firstName,lastName))){
            hashMap.get(makeNameString(firstName,lastName)).bumpYellowCards();
            return true;
        }
        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        if(hashMap.containsKey(makeNameString(firstName,lastName))){
            hashMap.get(makeNameString(firstName,lastName)).bumpRedCards();
            return true;
        }
        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(String teamName) {
        int toReturn = 0;
        if (teamName == null){
            return hashMap.size();
        }
        for(SoccerPlayer value: hashMap.values()){
            if (value.getTeamName().equalsIgnoreCase(teamName)){
                toReturn++;
            }
        }
        return toReturn;
    }

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        int temp = idx;
        if (teamName == null){
            for(SoccerPlayer value: hashMap.values()){
                if (temp == 0){
                    return value;
                } else temp--;
            }
        }
        for(SoccerPlayer value: hashMap.values()){
            if (temp == 0 && value.getTeamName().equalsIgnoreCase(teamName)){
                return value;
            }
            if (value.getTeamName().equalsIgnoreCase(teamName)){
                temp--;
            }
        }
        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
    // read data from file
    @Override
    public boolean readData(File file) {
        return file.exists();
    }

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
    // write data to file
    @Override
    public boolean writeData(File file) {
        return false;
    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see cs301.Soccer.SoccerDB#getTeams()
     */
    // return list of teams
    @Override
    public HashSet<String> getTeams() {
        return new HashSet<String>();
    }

}
