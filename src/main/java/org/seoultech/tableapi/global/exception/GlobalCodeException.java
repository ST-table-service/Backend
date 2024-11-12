package org.seoultech.tableapi.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.seoultech.tableapi.global.dto.ErrorReason;

@Getter
@AllArgsConstructor
public class GlobalCodeException extends RuntimeException {
    private BaseErrorCode errorCode;

    public ErrorReason getErrorReason() {
        return this.errorCode.getErrorReason();
    }
}
