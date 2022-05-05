package UI;

import Mill.GameException;
import Mill.StatusException;

import java.io.*;

public class MillUI {

    private static final String PRINT = "print";
    private static final String EXIT = "exit";
    private static final String CONNECT = "connect";
    private static final String OPEN = "open";
    private static final String SET = "set";
    private static String[] playerNames= new String[2];
    private final PrintStream outStream;
    private final String playerName;
    private final BufferedReader inBufferedReader;



    public static void main (String[] args) {
        playerNames[0]="Thomas";
        System.out.println("Welcome to Mill!");

        MillUI userCmd = new MillUI(playerNames[0], System.out, System.in);

         userCmd.printUsage();
         userCmd.runCommandLoop();
    }
    public MillUI(String playerName, PrintStream os, InputStream is) {
        this.playerName = playerName;
        this.outStream = os;
        this.inBufferedReader = new BufferedReader(new InputStreamReader(is));
    }


    public void printUsage() {
        StringBuilder b = new StringBuilder();

        b.append("\n");
        b.append("\n");
        b.append("valid commands:");
        b.append("\n");
        b.append(CONNECT);
        b.append(".. connect as tcp client");
        b.append("\n");
        b.append(OPEN);
        b.append(".. open port become tcp server");
        b.append("\n");
        b.append(PRINT);
        b.append(".. print board");
        b.append("\n");
        b.append(SET);
        b.append(".. set a piece");
        b.append("\n");
        b.append(EXIT);
        b.append(".. exit");

        this.outStream.println(b.toString());
    }
    public void runCommandLoop() {
        boolean again = true;

        while (again) {
            boolean rememberCommand = true;
            String cmdLineString = null;

            try {
                // read user input
                cmdLineString = inBufferedReader.readLine();

                // finish that loop if less than nothing came in
                if (cmdLineString == null) break;

                // trim whitespaces on both sides
                cmdLineString = cmdLineString.trim();

                // extract command
                int spaceIndex = cmdLineString.indexOf(' ');
                spaceIndex = spaceIndex != -1 ? spaceIndex : cmdLineString.length();

                // got command string
                String commandString = cmdLineString.substring(0, spaceIndex);

                // extract parameters string - can be empty
                String parameterString = cmdLineString.substring(spaceIndex);
                parameterString = parameterString.trim();

                // start command loop
                switch (commandString) {
                    case PRINT:
                        this.doPrint();
                        break;
                    case CONNECT:
                        this.doConnect(parameterString);
                        break;
                    case OPEN:
                        this.doOpen();
                        break;
                    case SET:
                        this.doSet(parameterString);
                        // redraw
                        this.doPrint();
                        break;
                    case "q": // convenience
                    case EXIT:
                        again = false; this.doExit(); break; // end loop

                    default:
                        this.outStream.println("unknown command:" + cmdLineString);
                        this.printUsage();
                        rememberCommand = false;
                        break;
                }
            } catch (IOException ex) {
                this.outStream.println("cannot read from input stream - fatal, give up");
                try {
                    this.doExit();
                } catch (IOException e) {
                    // ignore
                }
            } catch (StatusException ex) {
                this.outStream.println("wrong status: " + ex.getLocalizedMessage());
            } catch (GameException ex) {
                this.outStream.println("game exception: " + ex.getLocalizedMessage());
            } catch (RuntimeException ex) {
                this.outStream.println("runtime problems: " + ex.getLocalizedMessage());
            }
        }
    }

    private void doExit ()  throws IOException {
    }

    private void doSet (String parameterString) throws StatusException, GameException {

    }

    private void doOpen() {
    }

    private void doConnect(String parameterString) {
    }

    private void doPrint() {
    }

    public int mergeStringX(String xKoord) throws IOException {
        int x=0;
        switch (xKoord){
            case "A":
                x=0;
                break;
            case "B":
                x=1;
                break;
            case "C":
                x=2;
                break;
            default:
                throw new IOException("Eingabe der X Koordinate außerhalb des Feldes!");
        }
        return x;
    }
    public void checkY(int y) throws IOException {
        if (y<0 || y>2){
            throw new IOException("Eingabe der Y Koordinate außerhalb des Feldes!");
        }
    }
}
