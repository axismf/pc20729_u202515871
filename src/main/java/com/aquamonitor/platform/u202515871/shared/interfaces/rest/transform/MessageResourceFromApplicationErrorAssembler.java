package com.aquamonitor.platform.u202515871.shared.interfaces.rest.transform;

import com.aquamonitor.platform.u202515871.shared.application.result.ApplicationError;
import com.aquamonitor.platform.u202515871.shared.interfaces.rest.resources.MessageResource;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.ResponseEntity;

@NullMarked
public final class MessageResourceFromApplicationErrorAssembler {
    private MessageResourceFromApplicationErrorAssembler() {
    }

    public static ResponseEntity<MessageResource> toResponseFromApplicationError(ApplicationError error) {
        var problemDetail = ProblemDetailFromApplicationErrorAssembler.toProblemDetailFromApplicationError(error);
        var message = problemDetail.getDetail() != null ? problemDetail.getDetail() : error.message();
        return ResponseEntity.status(problemDetail.getStatus()).body(new MessageResource(message));
    }
}
