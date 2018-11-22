package com.wanguo.center_manager_sys.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * 描述：
 *
 * @author Badguy
 */
public class ValidationUtil {

    public static ResJson validation(BindingResult bindingResult, MessageSource messageSource) {
        if (bindingResult.hasErrors()) {
            StringBuilder msg = new StringBuilder();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                String errorMsg = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
                msg.append(fieldError.getField()).append("：").append(errorMsg).append("；");
            }
            return ResJson.failJson(4000, msg.toString(), null);
        }
        return ResJson.successJson("pass");
    }
}
