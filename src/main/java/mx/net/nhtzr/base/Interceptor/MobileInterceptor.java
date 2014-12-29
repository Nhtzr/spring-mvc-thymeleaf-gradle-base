package mx.net.nhtzr.base.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filtra los dispositivos mobiles, y les muestra una version mobil de la vista.
 * <p/>
 * <li> Solo considera los ModelAndView manejan ViewName</li>
 * <li> Ignora los viewName con prefijos de redirect y forward.</li>
 * <li> Requiere que las vistas esten organizadas en carpetas {@code web/} {@code mobile/}</li>
 *
 * @see org.springframework.web.servlet.view.UrlBasedViewResolver
 * @see org.springframework.web.servlet.view.UrlBasedViewResolver#REDIRECT_URL_PREFIX
 * @see org.springframework.web.servlet.view.UrlBasedViewResolver#FORWARD_URL_PREFIX
 */
public class MobileInterceptor extends HandlerInterceptorAdapter {

    private final Logger log = LoggerFactory.getLogger(MobileInterceptor.class);

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.trace("#postHandle() starts");
        if (modelAndView == null
                || modelAndView.getViewName() == null
                || hasPrefix(modelAndView.getViewName())) {
            super.postHandle(request, response, handler, modelAndView);
            return;
        }

        final String viewName = modelAndView.getViewName();
        final Device currentDevice = DeviceUtils.getCurrentDevice(request);

        interceptMobile(modelAndView, viewName, currentDevice);
        super.postHandle(request, response, handler, modelAndView);
    }

    private boolean hasPrefix(String viewName) {
        return viewName.startsWith(UrlBasedViewResolver.REDIRECT_URL_PREFIX)
                || viewName.startsWith(UrlBasedViewResolver.FORWARD_URL_PREFIX);
    }

    private void interceptMobile(ModelAndView modelAndView, String viewName, Device currentDevice) {
        if (currentDevice.isMobile()) {
            log.trace("#postHandle() => {viewVersion : 'mobile'}");
            modelAndView.setViewName("mobile/" + viewName);
            return;
        }

        log.trace("#postHandle() => {viewVersion : 'web'}");
        modelAndView.setViewName("web/" + viewName);
    }
}
