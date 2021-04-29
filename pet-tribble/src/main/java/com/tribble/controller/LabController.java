package com.tribble.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tribble.model.Lab;
import com.tribble.model.Tribble;
import com.tribble.service.LabService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

public class LabController {
    private LabService labService;
    private Gson gson;
    private GsonBuilder builder;

    public LabController() {
        this.labService = new LabService();
        builder = new GsonBuilder();
        builder.setPrettyPrinting();
        gson = builder.create();
    }

    public void get(HttpServletRequest req, HttpServletResponse res){
        Enumeration<String> params = req.getParameterNames();
        res.setContentType("application/json");
        try {
            if(params.hasMoreElements()){
                String labId = req.getParameter("lab-id");
                if(labId != null && !labId.equals("")){
                    res.setStatus(200);
                    res.getWriter().println(gson.toJson(labService.getLab(Integer.parseInt(labId))));
                } else{
                    res.setStatus(400);
                }
            } else{
                res.setStatus(200);
                res.getWriter().println(gson.toJson(labService.getAllLabs()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void post(HttpServletRequest req, HttpServletResponse res){
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = req.getReader()){
            String line;
            while((line = reader.readLine()) != null) sb.append(line);
            Lab l = gson.fromJson(sb.toString(), Lab.class);
            labService.createLab(l);
            res.setStatus(200);
        } catch (IOException e) {
            res.setStatus(400);
            e.printStackTrace();
        }
    }

    public void put(HttpServletRequest req, HttpServletResponse res){
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = req.getReader()){
            String line;
            while((line = reader.readLine()) != null) sb.append(line);
            Lab l = gson.fromJson(sb.toString(), Lab.class);
            labService.updateLab(l);
            res.setStatus(200);
        } catch (IOException e) {
            res.setStatus(400);
            e.printStackTrace();
        }
    }

    public void delete(HttpServletRequest req, HttpServletResponse res){
        Enumeration<String> params = req.getParameterNames();
        res.setContentType("application/json");
        if(params.hasMoreElements()){
            String labId = req.getParameter("lab-id");
            if(labId != null && !labId.equals("")){
                res.setStatus(200);
                labService.deleteLab(Integer.parseInt(labId));
            } else{
                res.setStatus(400);
            }
        } else{
            res.setStatus(400);
        }
    }
}
