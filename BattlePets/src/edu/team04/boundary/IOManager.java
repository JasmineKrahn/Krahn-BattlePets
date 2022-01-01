package edu.team04.boundary;

/**
 * IO Manager manages the calls for all the inputs and outputs for the battle pets game. Every call goes through IO Manager first.
 *
 * */
public class IOManager {
    private static IOManager ioManager;
    private Inputtable inputGetter = new InputGetter();
    private Outputtable outputGetter = new OutputGetter();

    private IOManager(){}

    /**
     * Constructor to set input getter and output getter objects to make calls.
     * @param inputGetter the input getter object to get inputs
     * @param outputGetter the output getter object to get the outputs
     * */
    public IOManager(Inputtable inputGetter, Outputtable outputGetter){
        inputGetter = this.inputGetter;
        outputGetter = this.outputGetter;
    }

    /**
     * Get method for inputtable object
     * */
    public Inputtable getInputtable() {
        return inputGetter;
    }

    /**
     * Get method for outputtable object
     * */
    public Outputtable getOutputtable() {
        return outputGetter;
    }

    /**
     * Modelled after the singleton pattern to only instantiate the object when it is called
     * */
    public static IOManager getInstance() {
        if(ioManager == null){
            ioManager = new IOManager();
        }
        return ioManager;
    }
}
