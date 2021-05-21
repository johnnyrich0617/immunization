package com.jrichardson.immunization.config.exceptions;

import java.util.Date;
import java.util.Objects;
public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String requestedResource;

    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.requestedResource = details;
    }

    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the details
     */
    public String getRequestedResource() {
        return requestedResource;
    }

    /**
     * @param details the details to set
     */
    public void setRequestedResource(String details) {
        this.requestedResource = details;
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestedResource, message, timestamp);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ErrorDetails)) {
            return false;
        }
        ErrorDetails other = (ErrorDetails) obj;
        return Objects.equals(requestedResource, other.requestedResource) && Objects.equals(message, other.message)
                && Objects.equals(timestamp, other.timestamp);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ErrorDetails [timestamp=");
        builder.append(timestamp);
        builder.append(", message=");
        builder.append(message);
        builder.append(", details=");
        builder.append(requestedResource);
        builder.append("]");
        return builder.toString();
    }
}
