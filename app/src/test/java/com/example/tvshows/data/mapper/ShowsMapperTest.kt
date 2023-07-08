package com.example.tvshows.data.mapper

import com.example.tvshows.data.network.model.ShowDto
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ShowsMapperTest {

    private val showsMapper = ShowsMapper()

    @Test
    fun `map should return the correct list of shows objects`() {
        val showDto1 = ShowDto(1, "Batman", "/aUrl1")
        val showDto2 = ShowDto(2, "Superman", "/aUrl2")
        val showDto3 = ShowDto(3, "Friends", "/aUrl3")
        val showsDto = listOf(showDto1, showDto2, showDto3)

        val showList = showsMapper.map(showsDto)

        assertEquals(showsDto.size, showList.size)

        for (i in showsDto.indices) {
            val expectedShowDto = showsDto[i]
            val actualShow = showList[i]

            assertEquals(expectedShowDto.id, actualShow.id)
            assertEquals(expectedShowDto.name, actualShow.name)
            assertEquals("https://image.tmdb.org/t/p/w500/"+expectedShowDto.path, actualShow.url)
        }
    }
}
