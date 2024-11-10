package org.seoultech.tableapi.global.exception;

import org.seoultech.tableapi.global.dto.ErrorReason;

public interface BaseErrorCode {
    public ErrorReason getErrorReason();
    String getExplainError() throws NoSuchFieldException;
}
