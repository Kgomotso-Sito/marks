package marks.marksmanagement;

import marks.marksmanagement.mark.dto.MarkList;
import marks.marksmanagement.mark.entity.Mark;
import marks.marksmanagement.mark.entity.MarkId;
import marks.marksmanagement.mark.service.MarksService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MarksManagementApplicationTests {

	@Autowired
	private MarksService marksService;

	@Test
	void contextLoads() {
	}

	@Test
	public void createMark() {
		MarkId markId = new MarkId("L20200000", 7);
		Mark mark = new Mark(markId);
		mark.setGrade(100);
		Mark newMark = marksService.createOrUpdate(mark);
		assertEquals(newMark, mark);
	}


	@Test
	public void findMarkByMarkId() {
		MarkId markId = new MarkId("L20200000", 7);
		Mark mark = new Mark(markId);
		mark.setGrade(100);
		Mark newMark = marksService.findMarkByMarkId(markId);
		assertEquals(newMark, mark);
	}

	@Test
	public void findMarksByMarkId_AssessmentId() {
		MarkList newMarks = marksService.findMarksByMarkId_AssessmentId(20);
		assertEquals(newMarks.getMarks().size(), 16);
	}
}
