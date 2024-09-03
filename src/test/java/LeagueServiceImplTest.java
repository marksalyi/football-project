import com.example.demo.dao.LeagueDAO;
import com.example.demo.dao.TeamDAO;
import com.example.demo.entity.*;
import com.example.demo.service.LeagueServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class LeagueServiceImplTest {
    @Mock
    private LeagueDAO leagueDAO;

    @Mock
    private TeamDAO teamDAO;

    @InjectMocks
    private LeagueServiceImpl leagueService;


    @Test
    public void testFindAllLeagues() {
        League league1 = new League();
        league1.setId(1);
        league1.setLeagueName("Premier League");

        League league2 = new League();
        league2.setId(2);
        league2.setLeagueName("La Liga");

        League league3 = new League();
        league3.setId(3);
        league3.setLeagueName("Bundesliga");

        ArrayList<League> allLeauges = new ArrayList<>();
        allLeauges.add(league1);
        allLeauges.add(league2);
        allLeauges.add(league3);
        when(leagueDAO.findAll()).thenReturn(allLeauges);
        // expectedleagues adja vissza


        List<League> actualLeagues = leagueService.findAll();


        assertEquals(allLeauges, actualLeagues);

    }

    @Test
    public void findLeagueById(){
        League league = new League();
        league.setId(1);
        league.setLeagueName("Premier League");

        when(leagueDAO.findById(1)).thenReturn(league);

        League expectedLeague = leagueService.findById(1);

        assertEquals(expectedLeague, league);
    }

    @Test
    public void testSave(){
        League league = new League();
        league.setId(4);
        league.setLeagueName("NB1");
        when(leagueDAO.save(league)).thenReturn(league);

        League expectedLeague = leagueService.save(league);

        assertEquals(expectedLeague, league);
    }


    @Test
    public void testAssignedTeamToLeague(){
        League league = new League();
        league.setId(1);
        league.setLeagueName("Premier League");

        FootballTeam footballTeam = new FootballTeam();
        footballTeam.setId(10);
        footballTeam.setTeamName("Liverpool");
        when(leagueDAO.findById(1)).thenReturn(league);
        when(teamDAO.findById(10)).thenReturn(footballTeam);

        leagueService.assignedTeamToLeague(1, 10);

        verify(teamDAO, times(1)).save(eq(footballTeam));

        Assertions.assertTrue(footballTeam.getLeagues().contains(league), "The football team contain the assigned league");


    }

    @Test
    public void testGetResult(){
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

        List<Match> allMatches = new ArrayList<>();
        allMatches.add(match);
        when(leagueDAO.findAllMatchesByLeagueId(1)).thenReturn(allMatches);

        Map<String, TeamResult> result = leagueService.getResults3(1);

        TeamResult liverpoolResult = new TeamResult();
        liverpoolResult.increment(Result.WIN);
        TeamResult arsenalResult = new TeamResult();
        arsenalResult.increment(Result.LOSS);

        Map<String, TeamResult> expectedResult = new HashMap<>();
        expectedResult.put("Liverpool", liverpoolResult);
        expectedResult.put("Arsenal", arsenalResult);

        assertEquals(expectedResult, result);

        verify(leagueDAO, times(1)).findAllMatchesByLeagueId(eq(1));
    }
}
