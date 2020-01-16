package beans;

import dto.PeriodDTO;
import entity.Admin;
import entity.Period;
import repository.AdminRepository;
import repository.PeriodRepository;
import repository.UserRepository;
import service.AuthenticationService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

import static util.EntityConverter.toAdmin;

@Named
@RequestScoped
public class PeriodBean implements Serializable {

    @EJB
    private PeriodRepository periodRepository;

    @EJB
    private UserRepository userRepository;

    @EJB
    private AdminRepository adminRepository;

    @Inject
    private AuthenticationService authenticationService;

    private PeriodDTO periodDTO;

    @PostConstruct
    public void init(){
        this.periodDTO = new PeriodDTO();
    }

    public PeriodDTO getPeriodDTO() {
        return periodDTO;
    }

    public void setPeriodDTO(PeriodDTO periodDTO) {
        this.periodDTO = periodDTO;
    }

    public String addPeriod(){
        Period period = new Period();

        period.setStartDate(this.periodDTO.getStartDate());
        period.setEndDate(this.periodDTO.getEndDate());

        Admin admin = adminRepository.findById(this.authenticationService.getCurrentUser().getId());
        period.setSetBy(admin);

        periodRepository.persist(period);

        return "index?faces-redirect=true";

    }
}
