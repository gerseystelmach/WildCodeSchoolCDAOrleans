package org.aenori.springboot;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class KnightController {
	
    @RequestMapping(value = "/knight", method = RequestMethod.GET)
    public String getAllCustomers(Model model) {
        List<String> knightList = Arrays.asList(
            new String[] { 
                "Mathieu", "Mathias", "Zurabi", "Arnaud",
                "Gersey", "Thuy", "Lahcen", "Raphaël" 
            }
        );

        model.addAttribute("knightList", knightList);
        return "knightIndex";
    }
}
