package com.jrichardson.immunization.config.response;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

import org.springframework.http.HttpStatus;

public class SuccessResponse<T> {

    private Timestamp timestamp;
    private String message;
    private Boolean success;
    private HttpStatus status;
    private T imaEntity;

    public SuccessResponse(Timestamp timestamp, String message, Boolean success, HttpStatus status, T entity) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
        this.success = success;
        this.imaEntity = entity;
    }

    /**
     *
     * @return Timestamp for this request
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @param timestamp
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /**
     *
     * @return Message for this response
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return HttpStatus for this response
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    /**
     * @return the success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * @return the imaEntity
     */
    public T getImaEntity() {
        return imaEntity;
    }

    /**
     * @param imaEntity the trinityEntity to set
     */
    public void setImaEntity(T imaEntity) {
        this.imaEntity = imaEntity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, status, success, timestamp, imaEntity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SuccessResponse)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        SuccessResponse<T> other = (SuccessResponse<T>) obj;
        return Objects.equals(message, other.message) && status == other.status
                && Objects.equals(success, other.success) && Objects.equals(timestamp, other.timestamp)
                && Objects.equals(imaEntity, other.imaEntity);
    }
}