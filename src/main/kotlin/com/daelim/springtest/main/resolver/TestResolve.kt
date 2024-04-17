package com.daelim.springtest.main.resolver

import com.daelim.springtest.main.api.model.dto.TestDto
import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.kickstart.tools.GraphQLQueryResolver
import kotlin.random.Random
import org.springframework.stereotype.Component

@Component
class TestResolver : GraphQLQueryResolver, GraphQLMutationResolver {
    private val tests = mutableListOf<TestDto>()

    fun findAllTests(): List<TestDto> {
        return tests
    }

    fun findTestById(id: String): TestDto? {
        return tests.find { it.id == id }
    }

    fun createTest(testDtoInput: TestDtoInput): TestDto {
        val adjectives = arrayOf("용감한", "귀여운", "멋진", "친절한", "똑똑한")
        val nouns = arrayOf("사자", "토끼", "여우", "너구리", "펭귄")

        val randomNickname = "${adjectives[Random.nextInt(adjectives.size)]} ${nouns[Random.nextInt(nouns.size)]}"

        val testDto = TestDto(
            id = testDtoInput.id,
            nickname = randomNickname
        )
        tests.add(testDto)
        return testDto
    }
}

data class TestDtoInput(
    val id: String
)
