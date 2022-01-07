package online.jtools.cimanager.controllers;

import online.jtools.cimanager.controllers.validator.exception.ValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ExceptionHandlingController {
    @ExceptionHandler({ValidationException.class})
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        final ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName("error");
        return mav;
    }

}
