package com.schoolnews.manage.application.bean;

import java.io.Serializable;

public class FeedbackBean implements Serializable {
    private String feedback;
    private long user_id;


    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
