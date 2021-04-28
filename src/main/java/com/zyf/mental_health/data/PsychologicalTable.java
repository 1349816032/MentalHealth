package com.zyf.mental_health.data;

import javax.persistence.*;

@Entity
@Table(name = "psychological_table", schema = "mentalhealth", catalog = "")
public class PsychologicalTable {
    private String psychologicalTableName;
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;

    @Id
    @Column(name = "psychological_table_name")
    public String getPsychologicalTableName() {
        return psychologicalTableName;
    }

    public void setPsychologicalTableName(String psychologicalTableName) {
        this.psychologicalTableName = psychologicalTableName;
    }

    @Basic
    @Column(name = "question")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Basic
    @Column(name = "answer_A")
    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    @Basic
    @Column(name = "answer_B")
    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    @Basic
    @Column(name = "answer_C")
    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PsychologicalTable that = (PsychologicalTable) o;

        if (psychologicalTableName != null ? !psychologicalTableName.equals(that.psychologicalTableName) : that.psychologicalTableName != null)
            return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        if (answerA != null ? !answerA.equals(that.answerA) : that.answerA != null) return false;
        if (answerB != null ? !answerB.equals(that.answerB) : that.answerB != null) return false;
        if (answerC != null ? !answerC.equals(that.answerC) : that.answerC != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = psychologicalTableName != null ? psychologicalTableName.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (answerA != null ? answerA.hashCode() : 0);
        result = 31 * result + (answerB != null ? answerB.hashCode() : 0);
        result = 31 * result + (answerC != null ? answerC.hashCode() : 0);
        return result;
    }
}
