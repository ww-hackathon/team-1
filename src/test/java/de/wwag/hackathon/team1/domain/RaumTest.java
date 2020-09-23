package de.wwag.hackathon.team1.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import de.wwag.hackathon.team1.web.rest.TestUtil;

public class RaumTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Raum.class);
        Raum raum1 = new Raum();
        raum1.setId(1L);
        Raum raum2 = new Raum();
        raum2.setId(raum1.getId());
        assertThat(raum1).isEqualTo(raum2);
        raum2.setId(2L);
        assertThat(raum1).isNotEqualTo(raum2);
        raum1.setId(null);
        assertThat(raum1).isNotEqualTo(raum2);
    }
}
