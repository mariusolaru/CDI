package repository;

import entity.Period;

import javax.ejb.Stateless;

@Stateless
public class PeriodRepository extends DataRepository<Period, Long> {

    public PeriodRepository(){
        super(Period.class);
    }

}
