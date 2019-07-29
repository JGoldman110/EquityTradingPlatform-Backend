package citivelociti.backend.Controllers;


import citivelociti.backend.Models.Strategy;
import citivelociti.backend.Services.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {


    @Autowired
    StrategyService strategyService;

    @RequestMapping("/")
    public String helloWorld(){
        Strategy s = new Strategy("GOOG",5.0,5.0,5.0);
        //TMAStrategy newTMA = new TMAStrategy("GOOG",5.0,5.0,5.0, 1, 10);
        // Trade t = new Trade(s.getId(), true, 5);

        strategyService.save(s);


        return "helloWorld";
    }
}
