package youtubetutorials.EventListener.Events;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;

import youtubetutorials.OptimisticLocking.Person3;

public class SaveUpdateEventListenerImpl implements SaveOrUpdateEventListener

{
    private static Logger logger = LogManager
            .getLogger(SaveUpdateEventListenerImpl.class);
    private static final long serialVersionUID = 1L;

    @Override
    public void onSaveOrUpdate(SaveOrUpdateEvent e) throws HibernateException {
        logger.info("onSaveOrUpdate is called.");

        Object obj = e.getEntity();
        if (obj instanceof Person3) {
            Person3 person = (Person3) obj;
            logger.info(person);
        }
    }
}
