package com.aquamonitor.platform.u202515871.shared.infrastructure.events;

import com.aquamonitor.platform.u202515871.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@NullMarked
public class DomainEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public DomainEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public <T extends AbstractDomainAggregateRoot<T>> void publishAndClearEvents(@Nullable T aggregate) {
        if (aggregate == null) {
            return;
        }
        aggregate.domainEvents().forEach(applicationEventPublisher::publishEvent);
        aggregate.clearDomainEvents();
    }
}
