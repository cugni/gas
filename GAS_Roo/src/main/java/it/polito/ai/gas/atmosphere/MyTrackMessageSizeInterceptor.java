package it.polito.ai.gas.atmosphere;

import org.atmosphere.cpr.Action;
import org.atmosphere.cpr.AtmosphereResource;

/**
 * Created with IntelliJ IDEA.
 * User: cesare
 * Date: 18/09/13
 * Time: 01:30
 * To change this template use File | Settings | File Templates.
 */
public class MyTrackMessageSizeInterceptor extends org.atmosphere.client.TrackMessageSizeInterceptor{
    @Override
    public Action inspect(AtmosphereResource r) {
        if (! r.getRequest().getRequestURI().matches("/ws/.+")){
            return    Action.CONTINUE;
        }
        return super.inspect(r);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
