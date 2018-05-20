package com.combishackaton.app.logging.controller;


import com.combishackaton.app.logging.entity.ExceptionLog;
import com.combishackaton.app.logging.service.ExceptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/exceptions")
public class ExceptionLogController {

    private ExceptionLogService exceptionLogService;

    @Autowired
    public ExceptionLogController(ExceptionLogService exceptionLogService) {
        this.exceptionLogService = exceptionLogService;
    }

    @GetMapping("/logs")
    private String getExceptionLogs(Pageable pageable, Model model) {
        try {
            Page<ExceptionLog> exceptionLogs = exceptionLogService.findExceptionLogs(pageable);
            model.addAttribute("exceptionLogs", exceptionLogs.getContent());
            model.addAttribute("totalElements", exceptionLogs.getTotalElements());
            return "exceptions/exception-list";
        } catch(Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "exceptions/exception-error";
        }
    }

    @GetMapping("/logs/{id}/stacktrace")
    @ResponseBody
    private String getLogStacktrace(@PathVariable(name = "id") Long logId) {
        return exceptionLogService.findById(logId).getStackTrace();
    }
}
