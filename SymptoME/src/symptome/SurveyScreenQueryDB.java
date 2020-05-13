package symptome;

import java.sql.SQLException;
import java.util.ArrayList;

public class SurveyScreenQueryDB extends QueryDB {
    
    public SurveyScreenQueryDB() throws SQLException{
        super();
    }
    
    public void addReport(Report report){
        executeUpdateQuery(buildReportEntry(report));
    }
    
    public boolean checkForCompletedSurvey(String username, java.sql.Date todaysDate){
        ArrayList<String> results = executeReadQuery("SELECT * FROM Reports WHERE reportDate = TO_DATE('" + todaysDate + "','YYYY-MM-DD') AND username ='" + username + "'");
        return (results == null || results.isEmpty()); // daily survey not yet submitted 
    }
    
    public String buildReportEntry(Report report){
        int cough = 0;
        int diffBreathing = 0;
        int fever = 0;
        int musclePain = 0;
        int soreThroat = 0;
        int lostTasteSmell = 0;
        int goneOut = 0; 
        int beenTested = 0;
        int testResult = 0;
        
        java.sql.Date reportDate = report.getDate();
        String username = report.getUsername();
        int feelingRating = report.getFeelingSlider().getValue();
        if (report.getCoughBox().isSelected()){
            cough = 1;
        }
        if (report.getDiffBreathingBox().isSelected()){
            diffBreathing = 1;
        }
        if (report.getFeverBox().isSelected()){
            fever = 1;
        }
        if (report.getPainBox().isSelected()){
            musclePain = 1;
        }
        if (report.getSoreThroatBox().isSelected()){
            soreThroat = 1;
        }
        if (report.getLossBox().isSelected()){
            lostTasteSmell = 1;
        }
        if (report.getOutsideRBY().isSelected()){
            goneOut = 1;
        }
        if (report.getTestedRBY().isSelected()){
            beenTested = 1;
        }
        if (report.getResultRBY().isSelected()){
            testResult = 1;
        }
        if (report.getResultRBNA().isSelected()){
            testResult = 2;
        }
        
        return ("INSERT INTO Reports (reportDate, username, feelingRating, cough, diffBreathing, " +
                "fever, musclePain, soreThroat, lostTasteSmell, goneOut, beenTested, testResult) " +
                "VALUES (TO_DATE('" + reportDate + "','YYYY-MM-DD'), '" + username + "', " + feelingRating + 
                ", " + cough + ", " + diffBreathing + ", " + fever + ", " + musclePain + ", " + soreThroat +
                ", " + lostTasteSmell + ", " + goneOut + ", " + beenTested + ", " + testResult + ")" );
              
    }     
}
