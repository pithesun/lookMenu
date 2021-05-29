package com.example.lookmenu;

public class ReviewItem {
    private String authorId;
    private String reviewContent ;
    private String reviewDate ;

    public void setAuthorId(String id) {
        authorId = id ;
    }
    public void setReviewContent(String content) {
        reviewContent = content ;
    }
    public void setReviewDate(String date) {
        reviewDate = date ;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public String getReviewContent() {
        return reviewContent;
    }

}
