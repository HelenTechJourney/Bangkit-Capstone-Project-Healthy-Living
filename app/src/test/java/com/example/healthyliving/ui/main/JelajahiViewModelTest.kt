package com.example.healthyliving.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.recyclerview.widget.ListUpdateCallback
import com.example.healthyliving.data.Repository
import com.example.healthyliving.remote.response.ArtikelItem
import com.example.healthyliving.utils.DataDummy
import com.example.healthyliving.utils.MainDispatcherRule
import com.example.healthyliving.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class JelajahiViewModelTest{
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        repository = Mockito.mock(Repository::class.java)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when Get Quote Should Not Null and Return Data`() = runTest {
        val dummyQuote = DataDummy.generateDummyArticleEntity()
        val data: PagingData<ArtikelItem> = QuotePagingSource.snapshot(dummyQuote)
        val expectedQuote = MutableLiveData<PagingData<ArtikelItem>>()
        expectedQuote.value = data
        Mockito.`when`(repository.getArticle(toString())).thenReturn(expectedQuote)

        val viewModel = JelajahiViewModel(repository)
        val actualQuote: PagingData<ArtikelItem> = viewModel.getArticle(toString()).getOrAwaitValue()

        val differ = AsyncPagingDataDiffer(
            diffCallback = ArticleAdapter.DIFF_CALLBACK,
            updateCallback = noopListUpdateCallback,
            workerDispatcher = Dispatchers.Main,
        )
        differ.submitData(actualQuote)

        assertNotNull(differ.snapshot())
        assertEquals(dummyQuote.size, differ.snapshot().size)
        assertEquals(dummyQuote[0], differ.snapshot()[0])
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when Get Quote Empty Should Return No Data`() = runTest {
        val data: PagingData<ArtikelItem> = PagingData.from(emptyList())
        val expectedQuote = MutableLiveData<PagingData<ArtikelItem>>()
        expectedQuote.value = data
        Mockito.`when`(repository.getArticle(toString())).thenReturn(expectedQuote)
        val viewModel = JelajahiViewModel(repository)
        val actualQuote: PagingData<ArtikelItem> = viewModel.getArticle(toString()).getOrAwaitValue()
        val differ = AsyncPagingDataDiffer(
            diffCallback = ArticleAdapter.DIFF_CALLBACK,
            updateCallback = noopListUpdateCallback,
            workerDispatcher = Dispatchers.Main,
        )
        differ.submitData(actualQuote)
        assertEquals(0, differ.snapshot().size)
    }
}

val noopListUpdateCallback = object : ListUpdateCallback {
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
}

class QuotePagingSource : PagingSource<Int, LiveData<List<ArtikelItem>>>() {
    companion object {
        fun snapshot(items: List<ArtikelItem>): PagingData<ArtikelItem> {
            return PagingData.from(items)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, LiveData<List<ArtikelItem>>>): Int {
        return 0
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LiveData<List<ArtikelItem>>> {
        return LoadResult.Page(emptyList(), 0, 1)
    }
}