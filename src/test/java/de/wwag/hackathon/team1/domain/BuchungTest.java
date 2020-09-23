package de.wwag.hackathon.team1.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import de.wwag.hackathon.team1.web.rest.TestUtil;

public class BuchungTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Buchung.class);
        Buchung buchung1 = new Buchung();
        buchung1.setId(1L);
        Buchung buchung2 = new Buchung();
        buchung2.setId(buchung1.getId());
        assertThat(buchung1).isEqualTo(buchung2);
        buchung2.setId(2L);
        assertThat(buchung1).isNotEqualTo(buchung2);
        buchung1.setId(null);
        assertThat(buchung1).isNotEqualTo(buchung2);
    }
}
