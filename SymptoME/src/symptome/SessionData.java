package symptome;

public class SessionData {
    private static final SessionData instance_ = new SessionData();
    static public SessionData instance(){
        return instance_;
    }
    
    String username = null;
    private SessionData() { ; }
    
    public String getUsername() {
        return username;
    }
    
    public SessionData setUsername(String newUsername) {
        this.username = newUsername;
        return instance_;
    }
}
