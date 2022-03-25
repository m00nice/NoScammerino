package controllers;


import com.example.freemoneynoscam.services.DataHandler;
import com.example.freemoneynoscam.services.EmailRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;
import java.sql.SQLException;
import java.util.Objects;
import java.util.regex.Pattern;

@Controller
public class IndexController {
    EmailRepository emailRepository = new EmailRepository();
    DataHandler dataHandler = new DataHandler();
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/test")
    public String test(WebRequest dataFromForm) throws SQLException {
        dataHandler.insertSyntax("USE EMAILS");
        Pattern pat = Pattern.compile("^(.+)@(.+)$");
        if(pat.matcher(Objects.requireNonNull(dataFromForm.getParameter("email"))).matches()){
            dataHandler.insertSyntax("INSERT INTO EMAIL (email) VALUES ('"+dataFromForm.getParameter("email")+"');");
        }else{
            System.out.println("EMAIL NOT VALID");
        }
        return "redirect:/";
    }



    @GetMapping("/email")
    public String emailFetch(Model model){
        try {
            String email = emailRepository.fetchEmail();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("email1",emailRepository);
        return "";
    }

}

