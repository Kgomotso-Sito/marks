package marks.subjectmaintenance;

import marks.subjectmaintenance.subject.dto.SubjectList;
import marks.subjectmaintenance.subject.entity.Assessment;
import marks.subjectmaintenance.subject.entity.Subject;
import marks.subjectmaintenance.subject.service.AssessmentService;
import marks.subjectmaintenance.subject.service.SubjectService;
import marks.subjectmaintenance.subject.service.UserSubjectService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class SubjectMaintenanceApplicationTests {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private AssessmentService assessmentService;

	@Autowired
	private UserSubjectService userSubjectService;

	@Test
	void contextLoads() {
	}

	@Test
	public void createAndSearchSubject() {
		Subject testSubject = new Subject();

		testSubject.setCode("LO");
		testSubject.setDescription("Life Orientation");
		Assessment assessment = new Assessment();
		assessment.setName("CT 1");
		assessment.setTotal(10);
		assessment.setDate(new Date());
		List<Assessment> listOfAssessment = new ArrayList<>(Arrays.asList(assessment));
		testSubject.setAssessmentList(listOfAssessment);
		Subject newSubject = subjectService.createOrUpdate(testSubject);

		assertTrue(newSubject.equals(testSubject));
	}

	@Test
	public void deactivateSubject() {
		Subject testSubject = new Subject();

		testSubject.setCode("Testing");
		testSubject.setDescription("Subject");
		testSubject.setActive(Boolean.TRUE);
		Subject activeSubject = subjectService.createOrUpdate(testSubject);
		subjectService.deactivateSubject(activeSubject.getId());
		Subject deactivatedSubject = subjectService.findBySubjectById(activeSubject.getId());

		assertEquals(deactivatedSubject.getActive(), Boolean.FALSE);
	}

	@Test
	public void findAllActiveSubjects() {
		SubjectList newSubjects = subjectService.findAllActiveSubjects();
		assertTrue(newSubjects.getSubjects().stream().allMatch(subject -> subject.getActive().equals(Boolean.TRUE)));
	}
}
