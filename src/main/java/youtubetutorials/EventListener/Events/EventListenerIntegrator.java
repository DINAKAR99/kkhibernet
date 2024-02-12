package youtubetutorials.EventListener.Events;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class EventListenerIntegrator implements Integrator {

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory,
            SessionFactoryServiceRegistry serviceRegistry) {
        // TODO Auto-generated method stub

    }

    @Override
    public void integrate(Metadata metadata,
            SessionFactoryImplementor sessionFactory,
            SessionFactoryServiceRegistry serviceRegistry) {

        EventListenerRegistry service = serviceRegistry.getService(EventListenerRegistry.class);

        service.getEventListenerGroup(EventType.SAVE_UPDATE).appendListener(new SaveUpdateEventListenerImpl());

    }

}
