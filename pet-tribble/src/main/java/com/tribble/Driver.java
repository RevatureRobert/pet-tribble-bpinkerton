package com.tribble;

import com.tribble.model.Lab;
import com.tribble.model.Tribble;
import com.tribble.service.LabService;
import com.tribble.service.TribbleService;

public class Driver {
    public static void main(String[] args) {
        TribbleService tribbleService = new TribbleService();
        LabService labService = new LabService();
        Lab test = new Lab("Aloha");
        Tribble tribble = new Tribble(1,"Bob",test);
        System.out.println(labService.getLab(1));
    }
}
