package com.example.healthyliving.ui.main

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ResepViewModelTest{

    @Mock
    private lateinit var newsRepository : NewsRepository
    private lateinit var newsViewModel: NewsViewModel
    private val dummyNews = DataDummy.generateDummyNewsEntity()
    @Before
    fun setUp() {
        newsViewModel = NewsViewModel(newsRepository)
    }

    @Test
    fun `when Get HeadlineNews Should Not Null and Return Success`() {
        val expectedNews = MutableLiveData<Result<List<NewsEntity>>>() //Result diambil dari package data yang sudah disiapkan pada starter project
        expectedNews.value = Result.Success(dummyNews)
        `when`(newsRepository.getHeadlineNews()).thenReturn(expectedNews)
        val actualNews = newsViewModel.getHeadlineNews()
        Assert.assertNotNull(actualNews)
    }
}