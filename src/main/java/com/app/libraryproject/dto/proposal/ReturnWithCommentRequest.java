package com.app.libraryproject.dto.proposal;

public class ReturnWithCommentRequest {

    private Long id;
    private String comment;

    public ReturnWithCommentRequest() {
    }

    public ReturnWithCommentRequest(Long id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
