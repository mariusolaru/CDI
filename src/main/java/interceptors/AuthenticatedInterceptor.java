package interceptors;

import service.AuthenticationService;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@ValidPeriod
@Interceptor
public class AuthenticatedInterceptor implements Serializable {

    @Inject
    private AuthenticationService authenticationService;

    @AroundInvoke
    public Object authenticated(InvocationContext invocationContext) throws Exception {
        if(authenticationService.getCurrentUser()== null){
            throw new Exception("Not authenticated!");
        }

        return invocationContext.proceed();
    }

}
