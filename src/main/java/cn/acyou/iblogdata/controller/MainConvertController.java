package cn.acyou.iblogdata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author youfang
 * @version [1.0.0, 2019-04-26 下午 02:51]
 **/
@Controller
@RequestMapping("/sfgz/student")
public class MainConvertController {

    @RequestMapping(value = "students", method = {RequestMethod.GET})
    public void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/student/students");
        dispatcher.forward(request, response);
    }




}
