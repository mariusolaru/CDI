package interceptors;

import entity.Period;
import repository.PeriodRepository;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ValidPeriod
@Interceptor
public class ValidPeriodInterceptor implements Serializable {

    @EJB
    private PeriodRepository periodRepository;

    @AroundInvoke
    public Object checkPeriodIfValid(InvocationContext invocationContext) throws Exception {

        boolean isValid = false;

        List<Period> periods = periodRepository.findAll();

        //If no specific periods are defined, then every submission will be accepted
        if(periods == null){
            isValid = true;
        } else {
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0);
            Date now = today.getTime();

            for(Period period : periods){
                if (now.after(period.getStartDate()) && now.before(period.getEndDate()))
                    isValid = true;
            }
        }

        if(!isValid){
            throw new Exception("Any submission (register/upload) is not allowed in this period!");
        }

        return invocationContext.proceed();
    }

}
