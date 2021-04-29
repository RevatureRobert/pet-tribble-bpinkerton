package com.tribble.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tribble.model.Tribble;
import com.tribble.service.LabService;
import com.tribble.service.TribbleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

public class TribbleController {
    private TribbleService tribbleService;
    private Gson gson;
    private GsonBuilder builder;

    public TribbleController() {
        this.tribbleService = new TribbleService();
        builder = new GsonBuilder();
        builder.setPrettyPrinting();
        gson = builder.create();
    }

    public void get(HttpServletRequest req, HttpServletResponse res){
        Enumeration<String> params = req.getParameterNames();
        res.setContentType("application/json");
        try {
            if(params.hasMoreElements()){
                String tribbleId = req.getParameter("tribble-id");
                if(tribbleId != null && !tribbleId.equals("")){
                    res.setStatus(200);
                    res.getWriter().println(gson.toJson(tribbleService.getTribble(Integer.parseInt(tribbleId))));
                } else{
                    res.setStatus(400);
                }
            } else{
                res.setStatus(200);
                res.getWriter().println(gson.toJson(tribbleService.getAllTribbles()));
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
            Tribble t = gson.fromJson(sb.toString(), Tribble.class);
            tribbleService.createTribble(t);
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
            Tribble t = gson.fromJson(sb.toString(), Tribble.class);
            tribbleService.updateTribble(t);
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
            String tribbleId = req.getParameter("tribble-id");
            if(tribbleId != null && !tribbleId.equals("")){
                res.setStatus(200);
                tribbleService.deleteTribble(Integer.parseInt(tribbleId));
            } else{
                res.setStatus(400);
            }
        } else{
            res.setStatus(400);
        }
    }
}
