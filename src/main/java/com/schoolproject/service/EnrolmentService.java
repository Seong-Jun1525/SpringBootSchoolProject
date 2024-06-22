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
		if(enrolmentOneRepository.existsByLectureNameAndStudentNumber(
				enrolment.getLectureName(), enrolment.getStudentNumber())) {
            throw new AlreadyExistsMajorEnrolmentException("이미 수강신청하신 과목입니다.");
        }
		enrolmentOneRepository.updateAutoIncrementValue();
        return enrolmentOneRepository.save(enrolment);
	}

	public List<Enrolment> findByMyEnrolment(int myStudentNumber) {
        return enrolmentOneRepository.findByStudentNumber(myStudentNumber);
	}
	
	// 교수가 수강신청 현황보기
	public List<Enrolment> findByEnrolmentStudentList(String lectureName) {
		return enrolmentOneRepository.findByLectureName(lectureName);
	}

	public void deleteStudent(String lectureName, int enrolmentStudentListSize, int lectureCapacity) {
		System.out.println("서비스에서 강의목록 체크1 : " + lectureName);
		System.out.println("서비스에서 강의목록 체크2 : " + enrolmentStudentListSize);
		System.out.println("서비스에서 강의목록 체크3 : " + lectureCapacity);
		if (enrolmentStudentListSize > lectureCapacity) {
            int deleteCount = enrolmentStudentListSize - lectureCapacity;
            List<Enrolment> enrolmentsToDelete = enrolmentOneRepository.findByLectureNameOrderByStudentPointDesc(lectureName);

            System.out.println(deleteCount);
            System.out.println(lectureCapacity);
            System.out.println(enrolmentStudentListSize);
            
            // studentPoint를 기준으로 내림차순으로 정렬한 후, deleteCount만큼 삭제
            for(int i = 0; i < deleteCount; i++) {
                System.out.println(enrolmentsToDelete.get(lectureCapacity).getStudentName());
                Enrolment enrolment = enrolmentsToDelete.get(lectureCapacity);
                enrolmentOneRepository.delete(enrolment);
                lectureCapacity++;
            }
        }
	}
	
	// 수강신청취소
	public void deleteEnrolment(Long id) {
		enrolmentOneRepository.deleteById(id);
		enrolmentOneRepository.updateAutoIncrementValue();
    }
}