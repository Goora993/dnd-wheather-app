package com.dnd.weather.management.business.bo;

import com.dnd.weather.domain.entity.UserData;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SessionBusinessObject {
    private final Long id;
    private final LocalDateTime creationDate;
    private final LocalDateTime modificationDate;
    private final Long version;
    private final String name;
    private final UserData userData; // TODO: Change to DTO
    private final SessionStateBusinessObject stateBusinessObject;
}
