package com.dev.clevertonsantos.nybooks.presentation.books

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dev.clevertonsantos.nybooks.R
import com.dev.clevertonsantos.nybooks.data.BooksResult
import com.dev.clevertonsantos.nybooks.data.model.Book
import com.dev.clevertonsantos.nybooks.data.repository.BooksRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class) // Com isso não necessita do setUp
class BooksViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var bookLiveDataObserver: Observer<List<Book>>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>

    private lateinit var viewModel: BooksViewModel

//    @Before
//    fun setUp() {
//        MockitoAnnotations.initMocks(this)
//    }

    @Test
    fun `when view model getBooks get success then sets booksLiveData`() {
        // Arrange
        val books = listOf(
            Book("Title 1", "Author 1", "Description 1")
        )
        val resultSuccess = MockRepository(BooksResult.Success(books))
        viewModel = BooksViewModel(resultSuccess)
        viewModel.booksLiveData.observeForever(bookLiveDataObserver)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(bookLiveDataObserver).onChanged(books)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))
    }

    @Test
    fun `when getBooks get ApiError 401 then sets with error 401`() {
        // Arrange
        val resultApiError = MockRepository(BooksResult.ApiError(401))
        viewModel = BooksViewModel(resultApiError)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.error_401))
    }

    @Test
    fun `when getBooks get ApiError is not 401 then sets error not mapped`() {
        // Arrange
        val resultApiError = MockRepository(BooksResult.ApiError(404))
        viewModel = BooksViewModel(resultApiError)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.error_not_mapped))
    }

    @Test
    fun `when view model getBooks get ServerError then sets viewFlipperLiveData`() {
        // Arrange
        val resultServerError = MockRepository(BooksResult.ServerError)
        viewModel = BooksViewModel(resultServerError)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.error_network))
    }
}

class MockRepository(private val result: BooksResult): BooksRepository {
    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {
        booksResultCallback(result)
    }
}