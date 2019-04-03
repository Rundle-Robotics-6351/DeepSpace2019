// This may be useless, but I don't think so
package frc.robot;

public class SolenoidFunction {
    private boolean state_bool;
    public String state; // Used for the programmer
    private String extendedState;
    private String retractedState;
    private String defaultState;
    private String temp_string;
    public SolenoidFunction(String extendedState, String retractedState, String defaultState) {
        this.extendedState = extendedState;
        this.retractedState = retractedState;
        this.defaultState = defaultState;
        this.state = this.defaultState;
    }

    public boolean getBoolean() {
        return this.state_bool;
    }
    public void setDesiredState(String value) {
        this.extendedState = value;
    }
    public void setOtherState(String value) {
        this.retractedState = value;
    }
    public void swapStates() {
        this.temp_string = this.extendedState;
        this.extendedState = this.retractedState;
        this.retractedState = this.temp_string;
        this.temp_string = "";
    }
    public boolean check() {
        if (this.state == this.extendedState) {
            return true;
        } else {
            return false;
        }
    }

  
}