package view;

import entity.Period;
import repository.PeriodRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class PeriodsView {

    @EJB
    private PeriodRepository periodRepository;

    private List<Period> periods;

    @PostConstruct
    public void init(){
        this.periods = periodRepository.findAll();
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }
}
