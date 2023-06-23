package com.sobu.parkinglot.api.student;


import com.sobu.parkinglot.app.entity.Response;
import com.sobu.parkinglot.data.model.Bus;
import com.sobu.parkinglot.data.model.Status;
import com.sobu.parkinglot.data.model.Student;
import com.sobu.parkinglot.data.model.StudentTracking;
import com.sobu.parkinglot.data.repository.BusRepository;
import com.sobu.parkinglot.data.repository.StudentRepository;
import com.sobu.parkinglot.data.repository.StudentTrackingRepository;
import com.sobu.parkinglot.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping("/api/student")
@Slf4j
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;
    private final BusRepository busRepository;
    private final StudentTrackingRepository studentTrackingRepository;

    @Autowired
    public JavaMailSender emailSender;

    @GetMapping
    public ResponseEntity findAll() {
        return Response.data(studentRepository.findAll(Sort.by("createAt")));
    }

    @PostMapping("/onChangeStatus")
    public ResponseEntity studentStatusChanged(@RequestBody String request) {
        JSONObject requestParsed = new JSONObject(request);
        Optional<Student> studentOpt = studentRepository.findById(requestParsed.optLong("id", 0));
        Student student;
        if (studentOpt.isPresent()) {
            student = studentOpt.get();

            student.setStatus(student.getStatus() == Status.ON_BUS ? Status.OFF_BUS : Status.ON_BUS);
        } else {
            student = new Student();
            student.setId(requestParsed.optLong("id", 0));
            student.setStatus(Status.ON_BUS);
        }

        String busCode = requestParsed.optString("busCode");
        Optional<Bus> busOptional = busRepository.findBusByCode(busCode);
        long busId = 0;
        Bus bus = null;
        if (busOptional.isPresent()) {
            bus = busOptional.get();
            busId = bus.getId();

            if (student.getStatus() == Status.ON_BUS) {
                student.setBus(bus);
            } else {
                student.setBus(null);
            }
        }
        assert bus != null;
        student.setBusCodeGet(bus.getCode());


        {
            StudentTracking studentTracking = new StudentTracking();
            studentTracking.setStudentId(student.getId());
            studentTracking.setBusId(busId);
            studentTracking.setStatus(student.getStatus() == Status.ON_BUS ? 1 : 0);
            String action = student.getStatus() == Status.ON_BUS ? "lên xe" : "xuống xe";
            String now = DateUtil.format(new Date());
            studentTracking.setMessage("Học sinh " + student.getFullName() + " đã " + action + " " + bus.getCode() + " lúc " + now);
            studentTracking.setCreateAt(now);
            studentTracking.setImage(requestParsed.optString("image"));
            studentTrackingRepository.save(studentTracking);
        }

        
        //student = studentRepository.save(student);
        /*{
            //send email to phu huynh
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(student.getEmailGrand());
            message.setSubject("Tracking bus!");
            String action = student.getStatus() == Status.ON_BUS ? "lên xe" : "xuống xe";
            String now = DateUtil.format(new Date());
            message.setText("Học sinh " + student.getFullName() + " đã " + action + " " + (bus != null ? bus.getCode() : "unknown") + " lúc " + now);
            // Send Message!
            this.emailSender.send(message);

            
            //send email to hoc sinh
            SimpleMailMessage message1 = new SimpleMailMessage();
            message1.setTo(student.getEmailStudent());
            message1.setSubject("Tracking bus!");
            message1.setText("Ban vua " + action + " " + (bus != null ? bus.getCode() : "unknown") + " lúc " + now);
            // Send Message!
            this.emailSender.send(message1);
            
        }*/

        
        student = studentRepository.save(student);

        return Response.data(student);


    }


    @GetMapping("/findById")
    public ResponseEntity findStudentById(@RequestParam long id) {
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isPresent()) {
            return Response.data(studentOpt.get());
        } else {
            return Response.error(HttpStatus.NO_CONTENT, "Not found");
        }
    }

    @PostMapping("/createOrUpdate")
    public ResponseEntity createOrUpdateStudent(@RequestBody Student student) {
        student.setClassName(student.getClassName().replace(" ", "").toUpperCase());
        student = studentRepository.save(student);

        return Response.data(student);
    }

    @GetMapping("/search")
    public ResponseEntity searchStudent(@RequestParam String searchKey) {
        return Response.data(studentRepository.searchStudent(searchKey));
    }

}

