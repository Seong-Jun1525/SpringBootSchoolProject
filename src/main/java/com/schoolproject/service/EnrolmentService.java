package com.schoolproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolproject.entity.Enrolment;
import com.schoolproject.exception.AlreadyExistsMajorEnrolmentException;
import com.schoolproject.repository.EnrolmentOneRepository;

@Service
public class EnrolmentService {
	@Autowired
	private EnrolmentOneRepository enrolmentOneRepository;
	
	public void enrolmentService(EnrolmentOneRepository enrolmentOneRepository) {
		this.enrolmentOneRepository = enrolmentOneRepository;
	}
	
	public Enrolment registerEnrolment(Enrolment enrolment) {
		// 만약 수강신청을 했을 경우
		if (enrolmentOneRepository.existsByLectureNameAndStudentNumber(
				enrolment.getLectureName(), enrolment.getStudentNumber())) {
            throw new AlreadyExistsMajorEnrolmentException("이미 수강신청하신 과목입니다.");
        }
        return enrolmentOneRepository.save(enrolment);
	}

	public List<Enrolment> findByMyEnrolment(int myStudentNumber) {
        return enrolmentOneRepository.findByStudentNumber(myStudentNumber);
	}
}
