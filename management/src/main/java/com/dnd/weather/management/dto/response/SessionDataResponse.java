package com.dnd.weather.management.dto.response;

import lombok.Builder;

@Builder
public record SessionDataResponse(long sessionId, long sessionStateId, String sessionName) {
}
