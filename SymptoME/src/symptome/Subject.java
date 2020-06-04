package symptome;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<Observer> observers = new ArrayList<>();
    private Object state;
    private Boolean notifying;
    
    public Object getState() {
        return state;
    }
    
    public void setState(Object state) {
        this.state = state;
    }
    
    public Subject attach(Observer observer) {
        this.observers.add(observer);
        return this;
    }
    
    public Subject detach(Observer observer) {
        this.observers.remove(observer);
        return this;
    }
    
    public void notifyObservers() {
        List<Observer> currentObservers = new ArrayList<>(observers);
        for (Observer observer : currentObservers) {
            observer.update(this);
        }
    }
}
