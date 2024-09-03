import com.example.demo.dao.TeamDAO;
import com.example.demo.entity.FootballTeam;
import com.example.demo.service.TeamServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeamServiceImplTest {

    @Mock
    private TeamDAO teamDAO;

    @InjectMocks
    private TeamServiceImpl teamService;

    @Test
    public void testFindById(){
        FootballTeam team = new FootballTeam();
        team.setId(10);
        team.setTeamName("DVSC");
        when(teamDAO.findById(10)).thenReturn(team);

        FootballTeam expectedTeam = teamService.findById(10);

        assertEquals(expectedTeam, team);

    }
}
