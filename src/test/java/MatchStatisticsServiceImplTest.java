import com.example.demo.dao.MatchDAO;
import com.example.demo.entity.FootballTeam;
import com.example.demo.entity.Match;
import com.example.demo.entity.MatchStatistics;
import com.example.demo.entity.Result;
import com.example.demo.service.MatchStatisticsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MatchStatisticsServiceImplTest {

    @Mock
    private MatchDAO matchDAO;

    @InjectMocks
    private MatchStatisticsServiceImpl matchStatisticsService;

    @Test
    public void testSave(){
        Match match = new Match();
        match.setId(1);
        FootballTeam homeTeam = new FootballTeam();
        homeTeam.setTeamName("Liverpool");
        homeTeam.setId(10);
        FootballTeam awayTeam = new FootballTeam();
        awayTeam.setTeamName("Arsenal");
        awayTeam.setId(20);
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        match.setHomeTeamScore(2);
        match.setAwayTeamScore(1);
        match.setResult(Result.WIN);
        match.setDate(LocalDate.of(2020, 1, 8));

        MatchStatistics matchStatistics = new MatchStatistics();
        matchStatistics.setCorners(5);
        matchStatistics.setRedCards(1);
        matchStatistics.setYellowCards(2);
        matchStatistics.setId(11);
        matchStatistics.setAwayPossession(BigDecimal.valueOf(55.5));
        matchStatistics.setHomePossession(BigDecimal.valueOf(45.5));
        matchStatistics.setMatchId(match);
        matchStatisticsService.save(matchStatistics);
    }
}
