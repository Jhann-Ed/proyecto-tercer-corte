package com.programacion.avanzada.proyectotercercorte.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.programacion.avanzada.proyectotercercorte.Services.PayrollService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PayrollLambdaHandler implements RequestHandler<Object, String> {

    private final ApplicationContext context;
    private final PayrollService payrollService;

    public PayrollLambdaHandler() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        payrollService = context.getBean(PayrollService.class);
    }

    @Override
    public String handleRequest(Object input, Context context) {
        return payrollService.processPayroll();
    }
}