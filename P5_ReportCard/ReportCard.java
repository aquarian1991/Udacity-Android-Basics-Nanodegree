package com.example.jmd.p5_reportcard;
import java.util.Map;

/**
 * Created by JMD on 09/01/17.
 * Modified by JMD on 27/02/17.
 */
public class ReportCard {

    private static final int NO_OF_SUBJECTS = 5;
    private static final int MAX_MARKS = 100;
    private static final int MIN_MARKS = 0;
    private static final int GRADE_A_LOWER_THRESHOLD = 80;
    private static final int GRADE_B_LOWER_THRESHOLD = 60;
    private static final int GRADE_C_LOWER_THRESHOLD = 40;
    private static final int GRADE_D_LOWER_THRESHOLD = 20;

    private String mStudentName;
    private double percentage;

    private enum SubjectCodes{
        English, Mathematics,Science,Hindi,SocialStudies
    }

    //maps individual subjects to corresponding marks.
    private Map<SubjectCodes, Integer> mapSubjectMarks;

    //maps individual subjects to corresponding grades
    private Map<SubjectCodes, String> mapSubjectGrades;


    //to create a ReportCard object with the student name as a param and rest of the values default initialised.
    public ReportCard(String mStudentName) {
        this.mStudentName = mStudentName;
        mapSubjectMarks.put(SubjectCodes.English, new Integer(0));
        mapSubjectMarks.put(SubjectCodes.Mathematics, new Integer(0));
        mapSubjectMarks.put(SubjectCodes.Science, new Integer(0));
        mapSubjectMarks.put(SubjectCodes.Hindi, new Integer(0));
        mapSubjectMarks.put(SubjectCodes.SocialStudies, new Integer(0));
    }

    //common setter function to set the marks for the subjectCode passed in the arguments.
    public boolean setMarks(SubjectCodes subjectCodes, int marks){
        boolean bStatus = false;
        if (marks >= MIN_MARKS && marks <= MAX_MARKS) {
            switch (subjectCodes) {
                case English:
                    mapSubjectMarks.put(SubjectCodes.English, new Integer(marks));
                    break;
                case Mathematics:
                    mapSubjectMarks.put(SubjectCodes.Mathematics, new Integer(marks));
                    break;
                case Science:
                    mapSubjectMarks.put(SubjectCodes.Science, new Integer(marks));
                    break;
                case Hindi:
                    mapSubjectMarks.put(SubjectCodes.Hindi, new Integer(marks));
                    break;
                case SocialStudies:
                    mapSubjectMarks.put(SubjectCodes.SocialStudies, new Integer(marks));
                    break;
            }
            bStatus = true;
        }
        return bStatus;
    }

    //called from setGrade() to compute the equivalent grade for the marks attained in the subject.
    private String computeGrades(int marks){
        String grade = new String("");
        if (marks > GRADE_A_LOWER_THRESHOLD && marks<=MAX_MARKS){
            grade = "A";
        } else if (marks >= GRADE_B_LOWER_THRESHOLD && marks<=GRADE_A_LOWER_THRESHOLD){
            grade = "B";
        } else if (marks >= GRADE_C_LOWER_THRESHOLD && marks<=GRADE_B_LOWER_THRESHOLD){
            grade = "C";
        } else if (marks >= GRADE_D_LOWER_THRESHOLD && marks<=GRADE_C_LOWER_THRESHOLD){
            grade = "D";
        } else if (marks >= MIN_MARKS && marks<=GRADE_C_LOWER_THRESHOLD){
            grade = "E";
        }
        return grade;
    }

    //computes the grade for all the subjects once the respective marks have been set.
    public void setGrade() {
        for (Map.Entry<SubjectCodes, Integer> pair: mapSubjectMarks.entrySet()){
            switch (pair.getKey()){
                case English:
                    mapSubjectGrades.put(SubjectCodes.English, computeGrades(pair.getValue()));
                    break;
                case Mathematics:
                    mapSubjectGrades.put(SubjectCodes.Mathematics, computeGrades(pair.getValue()));
                    break;
                case Science:
                    mapSubjectGrades.put(SubjectCodes.Science, computeGrades(pair.getValue()));
                    break;
                case Hindi:
                    mapSubjectGrades.put(SubjectCodes.Hindi, computeGrades(pair.getValue()));
                    break;
                case SocialStudies:
                    mapSubjectGrades.put(SubjectCodes.SocialStudies, computeGrades(pair.getValue()));
                    break;
            }
        }
    }

    //getter function for overall percentage.
    public double getPercentage() {
        return percentage;
    }

    //setter to compute overall percentage.
    public void setPercentage(double percentage) {
        double sum = 0.0;
        for (Map.Entry<SubjectCodes, Integer> pair: mapSubjectMarks.entrySet()){
            sum += pair.getValue();
        }
        this.percentage = sum/NO_OF_SUBJECTS;
    }

    //prints the marks, grades and the overall percentage in a human readable format. :)
    @Override
    public String toString(){
        return "Overall percentage for the student" + mStudentName + "is" + getPercentage()
                + "\n Subject wise marks and grades are as below"
                + "\n English Marks: "+ mapSubjectMarks.get(SubjectCodes.English) +  ", Grade: " + mapSubjectGrades.get(SubjectCodes.English)
                + "\n Mathematics Marks: "+ mapSubjectMarks.get(SubjectCodes.Mathematics) +  ", Grade: " + mapSubjectGrades.get(SubjectCodes.Mathematics)
                + "\n Science Marks: "+ mapSubjectMarks.get(SubjectCodes.Science) +  ", Grade: " + mapSubjectGrades.get(SubjectCodes.Science)
                + "\n Hindi Marks: "+ mapSubjectMarks.get(SubjectCodes.Hindi) +  ", Grade: " + mapSubjectGrades.get(SubjectCodes.Hindi)
                + "\n SocialStudies Marks: "+ mapSubjectMarks.get(SubjectCodes.SocialStudies) +  ", Grade: " + mapSubjectGrades.get(SubjectCodes.SocialStudies) ;
    }
}
