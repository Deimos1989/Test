package Application.Survey;

import Application.DAO.Repository;
import Application.Entity.DateTime;
import Application.Service.ServiceDateTime;
import Application.exchangeObject.ExchangeServiceObjectView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Timer;
import java.util.TimerTask;

@Controller
public class Agent {
    @GetMapping(value="/Agent/start")
    public String start(Model model, ExchangeServiceObjectView exchangeServiceObjectView) {

        Repository repository = new Repository();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {

                DateTime dateTime = new DateTime();
                ServiceDateTime serviceDateTime = new ServiceDateTime();

                dateTime.setHash(serviceDateTime.getHashStatic());
                dateTime.setTimestamp(serviceDateTime.getTimestampStatic());

                dateTime.setLocalDate(serviceDateTime.getLocalDateStatic());
                dateTime.setLocalTime(serviceDateTime.getLocalTimeStatic());
                dateTime.setLocalDateTime(serviceDateTime.getLocalDateTimeStatic());



                SetNodeBaseObject setNodeBaseObject = new SetNodeBaseObject();
                Thread thread = new Thread(setNodeBaseObject);
                thread.start();

                repository.setObT(dateTime);
                repository.save();

                serviceDateTime.setHashStatic(serviceDateTime.getHashDinamic());
                serviceDateTime.setTimestampStatic(serviceDateTime.getTimestampDinamic());

                serviceDateTime.setLocalDateStatic(serviceDateTime.getLocalDateDinamic());
                serviceDateTime.setLocalTimeStatic(serviceDateTime.getLocalTimeDinamic());
                serviceDateTime.setLocalDateTimeStatic(serviceDateTime.getLocalDateTimeDinamic());

            }

        };

        timer.scheduleAtFixedRate(task, 1000, 20000);
        model.addAttribute("name",  "Cбор статистики системы Megatrans-4");
        return "surleyNode";
    }


}