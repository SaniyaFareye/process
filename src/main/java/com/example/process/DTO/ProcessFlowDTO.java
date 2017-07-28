package com.example.process.DTO;

/**
 * Created by xplode on 28/7/17.
 */
public class ProcessFlowDTO {
    private String title;
    private String code;
    private String conditionExpression;
    private String nextTrueCondition;
    private String nextFalseCondition;

    public ProcessFlowDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getConditionExpression() {
        return conditionExpression;
    }

    public void setConditionExpression(String conditionExpression) {
        this.conditionExpression = conditionExpression;
    }

    public String getNextTrueCondition() {
        return nextTrueCondition;
    }

    public void setNextTrueCondition(String nextTrueCondition) {
        this.nextTrueCondition = nextTrueCondition;
    }

    public String getNextFalseCondition() {
        return nextFalseCondition;
    }

    public void setNextFalseCondition(String nextFalseCondition) {
        this.nextFalseCondition = nextFalseCondition;
    }
}
