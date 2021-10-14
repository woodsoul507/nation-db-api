package me.givo.nationdbapiproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.givo.nationdbapiproject.dto.ContinentsDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ContinentsConstraintsValidationExceptionTest {

    @Autowired
    private ContinentsServiceImpl continentsServiceImpl;

    @Test
    public void constraintsValidationExceptionTest() {

        assertThrows(ConstraintViolationException.class, () -> continentsServiceImpl.create(new ContinentsDto(null)));
        assertThrows(ConstraintViolationException.class, () -> continentsServiceImpl.create(new ContinentsDto("")));

        // Can't be bigger than 255 chars
        assertThrows(ConstraintViolationException.class, () -> continentsServiceImpl.create(new ContinentsDto(
                "Sint unde ut laboriosam quidem. Consequatur quia dolore est est ullam natus corrupti ut. Reiciendis rerum aspernatur ut quo delectus repudiandae nobis et. Et neque quisquam quas et omnis commodi. Illo ea sunt hic esse dolorum. Nulla in qui assumenda quis fugiat quo. Quas ipsum at aspernatur. Quis optio et voluptatem quas ut laboriosam nulla et. Nihil iste qui itaque aliquam tempore ducimus. Qui vel distinctio maxime rerum voluptas quis ut est. Repellat sed quo enim. Qui voluptas natus. Sint unde ut laboriosam quidem. Consequatur quia dolore est est ullam natus corrupti ut. Reiciendis rerum aspernatur ut quo delectus repudiandae nobis et. Et neque quisquam quas et omnis commodi. Illo ea sunt hic esse dolorum. Nulla in qui assumenda quis fugiat quo. Quas ipsum at aspernatur. Quis optio et voluptatem quas ut laboriosam nulla et. Nihil iste qui itaque aliquam tempore ducimus. Qui vel distinctio maxime rerum voluptas quis ut est. Repellat sed quo enim. Qui voluptas natus. Sint unde ut laboriosam quidem. Consequatur quia dolore est est ullam natus corrupti ut. Reiciendis rerum aspernatur ut quo delectus repudiandae nobis et. Et neque quisquam quas et omnis commodi. Illo ea sunt hic esse dolorum. Nulla in qui assumenda quis fugiat quo. Quas ipsum at aspernatur. Quis optio et voluptatem quas ut laboriosam nulla et. Nihil iste qui itaque aliquam tempore ducimus. Qui vel distinctio maxime rerum voluptas quis ut est. Repellat sed quo enim. Qui voluptas natus. Sint unde ut laboriosam quidem. Consequatur quia dolore est est ullam natus corrupti ut. Reiciendis rerum aspernatur ut quo delectus repudiandae nobis et. Et neque quisquam quas et omnis commodi. Illo ea sunt hic esse dolorum. Nulla in qui assumenda quis fugiat quo. Quas ipsum at aspernatur. Quis optio et voluptatem quas ut laboriosam nulla et. Nihil iste qui itaque aliquam tempore ducimus. Qui vel distinctio maxime rerum voluptas quis ut est. Repellat sed quo enim. Qui voluptas natus.")));

    }

    @Test
    public void createContinents() {
        ContinentsDto cDto = continentsServiceImpl.create(new ContinentsDto("Pulatina"));

        assertNotNull(cDto);
        assertEquals("Pulatina", cDto.getName());
        assertNotNull(cDto.getContinentId());
    }
}
