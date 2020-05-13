package symptome;

import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

// Builder Pattern: Product
public class Report {
    private String username;
    private java.sql.Date todaysDate;
    private JSlider feelingSlider;
    
    private JCheckBox coughBox;
    private JCheckBox diffBreathingBox;
    private JCheckBox feverBox;
    private JCheckBox painBox;
    private JCheckBox soreThroatBox;
    private JCheckBox lossBox;
    
    private JRadioButton outsideRBY;
    private JRadioButton outsideRBN;
    private JRadioButton testedRBY;
    private JRadioButton testedRBN;
    private JRadioButton resultRBY;
    private JRadioButton resultRBN;
    private JRadioButton resultRBNA;
    
    // Builder Pattern: Builder 
    public static class ReportBuilder {
        private String username;
        private java.sql.Date todaysDate;
        private JSlider feelingSlider;
        
        private JCheckBox coughBox;
        private JCheckBox diffBreathingBox;
        private JCheckBox feverBox;
        private JCheckBox painBox;
        private JCheckBox soreThroatBox;
        private JCheckBox lossBox;

        private JRadioButton outsideRBY;
        private JRadioButton outsideRBN;
        private JRadioButton testedRBY;
        private JRadioButton testedRBN;
        private JRadioButton resultRBY;
        private JRadioButton resultRBN;
        private JRadioButton resultRBNA;
        
        public ReportBuilder withUsername(String username){
            this.username = username;
            return this;
        }
        public ReportBuilder withDate(java.sql.Date todaysDate){
            this.todaysDate = todaysDate;
            return this;
        }
        public ReportBuilder withFeeling(JSlider feelingSlider){
            this.feelingSlider = feelingSlider;
            return this;
        }
        public ReportBuilder withCough(JCheckBox coughBox){
            this.coughBox = coughBox;
            return this;
        }
        public ReportBuilder withDiffBreathing(JCheckBox diffBreathingBox){
            this.diffBreathingBox = diffBreathingBox;
            return this;
        }
         public ReportBuilder withFever(JCheckBox feverBox){
            this.feverBox = feverBox;
            return this;
        }
        public ReportBuilder withPain(JCheckBox painBox){
            this.painBox = painBox;
            return this;
        }
        public ReportBuilder withSoreThroat(JCheckBox soreThroatBox){
            this.soreThroatBox = soreThroatBox;
            return this;
        }
        public ReportBuilder withLoss(JCheckBox lossBox){
            this.lossBox = lossBox;
            return this;
        }
        public ReportBuilder withOutsideRBY(JRadioButton outsideRBY){
            this.outsideRBY = outsideRBY;
            return this;
        }
        public ReportBuilder withOutsideRBN(JRadioButton outsideRBN){
            this.outsideRBN = outsideRBN;
            return this;
        }
        public ReportBuilder withTestedRBY(JRadioButton testedRBY){
            this.testedRBY = testedRBY;
            return this;
        }
        public ReportBuilder withTestedRBN(JRadioButton testedRBN){
            this.testedRBN = testedRBN;
            return this;
        }
        public ReportBuilder withResultRBY(JRadioButton resultRBY){
            this.resultRBY = resultRBY;
            return this;
        }
        public ReportBuilder withResultRBN(JRadioButton resultRBN){
            this.resultRBN = resultRBN;
            return this;
        }
        public ReportBuilder withResultRBNA(JRadioButton resultRBNA){
            this.resultRBNA = resultRBNA;
            return this;
        }
        public Report build(){
            return new Report(this);
        }
    }
    // Report constructor
    private Report(ReportBuilder builder){
        this.username = builder.username;
        this.todaysDate = builder.todaysDate;
        this.feelingSlider = builder.feelingSlider;
        this.coughBox = builder.coughBox;
        this.diffBreathingBox = builder.diffBreathingBox;
        this.feverBox = builder.feverBox;
        this.painBox = builder.painBox;
        this.soreThroatBox = builder.soreThroatBox;
        this.lossBox = builder.lossBox;
        this.outsideRBY = builder.outsideRBY;
        this.outsideRBN = builder.outsideRBN;
        this.testedRBY = builder.testedRBY;
        this.testedRBN = builder.testedRBN;
        this.resultRBY = builder.resultRBY;
        this.resultRBN = builder.resultRBN;
        this.resultRBNA = builder.resultRBNA;
    }
    public String getUsername(){
        return this.username;
    }
    public java.sql.Date getDate(){
        return this.todaysDate;
    }
    public JSlider getFeelingSlider(){
        return this.feelingSlider;
    }
    public JCheckBox getCoughBox(){
        return this.coughBox;
    }
    public JCheckBox getDiffBreathingBox(){
        return this.diffBreathingBox;
    }
     public JCheckBox getFeverBox(){
        return this.feverBox;
    }
    public JCheckBox getPainBox(){
        return this.painBox;
    }
    public JCheckBox getSoreThroatBox(){
        return this.soreThroatBox;
    }
    public JCheckBox getLossBox(){
        return this.lossBox;
    }
    public JRadioButton getOutsideRBY(){
        return this.outsideRBY;
    }
    public JRadioButton getOutsideRBN(){
        return this.outsideRBN;
    }
    public JRadioButton getTestedRBY(){
        return this.testedRBY;
    }
    public JRadioButton getTestedRBN(){
        return this.testedRBN;
    }
    public JRadioButton getResultRBY(){
        return this.resultRBY;
    }
    public JRadioButton getResultRBN(){
        return this.resultRBN;
    }
    public JRadioButton getResultRBNA(){
        return this.resultRBNA;
    }
}
