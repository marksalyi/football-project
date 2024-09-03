import com.example.demo.dao.MatchDAO;
import com.example.demo.entity.FootballTeam;
import com.example.demo.entity.League;
import com.example.demo.entity.Match;
import com.example.demo.entity.Result;
import com.example.demo.service.MatchServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MatchServiceImplTest {

    @Mock
    private MatchDAO matchDAO;

    @InjectMocks
    private MatchServiceImpl matchServiceImpl;

    @Test
    public void findByLeagueTest(){
        Match match = new Match();
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
        League league = new League();
        league.setLeagueName("Premier League");
        league.setId(1);
        Set<League> leagues = new HashSet<>();
        leagues.add(league);
        match.setLeagues(leagues);
        List<Match> matches = new ArrayList<>();
        matches.add(match);


        when(matchDAO.findByLeague(league)).thenReturn(matches);

        List<Match> actualMatches = matchServiceImpl.findByLeague(league);

        assertEquals(actualMatches, matches);
    }
}
