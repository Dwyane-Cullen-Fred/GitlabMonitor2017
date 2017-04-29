package org.bean;

/**
 * 所有方面的贡献集合
 */
public class ContributionAggregation {

    private String student;
    private int allCode;
    private int frontCode;
    private int endCode;
    private int codeFromOthers;
    private int codeToOthers;

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public int getAllCode() {
        return allCode;
    }

    public void setAllCode(int allCode) {
        this.allCode = allCode;
    }

    public int getFrontCode() {
        return frontCode;
    }

    public void setFrontCode(int frontCode) {
        this.frontCode = frontCode;
    }

    public int getEndCode() {
        return endCode;
    }

    public void setEndCode(int endCode) {
        this.endCode = endCode;
    }

    public int getCodeFromOthers() {
        return codeFromOthers;
    }

    public void setCodeFromOthers(int codeFromOthers) {
        this.codeFromOthers = codeFromOthers;
    }

    public int getCodeToOthers() {
        return codeToOthers;
    }

    public void setCodeToOthers(int codeToOthers) {
        this.codeToOthers = codeToOthers;
    }

    public ContributionAggregation(String student, int allCode, int frontCode, int endCode,
                                   int codeFromOthers, int codeToOthers) {
        this.student = student;
        this.allCode = allCode;
        this.frontCode = frontCode;
        this.endCode = endCode;
        this.codeFromOthers = codeFromOthers;
        this.codeToOthers = codeToOthers;
    }
}
