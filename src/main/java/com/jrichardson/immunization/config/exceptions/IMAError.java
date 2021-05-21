package com.jrichardson.immunization.config.exceptions;

import java.util.Objects;
import org.springframework.http.HttpStatus;


/**
 * @author richa
 *
 */

public class IMAError {

    private HttpStatus status;
    private Boolean success;
    private ErrorDetails errorDetails;

    public IMAError() {
        super();
    }

    public IMAError(HttpStatus status, Boolean success, ErrorDetails details) {
        super();
        this.status = status;
        this.success = success;
        this.errorDetails = details;
    }

    /**
     * @return the status
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
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
     * @return the errorDetails
     */
    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

    /**
     * @param errorDetails the errorDetails to set
     */
    public void setErrorDetails(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorDetails, status, success);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IMAError)) {
            return false;
        }
        IMAError other = (IMAError) obj;
        return Objects.equals(errorDetails, other.errorDetails) && status == other.status
                && Objects.equals(success, other.success);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TrinityApplicationError [status=");
        builder.append(status);
        builder.append(", success=");
        builder.append(success);
        builder.append(", errorDetails=");
        builder.append(errorDetails);
        builder.append("]");
        return builder.toString();
    }
}
